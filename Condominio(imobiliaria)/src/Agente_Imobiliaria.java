import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**<h1>Classe Agente_Imobiliaria<h1>
 * <p>Classe filha de {@code Cliente}</p>
 * <p>Representa um agente imobiliário em um sistema.</p>
 * <p>Contém uma lista estática de imóveis, ou seja, todos os agentes tem acesso a lista.</p>
 * <p>A comissão por cada aluguel é definida pela variável {@code final int COMISSAO}, sendo esta no valor de 12</p>
 * 
 * @author Wemerson Ferreira
 */
public class Agente_Imobiliaria extends Cliente {
    private static final int COMISSAO = 12;
    private static HashMap<String, Imovel> ListaImoveis;

    /**<h2>Construtor Agente_Imobiliaria():</h2>
     * <p>NÃO RECOMENDADO!</p>
     * <p>Inicia um objeto Agente_Imobiliario sem parâmetros, portanto as informações serão imprecisas</p>
     * <p>Recomenda-se utilizar o {@code Agente_Imobiliaria(String nome, String login, String senha)}.</p>
     */
    Agente_Imobiliaria(){
        super();
    }
    /**<h2>Construtor Agente_Imobiliaria(String nome, String login, String senha):</h2>
     * <p>Inicia um objeto Agente_Imobiliario com parâmetros fornecidos, garantindo mais autenticidade dos dados</p>
     */
    Agente_Imobiliaria(String nome, String login, String senha){
        super(nome, login, senha);
    }

    /**<h2>Método adicionaImovel():</h2>
     * <p>Adiciona um objeto {@code Imovel} na lista de imoveis da imobiliária.</p>
     * <p>IMPORTANTE: O endereço é utilizado como chave, então tenha certeza de sua integridade</p>
     * @param imovel
     */
    public void adicionarImovel(Imovel imovel){
        if(imovel!=null){
            ListaImoveis.put(imovel.getEndereco(), imovel);
        }
    }

    /**<h2>Método RemoverImovel():</h2>
     * <p>Remove um objeto {@code Imovel} na lista de imoveis da imobiliária.</p>
     * <p>IMPORTANTE: O endereço é utilizado como chave para remoção então tenha certeza de sua integridade</p>
     * @param imovel
     */
    public void removerImovel(Imovel imovel){
        if(imovel!=null){
            ListaImoveis.remove(imovel.getEndereco());
        }
    }
    
    /**<h2>Método calcularRendaTotal():<h2>
     * <p>Este método calcula o valor ganho em cada aluguel, e retorna a soma</p>
     * <p>Percorre a lista de imoveis da imobiliária calculando o aluguel</p>
     * <p>Caso precise de um aluguel especifico utilize {@code calcularRendaIndividual(String endereco)}</p>
     * @return Double rendaTotal
     * 
     */
    public Double calcularRendaTotal(){
        BigDecimal valorTotal = new BigDecimal(0.00);
        if(!ListaImoveis.isEmpty()){
            for (Map.Entry<String, Imovel> entry : ListaImoveis.entrySet()) {
                valorTotal.add(new BigDecimal(entry.getValue().calcularAluguelLiquido()));
            }
            Double porcentagemComissao = (double)COMISSAO/100;
            valorTotal.multiply(new BigDecimal(porcentagemComissao));
        }
        return valorTotal.doubleValue();
    }

    /**<h2>Método calcularRendaIndividual(String endereco)</h2>
     * <p>Este método calcula o valor arrecadado de um aluguel somente!</p>
     * <p>A chave para procura é o endereço, então senha certeza de sua autenticidade!<p/>
     * <p>Caso precise da renda total com alugueis utilize {@code calcularRendaTotal()}</p>
     * @param endereco
     * @return Double valorAluguel
     */
    public static Double calcularRendaIndividual(String endereco){
        BigDecimal valorAluguel;
        if(endereco!= null){
            if(!ListaImoveis.isEmpty()){
                Imovel imovel = ListaImoveis.get(endereco);
                valorAluguel = new BigDecimal(imovel.calcularAluguelLiquido());
                Double porcentagemComissao = (double)COMISSAO/100;
                valorAluguel.multiply(new BigDecimal(porcentagemComissao));
                return valorAluguel.doubleValue();
            }
            else{
                return 0.0;
            }
        }
        else{
            return 0.0;
        }
    }
}
