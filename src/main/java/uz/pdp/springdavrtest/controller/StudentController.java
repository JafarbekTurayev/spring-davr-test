package uz.pdp.springdavrtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.repository.StudentRepository;
import uz.pdp.springdavrtest.service.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PutMapping("changeGroup/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable(name = "id") Long studentId, @RequestParam Long preGroupId, Long newGroupId){
        ApiResponse response = studentService.changeStudentGroup(studentId, preGroupId, newGroupId);
        return ResponseEntity
                .status(response.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT)
                .body(response);
    }
}
