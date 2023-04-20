package com.example.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentMangementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"James Bond"),
            new Student(2,"Maria Jones"),
            new Student(3,"Anna Smith")
    );

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTREE')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('student:write')")
    public List<Student> getAllStudents() {
        return STUDENTS;
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println(studentId);
    }
    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student){
        System.out.println(String.format("%s %s",studentId,student));
    }

}
