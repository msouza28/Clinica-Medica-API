package io.github.msouza28.clinicamedicaapi.medico;

import io.github.msouza28.clinicamedicaapi.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
		
		@NotNull
		Long id, 
		String nome, 
		String telefone, 
		DadosEndereco endereco) {

}
