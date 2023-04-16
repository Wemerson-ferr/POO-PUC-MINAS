import java.math.BigDecimal;

/**<h1>Classe Beneficio:</h1>
 * <p>Representa benefícios atrelados à um imóvel, seja uma piscina, área de lazer, etc.</p>
 * <p>Tem os seguintes atributos para uso:
 * <ul>
 *  <li>{@code String nome}</li>
 *  <li>{@code BigDecimal valor}</li>
 *  <li>{@code String descricao}</li>
 * </ul>
 * </p>
 * @author Wemerson Ferreira
 */
public class Beneficio {
    String tipo;
    BigDecimal valor;
    String descricao;

    /**<h2>Construtor Beneficio():</h2>
     * <p>NÂO RECOMENDADO!</p>
     * Por não conter parâmetros irá iniciar o objeto com: </p>
     * <ul>
     *  <li>{@code String nome = "Não informado!"}</li>
     *  <li>{@code BigDecimal valor = 0.00}</li>
     *  <li>{@code String descricao = "Não informado!"}</li>
     * </ul>
     * <p>Recomenda-se usar o {@code Beneficio(String nome, BigDecimal valorAcrescimo, String descricao)}</p>
     */
    Beneficio(){
        this("Não informado!", new BigDecimal("0.00"), "Não informado!");
    }

    /**<h2>Construtor Beneficio(String nome, BigDecimal valorAcrescimo, String descricao):</h2>
     * <p>Inicia o objeto com os valores fornecidos, garantindo a integridade do objeto.</p>
     * <p>Caso prefira iniciar com um valor em Double, use {@codeBeneficio(String nome, Double valorAcrescimo, String descricao)}
     */
    Beneficio(String nome, BigDecimal valorAcrescimo, String descricao) {
        if(valorAcrescimo.compareTo(BigDecimal.ZERO)>=0){
            this.tipo= nome;
            this.descricao = descricao;
            this.valor = valorAcrescimo;
        }
    }

    /**<h2>Construtor Beneficio(String nome, BigDecimal valorAcrescimo, String descricao):</h2>
     * <p>Inicia o objeto com os valores fornecidos, garantindo a integridade do objeto.</p>
     * <p>Como foi inciado com valor em Double, irá fazer a conversão internamente.</p>
     */
    Beneficio(String nome, Double valorAcrescimo, String descricao) {
        if(valorAcrescimo>0){
            this.tipo= nome;
            this.descricao = descricao;
            this.valor = new BigDecimal(valorAcrescimo);
        }
    }

    public Double getValor(){
        return this.valor.doubleValue();
    }

    @Override
    public String toString() {
        return "tipo: "+ this.tipo + " valor: "+ valor.toString() + " Descrição: "+ descricao;
    }
}
