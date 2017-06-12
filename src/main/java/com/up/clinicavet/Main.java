package com.up.clinicavet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.up.clinicavet.dao.TipoAnimalDAO;
import com.up.clinicavet.model.Pessoa;

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
			//TipoAnimalDAO tipoAnimalDAO  = new TipoAnimalDAO();
			//tipoAnimalDAO.buscaEager(5);
		
			//Teste remover com relacionamentos
			//PessoaDAO pessoaDAO = new PessoaDAO();
			//pessoaDAO.removerComRelacionamentos(7);
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinica_pu");
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			Pessoa pessoa = em.find(Pessoa.class, 1);
			em.getTransaction().commit();
			
		System.out.println(pessoa.getNome());
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		} 
		
		
		
		System.out.println("Fim!");
	}
}