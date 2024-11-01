package com.team03.challenge02.coordinator.entity;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;



public class Coordinator extends Person implements Serializable {
}
