package br.com.mstec.quakus.ifood.cadastro.dto;

import java.math.BigDecimal;

import br.com.mstec.quakus.ifood.cadastro.dto.valid.ValidDTO;

@ValidDTO
public class AdicionarPratoDto {

    public String nome;

    public String descricao;

    public BigDecimal preco;
}
