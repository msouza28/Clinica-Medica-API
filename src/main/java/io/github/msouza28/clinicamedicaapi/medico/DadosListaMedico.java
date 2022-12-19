package io.github.msouza28.clinicamedicaapi.medico;

import io.github.msouza28.clinicamedicaapi.entity.Medico;

public record DadosListaMedico(
		Long id,
		String nome,
		String email,
		String crm,
		Especialidade especialidade) {
	
	public DadosListaMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
