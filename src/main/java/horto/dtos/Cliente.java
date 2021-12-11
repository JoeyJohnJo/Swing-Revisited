package horto.dtos;

public class Cliente {
	private String email, telefone, documento, nome, cidade;
	private int id;

	public Cliente() {

	}

	public Cliente(int id, String email, String telefone, String documento, String nome, String cidade) {
		this.id = id;
		this.email = email;
		this.telefone = telefone;
		this.documento = documento;
		this.nome = nome;
		this.cidade = cidade;
	}
	public static class ClienteBuilder {
		private Cliente cliente;
		ClienteBuilder() {
			cliente = new Cliente();
		}

		public ClienteBuilder id(int id) {
			cliente.setId(id);
			return this;
		}

		public ClienteBuilder email(String email) {
			cliente.setEmail(email);
			return this;
		}

		public ClienteBuilder telefone(String telefone) {
			cliente.setTelefone(telefone);
			return this;
		}

		public ClienteBuilder documento(String documento) {
			cliente.setDocumento(documento);
			return this;
		}
		public ClienteBuilder nome(String nome) {
			cliente.setNome(nome);
			return this;
		}
		public ClienteBuilder cidade(String cidade) {
			cliente.setCidade(cidade);
			return this;
		}
		public Cliente build() {
			return cliente;
		}
	}

	public static ClienteBuilder builder() {
		return new ClienteBuilder();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return this.getId() + " - " + this.getNome();
	}
}
