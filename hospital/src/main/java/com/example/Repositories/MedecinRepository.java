package com.example.Repositories;

import com.example.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    List<Medecin> findByName(String name);

    List<Medecin> findBySpeciality(String speciality);
}
