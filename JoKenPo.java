package JoKenPo;

import java.util.Random;
import java.util.Scanner;

public class JoKenPo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("    __     _____         _____  ");
        System.out.println(" __|  |___|  |  |___ ___|  _  |___ ");
        System.out.println("|  |  | . |    -| -_|   |   __| . |");
        System.out.println("|_____|___|__|__|___|_|_|__|  |___|");
        System.out.println("");

        System.out.println("Selecione o modo de jogo:");
        System.out.println("1. Jogar contra outro jogador");
        System.out.println("2. Jogar contra o computador");
        System.out.print("Digite a opcao desejada: ");
        int modoJogo = scanner.nextInt();
        System.out.println("");

        if (modoJogo == 1) {
            JogarContraOutroJogador();
        } else if (modoJogo == 2) {
            JogarContraComputador();
        } else {
            System.out.println("Opção inválida");
        }

        scanner.close();
    }

    private static void JogarContraOutroJogador() {
        Thread serverThread = new Thread(() -> {
            try {
                Servidor.main(new String[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread clientThread = new Thread(() -> {
            try {
                Cliente.main(new String[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientThread.start();

        try {
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void JogarContraComputador() {
         Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] opcoes = {"Pedra", "Papel", "Tesoura"};

        while (true) {
            System.out.print("Faça sua jogada (1-Pedra, 2-Papel, 3-Tesoura, 0-Sair): ");
            int jogadaJogador = scanner.nextInt();
            if (jogadaJogador == 0) break;

            int jogadaComputador = random.nextInt(3) + 1;

            System.out.println("Você escolheu: " + opcoes[jogadaJogador - 1]);
            System.out.println("Computador escolheu: " + opcoes[jogadaComputador - 1]);

            determinarVencedor(jogadaJogador, jogadaComputador);
        }
        scanner.close();
    }
    private static void determinarVencedor(int jogada1, int jogada2) {
        if (jogada1 == jogada2) {
            System.out.println("Empate!");
            System.out.println("");
        } else if ((jogada1 == 1 && jogada2 == 3) || (jogada1 == 2 && jogada2 == 1) || (jogada1 == 3 && jogada2 == 2)) {
            System.out.println("Você venceu!");
            System.out.println("");
        } else {
            System.out.println("Computador venceu!");
            System.out.println("");
        }
    }
}

