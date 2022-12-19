package io.github.msouza28.clinicamedicaapi.paciente;

import io.github.msouza28.clinicamedicaapi.entity.Paciente;

public record DadosListaPaciente(
		Long id, 
		String nome, 
		String email, 
		String telefone,
		Convenio convenio) { 
	
	public DadosListaPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getConvenio());
	}
}
