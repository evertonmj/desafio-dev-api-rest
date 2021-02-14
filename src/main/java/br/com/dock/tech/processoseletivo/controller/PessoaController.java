package br.com.dock.tech.processoseletivo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.dock.tech.processoseletivo.models.entity.Pessoa;
import br.com.dock.tech.processoseletivo.models.request.PessoaRequest;
import br.com.dock.tech.processoseletivo.models.response.Response;
import br.com.dock.tech.processoseletivo.repository.PessoaRepository;
import br.com.dock.tech.processoseletivo.utils.Utils;

@Controller
@RequestMapping(path="/pessoa")
public class PessoaController {
	@Autowired
	PessoaRepository pessoaRepository;
	
	@PostMapping(path="/criar")
	public @ResponseBody Response<Pessoa> criarPessoa(@RequestBody PessoaRequest requestBody) {
		Pessoa pessoa = new Pessoa();
		Response<Pessoa> response = new Response<Pessoa>();
		try {
			pessoa.setCpf(requestBody.getCpf());
			pessoa.setNome(requestBody.getNome());
			pessoa.setDataNascimento(Utils.converterStringData(requestBody.getDataNascimento()));
			pessoaRepository.save(pessoa);
			response.setMensagem("Pessoa criada com sucesso!");
			response.setPayload(pessoa);
		} catch (ParseException e) {
			e.printStackTrace();
			response.setMensagem("Erro ao formatar data de aniverario: " + e.getMessage());
			response.setPayload(pessoa);
			return response;
		}
		
		return response;
	}
}
