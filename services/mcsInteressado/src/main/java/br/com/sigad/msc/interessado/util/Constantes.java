package br.com.sigad.msc.interessado.util;

/**
 * Classe que posssui as constantes utilizadas no sistema.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class Constantes {

	//TAGS
	public static final String TAG_INTERESSADO = "interessado";
	
	
	//PATHS
	public static final String PATH_RAIZ = "/api/v1/sigad";

    public static final String PATH_SWAGGER = "/docs";
    
    //interessado resource
    public static final String PATH_INTERESSADO = PATH_RAIZ + "/interessado";
    
    
	//Swagger
    
    //serviços
    public static final String CREATE_INTERESSADO = "Criar um interessado";
    
    public static final String CREATE_INTERESSADO_NOTES = "Cria um interessado.";
    
    public static final String UPDATE_INTERESSADO = "Alterar um interessado";
    
    public static final String UPDATE_INTERESSADO_NOTES = "Altera um interessado.";
    
    public static final String RETRIEVE_INTERESSADO = "Recuperar um interessado";
    
    public static final String RETRIEVE_INTERESSADO_NOTES = "Recupera um interessado.";
    
    public static final String DELETE_INTERESSADO = "Deletar um interessado";
    
    public static final String DELETE_INTERESSADO_NOTES = "Deleta um interessado.";
    
    public static final String LIST_INTERESSADO = "Listar um interessado";
    
    public static final String LIST_INTERESSADO_NOTES = "Lista um interessado.";
    
    
    //DTOs
  	public static final String INTERESSADO_REQUEST_DTO = "Armazena os dados do request do interessado";
  	
  	public static final String INTERESSADO_REQUEST_NOME = "Armazena o nome do interessado";
  	
  	public static final String INTERESSADO_REQUEST_CPF_CNPJ = "Armazena o CPF/CNPJ do interessado";
  	
  	public static final String INTERESSADO_REQUEST_TIPO_DOCUMENTO = "Armazena o tipo de documento do interessado";
  	
  	public static final String INTERESSADO_REQUEST_EMAIL = "Armazena o e-mail do interessado";
  	
  	public static final String INTERESSADO_REQUEST_TELEFONE = "Armazena o telefone do interessado";
  	
  	
  	public static final String INTERESSADO_RESPONSE_DTO = "Armazena os dados do response do interessado";
  	
  	public static final String INTERESSADO_RESPONSE_ID = "Armazena o id do interessado";
  	
  	public static final String INTERESSADO_RESPONSE_NOME = "Armazena o nome do interessado";
  	
  	public static final String INTERESSADO_RESPONSE_CPF_CNPJ = "Armazena o CPF/CNPJ do interessado";
  	
  	public static final String INTERESSADO_RESPONSE_TIPO_DOCUMENTO = "Armazena o tipo de documento do interessado";
  	
  	public static final String INTERESSADO_RESPONSE_EMAIL = "Armazena o e-mail do interessado";
  	
  	public static final String INTERESSADO_RESPONSE_TELEFONE = "Armazena o telefone do interessado";
  	
  	public static final String INTERESSADO_RESPONSE_SITUACAO = "Armazena a situação do interessado";
  	
  	
  	public static final String INTERESSADO_FILTRO_DTO = "Armazena os dados do filtro do interessado";
  	
  	public static final String INTERESSADO_FILTRO_NOME = "Armazena o nome do interessado";
  	
  	public static final String INTERESSADO_FILTRO_CPF_CNPJ = "Armazena o CPF/CNPJ do interessado";
  	
  	public static final String INTERESSADO_FILTRO_TIPO_DOCUMENTO = "Armazena o tipo de documento do interessado";
  	
  	public static final String INTERESSADO_FILTRO_EMAIL = "Armazena o e-mail do interessado";
  	
  	public static final String INTERESSADO_FILTRO_TELEFONE = "Armazena o telefone do interessado";
  	
  	public static final String INTERESSADO_FILTRO_SITUACAO = "Armazena a situação do interessado";
    
}
