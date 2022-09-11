package br.com.mstec.quakus.ifood.cadastro.dto.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.mstec.quakus.ifood.cadastro.dto.DTO;

public class ValidDTOValidator implements ConstraintValidator<ValidDTO, DTO> {

	@Override
	public void initialize(ValidDTO constraintAnnotation) {
	}

	@Override
	public boolean isValid(DTO dto, ConstraintValidatorContext constraintValidatorContext) {
		return dto.isValid(constraintValidatorContext);
	}
}