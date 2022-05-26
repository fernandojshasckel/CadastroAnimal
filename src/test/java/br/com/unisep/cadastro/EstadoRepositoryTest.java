package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unisep.cadastro.model.Estado;
import br.com.unisep.cadastro.repository.EstadoRepository;

public class EstadoRepositoryTest {

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
		EstadoRepository estadoRepository = new EstadoRepository(connection);

		Estado estado = new Estado();
		estado.setNome("Santa Catarina");
		estado.setSigla("SC");

		estado = estadoRepository.insert(estado);

		Assert.assertNotNull(estado.getId());
	}

	@Test
	public void deleteTest() {

		EstadoRepository estadoRepository = new EstadoRepository(connection);

		estadoRepository.delete(3);

	}

	@Test
	public void findAllTest() {

		EstadoRepository estadoRepository = new EstadoRepository(connection);

		//estadoRepository.findAll();

		List<Estado> lista = estadoRepository.findAll();
 
		lista.forEach(estado -> {
			System.out.println(estado.getNome());
		});

		Assert.assertNotEquals(3, lista.size());

	}

	@Test
	public void UpdateTest() {

		EstadoRepository estadoRepository = new EstadoRepository(connection);

		Estado estado = new Estado();
		estado.setId(2);
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");

		estadoRepository.update(estado);

	}

	@Test
	public void findOneTest() {

		EstadoRepository estadoRepository = new EstadoRepository(connection);
		Estado estado = estadoRepository.findOne(2);

		assertEquals("Rio de Janeiro", estado.getNome());

	}

}
