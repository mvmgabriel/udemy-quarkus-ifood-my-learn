package br.com.mstec.quakus.ifood.cadastro.dto;

import br.com.mstec.quakus.ifood.cadastro.dto.valid.ValidDTO;

@ValidDTO
public class RestauranteDto {

	public Long id;

    public String proprietario;
    
    public String cnpj;
    
    public String nome;
    
    public LocalizacaoDto localizacao;

    public String dataCriacao;
    
    public String dataAtualizacao;
	    
}
