package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.PayType;
import uz.pdp.springdavrtest.entity.Course;
import uz.pdp.springdavrtest.entity.Student;
import uz.pdp.springdavrtest.repository.PayTypeRepository;
import uz.pdp.springdavrtest.repository.CourseRepository;
import uz.pdp.springdavrtest.repository.StudentRepository;

import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResponse addCourse(Course course) {
        //TODO aynan nomi bor kursni qo'shmaslik
        //jpa query
        Course save = courseRepository.save(course);
        return ApiResponse.builder().data(save).message("Saved!").success(true).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().success(true).message("Mana").data(courseRepository.findAll()).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(optionalCourse.get()).success(true).build();
    }

    public ApiResponse edit(Long ketmon, Course course) {

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
