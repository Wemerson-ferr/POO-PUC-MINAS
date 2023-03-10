import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PizzaTest {
    Pizza minhaPizza;
    static Double[] valores = new Double[9];
    
    @BeforeAll
    public static void iniciandoTeste(){
        for(int i=0; i<8; i++){
            valores[i] = 25.00D + (i*4);
        }
    }
    
    @BeforeEach
    public void criaUmaNovaPizzaPadrao(){
        minhaPizza = new Pizza();
    }

    @Test
    public void deveIniciarComValorAlterado(){
        Pizza tmp;
        for(int i=0; i<valores.length-1; i++){
            tmp = new Pizza(i);
            assertEquals(valores[i], tmp.somarTotal());
        }
    }

    @Test
    public void deveLimitarIngredientesEm08(){
        minhaPizza.adicionaIngrediente(9);
        assertNotEquals(9, minhaPizza.getIngredientes());
    }
    
    @Test
    public void deveIniciarComValorPadrao(){
        assertEquals(25.00D, minhaPizza.somarTotal());
    }

    @Test
    public void deveAdicionarOitoIngredientesDeUmaVez(){
        minhaPizza.adicionaIngrediente(8);
        assertEquals(8, minhaPizza.getIngredientes());
    }

    @Test
    public void deveAdicionarIngredientesDeUmEmUm(){
        for(int i=0; i<8; i++){
            assertEquals(i, minhaPizza.getIngredientes());
            minhaPizza.adicionaIngrediente();
        }
    }

    @Test
    public void deveRemoverUmIngredientes(){
        minhaPizza.adicionaIngrediente(8);
        minhaPizza.removerIngrediente();
        assertEquals(7, minhaPizza.getIngredientes());
    }

    @Test
    public void deveRemoverTodosOsIngredientesDoMax(){
        minhaPizza.adicionaIngrediente(8);
        for(int i= 8; i>0; i--){
            assertEquals(i, minhaPizza.getIngredientes());
            minhaPizza.removerIngrediente();
        }
    }

    @Test
    public void verificaSaidaPrecos(){
        minhaPizza = new Pizza();
        for(int i=0; i<valores.length-1; i++){
            assertEquals(valores[i], minhaPizza.somarTotal());
            minhaPizza.adicionaIngrediente();
        }
    }

    @Test
    public void testGerarNota() {
    minhaPizza.adicionaIngrediente(3);
    String notaEsperada = "Pizza: Queijo e Calabresa com 3 ingredientes adicionais.\nPreço total: 37.0";
    assertEquals(notaEsperada, minhaPizza.gerarNota());
}
}