
/**<h1>Classe Cliente</h1>
 * <p>É uma classe abstrata que define dados e regras para clientes de um sistema imobiliário.</p>
 * @author Wemerson Ferreira
 */
public abstract class Cliente {
    private String nome;
    private String login;
    private String senha;
    private Integer ID;
    
    /**<h1>Cliente()</h1>NÃO RECOMENDADO!
     * <p>Inicia um objeto Cliente sem parâmetros, portanto as informações serão armazenadas como:
     *  <ul>
     *      <li>nome: Desconhecido</li>
     *      <li>login: Default_User</li>
     *      <li>Senha: 000</li>
     *  </ul>
     * </p>
     */
    Cliente(){
        this("Desconhecido", "Default_User", "000");
    }

    /**<h1>Cliente(String nome, String login, String senha)</h1>
     * <p>Inicia um obejeto do tipo Cliente, com os parâmetros fornecidos.</p>
     * <p>Usa um método interno para gerar um ID único de cada objeto, que pode ser usado para verificar autenticidade.</p>
     * <p>Os parâmetros DEVEM ser fornecidos na ordem correta, para evitar erros de autenticidade.
     *   <ol>
     *       <li>nome</li>
     *       <li>login</li>
     *       <li>senha</li>
     *   </ol>
     * </p>
     * @param String nome
     * @param String login
     * @param String senha
     */
    Cliente(String nome, String login, String senha) {
        if(nome!=null && login!= null & senha != null){
            this.nome = nome;
            this.login = login;
            this.senha = senha;
            this.ID = gerarID();
        }
    }

    /**Método auxiliar:
     * É utilizado para gerar um ID de cliente baseado nos hashCode de cada atributo da classe, exceto o próprio ID. Garantindo que não haja objetos idênticos.
     * 
     * @return Integer auxiliar
     */
    private Integer gerarID(){
        Integer auxiliar;
        auxiliar = this.nome.hashCode();
        auxiliar += this.login.hashCode();
        auxiliar += this.senha.hashCode();

        return auxiliar;
    }
    
}
