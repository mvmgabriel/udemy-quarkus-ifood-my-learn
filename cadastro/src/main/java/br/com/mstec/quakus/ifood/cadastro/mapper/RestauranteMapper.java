package br.com.mstec.quakus.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.mstec.quakus.ifood.cadastro.dto.AdicionarRestauranteDto;
import br.com.mstec.quakus.ifood.cadastro.dto.AlterarRestauranteDto;
import br.com.mstec.quakus.ifood.cadastro.dto.RestauranteDto;
import br.com.mstec.quakus.ifood.cadastro.entity.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
	
	@Mapping(source = "dataCriacao", target = "dataCriacao", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "dataAtualizacao", target = "dataAtualizacao", dateFormat = "dd/MM/yyyy")
	RestauranteDto toRestauranteDto(Restaurante restaurante);
	
	Restaurante toRestaurante(AdicionarRestauranteDto adicionarRestauranteDto);
	
	void toRestaurante(AlterarRestauranteDto alterarRestauranteDto, @MappingTarget Restaurante restaurante);

}
