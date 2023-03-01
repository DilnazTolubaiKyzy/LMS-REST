package com.peaksoft.controller;

import com.peaksoft.dto.CompanyRequest;
import com.peaksoft.dto.CompanyResponse;
import com.peaksoft.dto.CourseResponse;
import com.peaksoft.dto.StudentResponse;
import com.peaksoft.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
@PreAuthorize("hasAuthority('ADMIN')")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> companies(){
      return companyService.getAll();
    }


    @PostMapping
    public CompanyResponse save(@RequestBody CompanyRequest request){
        return companyService.save(request);
    }
    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable("id") Long id){
        return companyService.getById(id);
    }

  @PutMapping("/{id}")
  public CompanyResponse update(@PathVariable("id") Long id, @RequestBody CompanyRequest request){
        return companyService.update(id, request);
  }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        companyService.delete(id);
        return "This company "+ id + " was deleted";
    }

    @GetMapping("/courses/{id}")
    public List<CourseResponse> courses(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return companyService.getCoursesByCompanyId(id,page,size);
    }
    @GetMapping("/students/{id}")
    public List<StudentResponse> students(@PathVariable(value = "id" ,required = false) Long id,
                                        @RequestParam(value = "page", required = false) int page,
                                        @RequestParam(name = "size",required = false) int size){
        return companyService.getStudentsByCompanyId(id,page,size);
    }

}



