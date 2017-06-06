package com.up.clinicavet.model;

import java.util.ArrayList;
import java.util.List;

public class TipoAnimal {
	private int id;
	private String nomeRaca;
	private String descricao;
	List<Animal> listaAnimal;
	List<Pessoa> listaPessoa;
	
	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}
	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}
	public List<Animal> getListaAnimal() {
		return listaAnimal;
	}
	public void setListaAnimal(List<Animal> listaAnimal) {
		this.listaAnimal = listaAnimal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeRaca() {
		return nomeRaca;
	}
	public void setNomeRaca(String nomeRaca) {
		this.nomeRaca = nomeRaca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}