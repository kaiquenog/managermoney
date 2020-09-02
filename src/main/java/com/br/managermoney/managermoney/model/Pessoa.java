package com.br.managermoney.managermoney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="pessoa")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long codigo;

    @NotNull
    String nome;

    @NotNull
    Boolean ativo;

    @Embedded
    private  Endereco endereco;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
