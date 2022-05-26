package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.unisep.cadastro.model.Cidade;
import br.com.unisep.cadastro.model.Estado;

public class CidadeTest extends TestUtil{

	@Test
	@Override
	public void testarPropriedade() {
		
		Estado estado = new Estado();
		
		estado.setNome("Santa Catarina");
		estado.setSigla("SC");
		
		Cidade cidade = new Cidade();
		
		cidade.setNome("Xanxere");
		cidade.setEstado(estado);
		
		assertEquals("Xanxere", cidade.getNome());
		assertEquals(estado, cidade.getEstado());
		
	}
	
}
