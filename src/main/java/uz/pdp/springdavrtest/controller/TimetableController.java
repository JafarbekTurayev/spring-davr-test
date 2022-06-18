package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.TimetableDTO;
import uz.pdp.springdavrtest.repository.TimetableRepository;
import uz.pdp.springdavrtest.service.TimetableService;

import java.util.UUID;

@RequestMapping("/timetable")
@RestController
public class TimetableController {

    @Autowired
    TimetableRepository timetableRepository;
    @Autowired
    TimetableService timetableService;

    @PostMapping
    public ApiResponse add(@RequestBody TimetableDTO timetableDTO) {
        ApiResponse apiResponse = timetableService.addTimetable(timetableDTO);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse getAll() {
        return timetableService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable UUID id) {
        return timetableService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(name = "id") UUID ketmon, @RequestBody TimetableDTO timetableDTO) {
        return timetableService.edit(ketmon, timetableDTO);
    }

    @DeleteMapping
    public ApiResponse delete(@RequestParam UUID id) {
        return timetableService.delete(id);
    }
}
