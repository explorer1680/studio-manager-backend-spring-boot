package personal.studio.manager.backend.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassRecordCancellation {

    @JsonProperty(value = "class_record_ids")
    private Integer[] classRecordIds;

    public Integer[] getClassRecordIds() {
        return classRecordIds;
    }

    public void setClassRecordIds(Integer[] classRecordIds) {
        this.classRecordIds = classRecordIds;
    }

    @Override
    public String toString() {
        return "ClassRecordCancellation{" +
                "classRecordIds=" + Arrays.toString(classRecordIds) +
                '}';
    }
}
