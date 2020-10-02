package br.com.sigad.msc.interessado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.sigad.msc.interessado.config.Propriedades;

@SpringBootApplication
@EnableConfigurationProperties({ Propriedades.class })
@EnableScheduling
@EnableCaching
public class MCSInteressado {

	public static void main(String[] args) {
		
		SpringApplication.run(MCSInteressado.class, args);
	}

}
