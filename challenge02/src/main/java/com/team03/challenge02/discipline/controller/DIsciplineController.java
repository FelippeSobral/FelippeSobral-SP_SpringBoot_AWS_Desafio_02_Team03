package com.team03.challenge02.discipline.controller;

import com.team03.challenge02.course.entity.Course;
import com.team03.challenge02.discipline.entity.Discipline;
import com.team03.challenge02.discipline.services.DisciplineServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DIsciplineController {

    public final DisciplineServices disciplineServices;

    public DIsciplineController(DisciplineServices disciplineServices) {
        this.disciplineServices = disciplineServices;
    }

    @GetMapping
    public ResponseEntity<List<Discipline>> findAll(){
        List<Discipline> list = disciplineServices.findAll();
        return ResponseEntity.ok().body(list);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        disciplineServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
