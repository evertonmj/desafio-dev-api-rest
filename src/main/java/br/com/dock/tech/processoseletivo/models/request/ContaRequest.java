package br.com.dock.tech.processoseletivo.models.request;

import java.math.BigDecimal;

import br.com.dock.tech.processoseletivo.models.enums.TipoConta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaRequest {
	private Integer idPessoa;
	private BigDecimal limiteSaqueDiario;
	private TipoConta tipoConta;
}
