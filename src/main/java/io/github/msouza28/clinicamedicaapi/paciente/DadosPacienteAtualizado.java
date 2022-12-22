package io.github.msouza28.clinicamedicaapi.paciente;

import io.github.msouza28.clinicamedicaapi.endereco.Endereco;
import io.github.msouza28.clinicamedicaapi.entity.Paciente;
import io.github.msouza28.clinicamedicaapi.enums.Convenio;

public record DadosPacienteAtualizado(Long id, String nome, String email, String telefone, Convenio convenio,  String cpf, Endereco endereco) {
	
	public DadosPacienteAtualizado(Paciente paciente) {
		this(paciente.getId(),paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getConvenio(), paciente.getCpf(), paciente.getEndereco());
	}

}
