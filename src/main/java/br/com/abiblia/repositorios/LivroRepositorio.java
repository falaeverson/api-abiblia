package br.com.abiblia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import br.com.abiblia.entidades.Livro;

public interface LivroRepositorio extends JpaRepository<Livro, Long>, QueryByExampleExecutor<Livro> {

}
