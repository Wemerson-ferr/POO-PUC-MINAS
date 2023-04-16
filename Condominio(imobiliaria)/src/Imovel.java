import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**<h1>Classe Imóvel</h1>
 * Classe abstrata, define atributos e métodos para criar objetos do tipo imóvel, como apartamento e casa.
 * @author Wemerson Ferreira
 */

public abstract class Imovel {
    protected Boolean alugado = false;
    protected BigDecimal valorDeVenda;
    protected String endereco;
    protected LocalDate anoDeConstrucao;
    protected int DESCONTO_QUINQUENAL;
    protected BigDecimal aluguelInicial;
    protected ArrayList<Beneficio> listaBeneficios;

    Imovel(Double valorVenda, String endereco, LocalDate anoConstrucao){
        if(valorVenda>0){
            this.valorDeVenda = new BigDecimal(valorVenda);
        }
        else{
            this.valorDeVenda = BigDecimal.ZERO;
        }
        this.endereco = endereco;
        this.anoDeConstrucao = anoConstrucao;
    }
    /**Método alugar:
     * <ul>
     *  <li>Define a taxa de seguro para casas, e taxa de condomínio para apartamentos.</li>
     * </ul>
     * @param taxa a ser paga pelo imóvel.
     */
    protected abstract void alugar(Double taxa);

    /**Método encerrar aluguel:
     * <ol>
     *  <li>Irá zerar o valor da taxa, seguro de incêndio(para casas) e condomínio(para apartamentos)</li>
     *  <li>Define o Boolean alugado para false</li>
     * </ol>
     */
    protected abstract void encerrarAluguel();

    /**Método calcular aluguel:
     * <ol>
     *  <li>Este método calcula o valor total a ser pago pelo locatário! Para o aluguel sem as taxas, use 
     *  {@code}calcularAluguelLiquido(){@code}.</li>
     *  <li>Utiliza o calcularAluguelLiquido para obter o aluguel com acréscimo de benefícios. </li>
     *  <li>Irá somar a taxa de incêndio, divido em 12x, ou condomínio mensal. </li>
     * </ol>
     * @return Double: valor do aluguel, com todos adicionais.
     */
    protected abstract Double calcularAluguelBruto();
    protected void adicionarBeneficios(Beneficio adicional){
        this.listaBeneficios.add(adicional);
    }

    /**Método calcula aluguel liquido:
     *<ol>
     *  <li>Calcula o valor do aluguel sem taxa de condomínio(para apartamento) e seg. incêndio(para casa)</li>
     *  <li>Este método percorrerá a lista de benefícios e somará o total.</li>
     *  <li>Irá somar a taxa de incêndio, divido em 12x, ou condomínio mensal. </li>
     *  <li>Irá chamar método interno para calculo de idade e descontar o valor determinado cada 5 anos de idade</li>
     *</ol>
     */
    protected abstract Double calcularAluguelLiquido();

    /**Método auxiliar para calcular a idade do imóvel
     * Leva em consideração a data de hoje(quando é chamado)
     * @return int idade
     */
    protected int verificaIdadeImovel(){
        LocalDate hoje = LocalDate.now(); // obtém a data atual
        int idade = hoje.getYear() - this.anoDeConstrucao.getYear(); // calcula a diferença entre os anos
        return idade;
    }

    /**Método adicionarBeneficio:
     * Recebe um objeto do tipo beneficio e insere na lista de benefícios do imóvel.
     * 
     * @param beneficio
     */
    public void adicionarBeneficio(Beneficio beneficio){
        listaBeneficios.add(beneficio);
    }

    /** Método removerBeneficio:
     * <ul>
     *  <li>Remove um benefício na lista do imóvel, deve se informar a posição do item. Considerando o primeiro item como 1.</li>
     * <li>Para listar os benefícios e vizualizar a ordem use {@code}listarBeneficios().{@code}</li>
     * </ul>
     * @param index
     */
    public void removerBeneficios(int index){
        this.listaBeneficios.remove(index-1);
    }
    public String listarBeneficios(){
        StringBuilder stringBeneficios = new StringBuilder();
        int cont = 1;
        for(Beneficio beneficio : listaBeneficios){
            stringBeneficios.append(cont+" - "+ beneficio.toString()+"\n");
        }
        return stringBeneficios.toString();
    }

    public String getEndereco(){
        return this.endereco;
    }
}
