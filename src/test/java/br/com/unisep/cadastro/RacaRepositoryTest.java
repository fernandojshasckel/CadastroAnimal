package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unisep.cadastro.model.Raca;
import br.com.unisep.cadastro.repository.RacaRepository;

public class RacaRepositoryTest {

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
		RacaRepository racaRepository = new RacaRepository(connection);

		Raca raca = new Raca();
		raca.setRaca("Nelore");
		raca.setCor("Branco");

		raca = racaRepository.insert(raca);

		Assert.assertNotNull(raca.getId());
	}

	@Test
	public void deleteTest() {

		RacaRepository racaRepository = new RacaRepository(connection);

		racaRepository.delete(0);

	}

	@Test
	public void findAllTest() {

		RacaRepository racaRepository = new RacaRepository(connection);

		// racaRepository.findAll();

		List<Raca> lista = racaRepository.findAll();

		lista.forEach(raca -> {
			System.out.println(raca.getRaca());
		});

		Assert.assertNotEquals(3, lista.size());

	}

	@Test
	public void UpdateTest() {

		RacaRepository racaRepository = new RacaRepository(connection);

		Raca raca = new Raca();
		raca.setId(1);
		raca.setRaca("Raca alterado");
		raca.setCor("Azul");

		racaRepository.update(raca);

	}

	@Test
	public void findOneTest() {

		RacaRepository racaRepository = new RacaRepository(connection);

		Raca raca = racaRepository.findOne(1);

		assertEquals("Raca alterado", raca.getRaca());

	}

}
