import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class BibliotecaJogos {
    private static List<String> jogosDisponiveis = new ArrayList<>();
    private static List<Double> precos = new ArrayList<>();

    public static void carregarJogosDeArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    jogosDisponiveis.add(partes[0]);
                    precos.add(Double.parseDouble(partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar jogos: " + e.getMessage());
        }
    }

    public static void removerJogo(String jogo) {
        int index = jogosDisponiveis.indexOf(jogo);
        if (index != -1) {
            jogosDisponiveis.remove(index);
            precos.remove(index);
        }
    }

    public static List<String> getJogosDisponiveis() {
        return jogosDisponiveis;
    }

    public static List<Double> getPrecos() {
        return precos;
    }
}
