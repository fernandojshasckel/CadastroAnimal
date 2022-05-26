package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unisep.cadastro.model.Animal;
import br.com.unisep.cadastro.model.Vacina;
import br.com.unisep.cadastro.model.VacinaAnimal;
import br.com.unisep.cadastro.repository.AnimalRepository;
import br.com.unisep.cadastro.repository.VacinaAnimalRepository;
import br.com.unisep.cadastro.repository.VacinaRepository;

public class VacinaAnimalRepositoryTest {

	private static Connection connection;
 
	@BeforeClass
	public static void iniciarClasse() throws SQLException {

		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalho_g1_fernando?useTimezone=true&serverTimezone=UTC", "root","03092002");
	}
 
	@AfterClass
	public static void encerrarClasse() throws SQLException {
		connection.close();
	}
 
	@Test 
	public void insertTest() {
		
		VacinaAnimalRepository vacinaAnimalRepository = new VacinaAnimalRepository(connection);

		VacinaAnimal vacinaAnimal = new VacinaAnimal();

		AnimalRepository animalRepository = new AnimalRepository(connection);  
		List<Animal> listaDeAnimaisCadastrados = animalRepository.findAll();	
		vacinaAnimal.setAnimal(listaDeAnimaisCadastrados.get(0));	
		
		VacinaRepository vacinaRepository = new VacinaRepository(connection);  
		List<Vacina> listaDeVacinasCadastrados = vacinaRepository.findAll();	
		vacinaAnimal.setVacina(listaDeVacinasCadastrados.get(0));	
		
		vacinaAnimal = vacinaAnimalRepository.insert(vacinaAnimal);

		Assert.assertNotNull(vacinaAnimal.getId());
	}

	@Test
	public void deleteTest() {

		VacinaAnimalRepository vacinaAnimalRepository = new VacinaAnimalRepository(connection);

		vacinaAnimalRepository.delete(0);

	}

	@Test
	public void findAllTest() {

		VacinaAnimalRepository vacinaAnimalRepository = new VacinaAnimalRepository(connection);

		vacinaAnimalRepository.findAll();

		List<VacinaAnimal> lista = vacinaAnimalRepository.findAll();

		lista.forEach(vacinaAnimal -> {
			System.out.println(vacinaAnimal.getAnimal());
		});

		Assert.assertNotEquals(3, lista.size());

	}

	@Test
	public void UpdateTest() {

		VacinaAnimalRepository vacinaAnimalRepository = new VacinaAnimalRepository(connection);

		VacinaAnimal vacinaAnimal = new VacinaAnimal();

		AnimalRepository animalRepository = new AnimalRepository(connection);  
		List<Animal> listaDeAnimaisCadastrados = animalRepository.findAll();	
		vacinaAnimal.setAnimal(listaDeAnimaisCadastrados.get(0));	
		
		VacinaRepository vacinaRepository = new VacinaRepository(connection);  
		List<Vacina> listaDeVacinasCadastrados = vacinaRepository.findAll();	
		vacinaAnimal.setVacina(listaDeVacinasCadastrados.get(0));

		vacinaAnimalRepository.update(vacinaAnimal);

	}

	@Test
	public void findOneTest() {

		VacinaAnimalRepository vacinaAnimalRepository = new VacinaAnimalRepository(connection);

		VacinaAnimal vacinaAnimal = vacinaAnimalRepository.findOne(1);

		assertEquals("RB-51", vacinaAnimal.getVacina());
 
	}
	
}
