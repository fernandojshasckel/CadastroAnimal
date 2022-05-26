package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.unisep.cadastro.model.Vacina;

public class VacinaTest extends TestUtil{

	@Test
	@Override
	public void testarPropriedade() {

		Vacina vacina = new Vacina();
		
		vacina.setVacina("RB-51");
		vacina.setMarca("MSD");
		vacina.setBula("Medicamento para brucelose");
		vacina.setMl(40);
		vacina.setPreco(105.2);
		
		assertEquals("RB-51", vacina.getVacina());
		assertEquals("MSD", vacina.getMarca());
		assertEquals("Medicamento para brucelose", vacina.getBula());
		
		
	}
	
}
