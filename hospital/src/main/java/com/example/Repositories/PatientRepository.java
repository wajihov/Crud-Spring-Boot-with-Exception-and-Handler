package com.example.Repositories;

import com.example.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByName(String name);

    @Query("SELECT p from Patient p WHERE  p.name like %?1%")
    List<Patient> searchByName(String name);

    @Query("SELECT p from Patient p WHERE  p.name like %:name%")
    List<Patient> searchByNameII(@Param("name") String name);

}
