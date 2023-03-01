package com.peaksoft.controller;

import com.peaksoft.dto.StudentRequest;
import com.peaksoft.dto.StudentResponse;
import com.peaksoft.entity.User;
import com.peaksoft.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> getAll(){
        return studentService.getAllStudent();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse save(@RequestBody StudentRequest request){
        return studentService.save(request);
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse getById(@PathVariable("id") Long id){
        return studentService.getById(id);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse update(@PathVariable("id") Long id, @RequestBody StudentRequest request){
        return studentService.update(id,request);

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String  delete(@PathVariable("id") Long id){
         studentService.delete(id);
         return "Student is deleted";
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<StudentResponse> search(@RequestParam(name = "text" ,required = false ) String text,
                                         @RequestParam(value = "page" , required = false) int page,
                                        @RequestParam(name = "size" , required = false) int size){
        return studentService.search(text,page,size);
    }

}
