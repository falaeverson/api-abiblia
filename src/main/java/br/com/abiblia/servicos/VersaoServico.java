package br.com.abiblia.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.abiblia.entidades.Versao;
import br.com.abiblia.repositorios.VersaoRepositorio;

@Service
public class VersaoServico {

     @Autowired
     private VersaoRepositorio versaoRepositorio;
     
     public Page<Versao> versoes(PageRequest pageRequest ) {
          
          Page<Versao> versoes = versaoRepositorio.findAll(pageRequest);
          
         return versoes;
     }
     
     public Optional<Versao> versao(Long id) {

          Optional<Versao> versao = versaoRepositorio.findById(id);
          
         return versao;
     }
}
