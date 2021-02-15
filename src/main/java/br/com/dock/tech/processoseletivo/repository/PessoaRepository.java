package br.com.dock.tech.processoseletivo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.dock.tech.processoseletivo.models.entity.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

}
