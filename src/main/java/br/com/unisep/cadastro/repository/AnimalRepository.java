package br.com.unisep.cadastro.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.unisep.cadastro.model.Animal;
import br.com.unisep.cadastro.model.Cidade;
import br.com.unisep.cadastro.model.Estado;
import br.com.unisep.cadastro.model.Raca;
import br.com.unisep.cadastro.model.TipoAnimal;

public class AnimalRepository {

	private final Connection connection;

	public	AnimalRepository(Connection connection) {
		this.connection = connection;
	}

	public Animal insert(Animal animal) {

		String sql = "insert into Animal(animal, id_nacionalidadecidade, id_nacionalidadeestado, id_raca, estimativavida, peso, tamanho, vacinado, id_tipoanimal) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, animal.getAnimal());
			preparedStatement.setObject(2, animal.getNacionalidadeCidade());
			preparedStatement.setObject(3, animal.getNacionalidadeEstado());
			preparedStatement.setInt(4, animal.getRaca().getId());
			preparedStatement.setInt(5, animal.getEstimativaVida());
			preparedStatement.setDouble(6, animal.getPeso());
			preparedStatement.setDouble(7, animal.getTamanho());
			preparedStatement.setBoolean(8, animal.isVacinado());
			preparedStatement.setInt(9, animal.getTipoAnimal().getId());

			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				resultSet.next();

				animal.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return animal;
	}

	public Animal update(Animal animal) {

		String sql = "update Animal set animal = ?, id_nacionalidadecidade = ?, id_nacionalidadeestado = ?, id_raca = ?, estimativavida = ?, peso = ?, tamanho = ?, vacinado = ?, id_tipoanimal = ? where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, animal.getAnimal());
			preparedStatement.setInt(2, animal.getNacionalidadeCidade().getId());
			preparedStatement.setInt(3, animal.getNacionalidadeEstado().getId());
			preparedStatement.setInt(4, animal.getRaca().getId());
			preparedStatement.setInt(5, animal.getEstimativaVida());
			preparedStatement.setDouble(6, animal.getPeso());
			preparedStatement.setDouble(7, animal.getTamanho());
			preparedStatement.setBoolean(8, animal.isVacinado());
			preparedStatement.setInt(9, animal.getTipoAnimal().getId());
			preparedStatement.setInt(10, animal.getId());
			
			preparedStatement.executeUpdate();

			return animal;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}

	public void delete(int id) {

		String sql = "delete from Animal where id = ?";

		try (PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
			prepareStatement.setInt(1, id);

			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public Animal findOne(int id) {

		String sql = "select * from Animal where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				resultSet.next();

				Animal animal = new Animal();
				Raca raca = new Raca();
				Estado estado = new Estado();
				Cidade cidade = new Cidade();
				TipoAnimal tipoAnimal = new TipoAnimal();
				
				animal.setAnimal(resultSet.getString("animal"));
				raca.setId(resultSet.getInt("id_raca"));
				animal.setPeso(resultSet.getFloat("peso"));
				animal.setTamanho(resultSet.getFloat("tamanho"));
				animal.setVacinado(resultSet.getBoolean("vacinado"));
				animal.setEstimativaVida(resultSet.getInt("estimativavida"));
				estado.setId(resultSet.getInt("id_nacionalidadeestado"));
				cidade.setId(resultSet.getInt("id_nacionalidadecidade"));
				tipoAnimal.setId(resultSet.getInt("id_tipoanimal"));
				
				animal.setRaca(raca);
				animal.setNacionalidadeCidade(cidade);
				animal.setNacionalidadeEstado(estado);
				animal.setTipoAnimal(tipoAnimal);
				
				return animal;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<Animal> findAll() {

		String sql = "select * from Animal";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) { 

				List<Animal> animais = new ArrayList<Animal>();

				while (resultSet.next()) {

					Animal animal = new Animal();
					Raca raca = new Raca();
					Estado estado = new Estado();
					Cidade cidade = new Cidade();
					TipoAnimal tipoAnimal = new TipoAnimal();
					
					animal.setAnimal(resultSet.getString("animal"));
					animal.setEstimativaVida(resultSet.getInt("estimativavida"));
					cidade.setId(resultSet.getInt("id_nacionalidadecidade"));
					estado.setId(resultSet.getInt("id_nacionalidadeestado"));
					animal.setPeso(resultSet.getFloat("peso"));
					raca.setId(resultSet.getInt("id_raca"));
					animal.setVacinado(resultSet.getBoolean("vacinado"));
					animal.setTamanho(resultSet.getFloat("tamanho"));
					tipoAnimal.setId(resultSet.getInt("id_tipoanimal"));
					
					animal.setRaca(raca);
					animal.setNacionalidadeCidade(cidade);
					animal.setNacionalidadeEstado(estado);
					animal.setTipoAnimal(tipoAnimal);
					
					animais.add(animal);

				}

				return animais;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
	
}
