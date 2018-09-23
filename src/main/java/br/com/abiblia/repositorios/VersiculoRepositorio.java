package br.com.abiblia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import br.com.abiblia.entidades.Versiculo;

public interface VersiculoRepositorio extends JpaRepository<Versiculo, Long>, QueryByExampleExecutor<Versiculo> {
	
}
