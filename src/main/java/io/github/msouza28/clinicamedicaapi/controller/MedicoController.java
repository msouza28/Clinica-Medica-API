package io.github.msouza28.clinicamedicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.msouza28.clinicamedicaapi.entity.Medico;
import io.github.msouza28.clinicamedicaapi.medico.DadosAtualizacaoMedico;
import io.github.msouza28.clinicamedicaapi.medico.DadosListaMedico;
import io.github.msouza28.clinicamedicaapi.medico.DadosMedico;
import io.github.msouza28.clinicamedicaapi.medico.DadosMedicoAtualizado;
import io.github.msouza28.clinicamedicaapi.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired(required = true)
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosMedico dados, UriComponentsBuilder uriBuilder) {

		var medico = new Medico(dados);
		repository.save(medico);

		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosMedicoAtualizado(medico));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListaMedico>> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListaMedico::new);
		
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {

		var medico = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosMedicoAtualizado(medico));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosMedicoAtualizado(medico));
	}

	// Exclusao logica
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();

	}

	// Exclusao total
//	@DeleteMapping("/{id}")
//	@Transactional
//	public void deletar(@PathVariable Long id) {
//		repository.deleteById(id);
//	}

}
