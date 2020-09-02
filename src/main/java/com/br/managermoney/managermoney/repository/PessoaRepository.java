package com.br.managermoney.managermoney.repository;

import com.br.managermoney.managermoney.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
