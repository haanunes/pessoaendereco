/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nunes.pessoaendereco.dto;

import br.com.nunes.pessoaendereco.entities.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hélder
 */
@Getter
@Setter
public class EnderecoDTO {

    @NotBlank
    private String logradouro;
    @NotBlank
    @Size(max = 9)
    private String CEP;
    @NotBlank
    private String numero; //pode existir endereço sem número "S/N"
    @NotBlank
    private String cidade;
    @NotBlank
    private String UF;
}
