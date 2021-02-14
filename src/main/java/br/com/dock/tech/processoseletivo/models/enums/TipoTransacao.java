package br.com.dock.tech.processoseletivo.models.enums;

public enum TipoTransacao {
	DEPOSITO(1),
	SAQUE(2);
	
	private Integer tipoTransacao;
	
	TipoTransacao(Integer tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Integer getTipoTransacao() {
		return tipoTransacao;
	}
}
