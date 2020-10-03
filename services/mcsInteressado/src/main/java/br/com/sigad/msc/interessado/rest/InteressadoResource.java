package br.com.sigad.msc.interessado.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigad.msc.interessado.dto.InteressadoFiltroDTO;
import br.com.sigad.msc.interessado.dto.InteressadoRequestDTO;
import br.com.sigad.msc.interessado.dto.InteressadoResponseDTO;
import br.com.sigad.msc.interessado.entity.HistoricoInteressado;
import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.enums.StatusEnum;
import br.com.sigad.msc.interessado.enums.TipoDocumentoEnum;
import br.com.sigad.msc.interessado.exception.SigadException;
import br.com.sigad.msc.interessado.service.InteressadoService;
import br.com.sigad.msc.interessado.util.Constantes;
import br.com.sigad.msc.interessado.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Classe que disponibiliza os serviços para manter o interessado.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@RestController
@RequestMapping(value=Constantes.PATH_INTERESSADO, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = Constantes.PATH_INTERESSADO, produces = MediaType.APPLICATION_JSON_VALUE, tags = { Constantes.TAG_INTERESSADO })
public class InteressadoResource {

	@Autowired
	private InteressadoService service;

	/**
	 * Método REST que cria um interessado.
	 * @param interessadoRequestDTO - interessado
	 * @return ResponseEntity<?> - interessado criado ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws SigadException 
	 * @since 2 de out. de 2020
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = Constantes.CREATE_INTERESSADO, 
	notes = Constantes.CREATE_INTERESSADO_NOTES, response = InteressadoResponseDTO.class)
	public @ResponseBody ResponseEntity<?> create(
			@Valid @RequestBody InteressadoRequestDTO interessadoRequestDTO) throws SigadException {

		Interessado interessado = Util.convertModelMapper(interessadoRequestDTO, Interessado.class);
		interessado.setTipoDocumento(TipoDocumentoEnum.obterTipoDocumento(interessadoRequestDTO.getTipoDocumento()));

		interessado = this.service.create(interessado);
		InteressadoResponseDTO response = Util.convertModelMapper(interessado, InteressadoResponseDTO.class);

		return new ResponseEntity<InteressadoResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * Método REST que altera um interessado.
	 * @param id - id do interessado
	 * @param interessadoRequestDTO - interessado
	 * @return ResponseEntity<?> - interessado alterado ou código de erro HTTP
	 * @throws SigadException 
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 2 de out. de 2020
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = Constantes.UPDATE_INTERESSADO, 
	notes = Constantes.UPDATE_INTERESSADO_NOTES, response = InteressadoResponseDTO.class)
	public @ResponseBody ResponseEntity<?> update(
			@PathVariable("id") Long id,
			@Valid @RequestBody InteressadoRequestDTO interessadoRequestDTO) throws SigadException {

		Interessado interessado = Util.convertModelMapper(interessadoRequestDTO, Interessado.class);
		interessado.setId(id);

		this.service.update(interessado);
		
		InteressadoResponseDTO response = Util.convertModelMapper(interessado, InteressadoResponseDTO.class);

		return new ResponseEntity<InteressadoResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * Método REST que recupera um interessado.
	 * @param id - id do interessado
	 * @return ResponseEntity<?> - interessado recuperado ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws SigadException 
	 * @since 2 de out. de 2020
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = Constantes.RETRIEVE_INTERESSADO, 
	notes = Constantes.RETRIEVE_INTERESSADO_NOTES, response = InteressadoResponseDTO.class)
	public @ResponseBody ResponseEntity<?> retrieve(
			@PathVariable("id") Long id) throws SigadException {

		Interessado interessado = this.service.retrieve(id);
		InteressadoResponseDTO response = Util.convertModelMapper(interessado, InteressadoResponseDTO.class);

		return new ResponseEntity<InteressadoResponseDTO>(response, HttpStatus.OK);
	}
	
	/**
	 * Método REST que recupera um interessado por CPF/CNPJ.
	 * @param cpfCnpj - CPF/CNPJ do interessado
	 * @return ResponseEntity<?> - interessado recuperado ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws SigadException 
	 * @since 2 de out. de 2020
	 */
	@RequestMapping(value = "/documento/{cpfCnpj}", method = RequestMethod.GET)
	@ApiOperation(value = Constantes.RETRIEVE_INTERESSADO, 
	notes = Constantes.RETRIEVE_INTERESSADO_NOTES, response = InteressadoResponseDTO.class)
	public @ResponseBody ResponseEntity<?> retrieve(
			@PathVariable("cpfCnpj") String cpfCnpj) throws SigadException {

		Interessado interessado = this.service.retrieve(cpfCnpj);
		InteressadoResponseDTO response = Util.convertModelMapper(interessado, InteressadoResponseDTO.class);

		return new ResponseEntity<InteressadoResponseDTO>(response, HttpStatus.OK);
	}

	/**
	 * Método REST que deleta um interessado.
	 * @param id - id do interessado
	 * @return ResponseEntity<?> - interessado deletado ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws SigadException 
	 * @since 2 de out. de 2020
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = Constantes.DELETE_INTERESSADO, 
	notes = Constantes.DELETE_INTERESSADO_NOTES)
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") Long id) throws SigadException {

		Interessado interessado = Interessado.builder().id(id).build();
		this.service.delete(interessado);

		return new ResponseEntity<InteressadoResponseDTO>(HttpStatus.OK);
	}

	/**
	 * Método REST que lista os interessados de acordo com os filtros informados.
	 * @param interessadoFiltroDTO - filtros do interessado
	 * @return ResponseEntity<?> - lista de interessados ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws SigadException 
	 * @since 2 de out. de 2020
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = Constantes.LIST_INTERESSADO, 
	notes = Constantes.LIST_INTERESSADO_NOTES, response = InteressadoResponseDTO.class)
	public @ResponseBody ResponseEntity<Page<?>> list(
			@PageableDefault(value = 30, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
			@RequestBody InteressadoFiltroDTO interessadoFiltroDTO) throws SigadException {

		Interessado interessado = Util.convertModelMapper(interessadoFiltroDTO, Interessado.class);
		interessado.setTipoDocumento(TipoDocumentoEnum.obterTipoDocumento(interessadoFiltroDTO.getTipoDocumento()));
		interessado.setSituacao(StatusEnum.obterStatus(interessadoFiltroDTO.getSituacao()));

		Page<Interessado> response = this.service.list(interessado, pageable);
		
		//FIXME - transformar para um Page de DTO

		return ResponseEntity.ok(response);
	}
	
	/**
	 * Método REST que lista o histórico do interessado.
	 * @param id - id do interessado
	 * @return ResponseEntity<?> - lista de histórico ou código de erro HTTP
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @throws SigadException 
	 * @since 2 de out. de 2020
	 */
	@RequestMapping(value = "/{id}/historico", method = RequestMethod.GET)
	@ApiOperation(value = Constantes.LIST_INTERESSADO, 
	notes = Constantes.LIST_INTERESSADO_NOTES, response = HistoricoInteressado.class)
	public @ResponseBody ResponseEntity<Page<?>> listHistoric(
			@PageableDefault(value = 30, sort = {"id"}, direction = Sort.Direction.ASC) 
			Pageable pageable, @PathVariable("id") Long id) throws SigadException {

		Interessado interessado = Interessado.builder().id(id).build();

		Page<HistoricoInteressado> response = this.service.listHistoric(interessado, pageable);
		
		//FIXME - transformar para um Page de DTO

		return ResponseEntity.ok(response);
	}

}
