package org.api.abiblia.repositorios;

import org.api.abiblia.entidades.Versao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VersaoRepositorio extends MongoRepository<Versao, Long> {

}