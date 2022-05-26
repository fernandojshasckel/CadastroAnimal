package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import br.com.unisep.cadastro.model.Estado;

public class EstadoTest extends TestUtil{

	@Test
	@Override
	public void testarPropriedade() {

		Estado estado = new Estado();
		
		estado.setNome("Santa Catarina");
		estado.setSigla("SC");
		
		assertEquals("Santa Catarina", estado.getNome());
		assertNotEquals("PR", estado.getSigla());
		assertEquals("SC", estado.getSigla());
		
	}
	
}
