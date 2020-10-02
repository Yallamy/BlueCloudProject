package br.com.sigad.msc.interessado.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import lombok.Getter;
import lombok.Setter;


/**
 * Classe que armazena as propriedades do sistema.
 * @author Yallamy Nascimento (yallamy@gmail.com)
 * @since 01 de out de 2020
 */
@ConfigurationProperties(ignoreUnknownFields = true)
@Getter
@Setter
@EnableConfigurationProperties
public class Propriedades {

	public ApiConfig apiConfig = new ApiConfig();

	@Getter
	@Setter
	public class ApiConfig {

		private Rest rest = new Rest();

		@Getter
		@Setter
		public class Rest {

			private int connectionTimeout;

			private int readTimeout;

		}

		private Failsafe failsafe = new Failsafe();

		@Getter
		@Setter
		public class Failsafe {

			private int failureRateThreshold;

			private int waitDurationInOpenState;

			private int slidingWindowSize;
			
			private int timeoutDuration;
		}
	}
}
