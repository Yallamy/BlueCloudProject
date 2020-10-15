package br.com.sigad.msc.interessado.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.sigad.msc.interessado.EntityGenericUtil;
import br.com.sigad.msc.interessado.MCSInteressado;
import br.com.sigad.msc.interessado.dto.InteressadoFiltroDTO;
import br.com.sigad.msc.interessado.dto.InteressadoRequestDTO;
import br.com.sigad.msc.interessado.enums.StatusEnum;
import br.com.sigad.msc.interessado.enums.TipoDocumentoEnum;
import br.com.sigad.msc.interessado.util.Constantes;

/**
 * Classe de teste que representa os cenários de testes da classe {@link InteressadoResource}
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@SpringBootTest(classes = MCSInteressado.class)
public class InteressadoResourceTest {

	private MockMvc mockMvc;

	@Autowired
	private InteressadoResource interessadoResource;

	@Autowired
	private Gson gson;

	private String urlBase = Constantes.PATH_INTERESSADO + "/";

	private InteressadoRequestDTO request;

	@BeforeEach
	public void setup() {

		this.mockMvc = MockMvcBuilders.standaloneSetup(interessadoResource)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();

		this.request = new InteressadoRequestDTO(EntityGenericUtil.getString(), 
				EntityGenericUtil.getCPF(), TipoDocumentoEnum.CPF.getTipoDocumento(),
				EntityGenericUtil.getEmail(), EntityGenericUtil.getTelefone());

		this.gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	}

	//create
	@Test
	public void createTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void createComCNPJTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getCNPJ());
		this.request.setTipoDocumento(TipoDocumentoEnum.CNPJ.getTipoDocumento());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void createJaCadastradoTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void createSemNomeTest() throws Exception {

		this.request.setNome(null);

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void createSemCpfTest() throws Exception {

		this.request.setCpfCnpj(null);

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void createCpfInvalidoTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void createSemCNPJTest() throws Exception {

		this.request.setCpfCnpj(null);
		this.request.setTipoDocumento(TipoDocumentoEnum.CNPJ.getTipoDocumento());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void createCNPJInvalidoTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getString());
		this.request.setTipoDocumento(TipoDocumentoEnum.CNPJ.getTipoDocumento());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void createTipoDocumentoInvalidoTest() throws Exception {

		this.request.setTipoDocumento(null);

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void createEmailInvalidoTest() throws Exception {

		this.request.setEmail(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void createTelefoneInvalidoTest() throws Exception {

		this.request.setTelefone(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	//update
	@Test
	public void updateTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getCPF());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.request.setNome(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void updateCNPJTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getCNPJ());
		this.request.setTipoDocumento(TipoDocumentoEnum.CNPJ.getTipoDocumento());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.request.setNome(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void updateJaCadastradoTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getCPF());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		this.request.setCpfCnpj(EntityGenericUtil.getCPF());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.request.setNome(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 2)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

	@Test
	public void updateNotFoundTest() throws Exception {

		this.request.setNome(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 15)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void updateSemNomeTest() throws Exception {

		this.request.setNome(null);

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void updateSemCpfTest() throws Exception {

		this.request.setCpfCnpj(null);

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void updateCpfInvalidoTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updateSemCNPJTest() throws Exception {

		this.request.setCpfCnpj(null);
		this.request.setTipoDocumento(TipoDocumentoEnum.CNPJ.getTipoDocumento());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void updateCNPJInvalidoTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getString());
		this.request.setTipoDocumento(TipoDocumentoEnum.CNPJ.getTipoDocumento());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updateTipoDocumentoInvalidoTest() throws Exception {

		this.request.setTipoDocumento(null);

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void updateEmailInvalidoTest() throws Exception {

		this.request.setEmail(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void updateTelefoneInvalidoTest() throws Exception {

		this.request.setTelefone(EntityGenericUtil.getString());

		this.mockMvc.perform(MockMvcRequestBuilders.put(this.urlBase + 1)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	//retrieve
	@Test
	public void retrieveTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getCPF());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + 1)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void retrieveNotFoundTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + 15)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	//retrieve por CPF/CNPJ
	@Test
	public void retrieveByCpfCnpjTest() throws Exception {

		this.request.setCpfCnpj(EntityGenericUtil.getCPF());

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + "documento/"+ this.request.getCpfCnpj())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void retrieveByCpfCnpjNotFoundTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + "documento/"+ EntityGenericUtil.getCNPJ())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	//delete
	@Test
	public void deleteTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete(this.urlBase + 1)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void deletePessoaNullTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete(this.urlBase + 15)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	//list
	@Test
	public void listTest() throws Exception {

		InteressadoFiltroDTO filtro = new InteressadoFiltroDTO();

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void listPorNomeTest() throws Exception {

		InteressadoFiltroDTO filtro = new InteressadoFiltroDTO();
		filtro.setNome(request.getNome());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorCpfCnpjTest() throws Exception {

		InteressadoFiltroDTO filtro = new InteressadoFiltroDTO();
		filtro.setCpfCnpj(request.getCpfCnpj());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorTipoDocumentoTest() throws Exception {

		InteressadoFiltroDTO filtro = new InteressadoFiltroDTO();
		filtro.setTipoDocumento(TipoDocumentoEnum.CPF.getTipoDocumento());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void listPorEmailTest() throws Exception {

		InteressadoFiltroDTO filtro = new InteressadoFiltroDTO();
		filtro.setEmail(request.getEmail());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorTelefoneTest() throws Exception {

		InteressadoFiltroDTO filtro = new InteressadoFiltroDTO();
		filtro.setTelefone(request.getTelefone());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listPorSituacaoTest() throws Exception {

		InteressadoFiltroDTO filtro = new InteressadoFiltroDTO();
		filtro.setSituacao(StatusEnum.ATIVO.getStatus());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase)
				.content(this.gson.toJson(filtro))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	//list historic
	@Test
	public void listHistoricTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post(this.urlBase)
				.content(gson.toJson(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + 1 + "/historico")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void listHistoricNotFoundTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get(this.urlBase + 15 + "/historico")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
