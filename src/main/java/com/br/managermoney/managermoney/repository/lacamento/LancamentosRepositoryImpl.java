package com.br.managermoney.managermoney.repository.lacamento;


import com.br.managermoney.managermoney.model.Lancamento;
import com.br.managermoney.managermoney.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentosRepositoryImpl implements LancamentosRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);
        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        adicionarRestricaoDePaginacao(query, pageable );

        return new PageImpl<>(query.getResultList(),pageable, total(lancamentoFilter));
    }

    private Long total(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricaoDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentofilter, CriteriaBuilder builder,
                                        Root<Lancamento> root){

        List<Predicate> predicates = new ArrayList<>();
        if(lancamentofilter.getDescricao() != null ){
            predicates.add(builder.like(
                    builder.lower
                            (root.get("descricao")), "%" + lancamentofilter.getDescricao().toLowerCase()+"%" ));

        }
        if(lancamentofilter.getDataVencimentoAte() != null ){
            predicates.add(builder.
                    greaterThanOrEqualTo(root.get("dataVencimento"),lancamentofilter.getDataVencimentoAte()));
        }
        if(lancamentofilter.getDataVencimentoDe() != null ){
            predicates.add(builder.
                    lessThanOrEqualTo(root.get("dataVencimento"),lancamentofilter.getDataVencimentoDe()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}

