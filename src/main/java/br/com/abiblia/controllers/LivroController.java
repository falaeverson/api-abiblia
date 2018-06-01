
package br.com.abiblia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.abiblia.responses.LivroResponse;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/")
public class LivroController {

     @RequestMapping("")
     @ResponseBody
     public String ok() {

          LivroResponse livro = new LivroResponse();
          livro.nome = "Joao";
          livro.Sila = "JO";

          return "App rodando";
          
     }
     
     @RequestMapping("/ok")
     @ResponseBody
     public LivroResponse home() {

          LivroResponse livro = new LivroResponse();
          livro.nome = "OK";
          livro.Sila = "JO";

          return livro;
          
     }

}
