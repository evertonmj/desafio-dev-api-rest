package com.dock.tech.processoseletivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dock.tech.processoseletivo.ProcessoSeletivoApplication;
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

@SpringBootTest(classes=ProcessoSeletivoApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ContaControllerTest {
	
	@Autowired
	PessoaController pessoaController;
	
	@Autowired
	ContaController contaController;
	
	@Before
	public void criarPessoa() {
		PessoaRequest pessoaRequest = new PessoaRequest();
		pessoaRequest.setCpf("0000");
		pessoaRequest.setDataNascimento("01/01/2000");
		pessoaRequest.setNome("Joao Test");
		
		Response<Pessoa> response = pessoaController.criarPessoa(pessoaRequest);
	}
	
	@Test
	public void testarCriacaoConta() {
		ContaRequest req = new ContaRequest();
		req.setIdPessoa(1);
		req.setLimiteSaqueDiario(new BigDecimal(100));
		req.setTipoConta(TipoConta.CORRENTE);
		Response<Conta> response = contaController.criarConta(req);
		
		assertNotNull(response.getPayload());
	}
	
	@Test
	public void testarDeposito() {
		ContaRequest reqConta = new ContaRequest();
		reqConta.setIdPessoa(1);
		reqConta.setLimiteSaqueDiario(new BigDecimal(100));
		reqConta.setTipoConta(TipoConta.CORRENTE);
		Response<Conta> responseConta = contaController.criarConta(reqConta);
		Conta conta = responseConta.getPayload();
		
		DepositoRequest req = new DepositoRequest();
		
		req.setIdConta(conta.getIdConta());
		req.setValor(new BigDecimal(10));
		
		Response<Transacao> response = contaController.depositarValor(req);
		
		assertNotNull(response.getPayload());
	}
	
	@Test
	public void testarSaque() {
		ContaRequest reqConta = new ContaRequest();
		reqConta.setIdPessoa(1);
		reqConta.setLimiteSaqueDiario(new BigDecimal(100));
		reqConta.setTipoConta(TipoConta.CORRENTE);
		
		Response<Conta> responseConta = contaController.criarConta(reqConta);
		Conta conta = responseConta.getPayload();
		
		DepositoRequest reqDeposito = new DepositoRequest();
		
		reqDeposito.setIdConta(conta.getIdConta());
		reqDeposito.setValor(new BigDecimal(10));
		
		contaController.depositarValor(reqDeposito);
		
		SaqueRequest req = new SaqueRequest();
		req.setIdConta(conta.getIdConta());
		req.setValor(new BigDecimal(5));
		
		Response<Transacao> response = contaController.sacarValor(req);
		
		assertNotNull(response.getPayload());
	}
	
	@Test
	public void testarSaldo() {
		ContaRequest reqConta = new ContaRequest();
		reqConta.setIdPessoa(1);
		reqConta.setLimiteSaqueDiario(new BigDecimal(100));
		reqConta.setTipoConta(TipoConta.CORRENTE);
		Response<Conta> responseConta = contaController.criarConta(reqConta);
		Conta conta = responseConta.getPayload();
		
		Response<BigDecimal> resp = contaController.obterSaldo(conta.getIdConta());
		assertNotNull(resp.getPayload());
	}
	
	@Test
	public void testarBloqueio() {
		ContaRequest reqConta = new ContaRequest();
		reqConta.setIdPessoa(1);
		reqConta.setLimiteSaqueDiario(new BigDecimal(100));
		reqConta.setTipoConta(TipoConta.CORRENTE);
		Response<Conta> responseConta = contaController.criarConta(reqConta);
		Conta conta = responseConta.getPayload();
		
		Response<Conta> resp = contaController.bloquearConta(conta.getIdConta());
		Conta contaBlock = resp.getPayload();
		assertEquals(contaBlock.getFlagAtivo(), false);
	}
	
	@Test
	public void testarExtrato() {
		ContaRequest reqConta = new ContaRequest();
		reqConta.setIdPessoa(1);
		reqConta.setLimiteSaqueDiario(new BigDecimal(100));
		reqConta.setTipoConta(TipoConta.CORRENTE);
		Response<Conta> responseConta = contaController.criarConta(reqConta);
		Conta conta = responseConta.getPayload();
		
		Response<List<Transacao>> resp = contaController.extratoConta(conta.getIdConta(), "01/01/2021", "01/05/2021");
		List<Transacao> transacoes = resp.getPayload();
		assertNotNull(transacoes);
		assertEquals(0, transacoes.size());
	}
}
