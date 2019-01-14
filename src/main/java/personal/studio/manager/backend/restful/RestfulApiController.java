package personal.studio.manager.backend.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import personal.studio.manager.backend.app.App;
import personal.studio.manager.backend.app.exception.PositiveBalanceException;
import personal.studio.manager.backend.model.Class;
import personal.studio.manager.backend.model.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class RestfulApiController {

    @Autowired
    private App app;

    @RequestMapping(value = "/get/classes", method = RequestMethod.GET)
    public List<Class> getAllClasses() {
        return app.getAllClasses();
    }

    @RequestMapping(value = "/save/class", method = RequestMethod.POST)
    public Class addClass(Class clz) {
        return app.addClass(clz);
    }

    @RequestMapping(value = "/update/class", method = RequestMethod.PUT)
    public void updateClass(Class clz) {
        app.updateClass(clz);
    }

    @RequestMapping(value = "/delete/class/{id}", method = RequestMethod.DELETE)
    public void deleteClass(@PathVariable("id") Integer id) {
        app.deleteClass(id);
    }

    @RequestMapping("/get/students")
    public List<Student> getAllStudents() {
        return app.getAllStudents();
    }

    @RequestMapping("/get/active/students")
    public List<Student> getAllActiveStudents() {
        return app.getAllActiveStudents();
    }

    @RequestMapping("/get/inactive/students")
    public List<Student> getAllInactiveStudents() {
        return app.getAllInactiveStudents();
    }

    @RequestMapping(value = "/save/student", method = RequestMethod.POST)
    public Student addStudent(Student student) {
        return app.addStudent(student);
    }

    @RequestMapping(value = "/switch/student/status/{id}", method = RequestMethod.PUT)
    public void switchStudentStatus(@PathVariable("id") Integer id) {
        app.switchStudentStatus(id);
    }

    @RequestMapping(value = "/update/student", method = RequestMethod.PUT)
    public void updateStudent(Student student) {
        app.updateStudent(student);
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public void makeTransaction(Transaction transaction) throws PositiveBalanceException {
        app.makeTransaction(transaction);
    }

    @RequestMapping(value = "/cancel/payment/{id}", method = RequestMethod.DELETE)
    public void cancelPayment(@PathVariable("id") Integer studentId) {
        app.cancelPayment(studentId);
    }

    @RequestMapping(value = "/cancel/class_records", method = RequestMethod.PUT)
    public void cancelPreviousClassRecords(ClassRecordCancellation classRecordCancellation){
        app.cancelPreviousClassRecords(classRecordCancellation);
    }

    @RequestMapping(value = "/customize/payment/{id}", method = RequestMethod.PUT)
    public void customizedPayment(@PathVariable("id") Integer studentId, LatestPayment latestPayment) throws PositiveBalanceException {
        app.customizedPayment(studentId, latestPayment);
    }

    @RequestMapping(value = "/customize/class_records", method = RequestMethod.PUT)
    public void customizeClassRecord(ClassInfo classInfo){
        app.customizeClassRecord(classInfo);
    }

    @RequestMapping(value = "/search/class", method = RequestMethod.PUT)
    public ClassSearchResult searchClass(ClassSearchQuery classSearchQuery){
        return app.searchClass(classSearchQuery);
    }

    @RequestMapping(value = "/search/student/statistics", method = RequestMethod.PUT)
    public StudentStatisticsResult searchStudent(SearchPeriod searchPeriod){
        return app.searchStudent(searchPeriod);
    }

    @RequestMapping(value = "/search/student/{id}", method = RequestMethod.PUT)
    public StudentSearchResult searchStudentById(@PathVariable("id") Integer id, SearchPeriod searchPeriod){
        return app.searchStudentById(id, searchPeriod);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal user(Principal principal) {
        System.out.println("********************************************************************************");
        System.out.println(principal);
        return principal;
    }
}
