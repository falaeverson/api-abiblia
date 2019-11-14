package org.api.abiblia.repositorios;

import java.util.List;

import org.api.abiblia.entidades.Versiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VersiculoRepositorio extends MongoRepository<Versiculo, Long>  {

	List<Versiculo> findByIdVersaoAndIdLivroAndCapitulo(Long idVersao, Long idLivro, Integer capitulo);


}