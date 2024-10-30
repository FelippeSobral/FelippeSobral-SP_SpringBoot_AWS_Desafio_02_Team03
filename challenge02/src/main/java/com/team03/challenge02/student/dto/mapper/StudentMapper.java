package com.team03.challenge02.student.dto.mapper;

import com.team03.challenge02.student.dto.StudentDto;
import com.team03.challenge02.student.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class StudentMapper {

    public static Student toStudent(StudentDto studentDto) {
        return new ModelMapper().map(studentDto,Student.class);
    }

//    public static StudentDto toStudentDto(Student student) {
//       String role = student.getRole().name().substring("ROLE_".length());
//       PropertyMap<Student,StudentDto> props = new PropertyMap<Student,StudentDto>() {
//          @Override
//           protected void configure() {
//                map().setRole(role);
//
//          }
//       };
//       ModelMapper mapper = new ModelMapper();
//       mapper.addMappings(props);
//       return mapper.map(student,StudentDto.class);
//    }

}
