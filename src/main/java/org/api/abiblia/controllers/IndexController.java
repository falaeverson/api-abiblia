package org.api.abiblia.controllers;

import org.api.abiblia.responses.LivroResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/")
public class IndexController {

	@RequestMapping("")
	public ModelAndView ok() {

		return new ModelAndView("redirect:/swagger-ui.html");

	}

	@RequestMapping("/ok")
	@ResponseBody
	public LivroResponse home() {

		LivroResponse livro = new LivroResponse();
		livro.nome = "OK";
		// livro.sila = "JO";

		return livro;

	}

}