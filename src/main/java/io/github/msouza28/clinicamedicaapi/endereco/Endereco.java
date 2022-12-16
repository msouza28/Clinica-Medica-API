package io.github.msouza28.clinicamedicaapi.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
	
	
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String numero;
	private String complemento;
	
	public Endereco(DadosEndereco dados) {
		this.logradouro = dados.logradouro();
		this.bairro = dados.bairro();
		this.cep = dados.cep();
		this.cidade = dados.cidade();
		this.uf = dados.uf();
		this.numero = dados.numero();
		this.complemento = dados.complemento();
	}

}

