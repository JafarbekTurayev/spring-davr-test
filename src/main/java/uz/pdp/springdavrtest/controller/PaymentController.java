package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.PaymentDTO;
import uz.pdp.springdavrtest.entity.Payment;
import uz.pdp.springdavrtest.repository.PaymentRepository;
import uz.pdp.springdavrtest.service.PaymentService;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ApiResponse add(@RequestBody PaymentDTO paymentDTO) {
        ApiResponse apiResponse = paymentService.addPayment(paymentDTO);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id) {
        return paymentService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(name = "id") Long ketmon, @RequestBody PaymentDTO paymentDTO) {
        return paymentService.edit(ketmon, paymentDTO);
    }

    @DeleteMapping
    public ApiResponse delete(@RequestParam Long id) {
        return paymentService.delete(id);
    }
}
