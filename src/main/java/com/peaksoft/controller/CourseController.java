package com.peaksoft.controller;

import com.peaksoft.dto.*;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.repository.CourseRepository;
import com.peaksoft.service.CompanyService;
import com.peaksoft.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final CourseRepository courseRepository;
    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.courseRepository = courseRepository;
    }

    @GetMapping
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<CourseResponse> courses(){
        return courseService.getAll();
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CourseResponse save(@RequestBody CourseRequest request){
        return courseService.save(request);
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CourseResponse getById(@PathVariable("id") Long id){
        return courseService.getById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public CourseResponse update(@PathVariable("id") Long id, @RequestBody CourseRequest request){
        return courseService.update(id, request);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete(@PathVariable("id") Long id){
        courseService.delete(id);
        return "This course "+ id + " was deleted";
    }

    @GetMapping("/groups/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<GroupResponse> groups(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return courseService.getGroupsByCourseId(id,page,size);
    }

    @GetMapping("/teachers/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<TeacherResponse> teachers(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return courseService.getTeachersByCourseId(id,page,size);
    }
}
