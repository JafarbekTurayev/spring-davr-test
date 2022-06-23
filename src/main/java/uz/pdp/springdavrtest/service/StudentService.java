package uz.pdp.springdavrtest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Group;
import uz.pdp.springdavrtest.entity.Student;
import uz.pdp.springdavrtest.repository.GroupRepository;
import uz.pdp.springdavrtest.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    //student guruhini o'zgartirish
    public ApiResponse changeStudentGroup(Long studentId, Long preGroupId, Long newGroupId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student Not Found"));
        Group group = groupRepository.findById(preGroupId).orElseThrow(() -> new IllegalArgumentException("Group Not found"));

        List<Student> students = group.getStudents();
        students.remove(student); //studentni o'qiyotgan guruhi ro'yhatidan o'chiradi
        group.setStudents(students);
        groupRepository.save(group); //ro'yhatni qaytadan saqlaydi

        //GroupService dagi addStudent funksiyasidan foydalanib, studentni yangi guruhga qo'shadi
        return ApiResponse.builder()
                .data(groupService.addStudent(studentId, newGroupId))
                .success(true)
                .message("Student Group Changed!")
                .build();
    }

    public ApiResponse getAll(){
        List<Student> allStudents = studentRepository.findAll();
        return ApiResponse.builder().success(true).message("Students List").data(allStudents).build();
    }

    public ApiResponse delete (Long id){
        studentRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Student Deleted").build();
    }

}
