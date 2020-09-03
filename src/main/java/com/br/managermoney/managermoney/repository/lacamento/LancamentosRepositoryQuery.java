package com.br.managermoney.managermoney.repository.lacamento;


import com.br.managermoney.managermoney.model.Lancamento;
import com.br.managermoney.managermoney.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentosRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
