import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
/**<h1>Classe Casa</h1>
 * <p>Filha de {@code Imovel}</p>
 * <p>Representa uma casa gerenciado por um sistema imobiliário</p>
 * <p>Tem um atributo que define o seguro anual, este será diluído no aluguel em 12x.</p>
 * 
 * @author Wemerson Ferreira
 */
public class Casa extends Imovel {
    private BigDecimal seguroIncendio;

    Casa(){
        super(0.0, "Não informado", LocalDate.now());
        this.seguroIncendio = BigDecimal.ZERO;
    }
    /**<h2>Casa(Double valorVenda, String endereco, LocalDate dataConstrucao)</h2>
     * <ol>
     *  <li>Define o valor de venda da casa</li>
     *  <li>Define o endereço da casa</li>
     *  <li>Define o aluguel da casa, seguindo o calculo de 0,05% do valor de venda</li>
     * </ol>
     * @param valorVenda
     * @param endereco
     * @param dataConstrucao
     */
    Casa(Double valorVenda, String endereco, LocalDate dataConstrucao){
        super(valorVenda, endereco, dataConstrucao);
        this.aluguelInicial = new BigDecimal(valorVenda*0.005D).setScale(2, RoundingMode.HALF_UP);
        this.DESCONTO_QUINQUENAL = 10;
    }

    /** Especialização para casas:
     * <li>Define o seguro contra incêndio de imóveis do tipo casa no processo de aluguel</li>
     * <li>Esta taxa deve ser informada em seu total, para pagamento em 12x junto ao aluguel.</li>
     * Define o Boolean alugado como true, evitando erros de dupla locação.
     */
    @Override
    public void alugar(Double taxa) {
        this.seguroIncendio = new BigDecimal(taxa).divide(new BigDecimal(12));
        this.alugado = true;
    }

    /**<h2>Método encerrarAluguel():</h2>
     * <p>Este método serve para tirar o status de alugado de um imóvel, zerando o valor do seguro e definindo o {@code Boolean alugado = false}. Liberando o imóvel para outros clientes.</p>
     */
    @Override
    public void encerrarAluguel() {
        this.seguroIncendio = BigDecimal.ZERO;
        this.alugado = false;
    }
    @Override
    public Double calcularAluguelBruto() {
        BigDecimal aux = new BigDecimal(calcularAluguelLiquido());
        aux.add(seguroIncendio);
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
