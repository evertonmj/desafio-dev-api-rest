package br.com.dock.tech.processoseletivo.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response <T> {
	private String mensagem;
	private T payload;
}
