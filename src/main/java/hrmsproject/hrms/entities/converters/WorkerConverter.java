package hrmsproject.hrms.entities.converters;

import hrmsproject.hrms.entities.concretes.Worker;
import hrmsproject.hrms.entities.dtos.RegisterWorkerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WorkerConverter {

    private final ModelMapper modelMapper;

    public WorkerConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Worker registerWorkerDtoToWorker(RegisterWorkerDto registerWorkerDto)
    {
        Worker worker = new Worker();
        this.modelMapper.map(registerWorkerDto,worker);
        return worker;
    }
}
