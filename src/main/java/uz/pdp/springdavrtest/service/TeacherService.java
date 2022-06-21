package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.TeacherDTO;
import uz.pdp.springdavrtest.entity.Group;
import uz.pdp.springdavrtest.entity.Student;
import uz.pdp.springdavrtest.entity.Teacher;
import uz.pdp.springdavrtest.repository.GroupRepository;
import uz.pdp.springdavrtest.repository.TeacherRepository;
import uz.pdp.springdavrtest.repository.PayTypeRepository;
import uz.pdp.springdavrtest.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResponse addTeacher(Teacher course) {
        //TODO aynan nomi bor kursni qo'shmaslik
        //jpa query
        Teacher save = teacherRepository.save(course);
        return ApiResponse.builder().data(save).message("Saved!").success(true).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().success(true).message("Mana").data(teacherRepository.findAll()).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (!optionalTeacher.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(optionalTeacher.get()).success(true).build();
    }

    public ApiResponse edit(Long ketmon, Teacher course) {

        ApiResponse response = new ApiResponse();
        teacherRepository.findById(ketmon).ifPresentOrElse(course1 -> {
            teacherRepository.save(course1);
        }, () -> {
            new RuntimeException("Xatolik");
        });
        return response;
    }

    public ApiResponse delete(Long id) {
        teacherRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }


    public ApiResponse getteacherstudent(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher Not found!"));

        List<Group> list = groupRepository.findAll();
        List<TeacherDTO> list1=new ArrayList<>();
        for (Group group : list) {
            TeacherDTO teacherDTO=new TeacherDTO();
            if (group.getTeacher().getId()==id) {
                if (!group.getStudents().isEmpty()){
                    List<String> result=new ArrayList<>();
                    for (Student student : group.getStudents()) {
                        result.add(student.getFullName());
                    }
                    teacherDTO.setGroupName(group.getName());
                    teacherDTO.setStudentName(result);
                }
            }
            if (teacherDTO.getGroupName()!=null&&!teacherDTO.getStudentName().isEmpty()){
                list1.add(teacherDTO);
            }
        }
        return ApiResponse.builder().message("Student List").success(true).data(list1).build();
    }
}
