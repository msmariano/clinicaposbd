package com.up.clinicavet;

import java.text.SimpleDateFormat;

import com.up.clinicavet.dao.AnimalDAO;
import com.up.clinicavet.dao.PessoaDAO;
import com.up.clinicavet.dao.TipoAnimalDAO;
import com.up.clinicavet.model.Animal;
import com.up.clinicavet.model.Pessoa;
import com.up.clinicavet.model.TipoAnimal;

public class Main {

	public static void main(String[] args) {
		
		
		try {
			//Inserir com chaves auto-geradas no persistir
			/*Pessoa pessoa = new Pessoa();
			pessoa.setNome("Marcelo dos Santos Mariano");
			pessoa.setCpf(87522020972L);
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");			
			pessoa.setNascimento(sd.parse("04/03/1970"));
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.persistir(pessoa);
			
			TipoAnimal tipoAnimal  = new TipoAnimal();
			tipoAnimal.setDescricao("Cachorro");
			tipoAnimal.setNomeRaca("Lhasa");
			TipoAnimalDAO tipoAnimalDAO  = new TipoAnimalDAO();
			tipoAnimalDAO.persistir(tipoAnimal);			
						
			Animal animal = new Animal();
			animal.setNome("Kenia");
			animal.setDono(pessoa);
			animal.setNascimento(sd.parse("01/01/2011"));
			animal.setTipo(tipoAnimal);
			AnimalDAO animalDAO = new AnimalDAO();
			animalDAO.persistir(animal);*/
			
			//buscarEager Tipo Animal
			TipoAnimalDAO tipoAnimalDAO  = new TipoAnimalDAO();
			tipoAnimalDAO.buscaEager(5);
		
			//Teste remover com relacionamentos
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.removerComRelacionamentos(7);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} 
		
		
		
		System.out.println("Fim!");
	}
}