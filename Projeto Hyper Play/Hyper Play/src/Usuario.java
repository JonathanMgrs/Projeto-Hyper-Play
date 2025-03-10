import java.io.*;
import java.util.*;

class Usuario {
    private String nome;
    private List<String> biblioteca;
    private CarteiraDigital carteira;
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    public Usuario(String nome) {
        this.nome = nome;
        this.biblioteca = new ArrayList<>();
        this.carteira = new CarteiraDigital();
        carregarUsuario();
    }

    public boolean adicionarJogo(String jogo, double preco) {
        if (carteira.getSaldo() >= preco) {
            carteira.removerSaldo(preco);
            biblioteca.add(jogo);
            BibliotecaJogos.removerJogo(jogo);
            salvarUsuario();
            return true;
        }
        return false;
    }

    public List<String> getBiblioteca() {
        return biblioteca;
    }

    public CarteiraDigital getCarteira() {
        return carteira;
    }

    private void carregarUsuario() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(nome)) {
                    carteira.setSaldo(Double.parseDouble(partes[1]));
                    if (partes.length > 2) {
                        biblioteca.addAll(Arrays.asList(partes[2].split(",")));
                    }
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuário: " + e.getMessage());
        }
    }

    private void salvarUsuario() {
        List<String> linhas = new ArrayList<>();
        boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(nome)) {
                    usuarioExiste = true;
                    linhas.add(nome + ";" + carteira.getSaldo() + ";" + String.join(",", biblioteca));
                } else {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            // Se o arquivo não existir, ele será criado
        }
        
        if (!usuarioExiste) {
            linhas.add(nome + ";" + carteira.getSaldo() + ";" + String.join(",", biblioteca));
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }
}
