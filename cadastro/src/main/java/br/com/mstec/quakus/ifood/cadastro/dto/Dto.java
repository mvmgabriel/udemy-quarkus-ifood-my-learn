package br.com.mstec.quakus.ifood.cadastro.dto;

import javax.validation.ConstraintValidatorContext;

public interface Dto {
	default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		return true;
	}
}
