package hrmsproject.hrms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workers")
public class Worker extends User{

    @Column(name = "first_name")
    @NotBlank(message = "First name can not be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name can not be empty")
    private String lastName;
}
