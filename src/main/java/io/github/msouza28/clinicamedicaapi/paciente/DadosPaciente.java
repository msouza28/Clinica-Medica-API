package io.github.msouza28.clinicamedicaapi.paciente;

import io.github.msouza28.clinicamedicaapi.endereco.DadosEndereco;
import io.github.msouza28.clinicamedicaapi.enums.Convenio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosPaciente(
		@NotBlank
		String nome, 
		@NotBlank
		@Email
		String email,
		@NotBlank
		String telefone,
		@NotNull
		Convenio convenio, 
		@NotBlank
		@Pattern(regexp = "\\d{11}")
		String cpf, 
		@NotNull
		@Valid
		DadosEndereco endereco) {

}
