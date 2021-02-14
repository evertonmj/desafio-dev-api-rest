package br.com.dock.tech.processoseletivo.models.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.dock.tech.processoseletivo.models.enums.TipoConta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaRequest {
	@NotNull(message="Codigo da pessoa nao pode ser vazio") @Pattern(regexp = "[0-9]", message="Codigo deve ser informado somente com numeros")
	@Schema(description = "Codigo da pessoa", example="3")	
	private Integer idPessoa;
	
	@NotNull(message="Valor do limite diario nao pode ser vazio")
	@Schema(description = "Limite diario de saque. Duas casas decimais separados ponto", example="1500.99")
	private BigDecimal limiteSaqueDiario;
	
	@NotNull(message="Tipo da conta nao pode ser vazio")
	@Schema(description = "Tipo da conta. Pode ser CORRENTE ou POUPANCA", example = "CORRENTE", allowableValues = {"CORRENTE", "POUPANCA"})
	private TipoConta tipoConta;
}
