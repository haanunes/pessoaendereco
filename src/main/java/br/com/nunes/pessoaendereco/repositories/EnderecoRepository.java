/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.nunes.pessoaendereco.repositories;

import br.com.nunes.pessoaendereco.entities.Endereco;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hélder
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    List<Endereco> findByPessoaId(Long id);
}
