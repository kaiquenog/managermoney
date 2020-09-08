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
    PessoaRepository pessoaRepository;

    public Pessoa pessoaAtualizar(Long codigo, Pessoa pessoa){
        Pessoa pessoaSalva = getPessoa(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoa);
    }

    public void atualizarStatus(Long codigo, Boolean ativo){
        Pessoa pessoaSalva = getPessoa(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    public Pessoa getPessoa(Long codigo) {
        Pessoa pessoaSalva = pessoaRepository.findById(codigo).orElse(null);
        if (pessoaSalva == null){
            throw new EmptyResultDataAccessException(1);

        }
        return pessoaSalva;
    }




}

