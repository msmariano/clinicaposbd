package com.up.clinicavet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.up.clinicavet.model.Alergia;
import com.up.clinicavet.model.Animal;
import com.up.clinicavet.model.Endereco;
import com.up.clinicavet.model.Pessoa;
import com.up.clinicavet.model.TipoAnimal;
import com.up.clinicavet.model.Vacina;

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
			Animal animal = em.find(Animal.class, 1);
			TipoAnimal tipoAnimal = em.find(TipoAnimal.class,1);
			Vacina vacina = em.find(Vacina.class, 1);
			Alergia alergia = em.find(Alergia.class, 1);
			
			Endereco endereco = new Endereco();
			endereco.setCep("80215-230");
			endereco.setNumero("57");
			endereco.setRua("Cyro Vellozo");
			em.persist(endereco);
			
			em.getTransaction().commit();
			
		System.out.println("Pessoa :" +pessoa.getNome());
		System.out.println("Animal :" +animal.getNome());
		System.out.println("TipoAnimal :" +tipoAnimal.getNomeRaca());
		System.out.println("Vacina :"+vacina.getNome());
		System.out.println("Alergia :"+alergia.getNomeAlergia());
		
		
		
		
		
		
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		} 
		
		
		
		System.out.println("Fim!");
	}
}