package hrmsproject.hrms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidates")
public class Candidate extends User{
    @Column(name = "first_name")
    @NotBlank(message = "First name can not be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name can not be empty")
    private String lastName;

    @Column(name ="identity_number")
    @NotBlank(message = "Identity number can not be empty")
    private String identityNumber;

    @Column(name = "birth_year")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private Date birthYear;
}
