package br.com.mstec.quakus.ifood.cadastro;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.mstec.quakus.ifood.cadastro.dto.AdicionarRestauranteDto;
import br.com.mstec.quakus.ifood.cadastro.dto.AlterarRestauranteDto;
import br.com.mstec.quakus.ifood.cadastro.dto.RestauranteDto;
import br.com.mstec.quakus.ifood.cadastro.entity.Prato;
import br.com.mstec.quakus.ifood.cadastro.entity.Restaurante;
import br.com.mstec.quakus.ifood.cadastro.mapper.RestauranteMapper;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

	@Inject
	private RestauranteMapper restauranteMapper;
	
    @GET
    @Tag(name = "/restaurantes")
    public List<RestauranteDto> buscarTodosRestaurantes() {
    	List<Restaurante> list = Restaurante.findAll().list(); 
        return list.stream()
			.map(r -> restauranteMapper.toRestauranteDto(r))
			.collect(Collectors.toList());
    }
    
    @POST
    @Tag(name = "/restaurantes")
    @Transactional
    public Response salvarRestaurante(@Valid AdicionarRestauranteDto adicionarRestauranteDto) {
    	Restaurante restaurante = restauranteMapper.toRestaurante(adicionarRestauranteDto);
    	restaurante.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{idRestaurante}")
    @Tag(name = "/restaurantes")
    @Transactional
    public void alterarRestaurante(@PathParam("idRestaurante") Long idRestaurante, AlterarRestauranteDto alterarRestauranteDto) {
    	Optional<Restaurante> restauranteOp = getRestaurante(idRestaurante);
    	Restaurante restaurante = restauranteOp.get();
    	restauranteMapper.toRestaurante(alterarRestauranteDto, restaurante);
    	restaurante.persist();
    }
    
    @DELETE
    @Path("{idRestaurante}")
    @Tag(name = "/restaurantes")
    @Transactional
    public void excluirRestaurante(@PathParam("idRestaurante") Long idRestaurante) {
    	Optional<Restaurante> restauranteOp = getRestaurante(idRestaurante);
    	restauranteOp.ifPresentOrElse(
			Restaurante::delete, 
			() -> { 
				throw new NotFoundException("Restaurante não existe!"); 
			});
    }
    
    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "/pratos")
    public List<Prato> buscarTodosPratos(@PathParam("idRestaurante") Long idRestaurante) {
    	Optional<Restaurante> restauranteOp = getRestaurante(idRestaurante);
    	return Prato.list("restaurante", restauranteOp.get());
    }
    
    @POST
    @Path("{idRestaurante}/pratos")
    @Tag(name = "/pratos")
    @Transactional
    public Response salvarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato pratoDto) {
    	Optional<Restaurante> restauranteOp = getRestaurante(idRestaurante);
    	
    	Prato prato = new Prato();
    	prato.nome = pratoDto.nome;
    	prato.descricao = pratoDto.descricao;
    	prato.preco = pratoDto.preco;
    	prato.restaurante = restauranteOp.get();
    	
    	prato.persist();
    	
    	return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{idPrato}")
    @Tag(name = "/pratos")
    @Transactional
    public void alterarPrato(@PathParam("idRestaurante") Long idRestaurante, 
    		@PathParam("idPrato") Long idPrato, Prato pratoDto) {
    	getRestaurante(idRestaurante);
    	
    	Optional<Prato> pratoOp = getPrato(idPrato);
    	Prato prato = pratoOp.get();
    	
    	prato.preco = pratoDto.preco;
    	prato.persist();
    }
    
    @DELETE
    @Path("{idRestaurante}/pratos/{idPrato}")
    @Tag(name = "/pratos")
    @Transactional
    public void excluirPrato(@PathParam("idRestaurante") Long idRestaurante,
    		@PathParam("idPrato") Long idPrato) {
    	getRestaurante(idRestaurante);
    	
    	Optional<Prato> pratoOp = getPrato(idPrato);
    	pratoOp.ifPresentOrElse(
    			Prato::delete, 
			() -> { 
				throw new NotFoundException("Prato não existe!"); 
			});
    }

	private Optional<Restaurante> getRestaurante(Long id) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe!");
    	}
		return restauranteOp;
	}
	
	private Optional<Prato> getPrato(Long id) {
		Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException("Prato não existe!");
    	}
		return pratoOp;
	}
}