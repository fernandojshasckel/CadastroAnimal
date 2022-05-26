package br.com.unisep.cadastro.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.unisep.cadastro.model.Cidade;
import br.com.unisep.cadastro.model.Estado;


public class CidadeRepository {

	private final Connection connection;

	public CidadeRepository(Connection connection) {
		this.connection = connection;
	}

	public Cidade insert(Cidade cidade) {

		String sql = "insert into cidade(nome, id_estado) values (?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, cidade.getNome());
			preparedStatement.setInt(2, cidade.getEstado().getId());

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				resultSet.next(); 

				cidade.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return cidade;
	}

	public Cidade update(Cidade cidade) {

		String sql = "update cidade set nome = ?, id_estado = ? where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, cidade.getNome());
			preparedStatement.setInt(2, cidade.getEstado().getId());
			preparedStatement.setInt(3, cidade.getId());

			preparedStatement.executeUpdate();

			return cidade;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}

	public void delete(int id) {

		String sql = "delete from cidade where id = ?";

		try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
			prepareStatement.setInt(1, id);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public Cidade findOne(int id) {

		String sql = "select * from cidade where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				resultSet.next();

				Cidade cidade = new Cidade();
				Estado estado = new Estado();
				cidade.setId(resultSet.getInt("id"));
				cidade.setNome(resultSet.getString("nome"));
				estado.setId(resultSet.getInt("id_estado"));   
				cidade.setEstado(estado);			  
				
				return cidade;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<Cidade> findAll() {

		String sql = "select * from cidade";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) { 

				List<Cidade> cidades = new ArrayList<Cidade>();

				while (resultSet.next()) {

					Cidade cidade = new Cidade();
					Estado estado = new Estado();
					
					cidade.setNome(resultSet.getString("nome"));
					estado.setId(resultSet.getInt("id_estado"));

					cidade.setEstado(estado);
					cidades.add(cidade);

				}

				return cidades;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
}
