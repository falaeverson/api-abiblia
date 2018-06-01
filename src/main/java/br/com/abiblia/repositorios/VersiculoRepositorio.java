package br.com.abiblia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.abiblia.entidades.Versiculo;

public interface VersiculoRepositorio extends JpaRepository<Versiculo, Long>{

}
