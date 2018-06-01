package br.com.abiblia.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.abiblia.entidades.Versiculo;
import br.com.abiblia.repositorios.LivroRepositorio;
import br.com.abiblia.repositorios.VersaoRepositorio;
import br.com.abiblia.repositorios.VersiculoRepositorio;

@Service
public class VersiculoServico {

     @Autowired
     private VersaoRepositorio versaoRepositorio;
     
     @Autowired
     private LivroRepositorio livroRepositorio;
     
     @Autowired
     private VersiculoRepositorio versiculoRepositorio;
     
     public Page<Versiculo> versiculos(PageRequest pageRequest ) {
          
          //versiculoRepositorio.findAll(example, sort)
          
          Page<Versiculo> versoes = versiculoRepositorio.findAll(pageRequest);
          
         return versoes;
     }
     
     public Optional<Versiculo> versiculo(Long id) {

          Optional<Versiculo> versao = versiculoRepositorio.findById(id);
          
         return versao;
     }
}
