package br.com.dock.tech.processoseletivo.models.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transacao")
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idtransacao")
	private Integer idTransacao;
	
	@Column(name="idconta")
	private Integer idConta;
	
	@Column(name="valor")
	private BigDecimal valor;
	
	@Column(name="datatransacao")
	private Date dataTransacao;
	
	@Column(name="tipotransacao")
	private Integer tipoTransacao;
}
