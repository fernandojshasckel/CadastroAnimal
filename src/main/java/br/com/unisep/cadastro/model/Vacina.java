package br.com.unisep.cadastro.model;

public class Vacina {

	private int id;
	private String vacina;
	private String marca;
	private double ml;
	private String bula;
	private double preco;
	
	public int getId() {
		return id; 
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getVacina() {
		return vacina;
	}
	public void setVacina(String vacina) {
		this.vacina = vacina;
	}
	public double getMl() {
		return ml;
	}
	public void setMl(double ml) {
		this.ml = ml;
	}
	public String getBula() {
		return bula;
	}
	public void setBula(String bula) {
		this.bula = bula;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void exibirInfo() {
		System.out.println(" Vacina: " + this.vacina + "\n Marca: " + this.marca + "\n Quantidade ml: " + this.ml + "\n Bula: " +
		this.bula + "\n Preço: " + this.preco);
	}
	
}
