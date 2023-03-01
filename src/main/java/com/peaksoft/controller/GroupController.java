package com.peaksoft.controller;

import com.peaksoft.dto.*;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.repository.GroupRepository;
import com.peaksoft.service.CourseService;
import com.peaksoft.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupController(GroupService groupService, CourseService courseService, GroupRepository groupRepository) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.groupRepository = groupRepository;
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<GroupResponse> companies(){
        return groupService.getAll();
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public GroupResponse save(@RequestBody GroupRequest request){
        return groupService.save(request);
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GroupResponse getById(@PathVariable("id") Long id){
        return groupService.getById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public GroupResponse update(@PathVariable("id") Long id, @RequestBody GroupRequest request){
        return groupService.update(id, request);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete(@PathVariable("id") Long id){
        groupService.delete(id);
        return "This group "+ id + " was deleted";
    }
    @GetMapping("/courses/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<CourseResponse> courses(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return groupService.getCoursesByGroupId(id,page,size);
    }

    @GetMapping("/students/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> students(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return groupService.getStudentsByGroupId(id,page,size);
    }
}
