package br.com.unisep.cadastro.model;

public class VacinaAnimal {

	private int Id;
	private Animal animal;
	private Vacina vacina;
	
	public int getId() {
		return Id; 
	}
	public void setId(int id) {
		Id = id;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Vacina getVacina() {
		return vacina;
	}
	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}
	
}
