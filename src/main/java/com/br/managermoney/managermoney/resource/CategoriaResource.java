package com.br.managermoney.managermoney.resource;

import com.br.managermoney.managermoney.event.RecursoCriadoEvent;
import com.br.managermoney.managermoney.model.Categoria;
import com.br.managermoney.managermoney.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody  Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarByCodigo(@PathVariable Long codigo){
        Optional<Categoria> categoria =  categoriaRepository.findById(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()): ResponseEntity.notFound().build();



    }

}
