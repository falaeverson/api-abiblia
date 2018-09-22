package br.com.abiblia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "livros")
public class Livro implements Serializable {

	private static final long serialVersionUID = -3531214565321680758L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "liv_id")
	private Long id;

	@Column(name = "liv_tes_id")
	private Long idTestamento;

	@Column(name = "liv_posicao")
	private Long posicao;

	@Column(name = "liv_nome")
	private String nome;

	@Column(name = "liv_abreviado")
	private String abreviacao;

}
