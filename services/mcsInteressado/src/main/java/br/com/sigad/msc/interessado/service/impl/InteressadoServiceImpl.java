package br.com.sigad.msc.interessado.service.impl;

import javax.annotation.PostConstruct;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.sigad.msc.interessado.entity.Interessado;
import br.com.sigad.msc.interessado.exception.SigadException;
import br.com.sigad.msc.interessado.repository.InteressadoRepository;
import br.com.sigad.msc.interessado.service.InteressadoService;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe que implementa os métodos do serviço para manter um interessado.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 1 de out. de 2020
 */
@Service
@Transactional
@Slf4j
public class InteressadoServiceImpl implements InteressadoService {
	
	@Autowired
	private InteressadoRepository repository;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private Gson gson;
	
	@PostConstruct
	public void init() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	/*
	 *
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#create(br.com.sigad.msc.interessado.entity.Interessado)
	 */
	@Override
	public Interessado create(Interessado interessado) throws SigadException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#update(br.com.sigad.msc.interessado.entity.Interessado)
	 */
	@Override
	public void update(Interessado interessado) throws SigadException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#retrieve(java.lang.Long)
	 */
	@Override
	public Interessado retrieve(Long id) throws SigadException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#retrieve(java.lang.String)
	 */
	@Override
	public Interessado retrieve(String cpfCnpj) throws SigadException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#delete(br.com.sigad.msc.interessado.entity.Interessado)
	 */
	@Override
	public void delete(Interessado interessado) throws SigadException {
		// TODO Auto-generated method stub
		
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see @see br.com.sigad.msc.interessado.service.InteressadoService#list(br.com.sigad.msc.interessado.entity.Interessado, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Interessado> list(Interessado interessado, Pageable pageable) throws SigadException {
		// TODO Auto-generated method stub
		return null;
	}

}
