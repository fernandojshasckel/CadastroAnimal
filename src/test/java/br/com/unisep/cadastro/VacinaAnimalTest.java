package br.com.unisep.cadastro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.unisep.cadastro.model.Animal;
import br.com.unisep.cadastro.model.Cidade;
import br.com.unisep.cadastro.model.Estado;
import br.com.unisep.cadastro.model.Raca;
import br.com.unisep.cadastro.model.TipoAnimal;
import br.com.unisep.cadastro.model.Vacina;
import br.com.unisep.cadastro.model.VacinaAnimal;

public class VacinaAnimalTest extends TestUtil{

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

		Vacina vacina = new Vacina();

		vacina.setVacina("RB-51");
		vacina.setMarca("MSD");
		vacina.setBula("Medicamento para brucelose");
		vacina.setMl(40);
		vacina.setPreco(105.2);

		VacinaAnimal vacinaAnimal = new VacinaAnimal();
		
		vacinaAnimal.setAnimal(animal);
		vacinaAnimal.setVacina(vacina);
		
		assertEquals(animal, vacinaAnimal.getAnimal());
		assertEquals(vacina, vacinaAnimal.getVacina());
		
	}
	
}
