package br.com.sigad.msc.interessado.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

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
	 * @param typeDestination
	 * @return Class<E> tipo de classe de destino
	 * @author Yallamy Nascimento (yallamy@gmail.com)
	 * @since 1 de out. de 2020
	 */
	public static <E, T> E convertModelMapper(T source, Class<E> typeDestination) {
		
        E model = null;
        
        if (Objeto.notBlank(source) && Objeto.notBlank(typeDestination)) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
             model = modelMapper.map(source, typeDestination);
        }

        return model;
	}

}
