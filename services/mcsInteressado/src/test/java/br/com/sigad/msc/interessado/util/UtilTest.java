package br.com.sigad.msc.interessado.util;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.sigad.msc.interessado.EntityGenericUtil;
import br.com.sigad.msc.interessado.dto.InteressadoRequestDTO;
import br.com.sigad.msc.interessado.entity.Interessado;

/**
 * Classe de teste que representa os cenários de testes da classe {@link Util}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@SuppressWarnings("static-access")
public class UtilTest {

	@InjectMocks
	private Util util;
	
	private InteressadoRequestDTO source;
	
	@BeforeEach
	public void setup() {

		this.source = new InteressadoRequestDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString(), 
				EntityGenericUtil.getString(), EntityGenericUtil.getString());
	}

	@Test
	public void convertModelMapperTest() {

		Interessado interessado = util.convertModelMapper(source, Interessado.class);

		InteressadoRequestDTO interessadoDTO = util.convertModelMapper(interessado, 
				InteressadoRequestDTO.class);

		assertNotNull(interessado);
		assertNotNull(interessadoDTO);
	}

	@Test
	public void convertModelMapperSourceNullTest() {

		Interessado interessado = util.convertModelMapper(null, Interessado.class);

		assertNull(interessado);
	}

	@Test
	public void convertModelMapperDestinationNullTest() {

		Interessado interessado = util.convertModelMapper(source, null);

		assertNull(interessado);
	}
	
	@Test
	public void convertModelMapperToListTest() {
		
		List<InteressadoRequestDTO> sourceList = 
				new LinkedList<InteressadoRequestDTO>();
		
		sourceList.add(this.source);
		
		List<Interessado> listaInteressado = util.convertModelMapperToList(sourceList, 
				Interessado.class);
		
		List<InteressadoRequestDTO> listaInteressadoDTO = util.convertModelMapperToList(listaInteressado, 
				InteressadoRequestDTO.class);
		
		
		assertNotNull(listaInteressado);
		assertNotNull(listaInteressadoDTO);
	}
	
	@Test
	public void convertModelMapperToListSourceNullTest() {
		
		List<Interessado> listaInteressado = util.convertModelMapperToList(null, 
				Interessado.class);
		
		assertNotNull(listaInteressado);
		assertEquals(0, listaInteressado.size());
	}
	
	@Test
	public void convertModelMapperToListDestinationNullTest() {
		
		List<InteressadoRequestDTO> sourceList = 
				new LinkedList<InteressadoRequestDTO>();
		
		sourceList.add(this.source);
		
		List<Interessado> listaInteressado = util.convertModelMapperToList(sourceList, 
				null);
		
		assertNotNull(listaInteressado);
		assertEquals(0, listaInteressado.size());
	}
	
	@Test
	public void convertModelMapperToPageTest() {
		
		List<InteressadoRequestDTO> sourceList = 
				new LinkedList<InteressadoRequestDTO>();
		
		sourceList.add(this.source);
		
		Page<InteressadoRequestDTO> pageSource = new PageImpl<InteressadoRequestDTO>(
				sourceList, Pageable.unpaged(), sourceList.size());
		
		Page<Interessado> pageResponse = util.convertModelMapperToPage(pageSource, 
				Interessado.class);
		
		assertNotNull(pageResponse);
		assertEquals(1, pageResponse.getContent().size());
	}
	
	@Test
	public void convertModelMapperToPageSourceNullTest() {
		
		Page<Interessado> pageResponse = util.convertModelMapperToPage(null, 
				Interessado.class);
		
		assertNull(pageResponse);
	}
	
	@Test
	public void convertModelMapperToPageDestinationNullTest() {
		
		List<InteressadoRequestDTO> sourceList = 
				new LinkedList<InteressadoRequestDTO>();
		
		sourceList.add(this.source);
		
		Page<InteressadoRequestDTO> pageSource = new PageImpl<InteressadoRequestDTO>(
				sourceList, Pageable.unpaged(), sourceList.size());
		
		Page<Interessado> pageResponse = util.convertModelMapperToPage(pageSource, 
				null);
		
		assertNull(pageResponse);
	}
	
	@Test
	public void telefoneValidoTest() {
		
		assertTrue(util.telefoneIsValido("83999887777"));
	}
	
	@Test
	public void telefoneInValidoTest() {
		
		assertFalse(util.telefoneIsValido("839998877776666"));
	}
	

	@Test
	public void telefoneNullTest() {
		
		assertFalse(util.telefoneIsValido(null));
	}
	
	@Test
	public void telefoneVazioTest() {
		
		assertFalse(util.telefoneIsValido(""));
	}
	
	@Test
	public void telefoneComCaracteresTest() {
		
		assertFalse(util.telefoneIsValido("a83999887777"));
	}

}
