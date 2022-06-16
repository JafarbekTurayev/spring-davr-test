package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.dto.GroupDTO;
import uz.pdp.springdavrtest.repository.GroupRepository;
import uz.pdp.springdavrtest.service.GroupService;

@RequestMapping("/group")
@RestController
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupService groupService;

    @PostMapping
    public ApiResponse add(@RequestBody GroupDTO groupDTO) {
        ApiResponse apiResponse = groupService.addGroup(groupDTO);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id) {
        return groupService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(name = "id") Long ketmon, @RequestBody GroupDTO groupDTO) {
        return groupService.edit(ketmon, groupDTO);
    }

    @DeleteMapping
    public ApiResponse delete(@RequestParam Long id) {
        return groupService.delete(id);
    }
}
