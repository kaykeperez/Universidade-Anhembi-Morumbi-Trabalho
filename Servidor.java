package JoKenPo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Aguardando conexão do Jogador 2...");
            Socket jogador2Socket = serverSocket.accept();
            System.out.println("Jogador 2 conectado.");

            ObjectOutputStream jogador2Output = new ObjectOutputStream(jogador2Socket.getOutputStream());
            ObjectInputStream jogador2Input = new ObjectInputStream(jogador2Socket.getInputStream());

            while (true) {
                System.out.print("Jogador 1, faça sua jogada (1-Pedra, 2-Papel, 3-Tesoura, 0-Sair): ");
                int jogada1 = scanner.nextInt();
                jogador2Output.writeObject(jogada1);

                if (jogada1 == 0) break;

                int jogada2 = (int) jogador2Input.readObject();
                System.out.println("Jogada do Jogador 2: " + jogada2);
                determinarVencedor(jogada1, jogada2);
            }

            serverSocket.close();
            scanner.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void determinarVencedor(int jogada1, int jogada2) {
        if (jogada1 == jogada2) {
            System.out.println("Empate!");
        } else if ((jogada1 == 1 && jogada2 == 3) || (jogada1 == 2 && jogada2 == 1) || (jogada1 == 3 && jogada2 == 2)) {
            System.out.println("Jogador 1 vence!");
        } else {
            System.out.println("Jogador 2 vence!");
        }
    }
}
