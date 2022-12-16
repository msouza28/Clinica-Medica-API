package io.github.msouza28.clinicamedicaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.msouza28.clinicamedicaapi.medico.DadosAtualizacaoMedico;
import io.github.msouza28.clinicamedicaapi.medico.DadosListaMedico;
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

	@GetMapping
	public Page<DadosListaMedico> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListaMedico::new);
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {

		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	//Exclusao total
//	@DeleteMapping("/{id}")
//	@Transactional
//	public void deletar(@PathVariable Long id) {
//		repository.deleteById(id);
//	}
	
	//Exclusao logica
	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();
		
	}

}
