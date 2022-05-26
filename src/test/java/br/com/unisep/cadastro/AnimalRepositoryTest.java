package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.*;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.unisep.cadastro.model.Animal;
import br.com.unisep.cadastro.model.Cidade;
import br.com.unisep.cadastro.model.Estado;
import br.com.unisep.cadastro.model.Raca;
import br.com.unisep.cadastro.model.TipoAnimal;
import br.com.unisep.cadastro.repository.AnimalRepository;
import br.com.unisep.cadastro.repository.CidadeRepository;
import br.com.unisep.cadastro.repository.EstadoRepository;
import br.com.unisep.cadastro.repository.RacaRepository;
import br.com.unisep.cadastro.repository.TipoAnimalRepository;


public class AnimalRepositoryTest {

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
		
		AnimalRepository animalRepository = new AnimalRepository(connection);
		
		Animal animal = new Animal();
		
		animal.setAnimal("Boi");
		animal.setEstimativaVida(12); 
		animal.setTamanho(1);
		animal.setPeso(120);
		animal.setVacinado(true);

		RacaRepository racaRepository = new RacaRepository(connection);
		List<Raca> listaDeRacasCadastrados = racaRepository.findAll();
		animal.setRaca(listaDeRacasCadastrados.get(0));
		
		CidadeRepository cidadeRepository = new CidadeRepository(connection);
		List<Cidade> listaDeCidadesCadastrados = cidadeRepository.findAll();
		animal.setNacionalidadeCidade(listaDeCidadesCadastrados.get(0));		
		
		EstadoRepository estadoRepository = new EstadoRepository(connection);
		List<Estado> listaDeEstadosCadastrados = estadoRepository.findAll();
		animal.setNacionalidadeEstado(listaDeEstadosCadastrados.get(0));	
		
		TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository(connection);
		List<TipoAnimal> listaDeTipos = tipoAnimalRepository.findAll();
		animal.setTipoAnimal(listaDeTipos.get(0));
		
		animal = animalRepository.insert(animal);

		assertNotNull(animal.getId());
	}

	@Test 
	public void deleteTest() { 

		AnimalRepository animalRepository = new AnimalRepository(connection);

		animalRepository.delete(2);

	}

	@Test
	public void findAllTest() {

		AnimalRepository animalRepository = new AnimalRepository(connection);

		animalRepository.findAll();

		List<Animal> lista = animalRepository.findAll();

		lista.forEach(animal -> {
			System.out.println(animal.getAnimal());
		});

		assertEquals(1, lista.size());

	}

	@Test
	public void UpdateTest() {

		AnimalRepository animalRepository = new AnimalRepository(connection);
		
		Animal animal = new Animal();
		animal.setId(1);
		animal.setAnimal("Boi");
		animal.setEstimativaVida(12);
		animal.setTamanho(1);
		animal.setPeso(1200);
		animal.setVacinado(true);

		RacaRepository racaRepository = new RacaRepository(connection);  
		List<Raca> listaDeRacasCadastrados = racaRepository.findAll();	
		animal.setRaca(listaDeRacasCadastrados.get(0));		
		
		CidadeRepository cidadeRepository = new CidadeRepository(connection);  
		List<Cidade> listaDeCidadesCadastrados = cidadeRepository.findAll();	
		animal.setNacionalidadeCidade(listaDeCidadesCadastrados.get(0));	
		
		EstadoRepository estadoRepository = new EstadoRepository(connection);  
		List<Estado> listaDeEstadosCadastrados = estadoRepository.findAll();	
		animal.setNacionalidadeEstado(listaDeEstadosCadastrados.get(0));	
		
		TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository(connection);
		List<TipoAnimal> listaDeTipos = tipoAnimalRepository.findAll();
		animal.setTipoAnimal(listaDeTipos.get(0));
		
		animalRepository.update(animal);
		assertEquals("Boi", animal.getAnimal());

	}

	@Test
	public void findOneTest() {

		AnimalRepository animalRepository = new AnimalRepository(connection);

		Animal animal = animalRepository.findOne(1);

		assertEquals("Boi", animal.getAnimal());

	}
	
}
