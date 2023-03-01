package com.peaksoft.service;

import com.peaksoft.dto.CompanyRequest;
import com.peaksoft.dto.CompanyResponse;
import com.peaksoft.dto.CourseResponse;
import com.peaksoft.dto.StudentResponse;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyService {
    List<CompanyResponse> getAll();
    CompanyResponse save(CompanyRequest company);
    CompanyResponse update(Long id, CompanyRequest company);
    CompanyResponse getById(Long id);
    void delete(Long id);
    List<CourseResponse> getCoursesByCompanyId(Long id, int page, int size);
    List<StudentResponse> getStudentsByCompanyId(Long id, int page, int size);

}
