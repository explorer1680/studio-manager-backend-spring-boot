package personal.studio.manager.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentStatisticsResult {

	@JsonProperty(value = "total_students")
    private Integer totalStudents;
	@JsonProperty(value = "inactive_students")
    private Integer inactiveStudents;
	@JsonProperty(value = "new_students")
    private Integer newStudents;

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getInactiveStudents() {
        return inactiveStudents;
    }

    public void setInactiveStudents(Integer inactiveStudents) {
        this.inactiveStudents = inactiveStudents;
    }

    public Integer getNewStudents() {
        return newStudents;
    }

    public void setNewStudents(Integer newStudents) {
        this.newStudents = newStudents;
    }
}
