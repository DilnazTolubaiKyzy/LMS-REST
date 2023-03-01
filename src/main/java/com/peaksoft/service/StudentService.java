package com.peaksoft.service;

import com.peaksoft.dto.StudentRequest;
import com.peaksoft.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudent();
    StudentResponse save(StudentRequest student);
    StudentResponse update(Long id, StudentRequest request);
    StudentResponse getById(Long id);
    void delete(Long id);

    List<StudentResponse> search(String text, int page, int size);
}
