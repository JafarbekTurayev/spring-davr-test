package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.repository.DayRepository;
import uz.pdp.springdavrtest.repository.TimeTableRepository;
import uz.pdp.springdavrtest.service.DayService;

@RequestMapping("/day")
@RestController
public class DayController {
    @Autowired
    DayRepository dayRepository;

    @Autowired
    TimeTableRepository timetableRepository;

    @Autowired
    DayService dayServise;

    @GetMapping("/{id}")    //dayni idsi beriladi -> dushanba = 1 ... yakshanba = 7
    public ApiResponse getGroupsFromDay (@PathVariable Long id){
        return dayServise.getGroups(id);
    }

}
