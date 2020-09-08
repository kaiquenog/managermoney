package com.br.managermoney.managermoney.repository.filter;


import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String dataVencimentoDe;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String dataVencimentoAte;

    private String descricao;

    public String getDataVencimentoDe() {
        return dataVencimentoDe;
    }

    public void setDataVencimentoDe(String dataVencimentoDe) {
        this.dataVencimentoDe = dataVencimentoDe;
    }

    public String getDataVencimentoAte() {
        return dataVencimentoAte;
    }

    public void setDataVencimentoAte(String dataVencimentoAte) {
        this.dataVencimentoAte = dataVencimentoAte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
