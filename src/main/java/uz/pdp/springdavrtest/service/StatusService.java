package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Status;
import uz.pdp.springdavrtest.repository.StatusRepository;
import uz.pdp.springdavrtest.repository.PayTypeRepository;
import uz.pdp.springdavrtest.repository.StudentRepository;

import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResponse addStatus(Status status) {
        //TODO aynan nomi bor kursni qo'shmaslik
        //jpa query
        Status save = statusRepository.save(status);
        return ApiResponse.builder().data(save).message("Saved!").success(true).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().success(true).message("Mana").data(statusRepository.findAll()).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (!optionalStatus.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(optionalStatus.get()).success(true).build();
    }

    public ApiResponse edit(Long ketmon, Status status) {

        ApiResponse response = new ApiResponse();
        statusRepository.findById(ketmon).ifPresentOrElse(status1 -> {
            statusRepository.save(status1);
        }, () -> {
            new RuntimeException("Xatolik");
        });
        return response;
    }

    public ApiResponse delete(Long id) {
        statusRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }
}
