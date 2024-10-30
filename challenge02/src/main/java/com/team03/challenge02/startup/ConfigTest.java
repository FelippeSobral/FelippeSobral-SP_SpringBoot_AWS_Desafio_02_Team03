package com.team03.challenge02.startup;

import com.team03.challenge02.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigTest implements CommandLineRunner {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            long count = teacherRepository.count();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falha ao conectar ao banco de dados");
        }
    }
}
