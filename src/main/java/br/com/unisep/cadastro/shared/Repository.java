package br.com.unisep.cadastro.shared;

import java.util.List;

public interface Repository<E> {

	E insert(E entity);

	E update(E entity);

	void delete(int id);

	E findOne(int id);

	List<E> findAll();
	
}
