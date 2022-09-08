package br.com.mstec.quakus.ifood.cadastro.dto;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AdicionarRestauranteDto implements Dto {

	@NotEmpty
	@NotNull
    public String proprietario;
    
	@Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
	@NotNull
    public String cnpj;
    
    @Size(min = 3, max = 30)
    public String nome;
    
    public LocalizacaoDto localizacao;

	@Override
	public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		// TODO Auto-generated method stub
		return Dto.super.isValid(constraintValidatorContext);
	}
    
}
