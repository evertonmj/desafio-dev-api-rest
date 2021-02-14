package br.com.dock.tech.processoseletivo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.dock.tech.processoseletivo.models.entity.Transacao;

public interface TransacaoRepository extends CrudRepository<Transacao, Integer> {
	
	//obtem todas as transacoes de saque de uma conta no dia atual
	@Query("SELECT tr FROM Transacao tr WHERE tr.idConta = :idConta AND tr.dataTransacao = CURDATE() AND tr.tipoTransacao = 2")
	List<Transacao> findByIdContaSaquesDia(@Param("idConta") Integer idConta);
}
