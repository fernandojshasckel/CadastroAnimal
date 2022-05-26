package br.com.unisep.cadastro.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unisep.cadastro.model.TipoAnimal;

public class TipoAnimalRepository {

	private final Connection connection;

	public TipoAnimalRepository(Connection connection) {
		this.connection = connection;
	}

	public TipoAnimal insert(TipoAnimal tipoAnimal) {

		String sql = "insert into tipoanimal(tipo) values (?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, tipoAnimal.getTipo()); 

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				resultSet.next();

				tipoAnimal.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return tipoAnimal;
	}

	public TipoAnimal update(TipoAnimal tipoAnimal) {

		String sql = "update tipoanimal set tipo = ? where id = ?"; // tipoAnimal analisar se nao da erro

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, tipoAnimal.getTipo());
			preparedStatement.setInt(2, tipoAnimal.getId());

			preparedStatement.executeUpdate();

			return tipoAnimal;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}

	public void delete(int id) {

		String sql = "delete from estado where id = ?";

		try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
			prepareStatement.setInt(1, id);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public TipoAnimal findOne(int id) {

		String sql = "select * from tipoAnimal where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				resultSet.next();

				TipoAnimal tipoAnimal = new TipoAnimal();
				tipoAnimal.setTipo(resultSet.getString("tipo"));

				return tipoAnimal;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<TipoAnimal> findAll() {

		String sql = "select * from tipoAnimal";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) { 

				List<TipoAnimal> tiposAnimal = new ArrayList<TipoAnimal>();

				while (resultSet.next()) {

					TipoAnimal tipoAnimal = new TipoAnimal();
					tipoAnimal.setTipo(resultSet.getString("tipo"));

					tiposAnimal.add(tipoAnimal);
 
				}

				return tiposAnimal;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
}
