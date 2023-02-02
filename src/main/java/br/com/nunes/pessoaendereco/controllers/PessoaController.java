/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nunes.pessoaendereco.controllers;

import br.com.nunes.pessoaendereco.dto.PessoaDTO;
import br.com.nunes.pessoaendereco.entities.Endereco;
import br.com.nunes.pessoaendereco.entities.Pessoa;
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
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;
    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PessoaDTO objDTO) {
        var obj = new Pessoa();

        obj.setDataNascimento(objDTO.getDataNascimento());
        obj.setNome(objDTO.getNome());
        Pessoa pessoa = pessoaService.save(obj);
        if (objDTO.getEnderecos()!=null && objDTO.getEnderecos().size() > 0) {
            for (Endereco e : objDTO.getEnderecos()) {
                e.setPessoa(pessoa);
                enderecoService.save(e);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(obj));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") Long id) {
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (!pessoaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        Optional<Pessoa> objOptional = pessoaService.findById(id);
        if (!objOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        pessoaService.delete(objOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Endereço removido com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid PessoaDTO objDTO) {
        Optional<Pessoa> objOptional = pessoaService.findById(id);
        if (!objOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        var obj = objOptional.get();
        BeanUtils.copyProperties(objDTO, obj);
        obj.setId(objOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(obj));
    }
}
