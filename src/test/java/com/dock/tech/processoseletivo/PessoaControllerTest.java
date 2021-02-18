package com.dock.tech.processoseletivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.dock.tech.processoseletivo.ProcessoSeletivoApplication;
import br.com.dock.tech.processoseletivo.controller.PessoaController;
import br.com.dock.tech.processoseletivo.models.entity.Pessoa;
import br.com.dock.tech.processoseletivo.models.request.PessoaRequest;
import br.com.dock.tech.processoseletivo.models.response.Response;

@SpringBootTest(classes=ProcessoSeletivoApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class PessoaControllerTest {
	
	public PessoaControllerTest() {
		super();
	}

	@Autowired
	PessoaController pessoaController;
	
	@Test
	public void testarCriacaoPessoa() {
		PessoaRequest pessoaRequest = new PessoaRequest();
		pessoaRequest.setCpf("0000");
		pessoaRequest.setDataNascimento("01/01/2000");
		pessoaRequest.setNome("Joao Test");
		
		Response<Pessoa> response = pessoaController.criarPessoa(pessoaRequest);
		
		assertEquals(response.getMensagem(), "Pessoa criada com sucesso!");
		assertNotNull(response.getPayload());
	}
}
