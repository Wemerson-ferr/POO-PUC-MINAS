/**<h1>Class Pizza</h1>
 * <p>Esta classe irá instanciar um objeto do tipo pizza.
 * Caso iniciada em branco receberá o valor final = 25.0 </p>
 * Se iniciado com um valor ente 1 e 8 irá alterar o valor, multiplicando
 * em 4 o valor inserido e alterando o valor final do objeto.
 */
public class Pizza {
    private static final Double PRECO_INICIAL = 25.00;
    private static final int INGREDIENTES_MAX = 8;
    private static final int PRECO_INGREDIENTE = 4;
    private int ingredientes;

    Pizza(){
        this(0);
    }
    Pizza(int quantidadeIngredientes){
        adicionaIngrediente(quantidadeIngredientes);;
    }    
    /**Adiciona 1 ingrediente a pizza */
    public void adicionaIngrediente(){
        adicionaIngrediente(1);
    }
    /**Adiciona de 1 à 8 ingredientes, se for passado um valor maior que 8 irá adicionar o máximo de 8 ingredientes,
     * caso seja digito um valor negativo será impresso na tela "Quantidade inválida".
    */
    public void adicionaIngrediente(int quantidade){
        if(quantidade > 0 && quantidade <= INGREDIENTES_MAX && ingredientes < INGREDIENTES_MAX){
            for(int i=0; i < quantidade; i++){
                ingredientes++;
            }
            
        }
        else if(quantidade > INGREDIENTES_MAX){ ingredientes = INGREDIENTES_MAX;}
        else if (ingredientes == INGREDIENTES_MAX){
            System.out.println("Limite de ingredientes atingido!");
        }
        else{
            System.out.println("Quantidade inválida");
        }
    }

    public void removerIngrediente(){
        removerIngrediente(1);
    }
    public void removerIngrediente(int quantidade){
        if(quantidade > 0 && (ingredientes - quantidade)>=0){
            ingredientes-=quantidade;
        }
        else if(quantidade<0){
            System.out.println("Quantidade negativa -> inválida");
        }
        else if(quantidade > INGREDIENTES_MAX){
            System.out.println("Impossível retirar mais que 8 ingredientes");
        }
        else{System.out.println("Quantidade inválida");}
    }

    /**Soma o valor total da pizza
     * @return Double
     */
    public Double somarTotal(){
        return PRECO_INICIAL + (ingredientes*PRECO_INGREDIENTE);
    }

    public String gerarNota(){
        return "Pizza: Queijo e Calabresa com "+ingredientes+" ingredientes adicionais.\n" + "Preço total: "+ somarTotal();
    }

    public int getIngredientes(){
        return this.ingredientes;
    }
}
