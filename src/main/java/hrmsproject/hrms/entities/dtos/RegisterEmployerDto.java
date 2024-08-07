package hrmsproject.hrms.entities.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployerDto extends RegisterUserDto{
    @NotBlank(message = "Company name can not be empty")
    private String companyName;

    @NotBlank(message = "Web address  can not be empty")
    private String webAddress;

    @NotBlank(message = "Phone number can not be empty")
    private String phoneNumber;
}
