package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.PaymentDTO;
import uz.pdp.springdavrtest.entity.PayType;
import uz.pdp.springdavrtest.entity.Payment;
import uz.pdp.springdavrtest.entity.Student;
import uz.pdp.springdavrtest.repository.PayTypeRepository;
import uz.pdp.springdavrtest.repository.PaymentRepository;
import uz.pdp.springdavrtest.repository.StudentRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResponse addPayment(PaymentDTO paymentDTO) {

        PayType payType = payTypeRepository.findById(paymentDTO.getPayTypeId()).orElseThrow(() -> new RuntimeException("Paytype not found!"));
        Student student = studentRepository.findById(paymentDTO.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found!"));

        Payment payment = new Payment();
        payment.setDescription(paymentDTO.getDescription());
        payment.setStudent(student);
        payment.setPayType(payType);
        payment.setSum(paymentDTO.getSum());
        Payment save = paymentRepository.save(payment);

        return ApiResponse.builder().data(save).message("Saved!").success(true).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().success(true).message("Mana").data(paymentRepository.findAll()).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (!optionalPayment.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(optionalPayment.get()).success(true).build();
    }

    public ApiResponse edit(Long ketmon, PaymentDTO paymentDTO) {
        PayType payType = payTypeRepository.findById(paymentDTO.getPayTypeId()).orElseThrow(() -> new RuntimeException("Paytype not found!"));
        Student student = studentRepository.findById(paymentDTO.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found!"));

        ApiResponse response = new ApiResponse();
//        AtomicReference<Payment> save = null;
        paymentRepository.findById(ketmon).ifPresentOrElse(payment -> {
            payment.setSum(paymentDTO.getSum());
            payment.setDescription(paymentDTO.getDescription());
            payment.setStudent(student);
            payment.setPayType(payType);
            Payment save = paymentRepository.save(payment);
            response.builder().data(save).success(true).message("Edited!").build();
        }, () -> {
            new RuntimeException("Xatolik");
        });
        return response;
    }

    public ApiResponse delete(Long id) {
        paymentRepository.deleteById(id);
       return ApiResponse.builder().success(true).message("Deleted!").build();
    }
}
