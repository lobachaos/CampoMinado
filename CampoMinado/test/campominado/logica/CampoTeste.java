package campominado.logica;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import campominado.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo ;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testarVizinhoEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean result = campo.adicionarVizinho(vizinho);

		assertEquals(true, result);
	}

	@Test
	void testarVizinhoDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean result = campo.adicionarVizinho(vizinho);

		assertEquals(true, result);
	}

	@Test
	void testarVizinhoFalso() {
		Campo vizinho = new Campo(1, 1);
		boolean result = campo.adicionarVizinho(vizinho);

		assertEquals(false, result);
	}
		
		@Test
		void testarAlterarMarcacao() {
			campo.alterarMarcacao();
			assertTrue(campo.isMarcado());
			
		}
		
		@Test
		void abrirNaoMinadoEMarcado() {
			campo.alterarMarcacao();
			assertFalse(campo.abrir());
			
		}
		
		@Test
		void abrirNaoMinadoENaoMarcado() {
			assertTrue(campo.abrir());
			
		}
		
		@Test
		void abrirMinadoEMarcado() {
			campo.alterarMarcacao();
			campo.minar();
			
			assertFalse(campo.abrir());
		}
		
		@Test
		void abrirMinado() {
			campo.minar();
			assertThrows(ExplosaoException.class, 
					()-> campo.abrir());
		}
		

}
