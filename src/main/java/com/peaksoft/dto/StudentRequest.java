package com.peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String studyFormat;
    private Long groupId;
}
