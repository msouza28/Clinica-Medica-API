package io.github.msouza28.clinicamedicaapi.medico;

import io.github.msouza28.clinicamedicaapi.endereco.Endereco;
import io.github.msouza28.clinicamedicaapi.entity.Medico;

public record DadosMedicoAtualizado(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

	public DadosMedicoAtualizado(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
	}
}
