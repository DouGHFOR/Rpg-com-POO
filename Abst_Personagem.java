package Personagem;

import Duelo.Arena;
import Personagens.Inimigos.Monstros;
import Personagens.Inimigos.TipoMonstro;
import Personagens.jogador.Jogador;

public abstract class Abst_Personagem {
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    //Crio uma classe abstrata que sera extendidas por outras classes
    //nesse caso tanto jogador como inimigo serão personagem

    private String nome;
    private int nivel = 1;
    private int atq;
    private int def;
    private int hp;
    private int mp;
    private int xp;
    private TipoMonstro tipo;
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public void atacar(Jogador jogador, Monstros inimigo, Arena arena) {
    }

    public void defender(Jogador jogador, Monstros inimigo, Arena arena) {
    }

    public void usarMagia(Jogador jogador, Monstros inimigo, Arena arena) {
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAtq() {
        return atq;
    }

    public void setAtq(int atq) {
        this.atq = atq;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public TipoMonstro getTipo() {
        return tipo;
    }

    public void setTipo(TipoMonstro tipo) {
        this.tipo = tipo;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

}
