package MainTeste;

import Duelo.Arena;
import Personagens.Inimigos.Monstros;
import Personagens.Inimigos.TipoMonstro;
import Personagens.jogador.Jogador;
import java.util.Scanner;

public class TesteRPG {

    public static void main(String[] args) {
        //=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

        Scanner s = new Scanner(System.in);
        Jogador jogador = new Jogador();
        Monstros inimigo = new Monstros(TipoMonstro.LOBO);
        Arena arena = new Arena();

        //=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        System.out.println("Você enfrentará diferentes inimigos, derrote-os para aumentar suas habilidades!\n");
        jogador.criarPersonagem(jogador);
        arena.chamarDuelo(jogador, inimigo, arena);

        //=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        while ((inimigo.getHp() <= 0) || (jogador.getHp() <= 0)) {
            System.out.println("Ver MENU: | [ 1 ] sim  | não [ 2 ]   |");
            int menu = s.nextInt();
            if (menu == 1) {
                arena.chamaMenuJogo(jogador, inimigo);
            } else {
                System.out.println("Saindo...");
            }
        }
        //=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    }
}
