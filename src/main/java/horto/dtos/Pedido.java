package horto.dtos;

public class Pedido {

	String origem, finalidade;
	double valor;
	int id, solicitante, produto;

	public Pedido() {

	}

	public Pedido(int id, int solicitante, int produto, String origem, String finalidade, double valor) {
		this.origem = origem;
		this.finalidade = finalidade;
		this.valor = valor;
		this.id = id;
		this.solicitante = solicitante;
		this.produto = produto;
	}

	public static class PedidoBuilder {
		private Pedido pedido;

		public PedidoBuilder() {
			pedido = new Pedido();
		}

		public PedidoBuilder origem(String origem) {
			pedido.setOrigem(origem);
			return this;
		}

		public PedidoBuilder finalidade(String finalidade) {
			pedido.setFinalidade(finalidade);
			return this;
		}

		public PedidoBuilder valor(double valor) {
			pedido.setValor(valor);
			return this;
		}

		public PedidoBuilder id(int id) {
			pedido.setId(id);
			return this;
		}

		public PedidoBuilder solicitante(int solicitante) {
			pedido.setSolicitante(solicitante);
			return this;
		}

		public PedidoBuilder produto(int produto) {
			pedido.setProduto(produto);
			return this;
		}

		public Pedido build() {
			return pedido;
		}
	}

	public static PedidoBuilder builder() {
		return new PedidoBuilder();
	}

	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getFinalidade() {
		return finalidade;
	}
	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(int solicitante) {
		this.solicitante = solicitante;
	}

	public int getProduto() {
		return produto;
	}
	public void setProduto(int produto) {
		this.produto = produto;
	}
}
