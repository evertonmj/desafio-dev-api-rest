package br.com.dock.tech.processoseletivo.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaRequest {
	private String nome;
	private String cpf;
	private String dataNascimento;
}
