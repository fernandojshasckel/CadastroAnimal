package br.com.unisep.cadastro.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.unisep.cadastro.model.Estado;


public class EstadoRepository {

	private final Connection connection;

	public EstadoRepository(Connection connection) {
		this.connection = connection;
	}

	public Estado insert(Estado estado) {

		String sql = "insert into estado(nome, sigla) values (?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, estado.getNome());
			preparedStatement.setString(2, estado.getSigla());

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				resultSet.next();

				estado.setId(resultSet.getInt(1)); 
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return estado;
	}

	public Estado update(Estado estado) {

		String sql = "update estado set nome = ?, sigla = ? where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, estado.getNome());
			preparedStatement.setString(2, estado.getSigla());
			preparedStatement.setInt(3, estado.getId());

			preparedStatement.executeUpdate();

			return estado;

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

	public Estado findOne(int id) {

		String sql = "select * from estado where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				resultSet.next();

				Estado estado = new Estado();
				estado.setId(resultSet.getInt("id"));
				estado.setNome(resultSet.getString("nome"));
				estado.setSigla(resultSet.getString("sigla"));
				
				return estado;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<Estado> findAll() {

		String sql = "select * from estado";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) { 

				List<Estado> estados = new ArrayList<Estado>();

				while (resultSet.next()) {

					Estado estado = new Estado();
					estado.setNome(resultSet.getString("nome"));
					estado.setSigla(resultSet.getString("sigla"));
					estado.setId(resultSet.getInt("id"));

					estados.add(estado);

				}

				return estados;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
}
