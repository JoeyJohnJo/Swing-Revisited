package horto.servicos;

import horto.Constantes;
import horto.dtos.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoProdutos extends Servico<Produto> {
	private static ServicoProdutos INSTANCE = null;

	private ServicoProdutos() {

	}

	public List<Produto> buscar() {
		var list = new ArrayList<Produto>();
		var rs = BD.select(Constantes.SELECT_PRODUTOS);
		try {
			while (rs.next()) {
				list.add(
					new Produto(
						rs.getInt("id"),
						rs.getString("nome_cient"),
						rs.getString("tipo"),
						rs.getString("especie"),
						rs.getDouble("preco"),
						rs.getDate("data_inicio"),
						rs.getDate("data_final")
					)
				);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return list;
	}

	public void editar(Produto produto) {
		BD.transformar(Constantes.atualizarProduto(produto));
	}

	public void deletar(int idDoProduto) {
		BD.transformar(Constantes.deletarProduto(idDoProduto));
	}

	public void inserir(Produto produto) {
		BD.transformar(Constantes.adicionarProduto(produto));
	}

	public static ServicoProdutos get() {
		if (INSTANCE == null)
			INSTANCE = new ServicoProdutos();
		return INSTANCE;
	}

}
