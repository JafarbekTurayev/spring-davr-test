package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.TimetableDTO;
import uz.pdp.springdavrtest.entity.*;
import uz.pdp.springdavrtest.repository.*;
import uz.pdp.springdavrtest.utils.DateFormatUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TimetableService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    DayRepository dayRepository;
    @Autowired
    TimetableRepository timetableRepository;
    @Autowired
    DateFormatUtil dateFormatUtil;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResponse addTimetable(TimetableDTO timetableDTO) {

        Day day = dayRepository.findById(timetableDTO.getDayId()).orElseThrow(() -> new RuntimeException("Time table Not found!"));
        List<Group> groupList = groupRepository.findAllById(timetableDTO.getGroupIds());

        Timetable timetable = new Timetable();
        timetable.setDay(day);
        timetable.setGroup(groupList);
        timetable.setStartTime(dateFormatUtil.dateConvertorTime(timetableDTO.getStartTime()));
        timetable.setEndTime(dateFormatUtil.dateConvertorTime(timetableDTO.getEndTime()));

        Timetable save = timetableRepository.save(timetable);
        return ApiResponse.builder().data(save).message("Saved!").success(true).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().success(true).message("Mana").data(timetableRepository.findAll()).build();
    }

    public ApiResponse getOne(UUID id) {
        Optional<Timetable> optionalTimetable = timetableRepository.findById(id);
        if (!optionalTimetable.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(optionalTimetable.get()).success(true).build();
    }

    public ApiResponse edit(UUID ketmon, TimetableDTO timetableDTO) {
//        PayType payType = payTypeRepository.findById(timetableDTO.getPayTypeId()).orElseThrow(() -> new RuntimeException("Paytype not found!"));
//        Student student = studentRepository.findById(timetableDTO.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found!"));
//
//        ApiResponse response = new ApiResponse();
////        AtomicReference<Timetable> save = null;
//        timetableRepository.findById(ketmon).ifPresentOrElse(timetable -> {
//            timetable.setSum(timetableDTO.getSum());
//            timetable.setDescription(timetableDTO.getDescription());
//            timetable.setStudent(student);
//            timetable.setPayType(payType);
//            Timetable save = timetableRepository.save(timetable);
//            response.builder().data(save).success(true).message("Edited!").build();
//        }, () -> {
//            new RuntimeException("Xatolik");
//        });
//        return response;
        return null;
    }

    public ApiResponse delete(UUID id) {
        timetableRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }
}
