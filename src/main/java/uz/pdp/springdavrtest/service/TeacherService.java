package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Teacher;
import uz.pdp.springdavrtest.repository.TeacherRepository;
import uz.pdp.springdavrtest.repository.PayTypeRepository;
import uz.pdp.springdavrtest.repository.StudentRepository;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResponse addTeacher(Teacher course) {
        //TODO aynan nomi bor kursni qo'shmaslik
        //jpa query
        Teacher save = courseRepository.save(course);
        return ApiResponse.builder().data(save).message("Saved!").success(true).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().success(true).message("Mana").data(courseRepository.findAll()).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Teacher> optionalTeacher = courseRepository.findById(id);
        if (!optionalTeacher.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(optionalTeacher.get()).success(true).build();
    }

    public ApiResponse edit(Long ketmon, Teacher course) {

        ApiResponse response = new ApiResponse();
        courseRepository.findById(ketmon).ifPresentOrElse(course1 -> {
            courseRepository.save(course1);
        }, () -> {
            new RuntimeException("Xatolik");
        });
        return response;
    }

    public ApiResponse delete(Long id) {
        courseRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }
}
