import java.math.BigDecimal;

public class Produto {
    private String nome;
    private static BigDecimal ultimo_preco = new BigDecimal(0.00);
    private BigDecimal preco_maximo;

    Produto(){
        this("Desconhecido", new BigDecimal(0.00));
    }

    Produto(String nome, BigDecimal valorMaximo){
        if(validarPreco(valorMaximo)){
            this.nome = nome;
            this.preco_maximo = valorMaximo;
            if(preco_maximo.compareTo(ultimo_preco)>0){
                ultimo_preco = preco_maximo;
            }
        }
    }

    public void atualizaPrecoMaximo(BigDecimal novoPreco) {
        if(validarPreco(novoPreco)){
            this.preco_maximo = novoPreco;
        }
    }

    private boolean validarPreco(BigDecimal preco){
        return preco.compareTo(BigDecimal.ZERO)>=0;
    }

    public boolean aprovarItem(){
        return(ultimo_preco.compareTo(preco_maximo)<=0);
    }

    public BigDecimal getPreco(){
        return preco_maximo;
    }
    public void setNome(String nome){
        if(nome!= null){
            this.nome = nome;
        }
    }
    public String getNome(){
        return this.nome;
    }
}


