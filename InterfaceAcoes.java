package Personagens.Inimigos;

import Duelo.Arena;
import Personagens.jogador.Jogador;

public interface InterfaceAcoes {
// Obrigando esses métodos a serem implementados pela interface

    public void atacar(Jogador jogador, Monstros inimigo, Arena arena);

    public void defender(Jogador jogador, Monstros inimigo, Arena arena);

    public void usarMagia(Jogador jogador, Monstros inimigo, Arena arena);
    //=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
}
