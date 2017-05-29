package br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario;

import javax.persistence.Embeddable;

@Embeddable
public class DadosBasicosVO {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
