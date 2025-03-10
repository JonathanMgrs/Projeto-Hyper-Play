import java.util.List;
import java.util.Scanner;

public class HyperPlay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaJogos.carregarJogosDeArquivo("jogos.txt");

        System.out.println("Bem-vindo à Biblioteca de Jogos!");
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = new Usuario(nomeUsuario);

        int opcao;
        List<String> jogos = null;
        List<Double> precos = null;
        do {
            System.out.println("1 - Listar jogos disponíveis");
            System.out.println("2 - Comprar jogo");
            System.out.println("3 - Ver biblioteca");
            System.out.println("4 - Adicionar saldo");
            System.out.println("5 - Ver saldo");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    jogos = BibliotecaJogos.getJogosDisponiveis();
                    precos = BibliotecaJogos.getPrecos();
                    System.out.println("Jogos disponíveis:");
                    for (int i = 0; i < jogos.size(); i++) {
                        System.out.printf("%d. %s - R$ %.2f\n", i + 1, jogos.get(i), precos.get(i));
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Digite o número do jogo que deseja comprar: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();
                    if (escolha > 0 && escolha <= jogos.size()) {
                        String jogoComprado = jogos.get(escolha - 1);
                        double precoJogo = precos.get(escolha - 1);
                        if (usuario.adicionarJogo(jogoComprado, precoJogo)) {
                            System.out.println("Jogo comprado com sucesso!");
                        } else {
                            System.out.println("Saldo insuficiente!");
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 3:
                    List<String> biblioteca = usuario.getBiblioteca();
                    System.out.println("Sua biblioteca de jogos:");
                    if (biblioteca.isEmpty()) {
                        System.out.println("Nenhum jogo na biblioteca.");
                    } else {
                        for (String jogo : biblioteca) {
                            System.out.println("- " + jogo);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Digite o valor para adicionar à carteira: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    usuario.getCarteira().adicionarSaldo(valor);
                    System.out.printf("Saldo adicionado! Novo saldo: R$ %.2f\n", usuario.getCarteira().getSaldo());
                    break;
                case 5:
                    System.out.printf("Saldo atual: R$ %.2f\n", usuario.getCarteira().getSaldo());
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}
