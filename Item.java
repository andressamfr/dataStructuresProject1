public class Item {

    String descricao;   //Descrição (nome do item)
    double preco;       //Preço unitário
    int qtd;            //Quantidade de Items

    public Item (String descricao, double preco, int qtd){
        this.descricao = descricao;
        this.preco = preco;
        this.qtd = qtd;
    }

    public double calcSubTotal() {
        return preco * qtd;
    }

    public void mostraItem() {
        System.out.println(descricao+"\t\t\t\t" + preco+"\t\t\t\t" + qtd+"\t\t\t\t\t\t\t" + calcSubTotal());
    }
}