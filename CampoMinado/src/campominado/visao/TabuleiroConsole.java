package campominado.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import campominado.excecao.ExplosaoException;
import campominado.excecao.SairException;
import campominado.logica.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {

		this.tabuleiro = tabuleiro;
		executarJogo();

	}

	private void executarJogo() {
		try {
			boolean continuar = true;

			while (continuar) {
				cicloDoJogo();

				System.out.println("Outra Partida ? (S/n) ");
				String resposta = entrada.nextLine();

				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciarCampo();
				}
			}
		} catch (SairException e) {

			System.out.println("Fim!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {

			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);

				String digitado = capturarValorDigitado("Digite (x,y)");
				Iterator<Integer> it = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();
				digitado = capturarValorDigitado("1 - Abrir ou 2 - Desmarcar");

				if ("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(it.next(), it.next());
				} else if ("2".equalsIgnoreCase(digitado)) {
					tabuleiro.marcar(it.next(), it.next());
				}

			}

			System.out.println("Win !");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Game Over!!");
		}
	}

	private String capturarValorDigitado(String text) {
		System.out.print(text);
		String digitado = entrada.nextLine();
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;
	}
}
