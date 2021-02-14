package br.com.dock.tech.processoseletivo.models.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositoRequest {
	
	@NotNull(message="Codigo da conta nao pode ser vazio") @Pattern(regexp = "[0-9]", message="Codigo deve ser informado somente com numeros")
	@Schema(description = "Codigo da conta", example="3")
	private Integer idConta;
	
	@NotNull(message="Valor do deposito nao pode ser vazio")
	@Schema(description = "Valor do deposito", example="50.90")
	private BigDecimal valor = BigDecimal.ZERO;
}
