package br.com.unisep.cadastro.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.unisep.cadastro.model.Animal;
import br.com.unisep.cadastro.model.Vacina;
import br.com.unisep.cadastro.model.VacinaAnimal;


public class VacinaAnimalRepository {

	private final Connection connection;

	public VacinaAnimalRepository(Connection connection) { 
		this.connection = connection;
	}

	public VacinaAnimal insert(VacinaAnimal vacinaAnimal) {

		String sql = "insert into vacinaAnimal(id_animal, id_vacina) values (?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, vacinaAnimal.getAnimal().getId());
			preparedStatement.setInt(2, vacinaAnimal.getVacina().getId());

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				resultSet.next();

				vacinaAnimal.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return vacinaAnimal;
	}

	public VacinaAnimal update(VacinaAnimal vacinaAnimal) {

		String sql = "update vacinaAnimal set id_animal = ?, id_vacina = ? where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, vacinaAnimal.getAnimal().getId());
			preparedStatement.setInt(2, vacinaAnimal.getVacina().getId());
			preparedStatement.setInt(3, vacinaAnimal.getId());

			preparedStatement.executeUpdate();

			return vacinaAnimal;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}

	public void delete(int id) {

		String sql = "delete from vacinaAnimal where id = ?";

		try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
			prepareStatement.setInt(1, id);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public VacinaAnimal findOne(int id) {

		String sql = "select * from vacinaAnimal where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				resultSet.next();

				VacinaAnimal vacinaAnimal = new VacinaAnimal();
				Animal animal = new Animal();
				Vacina vacina = new Vacina();
				
				animal.setId(resultSet.getInt("id_animal"));
				vacina.setId(resultSet.getInt("id_vacina"));
				
				vacinaAnimal.setAnimal(animal);
				vacinaAnimal.setVacina(vacina);
				
				return vacinaAnimal;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<VacinaAnimal> findAll() {

		String sql = "select * from vacinaAnimal";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) { 

				List<VacinaAnimal> vacinaAnimais = new ArrayList<VacinaAnimal>();

				while (resultSet.next()) {

					VacinaAnimal vacinaAnimal = new VacinaAnimal();
					Animal animal = new Animal();
					Vacina vacina = new Vacina();
					
					animal.setId(resultSet.getInt("id_animal"));
					vacina.setId(resultSet.getInt("id_vacina"));
					
					vacinaAnimal.setAnimal(animal);
					vacinaAnimal.setVacina(vacina);

					vacinaAnimais.add(vacinaAnimal);

				}

				return vacinaAnimais;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
}
