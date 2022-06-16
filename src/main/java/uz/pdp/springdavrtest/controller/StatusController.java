package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Status;
import uz.pdp.springdavrtest.repository.StatusRepository;
import uz.pdp.springdavrtest.service.StatusService;

@RequestMapping("/status")
@RestController
public class StatusController {

    @Autowired
    StatusRepository statusRepository;
    @Autowired
    StatusService statusService;

    @PostMapping
    public ApiResponse add(@RequestBody Status status) {
        ApiResponse apiResponse = statusService.addStatus(status);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse getAll() {
        return statusService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id) {
        return statusService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(name = "id") Long ketmon, @RequestBody Status status) {
        return statusService.edit(ketmon, status);
    }

    @DeleteMapping
    public ApiResponse delete(@RequestParam Long id) {
        return statusService.delete(id);
    }
}
