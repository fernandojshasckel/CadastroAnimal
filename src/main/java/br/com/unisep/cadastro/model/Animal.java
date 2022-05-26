package br.com.unisep.cadastro.model;

public class Animal {
	private int id;
	private String animal;
	private Raca raca;
	private float peso;
	private float tamanho;
	private boolean vacinado;
	private int estimativaVida;  
	private Estado nacionalidadeEstado;
	private Cidade nacionalidadeCidade;
	private TipoAnimal tipoAnimal;
	
	public int getId() {
		return id; 
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public Raca getRaca() {
		return raca;
	}
	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getTamanho() {
		return tamanho;
	}
	public void setTamanho(float tamanho) {
		this.tamanho = tamanho;
	}
	public boolean isVacinado() {
		return vacinado;
	}
	public void setVacinado(boolean vacinado) {
		this.vacinado = vacinado;
	}
	public int getEstimativaVida() {
		return estimativaVida;
	}
	public void setEstimativaVida(int estimativaVida) {
		this.estimativaVida = estimativaVida;
	}
	public Estado getNacionalidadeEstado() {
		return nacionalidadeEstado;
	}
	public void setNacionalidadeEstado(Estado nacionalidadeEstado) {
		this.nacionalidadeEstado = nacionalidadeEstado;
	}
	public Cidade getNacionalidadeCidade() {
		return nacionalidadeCidade;
	}
	public void setNacionalidadeCidade(Cidade nacionalidadeCidade) {
		this.nacionalidadeCidade = nacionalidadeCidade;
	}
	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}
	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
}
