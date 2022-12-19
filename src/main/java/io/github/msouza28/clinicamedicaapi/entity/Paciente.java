package io.github.msouza28.clinicamedicaapi.entity;

import io.github.msouza28.clinicamedicaapi.endereco.Endereco;
import io.github.msouza28.clinicamedicaapi.paciente.Convenio;
import io.github.msouza28.clinicamedicaapi.paciente.DadosAtualizacaoPaciente;
import io.github.msouza28.clinicamedicaapi.paciente.DadosPaciente;
import io.github.msouza28.clinicamedicaapi.paciente.DadosPacienteAtualizado;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String nome;
	String email;
	String cpf;
	String telefone;
	
	@Enumerated(EnumType.STRING)
	Convenio convenio;
	@Embedded
	Endereco endereco;
	
	public Paciente(@Valid DadosPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.cpf = dados.cpf();
		this.telefone = dados.telefone();
		this.convenio = dados.convenio();
		this.endereco = new Endereco(dados.endereco());
	}

	public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.email() != null) {
			this.email = dados.email();
		}
		if (dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if (dados.convenio() != null) {
			this.convenio = dados.convenio();
		}
		if (dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
		
	}
	
}
