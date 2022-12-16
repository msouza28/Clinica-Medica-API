package io.github.msouza28.clinicamedicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.msouza28.clinicamedicaapi.medico.DadosMedico;
import io.github.msouza28.clinicamedicaapi.medico.Medico;
import io.github.msouza28.clinicamedicaapi.medico.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosMedico dados) {
		repository.save(new Medico(dados));
	}

}
