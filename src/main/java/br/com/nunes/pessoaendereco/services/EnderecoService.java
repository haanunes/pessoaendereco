/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nunes.pessoaendereco.services;

import br.com.nunes.pessoaendereco.entities.Endereco;
import br.com.nunes.pessoaendereco.repositories.EnderecoRepository;
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
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco save(Endereco obj) {
        return enderecoRepository.save(obj);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    @Transactional
    public void delete(Endereco obj) {
        enderecoRepository.delete(obj);
    }
}
