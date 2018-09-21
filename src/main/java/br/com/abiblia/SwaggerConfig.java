package br.com.abiblia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.abiblia.util.ConstantesRest;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@EnableSwagger2
@Configuration
public class SwaggerConfig {
 
     @Bean
     public Docket detalheApi() {
 
          Docket docket = new Docket(DocumentationType.SWAGGER_2);
 
          docket
          .select()
          .apis(RequestHandlerSelectors.basePackage("br.com.abiblia"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(this.informacoesApi().build());
 
          return docket;
     }
 
     private ApiInfoBuilder informacoesApi() {
 
          ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
          apiInfoBuilder.title("Api-aBiblia");
          apiInfoBuilder.description("Api para distribuição de serviços da bíblia");
          apiInfoBuilder.version(ConstantesRest.API_VERSION);
          //apiInfoBuilder.termsOfServiceUrl("Termo de uso: Deve ser usada para estudos.");
          //apiInfoBuilder.license("Licença - Open Source");
          //apiInfoBuilder.licenseUrl("http://www.abiblia.teo.br");
          //apiInfoBuilder.contact(this.contato());
 
          return apiInfoBuilder;
 
     }
     /*
     private Contact contato() {
 
          return new Contact(
                    "Everson Teixeira",
                    "http://www.abiblia.teo.br", 
                    "tx.everson@gmail.com");
     }
     */
}