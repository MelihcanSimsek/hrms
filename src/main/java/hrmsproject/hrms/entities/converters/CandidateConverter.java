package hrmsproject.hrms.entities.converters;

import hrmsproject.hrms.entities.concretes.Candidate;
import hrmsproject.hrms.entities.dtos.RegisterCandidateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidateConverter {
    private final ModelMapper modelMapper;

    public CandidateConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Candidate registerCandidateDtoToCandidate(RegisterCandidateDto registerCandidateDto)
    {
        Candidate candidate = new Candidate();
        this.modelMapper.map(registerCandidateDto,candidate);
        return candidate;
    }
}
