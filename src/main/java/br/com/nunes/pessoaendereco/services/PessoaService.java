/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nunes.pessoaendereco.services;

import br.com.nunes.pessoaendereco.entities.Pessoa;
import br.com.nunes.pessoaendereco.entities.Pessoa;
import br.com.nunes.pessoaendereco.repositories.PessoaRepository;
import br.com.nunes.pessoaendereco.repositories.PessoaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hélder
 */
@Service
public class PessoaService {
     @Autowired
    PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa save(Pessoa obj) {
        return pessoaRepository.save(obj);
    }
/*Caso queira paginar
    public Page<Pessoa> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }
*/
     public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }


    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }
    @Transactional
    public void delete(Pessoa obj){
         pessoaRepository.delete(obj);
    }
}
