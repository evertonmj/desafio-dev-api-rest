package br.com.dock.tech.processoseletivo.models.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaRequest {
	@NotNull(message="Nome da pessoa nao pode ser vazio")
	@Schema(description = "Nome da pessoa", example="Everton M")
	private String nome;
	
	@NotNull(message="CPF nao pode ser vazio") @Pattern(regexp = "[0-9]", message="CPF deve ser informado somente com numeros")
	@Schema(description = "CPF da pessoa, somente numeros", example = "12345678900")
	private String cpf;
	
	@NotNull(message="Data de nascimento nao pode ser vazio") @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$", message="Data de nascimento deve estar no formato DD/MM/AAAA")
	@Schema(description = "Data de nascimento no formato DD/MM/AAAA", example="22/09/1987")
	private String dataNascimento;
}
