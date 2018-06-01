package br.com.abiblia.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.abiblia.entidades.Livro;
import br.com.abiblia.repositorios.LivroRepositorio;

@Service
public class LivroServico {

     @Autowired
     private LivroRepositorio livroRepositorio;
     
     public Page<Livro> livros(PageRequest pageRequest) {
          
          Page<Livro> livros = livroRepositorio.findAll(pageRequest);
          
         return livros;
     }
     
     public Optional<Livro> livro(Long id) {
          
          Optional<Livro> livro = livroRepositorio.findById(id);
          
         return livro;
     }
}
