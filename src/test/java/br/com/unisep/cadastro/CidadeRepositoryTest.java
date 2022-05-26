package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;
import java.sql.*;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unisep.cadastro.model.Cidade;
import br.com.unisep.cadastro.model.Estado;
import br.com.unisep.cadastro.repository.CidadeRepository;
import br.com.unisep.cadastro.repository.EstadoRepository;

public class CidadeRepositoryTest {

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
		CidadeRepository cidadeRepository = new CidadeRepository(connection);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Xanxere");
		
		EstadoRepository estadoRepository = new EstadoRepository(connection);
		List<Estado> listaDeEstadosCadastrados = estadoRepository.findAll();
		cidade.setEstado(listaDeEstadosCadastrados.get(0));		
		
		cidade = cidadeRepository.insert(cidade);	
		
		Assert.assertNotNull(cidade.getId());
	}

	@Test
	public void deleteTest() {

		CidadeRepository cidadeRepository = new CidadeRepository(connection);

		cidadeRepository.delete(3);

	}

	@Test
	public void findAllTest() {

		CidadeRepository cidadeRepository = new CidadeRepository(connection);

		cidadeRepository.findAll();

		List<Cidade> lista = cidadeRepository.findAll();

		lista.forEach(cidade -> { 
			System.out.println(cidade.getNome());
		});

		Assert.assertNotEquals(2, lista.size());

	}

	@Test
	public void UpdateTest() {

		CidadeRepository cidadeRepository = new CidadeRepository(connection);

		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setNome("Cidade alterada");
		
		EstadoRepository estadoRepository = new EstadoRepository(connection); 
		List<Estado> listaDeEstadosCadastrados = estadoRepository.findAll();	
		cidade.setEstado(listaDeEstadosCadastrados.get(0));		

		cidadeRepository.update(cidade);
		Assert.assertEquals("Cidade alterada", cidade.getNome());
	}

	@Test
	public void findOneTest() {

		CidadeRepository cidadeRepository = new CidadeRepository(connection);
		Cidade cidade = cidadeRepository.findOne(1);

		assertEquals("Cidade alterada", cidade.getNome());

	}
	
}
