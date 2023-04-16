import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**<h1>Classe Apartamento:</h1>
 * <p>Classe filha de {@code Imovel}, seguindo todas as regras de sua mãe.</p>
 * <p>Representa um apartamento gerenciado por um sistema imobiliário</p>
 * <p>Tem um atributo que define a taxa de condomínio a ser paga mensalmente pelo locatário.</p>
 * @author Wemerson Ferreira
 */
public class Apartamento extends Imovel {
    private BigDecimal taxaCondominio;

    Apartamento(){
        super(0.0, "Não informado", LocalDate.now());
        this.taxaCondominio = BigDecimal.ZERO;
    }
    /**<h2>Apartamento(Double valorVenda, String endereco, LocalDate dataConstrucao)</h2>
     * <ol>
     *  <li>Define o valor de venda do apartamento</li>
     *  <li>Define o endereço da apartamento</li>
     *  <li>Define o aluguel da apartamento, seguindo o calculo de 0,04% do valor de venda</li>
     * </ol>
     * @param valorVenda
     * @param endereco
     * @param dataConstrucao
     */
    Apartamento(Double valorVenda, String endereco, LocalDate dataConstrucao){
        super(valorVenda, endereco, dataConstrucao);
        this.aluguelInicial = new BigDecimal(valorVenda*0.004D).setScale(2, RoundingMode.HALF_UP);
        this.DESCONTO_QUINQUENAL = 5;
    }

    /**Especialização do método:
     * <p>
     * Ao definir um apartamento para alugar, deve-se informar a taxa do condomínio.<br>
     * Define a taxa de condomínio a ser paga, este valor não incidirá nos ganhos da imobiliária, nem do locatário.<br>
     * Define o Boolean alugado como true, evitando erros de dupla locação.</p> 
     */
    @Override
    public void alugar(Double taxa) {
        this.taxaCondominio = new BigDecimal(taxa);
        this.alugado = true;
    }

    @Override
    public void encerrarAluguel() {
        this.taxaCondominio = BigDecimal.ZERO;
        this.alugado = false;
    }
    @Override
    public Double calcularAluguelBruto() {
        BigDecimal aux = new BigDecimal(calcularAluguelLiquido());
        aux.add(taxaCondominio);
        return aux.doubleValue();
    }
    @Override
    public Double calcularAluguelLiquido() {
        BigDecimal aluguelTotal = this.aluguelInicial;

        for (Beneficio beneficio : this.listaBeneficios) {
            aluguelTotal.add(new BigDecimal(beneficio.getValor()));
        }

        int idadeQuinquenal = verificaIdadeImovel()/5; //Divide a idade do imóvel por 5, para saber quanto cobrar
        BigDecimal descontoQuinquenal = new BigDecimal(DESCONTO_QUINQUENAL / 100.0);
        BigDecimal porcentagemPagamento = BigDecimal.ONE.subtract(descontoQuinquenal);
        for (int i = idadeQuinquenal; i > 0; i--) {
            aluguelTotal = aluguelTotal.multiply(porcentagemPagamento);
        }

        return aluguelTotal.doubleValue();
    }
}
