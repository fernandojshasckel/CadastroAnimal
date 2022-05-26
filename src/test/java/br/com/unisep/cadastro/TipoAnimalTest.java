package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.unisep.cadastro.model.TipoAnimal;


public class TipoAnimalTest extends TestUtil{

	@Test
	@Override
	public void testarPropriedade() {

		TipoAnimal tipoAnimal = new TipoAnimal();
		
		tipoAnimal.setTipo("Corte");
		
		assertEquals("Corte", tipoAnimal.getTipo());
		
	} 

}
