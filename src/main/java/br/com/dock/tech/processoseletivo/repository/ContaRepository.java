package br.com.dock.tech.processoseletivo.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.dock.tech.processoseletivo.models.entity.Conta;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

}
