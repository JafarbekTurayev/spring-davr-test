package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.GroupDTO;
import uz.pdp.springdavrtest.entity.*;
import uz.pdp.springdavrtest.repository.*;
import uz.pdp.springdavrtest.utils.DateFormatUtil;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PayTypeRepository payTypeRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    DateFormatUtil dateFormatUtil;

    public ApiResponse addGroup(GroupDTO groupDTO) {
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new RuntimeException("Paytype not found!"));
        Room room = roomRepository.findById(groupDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Student not found!"));
        Status status = statusRepository.findById(groupDTO.getStatusId()).orElseThrow(() -> new RuntimeException("Student not found!"));
        Teacher teacher = teacherRepository.findById(groupDTO.getTeacherId()).orElseThrow(() -> new RuntimeException("Student not found!"));

        Group group = new Group();
        group.setCourse(course);
        group.setName(groupDTO.getName());
        group.setRoom(room);
        group.setTeacher(teacher);
        group.setStatus(status);
        group.setStartDate(dateFormatUtil.dateConvertor(groupDTO.getStartDate()));
        group.setEndDate(dateFormatUtil.dateConvertor(groupDTO.getEndDate()));
        Group save = groupRepository.save(group);

        return ApiResponse.builder().data(save).message("Saved!").success(true).build();
    }

    public ApiResponse getAll() {
        return ApiResponse.builder().success(true).message("Mana").data(groupRepository.findAll()).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(optionalGroup.get()).success(true).build();
    }

    public ApiResponse edit(Long ketmon, GroupDTO groupDTO) {
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new RuntimeException("Paytype not found!"));
        Room room = roomRepository.findById(groupDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Student not found!"));
        Status status = statusRepository.findById(groupDTO.getStatusId()).orElseThrow(() -> new RuntimeException("Student not found!"));
        Teacher teacher = teacherRepository.findById(groupDTO.getTeacherId()).orElseThrow(() -> new RuntimeException("Student not found!"));

        ApiResponse response = new ApiResponse();
        groupRepository.findById(ketmon).ifPresentOrElse(group -> {
            group.setCourse(course);
            group.setName(groupDTO.getName());
            group.setRoom(room);
            group.setTeacher(teacher);
            group.setStatus(status);
            group.setStartDate(dateFormatUtil.dateConvertor(groupDTO.getStartDate()));
            group.setEndDate(dateFormatUtil.dateConvertor(groupDTO.getEndDate()));
            Group save = groupRepository.save(group);
            response.builder().data(save).success(true).message("Edited!").build();
        }, () -> {
            new RuntimeException("Xatolik");
        });
        return response;
    }

    public ApiResponse delete(Long id) {
        groupRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }
}
