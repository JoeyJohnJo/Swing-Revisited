package horto;

import horto.banco.BancoDeDados;
import horto.gui.Janela;

public class Main {
	public static void main(String[] args) {
		BancoDeDados.get();
		new Janela(Constantes.LARGURA, Constantes.ALTURA);
	}

}
