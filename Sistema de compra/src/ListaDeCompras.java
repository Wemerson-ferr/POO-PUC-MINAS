import java.math.BigDecimal;
import java.util.LinkedList;

public class ListaDeCompras {
    private BigDecimal limiteDeCompra;
    private LinkedList<Produto> itens;

    ListaDeCompras(){
        this(new BigDecimal(0.00));
    }
    ListaDeCompras(BigDecimal limite){
        if(limite.compareTo(BigDecimal.ZERO)>=0){
            setLimiteDeCompra(limite);
        }
    }
    public void setLimiteDeCompra(BigDecimal limite) {
        this.limiteDeCompra = limite;
    }

    public void adicionaProduto(String nome, BigDecimal precoMaximo){
        if(validaInsercao()){
            Produto tmp = new Produto(nome, precoMaximo);
            if(tmp.aprovarItem()){
                itens.add(new Produto(nome, precoMaximo));
            }
        }
    }

    public void removeProduto(){
        if(itens.isEmpty()){
            System.out.println("Lista vazia");
        }
        else{
            itens.removeLast();
        }
    }

    public void removeProduto(int posicao){
        if(itens.isEmpty()){
            System.out.println("Lista vazia");
        }
        else{
            if(posicao> 0 && posicao<= itens.size()){
                itens.remove(posicao);
            }
        }
    }

    private boolean validaInsercao(){
        return valorTotalCompra().compareTo(limiteDeCompra) < 0;
    }

    public String statusDaCompra(){
        String descricao = "";
        int cont = 1;
        for (Produto produto : itens) {
            descricao += cont+"°"+produto.getNome()+": "+ produto.getPreco()+"\n";
        }
        descricao += "Total: "+valorTotalCompra();
        return descricao;
    }

    public BigDecimal valorTotalCompra(){
        BigDecimal valorAtualCompra = new BigDecimal(0.00);
        for( Produto item : itens){
            valorAtualCompra.add(item.getPreco());
        }
        return valorAtualCompra;
    }
}
