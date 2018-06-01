
package br.com.abiblia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "br.com.abiblia")
public class App {

     public static void main(String[] args) throws Exception {

          SpringApplication.run(App.class, args);
     }

}