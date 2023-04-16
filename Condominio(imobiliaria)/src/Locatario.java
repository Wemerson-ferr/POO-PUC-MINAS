/**<h1>Classe Locatario():</h1>
 * <p>Classe filha de {@code Cliente()}</p>
 * <p>Esta classe é uma representação do locatário em um sistema imobiliário, ou seja, uma pessoa que alugou um imóvel.</* p>
 * <p>Contém apenas um atributo, sendo este o imóvel em que o locatário está, todos os outros atributos são herdados</p>
 * @author Wemerson Ferreira
 */
public class Locatario extends Cliente{
    Imovel imovelAtual;

    /**<h2>Locatario()</h2>
     * <p>NÂO RECOMENDADO!</p>
     * <p>Irá iniciar um objeto vazio, de acordo com a classe mãe. Verifique {@code Cliente()}</p>
     * <p>Definirá o objeto interno {@code imovelAtual} como {@code null}</p>
     * 
     *  */ 
    Locatario(){
        super();
        imovelAtual = null;
    }

    /**<h2>Locatario(String nome, String login, String senha)</h2>
     * <p>Iniciará um objeto {@code Locatario} com os parâmetros fornecidos</p>
     * <p>Mantem o objeto {@code imovelAtual} como {@code nulo}, sendo necessário definir o imóvel por meio do {@code AlugarImovel()}, fornecendo uma taxa como parâmetro, para calculo do aluguel.</p>
     * @param String nome
     * @param String login
     * @param String senha
     */
    Locatario(String nome, String login, String senha){
        super(nome, login, senha);
        imovelAtual = null;
    }

    /**<h2>Locatario(String nome, String login, String senha, Imovel imovel)</h2>
     * <p>Iniciará um objeto {@code Locatario} com os parâmetros fornecidos</p>
     * <p>Define o {@code imovelAtual} como o fornecido via parâmetro</p>
     * <p>Ainda será necessário definir a taxa do aluguel, utilize {@code AlugarImovel(Double taxa)}, para confirmar e definir o aluguel do imóvel + taxas.</p>
     * @param String nome
     * @param String login
     * @param String senha
     * @param Imovel imovel
     */
    Locatario(String nome, String login, String senha, Imovel imovel){
        super(nome, login, senha);
        imovelAtual = imovel;
    }

    /**<h2>Locatario(String nome, String login, String senha, Imovel imovel, Double taxa)</h2>
     * <p>Iniciará um objeto {@code Locatario} com os parâmetros fornecidos</p>
     * <p>Define o {@code imovelAtual} como o fornecido via parâmetro</p>
     * <p>Define a taxa do aluguel do imóvel e já o colocar em modo alugado no sistema. Caso seja casa, forneça o valor anual do seguro incêndio, se for apartamento, forneça o valor mensal do condomínio.</p>
     * @param String nome
     * @param String login
     * @param String senha
     * @param Imovel imovel
     * @param Double taxa
     */
    Locatario(String nome, String login, String senha, Imovel imovel, Double taxa){
        super(nome, login, senha);
        imovelAtual = imovel;
        AlugarImovel(taxa);
    }

    /**<h2>Método AlugarImovel(Double taxa)</h2>
     * <p>Define a taxa que será paga junto do aluguel. Caso seja casa, forneça o valor anual do seguro incêndio, se for apartamento, forneça o valor mensal do condomínio.</p>
     * 
     * @param taxa a ser paga, observando a especificação acima.
     */
    public void AlugarImovel(Double taxa){
        imovelAtual.alugar(taxa);
    }

    /**<h2>Método AlugarImovel(Imovel imovel, Double taxa)</h2>
     * <p>Define o imovel fornecido como {@code imovelAtual}</p>
     * <p>Define a taxa que será paga junto do aluguel. Caso seja casa, forneça o valor anual do seguro incêndio, se for apartamento, forneça o valor mensal do condomínio.</p>
     * 
     * @param taxa a ser paga, observando a especificação acima.
     */
    public void AlugarImovel(Imovel imovel, Double taxa){
        imovelAtual = imovel;
        imovelAtual.alugar(taxa);
    }

    /**<h2>Método valorAluguel()</h2>
     * @return Retorna o valor que o locatário deverá pagar, já incluso todas as taxas e acréscimos necessários.
     */
    public Double valorAluguel(){
        return imovelAtual.calcularAluguelBruto();
    }
}
