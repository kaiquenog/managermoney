package com.br.managermoney.managermoney.service;


import com.br.managermoney.managermoney.model.Pessoa;
import com.br.managermoney.managermoney.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa PessoaAtualizar(Long codigo, Pessoa pessoa){
        Pessoa pessoaSalva = getPessoaSalva(codigo, pessoa);
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
         Pessoa pessoaSalva = getPessoa(codigo);
         pessoaSalva.setAtivo(ativo);
         pessoaRepository.save(pessoaSalva);
    }

    private Pessoa getPessoaSalva(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = getPessoa(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaSalva;
    }

    public Pessoa getPessoa(Long codigo) {
        Pessoa pessoaSalva = pessoaRepository.findById(codigo).orElse(null);
        if (pessoaSalva == null){
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }
}

