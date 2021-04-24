import java.util.ArrayList;
import java.util.Scanner;

public class Carrinho {
    
    private static final Scanner input = new Scanner(System.in);

    ArrayList<Item> listaItens;     //Lista de items
    double totalCompra;             //Valor total da compra
    double porcDesconto;            //Porcentagem de desconto
    double valorPagar;              //Valor a pagar

    public Carrinho(ArrayList<Item> listaItens, double porcDesconto) {
        this.listaItens = listaItens;
        this.porcDesconto = porcDesconto;
        this.totalCompra = 0;
        this.valorPagar = 0;
    }

    public void mostraCarrinho() {
        int i = 1;
        totalCompra = 0;
        System.out.println(
                "Item\t\t\t\t" +
                "Descrição\t\t\t\t" +
                "Preço\t\t\t\t" +
                "Quantidade\t\t\t\t" +
                "Subtotal"
        );
        for (Item item: listaItens) {
            System.out.print(i+"\t\t\t\t\t");
            item.mostraItem();
            totalCompra += item.calcSubTotal();
            i++;
        }
        valorPagar = totalCompra - calcDesconto();
        System.out.println("\nSubtotal: " + totalCompra);
        System.out.printf("Descontos: %.2f", calcDesconto());
        System.out.println("\nTotal a pagar: " + valorPagar);
    }
    
    public double calcDesconto() {
        return (porcDesconto/100)*totalCompra;
    }

    public void excluiItem() {
        String opcao = "S";
        int num_item;
        while(opcao.equalsIgnoreCase("S")){

            System.out.print("\nVocê deseja deletar algum item ? (S/N) ");
            opcao = input.next();

            while(!opcao.equalsIgnoreCase("S") && !opcao.equalsIgnoreCase("N")){
                System.out.println("Erro! Escolha S para Sim / N para Não");
                System.out.print("\nVocê deseja deletar algum item ? (S/N) ");
                opcao = input.next();
            }
            if(opcao.equalsIgnoreCase("N"))
                break;

            System.out.print("Digite o número do item a ser deletado: ");
            num_item = input.nextInt();
            listaItens.remove(num_item-1);
            mostraCarrinho();
        }
        System.out.println("Total da compra: " + valorPagar);
    }
}