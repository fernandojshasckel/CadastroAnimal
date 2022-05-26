package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.unisep.cadastro.model.Animal;
import br.com.unisep.cadastro.model.Cidade;
import br.com.unisep.cadastro.model.Estado;
import br.com.unisep.cadastro.model.Raca;
import br.com.unisep.cadastro.model.TipoAnimal;


public class AnimalTest extends TestUtil{

	@Test
	@Override
	public void testarPropriedade() {
		
		Estado estado = new Estado();
		estado.setNome("Santa Catarina");
		estado.setSigla("SC");
		
		Cidade cidade = new Cidade();
		cidade.setNome("Xanxere");
		cidade.setEstado(estado);
		
		Raca raca = new Raca();
		raca.setRaca("Holandes");
		raca.setCor("Preto e Branco");
		
		TipoAnimal tipoAnimal = new TipoAnimal();
		tipoAnimal.setTipo("Corte");
		
		Animal animal = new Animal();
		
		animal.setAnimal("Boi");
		animal.setEstimativaVida(12);
		animal.setNacionalidadeCidade(cidade);
		animal.setNacionalidadeEstado(estado);
		animal.setPeso(120);
		animal.setRaca(raca);
		animal.setTamanho(1);
		animal.setTipoAnimal(tipoAnimal);
		animal.setVacinado(true);
		
		
		assertEquals("Boi", animal.getAnimal());
		assertEquals(12, animal.getEstimativaVida());
		assertEquals(cidade, animal.getNacionalidadeCidade());
		assertEquals(estado, animal.getNacionalidadeEstado());
		assertEquals("Holandes", animal.getRaca().getRaca());
		assertEquals("Corte", animal.getTipoAnimal().getTipo());
		assertEquals(true, animal.isVacinado());
	}
}
