package io.github.msouza28.clinicamedicaapi.paciente;

import io.github.msouza28.clinicamedicaapi.endereco.DadosEndereco;
import io.github.msouza28.clinicamedicaapi.enums.Convenio;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull
		Long id,
		String nome,
		String email,
		String telefone,
		Convenio convenio,
		DadosEndereco endereco) {

}
