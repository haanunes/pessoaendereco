/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nunes.pessoaendereco.dto;

import br.com.nunes.pessoaendereco.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hélder
 */
@Getter
@Setter
public class PessoaDTO {
    @NotBlank
    private String nome;
    @Past
    private LocalDate dataNascimento;
     private List<Endereco> enderecos;
     private boolean principal;
}
