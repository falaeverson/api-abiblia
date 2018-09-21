
package br.com.abiblia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import br.com.abiblia.config.AbibliaProperties;
import br.com.abiblia.config.SwaggerConfig;

@SpringBootApplication(scanBasePackages = "br.com.abiblia")
@EnableConfigurationProperties({ AbibliaProperties.class })
@Import(SwaggerConfig.class)
public class App {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(App.class, args);
	}

}