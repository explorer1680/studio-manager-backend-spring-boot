package personal.studio.manager.backend.app;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import personal.studio.manager.backend.app.exception.PositiveBalanceException;
import personal.studio.manager.backend.dao.DAO;
import personal.studio.manager.backend.model.*;
import personal.studio.manager.backend.model.Class;

@Component
@Transactional
public class App {

    @Autowired
    DAO dao;

    public List<Class> getAllClasses() {
        return dao.getAllClasses();
    }

    public Class addClass(Class clz) {
        return dao.addClass(clz);
    }

    public void updateClass(Class clz) {
        dao.updateClass(clz);
    }

    public void deleteClass(Integer id) {
        dao.deleteClass(id);
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = dao.getAllStudents();
        return studentList;
    }

    public List<Student> getAllActiveStudents() {
        return dao.getAllActiveStudents();
    }

    public List<Student> getAllInactiveStudents() {
        return dao.getAllInactiveStudents();
    }

    public Student addStudent(Student student) {
        return dao.addStudent(student);
    }

    public void switchStudentStatus(Integer id) {
        dao.switchStudentStatus(id);
    }

    public void updateStudent(Student student) {
        dao.updateStudent(student);
    }

    @Transactional(rollbackFor=PositiveBalanceException.class)
    public void makeTransaction(Transaction transaction) throws PositiveBalanceException {
        for (ClassInfo classInfo : transaction.getClasses()) {//please note, this part should be before the payment part.
            Student student = dao.getStudentById(classInfo.getStudentId());
            if (newClassRecord(student, classInfo)) {
                dao.addNewClassRecord(classInfo);
            }
            dao.refresh(student);//without this, following getStudentById will not get the newest info.
        }


        for (PaymentInfo paymentInfo : transaction.getPayments()) {
            Student student = dao.getStudentById(paymentInfo.getStudentId());
            Payment previousPayment = student.getLatestPayment();
            if (previousPayment == null) {//it is new student
                dao.addNewPayment(paymentInfo);
                dao.refresh(student);
//                changeClassRecordsToLatestPayment(student);
            }
            //check if the latest payment date is transaction day, --avoid duplicate payment
            else if (!isDuplicatedPayment(previousPayment, paymentInfo)) {
                if (studentCanAcceptNewPayment(student)) {//latest payment has run out
                    for (int i = 0; i < previousPayment.getTimes(); i++) {
                        ClassRecord classRecord = student.getClassRecords().get(i);
                        classRecord.setHistoryRecord(YesNo.YES);
                        classRecord.setPayment(previousPayment);
                    }
                    dao.refresh(student);
                    dao.changeLatestPaymentToNo(previousPayment, student);
//                    dao.refresh(student);
                    dao.addNewPayment(paymentInfo);
//                    dao.refresh(student);
//                    changeClassRecordsToLatestPayment(student);
                } else {
                    throw new PositiveBalanceException(student);
                }
            }
        }
    }

    @Transactional(rollbackFor=PositiveBalanceException.class)
    public void customizedPayment(Integer studentId, LatestPayment latestPayment) throws PositiveBalanceException {
        Student student = dao.getStudentById(studentId);
        Payment previousPayment = student.getLatestPayment();
        if (previousPayment == null) {//it is new student
            dao.addNewPayment(student, latestPayment);
            dao.refresh(student);
//                changeClassRecordsToLatestPayment(student);
        } else {
            if (studentCanAcceptNewPayment(student)) {//latest payment has run out
                for (int i = 0; i < previousPayment.getTimes(); i++) {
                    ClassRecord classRecord = student.getClassRecords().get(i);
                    classRecord.setHistoryRecord(YesNo.YES);
                    classRecord.setPayment(previousPayment);
                }
                dao.refresh(student);
                dao.changeLatestPaymentToNo(previousPayment, student);
                dao.addNewPayment(student, latestPayment);
            } else {
                throw new PositiveBalanceException(student);
            }
        }
    }

    public void cancelPayment(Integer studentId) {
        Student student = dao.getStudentById(studentId);
        Payment previousPayment = dao.getPreviousPaymentByStudentId(studentId);
        if (previousPayment != null) {
            List<ClassRecord> classRecordList = dao.getClassRecordListByPayment(previousPayment);
            for (ClassRecord classRecord : classRecordList) {
                classRecord.setPayment(null);
                classRecord.setHistoryRecord(YesNo.NO);
            }
            Payment latestPayment = student.getLatestPayment();
            List<ClassRecord> latestPaymentClassRecordList = dao.getClassRecordListByPayment(latestPayment);
            for(ClassRecord classRecord: latestPaymentClassRecordList){//in case latest payment associated with some class records.
            	classRecord.setPayment(null);
                classRecord.setHistoryRecord(YesNo.NO);
            }
            dao.refresh(student);
            student.setLatestPayment(null);
            dao.deletePayment(latestPayment);
            dao.refresh(student);
            LatestPayment newLatestPayment = dao.changeLatestPaymentToYes(previousPayment);
            student.setLatestPayment(newLatestPayment);
        } else {
            Payment latestPayment = student.getLatestPayment();
            student.setLatestPayment(null);
            dao.deletePayment(latestPayment);
        }
    }

    public void cancelPreviousClassRecords(ClassRecordCancellation classRecordCancellation) {
        if(classRecordCancellation != null && classRecordCancellation.getClassRecordIds() != null) {
            for (Integer recordId : classRecordCancellation.getClassRecordIds()) {
                dao.deleteClassRecordById(recordId);
            }
        }
    }

    public void customizeClassRecord(ClassInfo classInfo) {
        for (int i = 0; i < classInfo.getTimes(); i++) {
            dao.addNewClassRecord(classInfo);
        }
    }


    private boolean newClassRecord(Student student, ClassInfo classInfo) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        if (student.getClassRecords() == null || student.getClassRecords().isEmpty()) {
            return true;
        } else {
            for (ClassRecord classRecord : student.getClassRecords()) {
                if (format.format(classRecord.getDate()).equalsIgnoreCase(format.format(classInfo.getDate()))) {//student has a record as same date
                    return false;
                }
            }
        }
        return true;
    }

    private void changeClassRecordsToLatestPayment(Student student) {
        if (student.getClassRecords().size() > 0 && student.getClassRecords().size() <= student.getLatestPayment().getTimes()) {
            for (ClassRecord classRecord : student.getClassRecords()) {
                classRecord.setPayment(student.getLatestPayment());
            }
        } else if (student.getClassRecords().size() > 0 && student.getClassRecords().size() > student.getLatestPayment().getTimes()) {
            for (int j = 0; j < student.getLatestPayment().getTimes(); j++) {
                student.getClassRecords().get(j).setPayment(student.getLatestPayment());
            }
        }
    }

    private boolean studentCanAcceptNewPayment(Student student) {
        if (student.getLatestPayment().getTimes() < student.getClassRecords().size()) {//when equal, can't pay
            return true;
        } else {
            return false;
        }
    }

    private boolean isDuplicatedPayment(Payment latestPayment, PaymentInfo transactionPaymentInfo) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(latestPayment.getDate())
                .equalsIgnoreCase((format.format(transactionPaymentInfo.getDate())));
    }


    public ClassSearchResult searchClass(ClassSearchQuery classSearchQuery) {
        if(classSearchQuery.getClassId() == -1){
            ClassSearchResult result = new ClassSearchResult();
            Object[] searchResult = dao.searchClassStatisticsForAll(classSearchQuery);
            result.setPaymentTimes(((BigInteger)searchResult[0]).intValue());
            if(searchResult[1] != null) {
                result.setTotalAmount(((Double) searchResult[1]).floatValue());
            }else {
                result.setTotalAmount(0f);
            }
            result.setClassRecordNumbers(dao.searchClassRecordStatisticsForAll(classSearchQuery));
            return result;
        }else {
            ClassSearchResult result = new ClassSearchResult();
            Object[] searchResult = dao.searchClassStatisticsByClassId(classSearchQuery);
            result.setPaymentTimes(((BigInteger)searchResult[0]).intValue());
            if(searchResult[1] != null) {
                result.setTotalAmount(((Double) searchResult[1]).floatValue());
            }else {
                result.setTotalAmount(0f);
            }
            result.setClassRecordNumbers(dao.searchClassRecordStatisticsByClassId(classSearchQuery));
            return result;
        }

    }

    public StudentStatisticsResult searchStudent(SearchPeriod searchPeriod) {
        StudentStatisticsResult studentStatisticsResult = new StudentStatisticsResult();
        studentStatisticsResult.setTotalStudents(dao.searchTotalStudentInPeriod(searchPeriod));
        studentStatisticsResult.setInactiveStudents(dao.searchInActiveStudentInPeriod(searchPeriod));
        studentStatisticsResult.setNewStudents(dao.searchNewStudentInPeriod(searchPeriod));
        return studentStatisticsResult;
    }

	public StudentSearchResult searchStudentById(Integer id, SearchPeriod searchPeriod) {
		List<Payment> paymentList = dao.searchPaymentByStudentIdAndPeriod(id, searchPeriod);
		List<ClassRecord> classRecordList = dao.searchClassRecordByStudentIdAndPeriod(id, searchPeriod);
		StudentSearchResult studentSearchResult = new StudentSearchResult();
		studentSearchResult.setClassRecordList(classRecordList);
		studentSearchResult.setPaymentList(paymentList);
		return studentSearchResult;
	}
}

