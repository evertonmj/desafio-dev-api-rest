package com.dock.tech.processoseletivo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dock.tech.processoseletivo.controller.ContaController;
import br.com.dock.tech.processoseletivo.controller.PessoaController;
import br.com.dock.tech.processoseletivo.models.entity.Conta;
import br.com.dock.tech.processoseletivo.models.entity.Pessoa;
import br.com.dock.tech.processoseletivo.models.entity.Transacao;
import br.com.dock.tech.processoseletivo.models.enums.TipoConta;
import br.com.dock.tech.processoseletivo.models.request.ContaRequest;
import br.com.dock.tech.processoseletivo.models.request.DepositoRequest;
import br.com.dock.tech.processoseletivo.models.request.PessoaRequest;
import br.com.dock.tech.processoseletivo.models.request.SaqueRequest;
import br.com.dock.tech.processoseletivo.models.response.Response;
import br.com.dock.tech.processoseletivo.repository.ContaRepository;
import br.com.dock.tech.processoseletivo.repository.TransacaoRepository;

@SpringBootTest(classes=ContaControllerTest.class)
public class ContaControllerTest {
	
	@Autowired
	PessoaController pessoaController;
	
	@Autowired
	ContaController contaController;
	
	@InjectMocks
	ContaRepository contaRepository;
	
	@InjectMocks
	TransacaoRepository transacaoRepository;
	
	private Pessoa pessoa;
	private Conta conta;
	
	@Before
	public void criarPessoa() {
		PessoaRequest pessoaRequest = new PessoaRequest();
		pessoaRequest.setCpf("0000");
		pessoaRequest.setDataNascimento("01/01/2000");
		pessoaRequest.setNome("Joao Test");
		
		Response<Pessoa> response = pessoaController.criarPessoa(pessoaRequest);
		this.pessoa = response.getPayload();
	}
	
	@Test
	public void testarCriacaoConta() {
		ContaRequest req = new ContaRequest();
		req.setIdPessoa(pessoa.getIdPessoa());
		req.setLimiteSaqueDiario(new BigDecimal(100));
		req.setTipoConta(TipoConta.CORRENTE);
		Response<Conta> response = contaController.criarConta(req);
		
		assertNotNull(response.getPayload());
		this.conta = response.getPayload();
	}
	
	@Test
	public void testarDeposito() {
		DepositoRequest req = new DepositoRequest();
		
		req.setIdConta(conta.getIdConta());
		req.setValor(new BigDecimal(10));
		
		Response<Transacao> response = contaController.depositarValor(req);
		
		assertNotNull(response.getPayload());
	}
	
	@Test
	public void testarSaque() {
		SaqueRequest req = new SaqueRequest();
		
		req.setIdConta(conta.getIdConta());
		req.setValor(new BigDecimal(10));
		
		Response<Transacao> response = contaController.sacarValor(req);
		
		assertNotNull(response.getPayload());
	}
	
	@Test
	public void testarSaldo() {
		Response<BigDecimal> resp = contaController.obterSaldo(conta.getIdConta());
		assertEquals(new BigDecimal(100), resp.getPayload());
	}
	
	@Test
	public void testarBloqueio() {
		Response<Conta> resp = contaController.bloquearConta(conta.getIdConta());
		Conta conta = resp.getPayload();
		assertEquals(conta.getFlagAtivo(), false);
	}
	
	@Test
	public void testarExtrato() {
		Response<List<Transacao>> resp = contaController.extratoConta(conta.getIdConta(), "01/01/2021", "01/05/2021");
		List<Transacao> transacoes = resp.getPayload();
		assertNotNull(transacoes);
		assertEquals(2, transacoes.size());
	}
}
