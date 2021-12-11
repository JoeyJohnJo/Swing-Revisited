package horto.servicos;

import horto.banco.BancoDeDados;

import java.util.List;

public abstract class Servico<T> {
	protected final BancoDeDados BD = BancoDeDados.get();
	public abstract List<T> buscar();
	public abstract void editar(T t);
	public abstract void deletar(int id);
	public abstract void inserir(T t);
}
