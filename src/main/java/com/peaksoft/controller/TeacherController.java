package com.peaksoft.controller;

import com.peaksoft.dto.*;
import com.peaksoft.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teachers")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping
    public List<TeacherResponse> getAll(){
        return teacherService.getAllTeacher();
    }
    @PostMapping
    public TeacherResponse save(@RequestBody TeacherRequest request){
        return teacherService.save(request);
    }
    @GetMapping("{id}")
    public TeacherResponse getById(@PathVariable("id") Long id){
        return teacherService.getById(id);
    }

    @PutMapping("{id}")
    public TeacherResponse update(@PathVariable("id") Long id, @RequestBody TeacherRequest request){
        return teacherService.update(id, request);
    }
    @DeleteMapping("{id}")
        public String delete(@PathVariable("id") Long id){
       teacherService.delete(id);
       return "Teacher is deleted ";
        }

        @GetMapping("/courses/{id}")
    public List<CourseResponse> courses(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return teacherService.getCoursesByTeacherId(id,page,size);
        }

    @GetMapping("/students/{id}")
    public List<StudentResponse> students(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return teacherService.quantityOfStudents(id,page,size);
    }


}
