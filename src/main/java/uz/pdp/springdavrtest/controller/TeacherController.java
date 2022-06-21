package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Teacher;
import uz.pdp.springdavrtest.repository.TeacherRepository;
import uz.pdp.springdavrtest.service.TeacherService;

@RequestMapping("/teacher")
@RestController
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherService teacherService;

    @PostMapping
    public ApiResponse add(@RequestBody Teacher teacher) {
        ApiResponse apiResponse = teacherService.addTeacher(teacher);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id) {
        return teacherService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(name = "id") Long ketmon, @RequestBody Teacher teacher) {
        return teacherService.edit(ketmon, teacher);
    }

    @DeleteMapping
    public ApiResponse delete(@RequestParam Long id) {
        return teacherService.delete(id);
    }

    @GetMapping("/T_students/{id}")
    public ApiResponse getTeacherStudents(@PathVariable Long id){
        return teacherService.getteacherstudent(id);
    }
}
