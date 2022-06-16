package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Course;
import uz.pdp.springdavrtest.repository.CourseRepository;
import uz.pdp.springdavrtest.service.CourseService;

@RequestMapping("/course")
@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseService courseService;

    @PostMapping
    public ApiResponse add(@RequestBody Course course) {
        ApiResponse apiResponse = courseService.addCourse(course);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id) {
        return courseService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(name = "id") Long ketmon, @RequestBody Course course) {
        return courseService.edit(ketmon, course);
    }

    @DeleteMapping
    public ApiResponse delete(@RequestParam Long id) {
        return courseService.delete(id);
    }
}
