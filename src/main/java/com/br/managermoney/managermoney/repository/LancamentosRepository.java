package com.br.managermoney.managermoney.repository;

import com.br.managermoney.managermoney.model.Lancamento;
import com.br.managermoney.managermoney.repository.lacamento.LancamentosRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentosRepository  extends JpaRepository<Lancamento, Integer>, LancamentosRepositoryQuery {

}
