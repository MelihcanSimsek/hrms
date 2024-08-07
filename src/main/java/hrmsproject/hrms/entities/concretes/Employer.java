package hrmsproject.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisements"})
public class Employer extends User {

    @Column(name = "company_name")
    @NotBlank(message = "Company name can not be empty")
    private String companyName;

    @Column(name = "web_address")
    @NotBlank(message = "Web address can not be empty")
    private String webAddress;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number can not be empty")
    private String phoneNumber;

    @OneToMany(mappedBy = "employer",fetch = FetchType.LAZY)
    private List<JobAdvertisement> jobAdvertisements;
}
