package hrmsproject.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirmations")
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_confirmed",nullable = false,columnDefinition = "boolean default true")
    private Boolean isConfirmed;

    @Column(name = "confirmation_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date confirmationDate;

    @ManyToOne()
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne()
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
