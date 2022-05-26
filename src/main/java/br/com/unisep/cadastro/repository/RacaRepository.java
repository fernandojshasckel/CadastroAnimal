package br.com.unisep.cadastro.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.unisep.cadastro.model.Raca;


public class RacaRepository {

	private final Connection connection;

	public RacaRepository(Connection connection) {
		this.connection = connection;
	}

	public Raca insert(Raca raca) {

		String sql = "insert into raca(raca, cor) values (?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, raca.getRaca());
			preparedStatement.setString(2, raca.getCor());

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				resultSet.next();

				raca.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return raca;
	}

	public Raca update(Raca raca) {

		String sql = "update raca set raca = ?, cor = ? where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, raca.getRaca());
			preparedStatement.setString(2, raca.getCor());
			preparedStatement.setInt(3, raca.getId());

			preparedStatement.executeUpdate();

			return raca;

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

	public Raca findOne(int id) {

		String sql = "select * from raca where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				resultSet.next();

				Raca raca = new Raca();
				raca.setRaca(resultSet.getString("raca"));
				raca.setCor(resultSet.getString("cor"));

				return raca;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<Raca> findAll() {

		String sql = "select * from raca";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) { 

				List<Raca> racas = new ArrayList<Raca>();

				while (resultSet.next()) {

					Raca raca = new Raca();
					raca.setRaca(resultSet.getString("raca"));
					raca.setCor(resultSet.getString("cor"));

					racas.add(raca);

				}

				return racas;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
}
