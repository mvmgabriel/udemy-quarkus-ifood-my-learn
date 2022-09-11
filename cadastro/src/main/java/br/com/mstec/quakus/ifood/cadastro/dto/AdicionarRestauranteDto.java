package br.com.mstec.quakus.ifood.cadastro.dto;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.mstec.quakus.ifood.cadastro.dto.valid.ValidDTO;
import br.com.mstec.quakus.ifood.cadastro.entity.Restaurante;

@ValidDTO
public class AdicionarRestauranteDto implements DTO {

	@NotEmpty
	@NotNull
    public String proprietario;
    
	@Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
	@NotNull
    public String cnpj;
    
    @Size(min = 3, max = 30, message = "O tamanho do nome deve conter no mínimo 3 e no máximo 30 caracteres")
    public String nome;
    
    public LocalizacaoDto localizacao;

    @Override
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Restaurante.find("cnpj", cnpj).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
                    .addPropertyNode("cnpj")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
    
}
