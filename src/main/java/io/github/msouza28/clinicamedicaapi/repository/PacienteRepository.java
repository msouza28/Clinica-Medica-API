package io.github.msouza28.clinicamedicaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.msouza28.clinicamedicaapi.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
