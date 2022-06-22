package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.ResGroupDTO;
import uz.pdp.springdavrtest.entity.Day;
import uz.pdp.springdavrtest.entity.Group;
import uz.pdp.springdavrtest.entity.TimeTable;
import uz.pdp.springdavrtest.repository.DayRepository;
import uz.pdp.springdavrtest.repository.TimeTableRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DayService {

    @Autowired
    TimeTableRepository timetableRepository;
    @Autowired
    DayRepository dayRepository;

    public ApiResponse getGroups(Long dayId) {
        List<TimeTable> timetableList= timetableRepository.findAll();
        Optional<Day> optionalDay = dayRepository.findById(dayId);
        Set<Group> groupSet = new HashSet<>();
        if (!optionalDay.isPresent()){
            return ApiResponse.builder().success(false).message("Bunday kun yo`q").build();
        }
        else {
            Day day = optionalDay.get();
            for (TimeTable timetable : timetableList) {
                if (timetable.getDay().equals(day)){
                    List<Group> groups = timetable.getGroups();
                    for (Group group : groups) {
                        groupSet.add(group);
                    }
                }
            }
            List<ResGroupDTO> collect = groupSet.stream().map(this::mapToDTO).collect(Collectors.toList());
            System.out.println(collect);
        return ApiResponse.builder().success(true).message("Bu kuni shu guruhlar o`qiydi!").data(collect).build();
        }
    }
    public ResGroupDTO mapToDTO(Group group) {
        return ResGroupDTO.builder().startDate(String.valueOf(group.getStartDate())).endDate(String.valueOf(group.getEndDate())).courseName(group.getCourse().getName()).teacherName(group.getTeacher().getFullName()).groupName(group.getName()).roomName(group.getRoom().getName()).statusName(group.getStatus().getName()).build();
    }

}
