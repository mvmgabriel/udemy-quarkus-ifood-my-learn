package br.com.mstec.quakus.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.mstec.quakus.ifood.cadastro.dto.AdicionarPratoDto;
import br.com.mstec.quakus.ifood.cadastro.dto.AlterarPratoDto;
import br.com.mstec.quakus.ifood.cadastro.dto.PratoDto;
import br.com.mstec.quakus.ifood.cadastro.entity.Prato;


@Mapper(componentModel = "cdi")
public interface PratoMapper {

    PratoDto toDTO(Prato p);

    Prato toPrato(AdicionarPratoDto dto);

    void toPrato(AlterarPratoDto dto, @MappingTarget Prato prato);

}
