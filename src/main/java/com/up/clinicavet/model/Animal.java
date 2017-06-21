package com.up.clinicavet.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@ManyToOne
	@JoinColumn(name="tipoanimal_id")
	private TipoAnimal tipoAnimal;
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa dono;
	@ManyToMany
	@JoinTable(name="alergiaanimal",
		joinColumns={@JoinColumn(name="animal_id",referencedColumnName="animal_id")},
		inverseJoinColumns={@JoinColumn(name="alergia_id",referencedColumnName="alergia_id")})
	private List<Alergia> alergias;
	@OneToMany(mappedBy="vacinaAnimalID.animal")
	private List<VacinaAnimal> vacinasAnimal;
	
	
	
	public List<VacinaAnimal> getVacinasAnimal() {
		return vacinasAnimal;
	}
	public void setVacinasAnimal(List<VacinaAnimal> vacinasAnimal) {
		this.vacinasAnimal = vacinasAnimal;
	}
	public Pessoa getDono() {
		return dono;
	}
	public void setDono(Pessoa dono) {
		this.dono = dono;
	}
	public List<Alergia> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<Alergia> alergias) {
		this.alergias = alergias;
	}
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
