package personal.studio.manager.backend.dao;

import java.math.BigInteger;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import personal.studio.manager.backend.model.*;
import personal.studio.manager.backend.model.Class;

@Repository
public class DAO {

	@PersistenceContext
	private EntityManager manager;

	public List<Class> getAllClasses() {
		return manager.createQuery("SELECT c FROM Class c").getResultList();
	}

	public Class addClass(Class clz) {
		manager.persist(clz);
		manager.flush();
		return clz;
	}

	//the input Class don't have students info.
	public void updateClass(Class clz) {
		Class originalClass = manager.find(Class.class, clz.getId());
		List<Student> studentList = originalClass.getStudents();
		originalClass.setStudents(new ArrayList<Student>());
		Class newClass = manager.merge(clz);
		newClass.setStudents(studentList);
	}

	public void deleteClass(Integer id) {
		Class clz = manager.find(Class.class, id);
		manager.remove(clz);
	}

	public List<Student> getAllStudents() {
		return manager.createQuery("SELECT s FROM Student s").getResultList();
	}

	public List<Student> getAllActiveStudents() {
		return manager.createQuery("SELECT s FROM Student s WHERE s.status = 'ACTIVE'").getResultList();
	}

	public List<Student> getAllInactiveStudents() {
		return manager.createQuery("SELECT s FROM Student s WHERE s.status = 'INACTIVE'").getResultList();
	}

	public Student getStudentById(Integer id) {
		return manager.find(Student.class, id);
	}

	public Student addStudent(Student student) {
		// Class clz = manager.getReference(Class.class, student.getClassId());
		// student.setClz(clz);
		student.setStatus(STATUS.ACTIVE);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date toDay = cal.getTime();
		student.setStartedDate(toDay);
		manager.persist(student);
		manager.flush();
		manager.refresh(student);
		return student;
	}

	public void switchStudentStatus(Integer id) {
		Student student = manager.find(Student.class, id);
		if (student.getStatus() == STATUS.ACTIVE) {
			student.setStatus(STATUS.INACTIVE);

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date toDay = cal.getTime();
			student.setInactiveDate(toDay);

		} else {
			student.setStatus(STATUS.ACTIVE);
			student.setInactiveDate(null);
		}
		manager.flush();
	}

	public void updateStudent(Student student) {
		manager.merge(student);
		// Set<Contactor> newContactors = new HashSet<>();
		// for (Contactor contactor : student.getContactors()) {
		// if (contactor.getId() == null) {
		// newContactors.add(contactor);
		// }
		// }
		// student.getContactors().removeAll(newContactors);
		// Student updatedStudent = manager.merge(student);
		// Class clz = manager.getReference(Class.class, student.getClassId());
		// updatedStudent.setClz(clz);
		//
		// for (Contactor c : newContactors) {
		// updatedStudent.getContactors().add(c);
		// }

		manager.flush();
	}

	// public Payment getLatestPayment(Integer id){
	// return manager.find(Student.class, id).getLatestPayment();
	// }

	public void changeLatestPaymentToNo(Payment payment, Student student) {
		Integer id = payment.getId();
		manager.detach(payment);
		manager.detach(student);
		manager.createNativeQuery("UPDATE Payment SET latest_payment = 'NO' WHERE id = :id").setParameter("id", id)
				.executeUpdate();
		manager.flush();
	}

	public LatestPayment changeLatestPaymentToYes(Payment payment) {
		Integer id = payment.getId();
		manager.detach(payment);
		manager.createNativeQuery("UPDATE Payment SET latest_payment = 'YES' WHERE id = :id").setParameter("id", id)
				.executeUpdate();
		manager.flush();
		return manager.find(LatestPayment.class, id);
	}

	public void addNewPayment(PaymentInfo paymentInfo) {
		Student s = manager.find(Student.class, paymentInfo.getStudentId());
		Class clz = manager.find(Class.class, s.getClassId());
		LatestPayment payment = new LatestPayment(s, paymentInfo.getDate(), clz.getPrice());
		manager.persist(payment);
	}

	public void addNewPayment(Student student, LatestPayment latestPayment) {
		latestPayment.setStudent(student);
		manager.persist(latestPayment);
	}

	public void addNewClassRecord(ClassInfo classInfo) {
		Student s = manager.getReference(Student.class, classInfo.getStudentId());
		ClassRecord classRecord = new ClassRecord(s, classInfo.getDate(), classInfo.getComment());
		manager.persist(classRecord);
	}

	public void refresh(Student student) {
		manager.flush();
		manager.refresh(student);
	}

	public Payment getPreviousPaymentByStudentId(Integer studentId) {
		List<Payment> payments = manager
				.createNativeQuery(
						"SELECT p.id FROM Payment p WHERE p.student_id = :student_id AND p.latest_payment = 'NO' ORDER BY p.date DESC")
				.setParameter("student_id", studentId).getResultList();
		if (payments != null && !payments.isEmpty()) {
			return manager.find(Payment.class, payments.get(0));
		} else {
			return null;
		}
	}

	public List<ClassRecord> getClassRecordListByPayment(Payment payment) {
		return manager.createQuery("FROM ClassRecord c WHERE c.payment = :payment").setParameter("payment", payment)
				.getResultList();
	}

	public void deletePayment(Payment payment) {
		manager.remove(payment);
	}

	public void deleteClassRecordById(Integer recordId) {
		ClassRecord classRecord = manager.getReference(ClassRecord.class, recordId);
		manager.remove(classRecord);
	}

	public Object[] searchClassStatisticsForAll(ClassSearchQuery classSearchQuery) {
		Query query = manager
				.createNativeQuery(
						"SELECT COUNT(*), SUM(p.amount) FROM Payment p WHERE p.date BETWEEN :beginDate AND :endDate")
				.setParameter("beginDate", classSearchQuery.getStartDate())
				.setParameter("endDate", classSearchQuery.getEndDate());
		return (Object[]) query.getSingleResult();
	}

	public Integer searchClassRecordStatisticsForAll(ClassSearchQuery classSearchQuery) {
		Query query = manager
				.createNativeQuery("SELECT COUNT(*) FROM ClassRecord cr WHERE cr.date BETWEEN :beginDate AND :endDate")
				.setParameter("beginDate", classSearchQuery.getStartDate())
				.setParameter("endDate", classSearchQuery.getEndDate());
		return ((BigInteger) query.getSingleResult()).intValue();
	}

	public Object[] searchClassStatisticsByClassId(ClassSearchQuery classSearchQuery) {
		Query query = manager
				.createNativeQuery(
						"SELECT COUNT(*), SUM(p.amount) FROM Payment p, Student s WHERE p.date BETWEEN :beginDate AND :endDate AND p.student_id = s.id AND s.clz_id = :id")
				.setParameter("beginDate", classSearchQuery.getStartDate())
				.setParameter("endDate", classSearchQuery.getEndDate())
				.setParameter("id", classSearchQuery.getClassId());
		return (Object[]) query.getSingleResult();
	}

	public Integer searchClassRecordStatisticsByClassId(ClassSearchQuery classSearchQuery) {
		Query query = manager
				.createNativeQuery(
						"SELECT COUNT(*) FROM ClassRecord cr, Student s WHERE cr.date BETWEEN :beginDate AND :endDate AND cr.student_id = s.id AND s.clz_id = :id")
				.setParameter("beginDate", classSearchQuery.getStartDate())
				.setParameter("endDate", classSearchQuery.getEndDate())
				.setParameter("id", classSearchQuery.getClassId());
		return ((BigInteger) query.getSingleResult()).intValue();
	}

	public Integer searchTotalStudentInPeriod(SearchPeriod searchPeriod) {
		return ((BigInteger) manager
				.createNativeQuery(
						"SELECT COUNT(DISTINCT s.id) FROM Student s, ClassRecord cr WHERE cr.date BETWEEN :beginDate AND :endDate AND cr.student_id = s.id")
				.setParameter("beginDate", searchPeriod.getStartDate())
				.setParameter("endDate", searchPeriod.getEndDate()).getSingleResult()).intValue();
	}

	public Integer searchInActiveStudentInPeriod(SearchPeriod searchPeriod) {
		return ((BigInteger) manager
				.createNativeQuery(
						"SELECT COUNT(s.id) FROM Student s WHERE s.inactive_date BETWEEN :beginDate AND :endDate")
				.setParameter("beginDate", searchPeriod.getStartDate())
				.setParameter("endDate", searchPeriod.getEndDate()).getSingleResult()).intValue();
	}

	public Integer searchNewStudentInPeriod(SearchPeriod searchPeriod) {
		return ((BigInteger) manager
				.createNativeQuery(
						"SELECT COUNT(s.id) FROM Student s WHERE s.started_date BETWEEN :beginDate AND :endDate")
				.setParameter("beginDate", searchPeriod.getStartDate())
				.setParameter("endDate", searchPeriod.getEndDate()).getSingleResult()).intValue();
	}

	public List<Payment> searchPaymentByStudentIdAndPeriod(Integer id, SearchPeriod searchPeriod) {
		Student student = manager.getReference(Student.class, id);
		return manager
				.createQuery(
						"SELECT p FROM Payment p WHERE p.student = :student AND p.date BETWEEN :beginDate AND :endDate ORDER BY p.date")
				.setParameter("student", student).setParameter("beginDate", searchPeriod.getStartDate())
				.setParameter("endDate", searchPeriod.getEndDate()).getResultList();
	}

	public List<ClassRecord> searchClassRecordByStudentIdAndPeriod(Integer id, SearchPeriod searchPeriod) {
		Student student = manager.getReference(Student.class, id);
		return manager
				.createQuery(
						"SELECT cr FROM ClassRecord cr WHERE cr.student = :student AND cr.date BETWEEN :beginDate AND :endDate ORDER BY cr.date")
				.setParameter("student", student).setParameter("beginDate", searchPeriod.getStartDate())
				.setParameter("endDate", searchPeriod.getEndDate()).getResultList();
	}
}
