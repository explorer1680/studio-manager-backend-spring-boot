package personal.studio.manager.backend.model;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

import javax.persistence.*;

@Entity
public class Student {
	
	public Student(){}
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="started_date")
    private Date startedDate;

    @JsonProperty(value="first_name")
    @Column(name="first_name")
    private String firstName;

    @JsonProperty(value="last_name")
    @Column(name="last_name")
    private String lastName;

//    @XmlTransient
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="clz_id")
//    private Class clz;

    @JsonProperty(value="class_id")
    @Column(name="clz_id")//, updatable=false, insertable = false)
    private Integer classId;
    
    @Enumerated(EnumType.STRING)
    private STATUS status;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="inactive_date")
    private Date inactiveDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval=true, fetch = FetchType.EAGER)
    @OrderBy("id")
    @JoinTable(name="Student_Contactor")
    private Set<Contactor> contactors = new HashSet<>();

    @JsonProperty(value="latest_payment")
    @OneToOne(mappedBy="student",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private LatestPayment latestPayment;

    @JsonProperty(value="class_records")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, mappedBy = "student")
    @Where(clause = "history_record='NO'")
    @OrderBy("date")
    private List<ClassRecord> classRecords = new ArrayList<>();

    @Column(name = "discount", precision = 8, scale = 2)
    private Float discount;

    private String comment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Class getClz() {
//        return clz;
//    }
//
//    public void setClz(Class clz) {
//        this.clz = clz;
//    }

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
    
    public Set<Contactor> getContactors() {
		return contactors;
	}

	public void setContactors(Set<Contactor> contactors) {
		this.contactors = contactors;
	}

    public Payment getLatestPayment() {
		return latestPayment;
	}

	public void setLatestPayment(LatestPayment latestPayment) {
		this.latestPayment = latestPayment;
	}

	public List<ClassRecord> getClassRecords() {
		return classRecords;
	}

	public void setClassRecords(List<ClassRecord> classRecords) {
		this.classRecords = classRecords;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", classId=" + classId +
                ", status=" + status +
                ", contactors=" + contactors +
                ", latestPayment=" + latestPayment +
                ", classRecords=" + classRecords +
                ", discount=" + discount +
                ", comment='" + comment + '\'' +
                '}';
    }
}
