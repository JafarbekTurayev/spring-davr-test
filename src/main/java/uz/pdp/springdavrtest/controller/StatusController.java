package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> add(@RequestBody Status status) {
        ApiResponse apiResponse = statusService.addStatus(status);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.CREATED
                : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(statusService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = statusService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long ketmon, @RequestBody Status status) {
        ApiResponse apiResponse = statusService.edit(ketmon, status);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        ApiResponse response = statusService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}
