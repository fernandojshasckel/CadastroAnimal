package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unisep.cadastro.model.TipoAnimal;
import br.com.unisep.cadastro.repository.TipoAnimalRepository;

public class TipoAnimalRepositoryTest {

	private static Connection connection;

	@BeforeClass
	public static void iniciarClasse() throws SQLException {

		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/trabalho_g1_fernando?useTimezone=true&serverTimezone=UTC", "root",
				"03092002");
	}

	@AfterClass   
	public static void encerrarClasse() throws SQLException {
		connection.close();
	}
 
	@Test
	public void insertTest() {
		TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository(connection);

		TipoAnimal tipoAnimal = new TipoAnimal();
		tipoAnimal.setTipo("Corte");

		tipoAnimal = tipoAnimalRepository.insert(tipoAnimal);

		Assert.assertNotNull(tipoAnimal.getId());
	}

	@Test
	public void deleteTest() {

		TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository(connection);

		tipoAnimalRepository.delete(0);

	}

	@Test
	public void findAllTest() {

		TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository(connection);

		tipoAnimalRepository.findAll();

		List<TipoAnimal> lista = tipoAnimalRepository.findAll();

		lista.forEach(tipoAnimal -> {
			System.out.println(tipoAnimal.getTipo()); 
		});

		Assert.assertNotEquals(1, lista.size());

	}
 
	@Test
	public void UpdateTest() {

		TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository(connection);

		TipoAnimal tipoAnimal = new TipoAnimal();
		tipoAnimal.setId(1);
		tipoAnimal.setTipo("Cria");

		tipoAnimalRepository.update(tipoAnimal);

	}

	@Test
	public void findOneTest() {

		TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository(connection);
		TipoAnimal tipoAnimal = tipoAnimalRepository.findOne(1);

		assertEquals("Cria", tipoAnimal.getTipo());

	}

	
}
