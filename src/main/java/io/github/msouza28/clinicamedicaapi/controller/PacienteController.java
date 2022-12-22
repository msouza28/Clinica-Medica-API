package io.github.msouza28.clinicamedicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.msouza28.clinicamedicaapi.entity.Paciente;
import io.github.msouza28.clinicamedicaapi.paciente.DadosAtualizacaoPaciente;
import io.github.msouza28.clinicamedicaapi.paciente.DadosListaPaciente;
import io.github.msouza28.clinicamedicaapi.paciente.DadosPaciente;
import io.github.msouza28.clinicamedicaapi.paciente.DadosPacienteAtualizado;
import io.github.msouza28.clinicamedicaapi.repository.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired(required = true)
	private PacienteRepository repository;
	
	@PostMapping
	public ResponseEntity cadastar(@RequestBody @Valid DadosPaciente dados, UriComponentsBuilder uriCompenent) {
		
		var paciente = new Paciente(dados);
		repository.save(paciente);
		
		var uri = uriCompenent.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosPacienteAtualizado(paciente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		
		var paciente = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosPacienteAtualizado(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListaPaciente>> listar(Pageable paginacao){
		
		var page = repository.findAll(paginacao).map(DadosListaPaciente::new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPaciente dados) {
		
		Paciente paciente = repository.getReferenceById(dados.id());	
		paciente.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosPacienteAtualizado(paciente)); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}

}
