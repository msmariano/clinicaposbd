package com.up.clinicavet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="animal_id",updatable=false)
	private int id;
	@Column(name="tipo_id")
	private Integer tipoId;
	@Column(name="pessoa_id")
	private Integer pessoaId;
	@Column	(length=30, nullable=false)
	private String nome;
	@Column	(nullable=	true)
	private Date nascimento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getTipoId() {
		return tipoId;
	}
	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}
	public Integer getPessoaId() {
		return pessoaId;
	}
	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	@Override
	public int hashCode() {
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
