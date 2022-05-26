package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unisep.cadastro.model.Vacina;
import br.com.unisep.cadastro.repository.VacinaRepository;

public class VacinaRepositoryTest {

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
		VacinaRepository vacinaRepository = new VacinaRepository(connection);

		Vacina vacina = new Vacina();
		vacina.setVacina("RB-51");
		vacina.setMarca("MSD");
		vacina.setMl(40);
		vacina.setBula("Medicamento para brucelose");
		vacina.setPreco(70.8);

		vacina = vacinaRepository.insert(vacina);

		Assert.assertNotNull(vacina.getId());
	}

	@Test
	public void deleteTest() {

		VacinaRepository vacinaRepository = new VacinaRepository(connection);

		vacinaRepository.delete(0);

	}

	@Test
	public void findAllTest() {

		VacinaRepository vacinaRepository = new VacinaRepository(connection);

		//vacinaRepository.findAll();

		List<Vacina> lista = vacinaRepository.findAll();

		lista.forEach(vacina -> {
			System.out.println(vacina.getVacina());
		});
		
		assertNotEquals(3, lista.size());

	}

	@Test
	public void UpdateTest() {

		VacinaRepository vacinaRepository = new VacinaRepository(connection);

		Vacina vacina = new Vacina();
		vacina.setId(1);
		vacina.setVacina("ALTERADO");
		vacina.setMarca("ALTERADO");
		vacina.setMl(90);
		vacina.setBula("ALTERADO");
		vacina.setPreco(13.5);
		
		assertEquals("ALTERADO", vacina.getVacina());
		assertEquals("ALTERADO", vacina.getMarca());
		assertEquals("ALTERADO", vacina.getBula());
		
		vacinaRepository.update(vacina);

	}

	@Test
	public void findOneTest() {

		VacinaRepository vacinaRepository = new VacinaRepository(connection);
		Vacina vacina = vacinaRepository.findOne(1);

		assertEquals("ALTERADO", vacina.getVacina());

	}
	
}
