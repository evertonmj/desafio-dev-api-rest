package br.com.dock.tech.processoseletivo.models.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaqueRequest {
	@NotNull(message="Codigo da pessoa nao pode ser vazio") @Pattern(regexp = "[0-9]", message="Codigo deve ser informado somente com numeros")
	@Schema(description = "Codigo da pessoa", example="3")
	private Integer idConta;
	
	@NotNull(message="Valor do saque")
	@Schema(description = "Valor do saque", example="15.00")
	private BigDecimal valor;
}
