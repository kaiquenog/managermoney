package com.br.managermoney.managermoney.resource;

import com.br.managermoney.managermoney.event.RecursoCriadoEvent;
import com.br.managermoney.managermoney.exceptionhandler.managerApiExceptionHandler;
import com.br.managermoney.managermoney.model.Lancamento;
import com.br.managermoney.managermoney.repository.LancamentosRepository;
import com.br.managermoney.managermoney.repository.filter.LancamentoFilter;
import com.br.managermoney.managermoney.service.LancamentoService;
import com.br.managermoney.managermoney.service.exception.CategoriaInexistenteException;
import com.br.managermoney.managermoney.service.exception.PessoaInexitenteOuInativaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentosResource {

    @Autowired
    private LancamentosRepository lancamentosRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public Page<Lancamento> listar(LancamentoFilter lancamentoFilter, Pageable pageable){
        System.out.println(Date.valueOf(lancamentoFilter.getDataVencimentoDe())+ " - " + lancamentoFilter.getDataVencimentoAte() );
        return lancamentosRepository.filtrar(lancamentoFilter, pageable);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer codigo){
        lancamentosRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarByCodigo(@PathVariable Integer codigo){
        Lancamento  lancamentos =  lancamentosRepository.getOne(codigo);
        return lancamentos == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(lancamentos);
    }

    @PostMapping
    public ResponseEntity<Lancamento> Criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){

        Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return  ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @ExceptionHandler({PessoaInexitenteOuInativaException.class})
    public ResponseEntity<Object> handlerPessoaInexitenteOuInativaException(PessoaInexitenteOuInativaException ex){
        String mensagemUsuario = messageSource.getMessage("pessoa.inesxistente-ou-inativa",null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<managerApiExceptionHandler.Erro> erros = Arrays.asList(new managerApiExceptionHandler.Erro(mensagemUsuario,mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);

    }

    @ExceptionHandler({CategoriaInexistenteException.class})
    public ResponseEntity<Object> handlerCategoriaInexistenteException(CategoriaInexistenteException ex){
        String mensagemUsuario = messageSource.getMessage("categoria.inesxistente",null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<managerApiExceptionHandler.Erro> erros = Arrays.asList(new managerApiExceptionHandler.Erro(mensagemUsuario,mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);

    }

}
