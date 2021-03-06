package uz.pdp.springdavrtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.GroupDTO;
import uz.pdp.springdavrtest.dto.ResGroupDTO;
import uz.pdp.springdavrtest.entity.*;
import uz.pdp.springdavrtest.repository.*;
import uz.pdp.springdavrtest.utils.DateFormatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Group> all = groupRepository.findAll();
        //1-variant
        List<ResGroupDTO> collect = all.stream().map(this::mapToDTO).collect(Collectors.toList());
//2-variant
        //        List<ResGroupDTO> collect = new ArrayList<>();
//        for (Group group : all) {
//            ResGroupDTO resGroupDTO = mapToDTO(group);
//            collect.add(resGroupDTO);
//        }
        return ApiResponse.builder().success(true).message("Mana").data(collect).build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent()) return ApiResponse.builder().success(false).message("Not").build();

        return ApiResponse.builder().message("Mana").data(mapToDTO(optionalGroup.get())).success(true).build();
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

    public ApiResponse addStudent(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Not found!"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group Not found!"));

        //eski royhatni olib
        List<Student> students = group.getStudents();
        students.add(student);
        //yangiladik
        group.setStudents(students);
        Group save = groupRepository.save(group);
        return ApiResponse.builder().data(save).success(true).message("Student added to Group").build();
    }


    //metod Entitydan RESGROUPDTO ga o'tkazish
    public ResGroupDTO mapToDTO(Group group) {
        return ResGroupDTO.builder().startDate(String.valueOf(group.getStartDate())).endDate(String.valueOf(group.getEndDate())).courseName(group.getCourse().getName()).teacherName(group.getTeacher().getFullName()).groupName(group.getName()).roomName(group.getRoom().getName()).statusName(group.getStatus().getName()).build();
    }

}
