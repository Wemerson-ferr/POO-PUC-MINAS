import java.util.HashMap;
import java.util.Map;
/**<h1>Classe Cliente</h1>
 * <p>Representa o cliente que irá usar o sistema da plataforma de Streaming</p>
 * <p>Contém os seguintes atributos:
 *  <ul>
 *    <li>String nome</li>
 *    <li>String email</li>
 *    <li>integer ID (será gerado internamente)</li>
 *    <li>HashMap<Integer, Serie> seriesAssistidas</l1>
 *    <li>HashMap<Integer, Serie> assistirDepois</li>
 * </ul>
 * </p> 
 * <p>Métodos disponíveis:
 *  <ul>
 *    <li>adicionarAssistidas(Serie assistida)</li>
 *    <li>adicionarAssistirDepois(Serie desejada)</li>
 *    <li>removerAssistirDepois(Serie desejada)</li>
 *    <li>listarSeriesAssistidas()</l1>
 *    <li>listarSeriesAssistirDepois</li>
 * </ul>
 * </p>
 */
public class Cliente {
  private String nome;
  private Integer ID = 0;
  private String email;
  private HashMap<Integer, Serie> seriesAssistidas;
  private HashMap<Integer, Serie> assistirDepois;

  /**<h2>Contrutor padrão Cliente()</h2> 
   * <p>NÂO RECOMENDADO!</p>
   * <p>Para garantir a integridade dos dados prefira o construtor:
   * {@code Cliente(String nome, String email)}</p>
  */
  Cliente() {
    this("Desconhecido", "email@email.com");
  }

  /**<h2>Contrutor Cliente(String nome, String email)</h2> 
   * <p>Construtor recomendado, para evitar dados incorretos</p>
   * <p>Tenha certeza da validade dos dados antes de inseri-los</p>
  */
  Cliente(String nome, String email) {
    this.nome = nome;
    this.email = email;
    this.ID = geraId();
    seriesAssistidas = new HashMap<Integer, Serie>();
  }


  /**<h2>geraId()</h2>
   * <p>Usado para gerar o id a partir do hash code de {@code nome} e {@code email}, garantindo que não haja clientes duplicados</p>
   * @return int id
   */
  private int geraId() {
    final int controle = 31;
    int res = 1;
    res = controle * res + ((this.nome == null) ? 0 : nome.hashCode());
    res = controle * res + ((this.email == null) ? 0 : email.hashCode());
    return res;
  }

  /**<h2>AdicionarAssistidas()</h2>
   * <p>Este método adiciona uma série a lista de series já assistidas pelo usuário</p>
   * <p>IMPORTANTE: Tome cuidado a fazer a inserção, pois não poderá remover!</p>
   * @param Serie assistida
   */
  public void adicionarAssistidas(Serie assistida) {
    seriesAssistidas.put(assistida.getID(), assistida);
  }

  /**<h2>AdicionarAssistirDepois(Serie desejada)</h2>
   * <p>Este método adiciona uma série a lista de assistir depois do usuário</p>
   * <p>Para remover uma série, utilize {@code removerAssistirDepois(Serie desejada)}</p>
   * @param Serie desejada
   */
  public void adicionarAssistirDepois(Serie desejada) {
    if (seriesAssistidas.get(desejada.getID()) != null) {
      assistirDepois.put(desejada.getID(), desejada);
    }
  }

  /**<h2>removerAssistirDepois(Serie desejada)</h2>
   * <p>Este método irá remover uma série recebida por parâmetro da lista de assistir depois do usuário</p>
   * @param desejada
   */
  public void removerAssistirDepois(Serie desejada){
    if (seriesAssistidas.get(desejada.getID()) != null) {
      assistirDepois.remove(desejada.getID());
    }
  }

  /**<h2>listarSeriesAssistidas()</h2>
   * <p>Este método irá retornar uma String com todas as séries assistidas do usuário.</p>
   * 
   * @return String seriesAssistidas
   */
  public String listarSeriesAssistidas(){
    return listarSeries(seriesAssistidas);
  }

  /**<h2>listarSeriesAssistidas()</h2>
   * <p>Este método irá retornar uma String com todas as séries assistidas do usuário.</p>
   * 
   * @return String seriesAssistidas
   */
  public String listarSeriesAssistirDepois(){
    return listarSeries(assistirDepois);
  }

  /**<h2>listarSeries(HashMap<Integer, Serie> series)</h2>
   * <p>Método auxiliar para percorrer um HashMap<Integer, Serie></p>
   * @param HashMap<Integer, Serie> series
   * @return String seriesString
   */
  private String listarSeries(HashMap<Integer, Serie> series){
    StringBuilder seriesString = new StringBuilder();
    if(!assistirDepois.isEmpty()){
      for (Map.Entry<Integer, Serie> entry : assistirDepois.entrySet()) {
          seriesString.append(entry.getValue().toString());
      }
    }
    return seriesString.toString();
  }

  /**<h2>getID()</h2>
   * <p>Retorna o id do Cliente, utilize para verificação.</p>
   * @return int ID 
   */
  public int getID() {
    return this.ID;
  }
}
