import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Andressa Marques Fernandes - 31786812
// Gustavo de Lima Carmassi - 32046693

public class Main {

    static final Scanner input = new Scanner(System.in);
    static double valor_arrecadado = 0;
    static int opcao = 0;

    public static void main(String[] args) {

        do {
            do {
                try {
                    System.out.println("\nMenu principal \n" +
                            "1- Iniciar compra \n" +
                            "2- Exibir total diário arrecadado \n" +
                            "3- Sair");
                    System.out.print("Sua opção: ");
                    opcao = input.nextInt();
                    break;
                }
                catch (InputMismatchException e) {
                    System.out.println("Opção inválida!");
                }
                input.nextLine();
            } while(opcao <= 0);

            switch (opcao) {
                case 1:
                    ArrayList<Item> listaItens = new ArrayList<>();
                    criaItem(listaItens);
                    Carrinho carrinho = new Carrinho(listaItens, 10);
                    carrinho.calcDesconto();
                    carrinho.mostraCarrinho();
                    carrinho.excluiItem();
                    pagamento(carrinho);
                    break;

                case 2:
                    System.out.println("Valor arrecadado hoje: " + valor_arrecadado);
                    break;

                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    public static void criaItem(ArrayList<Item> listaItens) {
        String opcao_item = "S";
        int i = 1;
        while (opcao_item.equalsIgnoreCase("S")) {

            try {
                System.out.println("Item " + i);

                System.out.print("Descrição: ");
                String descricao = input.next();

                System.out.print("Preço: ");
                double preco = input.nextDouble();

                System.out.print("Quantidade: ");
                int qtd = input.nextInt();

                Item item = new Item(descricao, preco, qtd);
                listaItens.add(item);

                System.out.print("Deseja adicionar mais um item? (S/N) ");
                opcao_item = input.next();

                while(!opcao_item.equalsIgnoreCase("S") && !opcao_item.equalsIgnoreCase("N")){
                    System.out.println("Erro! Escolha S para Sim / N para Não");
                    System.out.print("Deseja adicionar mais um item? (S/N) ");
                    opcao_item = input.next();
                }
                i++;
            } catch (InputMismatchException e) {
                System.out.println("Preencha os dados corretamente!");
            }
            input.nextLine();
        }
    }

    public static void pagamento(Carrinho carrinho) {

        double valor_recebido;
        double troco = 0;

        System.out.print("\nInforme o tipo de pagamento (D= Dinheiro / C = Cartão): ");
        String tipo_pagamento = input.next();

        while(!tipo_pagamento.equalsIgnoreCase("D") && !tipo_pagamento.equalsIgnoreCase("C")){
            System.out.println("Tipo de pagamento inválido. D para Dinheiro / C para Cartão)");
            System.out.print("\nTipo de pagamento (D= Dinheiro / C = Cartão): ");
            tipo_pagamento = input.next();
        }

        if (tipo_pagamento.equalsIgnoreCase("D")) {
            System.out.print("\nValor recebido: R$");
            valor_recebido = input.nextDouble();
            while (valor_recebido < carrinho.totalCompra) {
                System.out.print("\nValor menor que o total a pagar!");
                System.out.print("\nInforme o valor a pagar: R$");
                valor_recebido = input.nextDouble();
            }
            troco = valor_recebido - carrinho.valorPagar;
            System.out.printf("\nTroco: %.2f\n", troco);
        }
        if (tipo_pagamento.equalsIgnoreCase("C")) {
            valor_recebido = carrinho.valorPagar;
            System.out.print("Valor recebido: R$" + valor_recebido);
            System.out.printf("\nTroco: %.2f\n", troco);
        }

        valor_arrecadado += carrinho.valorPagar;
    }

}
