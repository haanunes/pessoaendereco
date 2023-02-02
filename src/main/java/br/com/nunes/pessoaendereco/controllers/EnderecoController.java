/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nunes.pessoaendereco.controllers;

import br.com.nunes.pessoaendereco.dto.EnderecoDTO;
import br.com.nunes.pessoaendereco.entities.Endereco;
import br.com.nunes.pessoaendereco.entities.Pessoa;
import br.com.nunes.pessoaendereco.repositories.EnderecoRepository;
import br.com.nunes.pessoaendereco.services.EnderecoService;
import br.com.nunes.pessoaendereco.services.PessoaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hélder
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;
    @Autowired
    PessoaService pessoaService;
    @Autowired
    EnderecoRepository repository;
    
    @PostMapping("/{id}")
    public ResponseEntity<Object> save(@RequestBody @Valid EnderecoDTO objDTO, @PathVariable(value = "id") Long id) {
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (!pessoaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa, dona do endereço, não foi encontrado.");
        }
        var obj = new Endereco();
        BeanUtils.copyProperties(objDTO, obj);
        obj.setPessoa(pessoaOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(obj));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id) {
        Optional<Endereco> enderecoOptional = enderecoService.findById(id);
        if (!enderecoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional.get());
    }
    
    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Object> getEnderecosByIdPessoa(@PathVariable(value = "id") Long id) {
        List<Endereco> enderecos =  repository.findByPessoaId(id);
        if (enderecos.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecos);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        Optional<Endereco> objOptional = enderecoService.findById(id);
        if (!objOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        enderecoService.delete(objOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Endereço removido com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid EnderecoDTO objDTO) {
        Optional<Endereco> objOptional = enderecoService.findById(id);
        if (!objOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        var obj = objOptional.get();
        BeanUtils.copyProperties(objDTO, obj);
        obj.setId(objOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(obj));
    }
    @PutMapping("/principal/{idEndereco}/pessoa/{idPessoa}")
    public ResponseEntity<Object> updateEnderecoPrincipal(@PathVariable(value = "idEndereco") Long idEndereco, @PathVariable(value = "idPessoa") Long idPessoa ) {
        Optional<Endereco> objEnderecoOptional = enderecoService.findById(idEndereco);
        if (!objEnderecoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
       Optional<Pessoa> objPessoaOptional = pessoaService.findById(idPessoa);
        if (!objPessoaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        objEnderecoOptional.get().setPrincipal(true);
        List<Endereco> listaEndereco = repository.findByPessoaId(idPessoa);
        if (listaEndereco.size()>0){
            for (Endereco e :listaEndereco){
                if (e.isPrincipal()){
                    e.setPrincipal(false);
                    enderecoService.save(e);
                }
            }
        }
        objEnderecoOptional.get().setPrincipal(true);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(objEnderecoOptional.get()));
    }

}
