package campominado.logica;

import java.util.ArrayList;
import java.util.List;

import campominado.excecao.ExplosaoException;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private int minas;

	private final List<Campo> campos = new ArrayList<Campo>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();

	}
	
	public void abrir(int linha , int coluna) {
		try {
			campos.stream().filter(c -> c.getLinha() == linha
					&& c.getColuna() == coluna).findFirst()
			.ifPresent(c->c.abrir());
		} catch (ExplosaoException e) {
			campos.forEach(c->c.setAberto(true));
			
			throw e;
		}
	}
	public void marcar(int linha , int coluna) {
		campos.stream().filter(c -> c.getLinha() == linha
				&& c.getColuna() == coluna).findFirst()
		.ifPresent(c->c.alterarMarcacao());
	}

	// i = linhas j = colunas
	private void gerarCampos() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				campos.add(new Campo(i, j));
			}
		}

	}

	private void associarVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		int minasArmadas = 0;

		do {
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = (int) campos.stream().filter(m -> m.isMinado()).count();
		} while (minasArmadas < minas);

	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciarCampo() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("  ");
		for(int c = 0 ; c<colunas ; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		sb.append("\n");
		
		
		int inicio = 0;
		for (int i = 0 ; i<linhas ; i++) {
			sb.append(i);
			sb.append(" ");
			for(int j = 0 ; j<colunas ; j++) {
				sb.append(" ");
				sb.append(campos.get(inicio));
				sb.append(" ");
				inicio++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
