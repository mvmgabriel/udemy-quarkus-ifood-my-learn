package br.com.mstec.quakus.ifood.cadastro.dto;

import java.math.BigDecimal;

public class PratoDto {

    public Long id;

    public String nome;
    
    public String descricao;

    public AdicionarRestauranteDto restaurante;
    
    public BigDecimal preco;
}
