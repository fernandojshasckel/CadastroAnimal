package br.com.unisep.cadastro.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.unisep.cadastro.model.Vacina;


public class VacinaRepository {

	private final Connection connection;

	public VacinaRepository(Connection connection) {
		this.connection = connection;
	} 

	public Vacina insert(Vacina vacina) {

		String sql = "insert into Vacina(vacina, marca, ml, bula, preco) values (?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, vacina.getVacina());
			preparedStatement.setString(2, vacina.getMarca());  
			preparedStatement.setDouble(3, vacina.getMl());
			preparedStatement.setString(4, vacina.getBula());
			preparedStatement.setDouble(5, vacina.getPreco());

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				resultSet.next();

				vacina.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return vacina;
	}

	public Vacina update(Vacina vacina) {

		String sql = "update vacina set vacina = ?, marca = ?, ml = ?, bula = ?, preco = ? where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, vacina.getVacina());
			preparedStatement.setString(2, vacina.getMarca());
			preparedStatement.setDouble(3, vacina.getMl());
			preparedStatement.setString(4, vacina.getBula());
			preparedStatement.setDouble(5, vacina.getPreco());
			preparedStatement.setInt(6, vacina.getId());

			preparedStatement.executeUpdate();

			return vacina;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}

	public void delete(int id) {

		String sql = "delete from vacina where id = ?";

		try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
			prepareStatement.setInt(1, id);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public Vacina findOne(int id) {

		String sql = "select * from vacina where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				resultSet.next();

				Vacina vacina = new Vacina();
				vacina.setVacina(resultSet.getString("vacina"));
				vacina.setMarca(resultSet.getString("marca"));
				vacina.setBula(resultSet.getString("bula"));
				vacina.setPreco(resultSet.getDouble("preco"));
				vacina.setMl(resultSet.getDouble("ml"));
				vacina.setId(resultSet.getInt("id"));

				return vacina;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<Vacina> findAll() {

		String sql = "select * from vacina";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) { 

				List<Vacina> vacinas = new ArrayList<Vacina>();

				while (resultSet.next()) {

					Vacina vacina = new Vacina();
					vacina.setVacina(resultSet.getString("vacina"));
					vacina.setMarca(resultSet.getString("marca"));
					vacina.setBula(resultSet.getString("bula"));
					vacina.setPreco(resultSet.getDouble("preco"));
					vacina.setMl(resultSet.getDouble("ml"));

					vacinas.add(vacina);

				}

				return vacinas;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
}
