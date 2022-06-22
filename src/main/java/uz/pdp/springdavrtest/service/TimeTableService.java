package uz.pdp.springdavrtest.service;

import uz.pdp.springdavrtest.dto.ResTimeTableDto;
import uz.pdp.springdavrtest.dto.TimeTableDto;
import uz.pdp.springdavrtest.entity.Day;
import uz.pdp.springdavrtest.entity.Group;
import uz.pdp.springdavrtest.entity.TimeTable;
import uz.pdp.springdavrtest.repository.DayRepository;
import uz.pdp.springdavrtest.repository.GroupRepository;
import uz.pdp.springdavrtest.repository.TimeTableRepository;
import uz.pdp.springdavrtest.utils.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author "Husniddin Ulachov"
 * @created 11:54 AM on 6/20/2022
 * @project Spring-Davr-Task
 */
@Service
@RequiredArgsConstructor
public class TimeTableService {
    private final DayRepository dayRepository;
    private final DateFormatUtil dateFormatUtil;
    private final GroupRepository groupRepository;
    private final TimeTableRepository timeTableRepository;

    public ResTimeTableDto addTime(TimeTableDto timeTableDto) {
        Day day = dayRepository.findById(timeTableDto.getDayId()).orElseThrow(() ->
                new RuntimeException("not found day"));
        List<Group> groupList = groupRepository.findAllById(timeTableDto.getGroupIds());
        timeTableRepository.save(TimeTable.builder()
                .startTime(dateFormatUtil.parseTime(timeTableDto.getStartTime()))
                .endTime(dateFormatUtil.parseTime(timeTableDto.getEndTime()))
                .day(day)
                .groups(groupList)
                .build());
        return ResTimeTableDto.builder()
                .day(day.getName())
                .startTime(timeTableDto.getStartTime())
                .endTime(timeTableDto.getEndTime())
                .groupIds(timeTableDto.getGroupIds()).build();
    }

    public ResTimeTableDto update(Long id, TimeTableDto dto) {

        Day day = dayRepository.findById(dto.getDayId()).orElseThrow(() ->
                new RuntimeException("not found day"));

        List<Group> groups = new ArrayList<>();
        List<Integer> groupIds = new ArrayList<>();
        for (Long groupId : dto.getGroupIds()){
            Group found = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("not found"));
            groups.add(found);
        }

        TimeTable foundTime = timeTableRepository.findById(id).orElseThrow(() -> new RuntimeException("not found time"));
        foundTime.setStartTime(dateFormatUtil.parseTime(dto.getStartTime()));
        foundTime.setEndTime(dateFormatUtil.parseTime(dto.getEndTime()));
        foundTime.setDay(day);
        foundTime.setGroups(groups);
        timeTableRepository.save(foundTime);
        return ResTimeTableDto.builder()
                .day(day.getName())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .groupIds(dto.getGroupIds())
                .build();
    }
    public ResTimeTableDto get(Long id) {
        TimeTable repositoryById = timeTableRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Jasurda buu");
        });
        return ResTimeTableDto.builder()
                .day(repositoryById.getDay().getName())
                .startTime(dateFormatUtil.parseTime(repositoryById.getStartTime()))
                .endTime(dateFormatUtil.parseTime(repositoryById.getEndTime()))
                .groupIds(repositoryById.getGroups().stream().map(Group::getId).collect(Collectors.toList()))
                .build();
    }
    public List<ResTimeTableDto> getAll() {
        List<TimeTable> timeTables = timeTableRepository.findAll();
        List<ResTimeTableDto> tableDtos  = new ArrayList<>();

//        tableDtos.add(ResTimeTableDto.builder()
//                        .day()
//                        .startTime("")
//                        .endTime("")
//                        .groupIds()
//                .build());


        for (TimeTable timeTable : timeTables) {
           tableDtos.add(ResTimeTableDto.builder()
                   .day(timeTable.getDay().getName())
                   .startTime(dateFormatUtil.parseTime(timeTable.getStartTime()))
                   .endTime(dateFormatUtil.parseTime(timeTable.getEndTime()))
                   .groupIds(timeTable.getGroups().stream().map(Group::getId).collect(Collectors.toList()))
                   .build());
        }
        return tableDtos;
    }
}
