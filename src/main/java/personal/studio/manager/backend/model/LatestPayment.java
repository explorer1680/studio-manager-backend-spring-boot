package personal.studio.manager.backend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("YES")
public class LatestPayment extends Payment {

    public LatestPayment() {
    }

//    public LatestPayment(Student student, Date date) {
//        super(student, date);
//    }
    
    public LatestPayment(Student student, Date date, float unitPrice) {
        super(student, date, unitPrice);
    }

    @Override
    public String toString() {
        return "LatestPayment" +super.toString();
    }
}
