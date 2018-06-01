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
@Table(name = "versoes")
public class Versao implements Serializable {

     private static final long serialVersionUID = 413633947594162587L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "vrs_id")
     private Long id;
     
     @Column(name = "vrs_nome")
     private String nome;
     
     @Column(name = "vrs_abreviado")
     private String abreviacao;     
}
