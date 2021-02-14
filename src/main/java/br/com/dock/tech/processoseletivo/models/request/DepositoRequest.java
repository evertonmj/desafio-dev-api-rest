package br.com.dock.tech.processoseletivo.models.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositoRequest {
	private Integer idConta;
	private BigDecimal valor = BigDecimal.ZERO;
}
