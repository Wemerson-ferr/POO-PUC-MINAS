import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
/**<h1>Classe Locador:</h1>
 * <p>Classe filha de {@code Cliente}, e representa um locador, ou seja, o dono do imóvel a ser alugado dentro de um sistema imobiliário.</p>
 * <p>Contém uma lista de imóveis que o locador tem no sistema, sendo possível adicionar, remover e consultar dessa lista.</p>
 * @author Wemerson Ferreira
 */
public class Locador extends Cliente {
    HashMap<String, Imovel> ListaImoveis;
    
    /**<h2>Locador()</h2>
     * <p>NÂO RECOMENDADO!</p>
     * <p>Inicia um objeto com valores padrão da classe mãe, sem informações pertinentes.</p>
     * <p>Recomenda-se o uso do {@code Locador(String nome, String login, String senha)}, para garantir integridade.</p>
     */
    Locador(){
        super();
    }

    /**<h2>Locador(String nome, String login, String senha)</h2>
     * <p>Inicia um objeto com valores fornecidos via parâmetro.</p>
     * <p>Garante mais integridade dos dados.</p>
     * 
     * @param String nome
     * @param String login
     *  @param String senha
     */
    Locador(String nome, String login, String senha){
        super(nome, login, senha);
    }

    /**<h2>Locador(String nome, String login, String senha)</h2>
     * <p>Inicia um objeto com valores fornecidos via parâmetro.</p>
     * <p>Garante mais integridade dos dados.</p>
     * <p>Inicia o locador com um imóvel fornecido via parâmetro.</p>
     * 
     * @param String nome
     * @param String login
     * @param String senha
     * @param Imovel imovel
     */
    Locador(String nome, String login, String senha, Imovel imovel){
        super(nome, login, senha);
        adicionarImovel(imovel);
    }
    /**<h2>Método adicionarImovel(Imovel imovel)</h2>
     * <p>Adiciona o objeto {@code Imovel} fornecido a lista de imoveis do usuário.</p>
     * <p>IMPORTANTE: O endereço é utilizado como chave, então tenha certeza de sua autenticidade</p>
     * @param imovel, objeto fornecido por parâmetro.
     */
    public void adicionarImovel(Imovel imovel){
        if(imovel!=null){
            ListaImoveis.put(imovel.getEndereco(), imovel);
        }
    }

    /**<h2>Método removerImovel(Imovel imovel)</h2>
     * <p>Remove o objeto {@code Imovel} fornecido da lista de imoveis do usuário.</p>
     * <p>IMPORTANTE: O endereço é utilizado como chave, então tenha certeza de sua autenticidade</p>
     * 
     */
    public void removerImovel(Imovel imovel){
        if(imovel!=null){
            ListaImoveis.remove(imovel.getEndereco());
        }
    }

    /**<h1>Método rendaBruta():</h2>
     * <p>Calcula quanto os alugueis do usuário rendem, sem retirar a porcentagem da imobiliária.</p>
     * <p>Para calculo com descontos utilize {@code rendaLiquida()}</p>
     * @return Double rendaBruta
     */
    public Double rendaBruta(){
        BigDecimal valorTotal = new BigDecimal(0.00);
        if(!ListaImoveis.isEmpty()){
            for (Map.Entry<String, Imovel> entry : ListaImoveis.entrySet()) {
                valorTotal.add(new BigDecimal(entry.getValue().calcularAluguelLiquido()));
            }
        }
        return valorTotal.doubleValue();
    }

    /**<h1>Método rendaLiquida():</h2>
     * <p>Calcula quanto os alugueis do usuário rendem, retirando a porcentagem da imobiliária, e taxas externas(seguro ou condomínio).</p>
     * <p>Para calculo de renda bruta {@code rendaBruta()}</p>
     * @return Double rendaLiquida
     */
    public Double rendaLiquida(){
        BigDecimal valorLiquido = new BigDecimal(0.00);
        BigDecimal desconto = new BigDecimal(0.00);
        if(!ListaImoveis.isEmpty()){
            for (Map.Entry<String, Imovel> entry : ListaImoveis.entrySet()) {
                valorLiquido.add(new BigDecimal(entry.getValue().calcularAluguelLiquido()));
                desconto.add(new BigDecimal(Agente_Imobiliaria.calcularRendaIndividual(entry.getKey())));
            }
            valorLiquido.subtract(desconto);
        }
        return valorLiquido.doubleValue();
    }
}
