package com.team03.challenge02.discipline.repository;


import com.team03.challenge02.discipline.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

}
