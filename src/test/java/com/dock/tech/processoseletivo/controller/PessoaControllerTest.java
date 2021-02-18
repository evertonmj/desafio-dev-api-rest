package com.dock.tech.processoseletivo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dock.tech.processoseletivo.controller.PessoaController;
import br.com.dock.tech.processoseletivo.models.entity.Pessoa;
import br.com.dock.tech.processoseletivo.models.request.PessoaRequest;
import br.com.dock.tech.processoseletivo.models.response.Response;
import br.com.dock.tech.processoseletivo.repository.PessoaRepository;

@SpringBootTest(classes=PessoaControllerTest.class)
public class PessoaControllerTest {
	
	@Autowired
	PessoaController pessoaController;
	
	@InjectMocks
	PessoaRepository pessoaRepository;
	
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
