package hrmsproject.hrms.entities.converters;

import hrmsproject.hrms.entities.concretes.Employer;
import hrmsproject.hrms.entities.dtos.RegisterEmployerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployerConverter {

    private final ModelMapper modelMapper;

    public EmployerConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Employer registerEmployerDtoToEmployer(RegisterEmployerDto registerEmployerDto)
    {
        Employer employer = new Employer();
        this.modelMapper.map(registerEmployerDto,employer);
        return employer;
    }
}
