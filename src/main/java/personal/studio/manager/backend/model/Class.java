package personal.studio.manager.backend.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Enumerated(EnumType.STRING)
    private WeekDay weekday;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="hour", column=@Column(name="start_hour")),
            @AttributeOverride(name="minute", column=@Column(name="start_minute"))
    })
    @JsonProperty(value="start_time")
    private StartTime startTime;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="clz_id")
    private List<Student> students = new ArrayList<>();

    private Float period;
    private Float price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeekDay getDate() {
        return weekday;
    }

    public void setDate(WeekDay weekday) {
        this.weekday = weekday;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

    public Float getPeriod() {
        return period;
    }

    public void setPeriod(Float period) {
        this.period = period;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public WeekDay getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekDay weekday) {
        this.weekday = weekday;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weekday=" + weekday +
                ", startTime=" + startTime +
                ", students=" + students +
                ", period=" + period +
                ", price=" + price +
                '}';
    }
}
