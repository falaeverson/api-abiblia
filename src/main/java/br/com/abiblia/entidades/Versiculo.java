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
@Table(name = "versiculos")
public class Versiculo implements Serializable {

	private static final long serialVersionUID = 8111759554933854173L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ver_id")
	private Long id;

	@Column(name = "ver_vrs_id")
	private Long idVersao;

	@Column(name = "ver_tes_id")
	private Long idTestamento;

	@Column(name = "ver_liv_id")
	private Long idLivro;

	@Column(name = "ver_capitulo")
	private Integer capitulo;

	@Column(name = "ver_versiculo")
	private Integer versiculoNumero;

	@Column(name = "ver_texto")
	private String versiculoTexto;
}
