package com.team03.challenge02.student.dto.mapper;

import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toStudent(StudentDto studentDto) {
        return new ModelMapper().map(studentDto,Student.class);
    }

    public static StudentDto toStudentDto(Student student) {
       String role = student.getRole().name().substring("ROLE_".length());
       PropertyMap<Student,StudentDto> props = new PropertyMap<Student,StudentDto>() {
          @Override
           protected void configure() {
               // map().setRole(role);

          }
       };
       ModelMapper mapper = new ModelMapper();
       mapper.addMappings(props);
       return mapper.map(student,StudentDto.class);
    }
public static List<StudentDto> toListDTO(List<Student> userEntities){
    return userEntities.stream().map(user -> toStudentDto(user)).collect(Collectors.toList());
}

}
