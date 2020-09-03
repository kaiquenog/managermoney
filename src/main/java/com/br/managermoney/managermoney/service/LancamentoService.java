package com.br.managermoney.managermoney.service;

import com.br.managermoney.managermoney.model.Lancamento;
import com.br.managermoney.managermoney.model.Pessoa;
import com.br.managermoney.managermoney.repository.LancamentosRepository;
import com.br.managermoney.managermoney.repository.PessoaRepository;
import com.br.managermoney.managermoney.service.exception.PessoaInexitenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentosRepository lancamentosRepository;

    public Lancamento salvar(Lancamento lancamento){
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);;
        if(pessoa == null || !pessoa.getAtivo()){
            throw  new PessoaInexitenteOuInativaException();
        }
        return lancamentosRepository.save(lancamento);
    }
}
