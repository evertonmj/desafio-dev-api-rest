package br.com.dock.tech.processoseletivo.models.enums;

public enum TipoConta {
	CORRENTE(1),
	POUPANCA(2);

	private Integer tipoConta;
	
	TipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Integer getTipoConta() {
		return tipoConta;
	}
}
