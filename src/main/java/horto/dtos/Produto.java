package horto.dtos;

import java.sql.Date;

public class Produto {
	private int id;
	private String nome, tipo, especie;
	private Date inicio, fim;
	private Double preco;

	public Produto() {

	}

	public Produto(int id, String nome, String tipo, String especie, Double preco, Date inicio, Date fim) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.especie = especie;
		this.preco = preco;
		this.inicio = inicio;
		this.fim = fim;
	}

	public static class ProdutoBuilder {
		private Produto produto;
		ProdutoBuilder() {
			produto = new Produto();
		}

		public ProdutoBuilder id(int id) {
			produto.setId(id);
			return this;
		}

		public ProdutoBuilder tipo(String tipo) {
			produto.setTipo(tipo);
			return this;
		}

		public ProdutoBuilder especie(String especie) {
			produto.setEspecie(especie);
			return this;
		}

		public ProdutoBuilder preco(Double preco) {
			produto.setPreco(preco);
			return this;
		}
		public ProdutoBuilder nome(String nome) {
			produto.setNome(nome);
			return this;
		}
		public ProdutoBuilder inicio(Date inicio) {
			produto.setInicio(inicio);
			return this;
		}

		public ProdutoBuilder fim(Date fim) {
			produto.setFim(fim);
			return this;
		}
		public Produto build() {
			return produto;
		}
	}

	public static ProdutoBuilder builder() {
		return new ProdutoBuilder();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}

	@Override
	public String toString() {
		return this.getId() + " - " + this.getNome();
	}
}

