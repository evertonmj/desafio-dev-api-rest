package br.com.dock.tech.processoseletivo.models.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idconta")
	private Integer idConta;
	
	@Column(name="idpessoa")
	private Integer idPessoa;
	
	@Column(name="saldo")
	private BigDecimal saldo;
	
	@Column(name="limitesaquediario")
	private BigDecimal limiteSaqueDiario;
	
	@Column(name="flagativo")
	private Boolean flagAtivo;
	
	@Column(name="tipoconta")
	private Integer tipoConta;
	
	@Column(name="datacriacao")
	private Date dataCriacao;
}
