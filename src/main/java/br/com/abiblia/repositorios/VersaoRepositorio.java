package br.com.abiblia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import br.com.abiblia.entidades.Versao;

public interface VersaoRepositorio extends JpaRepository<Versao, Long>, QueryByExampleExecutor<Versao> {

}
