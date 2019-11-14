package org.api.abiblia.repositorios;

import org.api.abiblia.entidades.Livro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LivroRepositorio extends MongoRepository<Livro, Long> {

}