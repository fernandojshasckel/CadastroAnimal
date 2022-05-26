package br.com.unisep.cadastro;

import org.junit.Test;

import br.com.unisep.cadastro.model.Raca;

public class RacaTest extends TestUtil{

	@Test
	@Override
	public void testarPropriedade() {

		Raca raca = new Raca();
		raca.setRaca("Nelore");
		raca.setCor("Branco");
		
	}

}
