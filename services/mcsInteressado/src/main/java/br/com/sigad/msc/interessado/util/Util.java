package br.com.sigad.msc.interessado.util;

import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.twsoftware.alfred.object.Objeto;

/**
 * Classe que apresenta métodos úteis para todo o projeto.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
public class Util {
	
	/**
	 * Método que converte um tipo de classe para outro tipo de classe.
	 * @param source
	 * @param destinationType
	 * @return Class<E> tipo de classe de destino
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public static <E, T> E convertModelMapper(T source, Class<E> destinationType) {
		
        E model = null;
        
        if (Objeto.notBlank(source) && Objeto.notBlank(destinationType)) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
             model = modelMapper.map(source, destinationType);
        }

        return model;
	}
	
	/**
	 * Método que converte um tipo de lista para outro tipo de lista.
	 * @param source
	 * @param destinationType
	 * @return List<E>  tipo de classe de destino
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 5 de out. de 2020
	 */
	public static <E, T> List<E> convertModelMapperToList(List<T> source, Class<E> destinationType) {
		
		List<E> model = new LinkedList<E>();
		
		if (Objeto.notBlank(source) && Objeto.notBlank(destinationType)) {
			
			for (T item : source) {
				model.add(convertModelMapper(item, destinationType));
			}
		}

		return model;
	}
	
	/**
	 * Método que converte um tipo de page para outro tipo de page.
	 * @param source
	 * @param typeDestination
	 * @return Page<E>
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 5 de out. de 2020
	 */
	public static <T, E> Page<E> convertModelMapperToPage(Page<T> source, Class<E> destinationType) {

		Page<E> model = null;
		
		if (Objeto.notBlank(source) && Objeto.notBlank(destinationType)) {

			List<E> listContent = convertModelMapperToList(source.getContent(), destinationType);
			
			Page<E> pageResponse = new PageImpl<>(listContent, source.getPageable(), source.getTotalElements());
			model = pageResponse;
		}

		return model;
	}

}
