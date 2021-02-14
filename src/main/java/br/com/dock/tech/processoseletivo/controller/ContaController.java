package br.com.dock.tech.processoseletivo.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.dock.tech.processoseletivo.models.entity.Conta;
import br.com.dock.tech.processoseletivo.models.entity.Transacao;
import br.com.dock.tech.processoseletivo.models.enums.TipoTransacao;
import br.com.dock.tech.processoseletivo.models.request.ContaRequest;
import br.com.dock.tech.processoseletivo.models.request.DepositoRequest;
import br.com.dock.tech.processoseletivo.models.request.SaqueRequest;
import br.com.dock.tech.processoseletivo.models.response.Response;
import br.com.dock.tech.processoseletivo.repository.ContaRepository;
import br.com.dock.tech.processoseletivo.repository.TransacaoRepository;
import br.com.dock.tech.processoseletivo.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping(path="/conta")
@Tag(name = "API para gerenciamento de contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Operation(summary = "Cria uma nova conta")
	@PostMapping(path="/criar")
	public @ResponseBody Response<Conta> criarConta(@RequestBody ContaRequest requestBody) {
		Conta conta = new Conta();
		Response<Conta> response = new Response<Conta>(); 
		
		conta.setIdPessoa(requestBody.getIdPessoa());
		conta.setLimiteSaqueDiario(requestBody.getLimiteSaqueDiario());
		conta.setTipoConta(requestBody.getTipoConta().getTipoConta());
		conta.setDataCriacao(new Date(Calendar.getInstance().getTime().getTime()));
		conta.setFlagAtivo(true);
		conta.setSaldo(BigDecimal.ZERO);
		
		contaRepository.save(conta);
		response.setMensagem("Conta criada com sucesso");
		response.setPayload(conta);
		
		return response;
	}
	
	@Operation(summary = "Faz um deposito em uma conta")
	@PostMapping(path="/depositar")
	public @ResponseBody Response<Transacao> depositarValor(@RequestBody DepositoRequest requestBody) {
		Optional<Conta> contaRep = contaRepository.findById(requestBody.getIdConta());
		Response<Transacao> response = new Response<Transacao>();
		Conta conta = new Conta();
		
		if (!contaRep.isPresent()) {
			response.setMensagem("Conta para deposito nao encontrada!");
			return response;
		} 
		
		//obtem conta e faz update no deposito somando ao saldo
		conta = contaRep.get();
		
		//checa se conta esta bloqueada
		if (conta.getFlagAtivo().equals(false)) {
			response.setMensagem("Conta bloqueada!");
			return response;
		}
		BigDecimal saldoAtual = conta.getSaldo();
		conta.setSaldo(saldoAtual.add(requestBody.getValor()));
		contaRepository.save(conta);
		
		//salva deposito na tabela de transacoes
		Transacao transacao = new Transacao();
		transacao.setIdConta(conta.getIdConta());
		transacao.setDataTransacao(new Date(Calendar.getInstance().getTime().getTime()));
		transacao.setValor(requestBody.getValor());
		transacao.setTipoTransacao(TipoTransacao.DEPOSITO.getTipoTransacao());
		transacaoRepository.save(transacao);
		
		response.setMensagem("Deposito efetuado com sucesso");
		response.setPayload(transacao);
		
		return response;
	}
	
	@Operation(summary = "Obtem o saldo de uma conta")
	@GetMapping(path="/saldo/{idConta}")
	public @ResponseBody Response<BigDecimal> obterSaldo(@PathVariable Integer idConta) {
		Conta conta = new Conta();
		Response<BigDecimal> response = new Response<BigDecimal>();
		Optional<Conta> contaRep = contaRepository.findById(idConta);
		
		if (!contaRep.isPresent()) {
			response.setMensagem("Conta nao encontrada!");
			return response;
		}
		
		conta = contaRep.get();
		if (conta.getFlagAtivo().equals(false)) {
			response.setMensagem("Conta bloqueada!");
			return response;
		}
		
		response.setMensagem("Saldo da conta");
		response.setPayload(conta.getSaldo());
		
		return response;
	}
	
	@Operation(summary = "Bloqueia uma conta")
	@PostMapping(path="/bloquear/{idConta}")
	public @ResponseBody Response<Conta> bloquearConta(@PathVariable Integer idConta) {
		Conta conta = new Conta();
		Response<Conta> response = new Response<Conta>();
		Optional<Conta> contaRep = contaRepository.findById(idConta);
		
		if (!contaRep.isPresent()) {
			response.setMensagem("Conta nao encontrada!");
			return response;
		}
		
		conta = contaRep.get();
		conta.setFlagAtivo(false);
		
		contaRepository.save(conta);
		
		response.setMensagem("Conta bloqueada com sucesso");
		response.setPayload(conta);
		
		return response;
	}
	
	@Operation(summary = "Faz um saque em uma conta")
	@PostMapping(path="/sacar")
	public @ResponseBody Response<Transacao> sacarValor(@RequestBody SaqueRequest requestBody) {
		Optional<Conta> contaRep = contaRepository.findById(requestBody.getIdConta());
		Response<Transacao> response = new Response<Transacao>();
		Conta conta = new Conta();
		
		//checa se conta existe
		if (!contaRep.isPresent()) {
			response.setMensagem("Conta para deposito nao encontrada!");
			return response;
		} 
		
		//obtem conta e faz update no deposito somando ao saldo
		conta = contaRep.get();
		
		//checa se conta esta bloqueada
		if (conta.getFlagAtivo().equals(false)) {
			response.setMensagem("Conta bloqueada!");
			return response;
		}
		
		//checa se valor e maior que limite diario
		int saldoDiario = requestBody.getValor().compareTo(conta.getLimiteSaqueDiario());
		if (saldoDiario == 1) {
			response.setMensagem("O valor informado e maior que o limite diario estabelecido!");
			return response;
		}
		
		//obtem o total de transacoes da conta no dia para verificar se excedeu limite de saques
		List<Transacao> transacoes = transacaoRepository.findByIdContaSaquesDia(conta.getIdConta());
		BigDecimal totalTransacoesSaque = BigDecimal.ZERO;
		
		//soma todas as transacoes diarias
		for (Transacao transacao : transacoes) {
			totalTransacoesSaque = totalTransacoesSaque.add(transacao.getValor());
		}
		
		int limite = totalTransacoesSaque.compareTo(conta.getLimiteSaqueDiario()); 
		if(limite == 0 || limite == 1) {
			response.setMensagem("Saldo diario de saques excedido!");
			return response;
		}
		
		BigDecimal saldoAtual = conta.getSaldo();
		
		//verifica se a conta tem saldo
		int saldoValido = saldoAtual.compareTo(requestBody.getValor());
		if (saldoValido == -1) {
			response.setMensagem("Voce nao possui saldo suficiente na conta!");
			return response;
		}
		
		conta.setSaldo(saldoAtual.subtract(requestBody.getValor()));
		contaRepository.save(conta);
		
		//salva deposito na tabela de transacoes
		Transacao transacao = new Transacao();
		transacao.setIdConta(conta.getIdConta());
		transacao.setDataTransacao(new Date(Calendar.getInstance().getTime().getTime()));
		transacao.setValor(requestBody.getValor());
		transacao.setTipoTransacao(TipoTransacao.SAQUE.getTipoTransacao());
		transacaoRepository.save(transacao);
		
		response.setMensagem("Saque efetuado com sucesso");
		response.setPayload(transacao);
		
		return response;
	}
	
	@Operation(summary = "Retorna o extrato de transacoes de uma conta")
	@GetMapping(path="/extrato")
	public @ResponseBody Response<List<Transacao>> extratoConta(@RequestParam Integer idConta, @RequestParam(required = false) String dataInicial, @RequestParam(required = false) String dataFinal) {
		Conta conta = new Conta();
		Response<List<Transacao>> response = new Response<List<Transacao>>();
		
		List<Transacao> transacoes = new ArrayList<Transacao>();
		if (!ObjectUtils.isEmpty(dataInicial) && !ObjectUtils.isEmpty(dataFinal)) {
			try {
				transacoes = transacaoRepository.findByIdConta(idConta, Utils.converterStringData(dataInicial) , Utils.converterStringData(dataFinal));
			} catch (ParseException e) {
				e.printStackTrace();
				response.setMensagem("Erro ao converter datas");
				return response;
			}
		} else {
			transacoes = transacaoRepository.findByIdConta(idConta);
		}
		
		response.setMensagem("Transacoes da conta");
		response.setPayload(transacoes);
		
		return response;
	}
}
