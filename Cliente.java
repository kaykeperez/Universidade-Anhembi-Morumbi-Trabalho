package JoKenPo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            while (true) {
                int jogada1 = (int) input.readObject();
                if (jogada1 == 0) break;

                System.out.print("Jogador 2, faça sua jogada (1-Pedra, 2-Papel, 3-Tesoura): ");
                int jogada2 = scanner.nextInt();
                output.writeObject(jogada2);
            }

            socket.close();
            scanner.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
