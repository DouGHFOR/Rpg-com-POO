package Personagens.Inimigos;
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

import Duelo.Arena;
import Personagens.jogador.Jogador;
import java.util.Random;

public class Monstros extends Personagem.Abst_Personagem implements InterfaceAcoes {
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// Atributos e Construtores

    private int atq;
    private int def;
    private int hp;
    private int mp;
    private int xp;
    private TipoMonstro tipo;
    private Arena arena;
    public Monstros(TipoMonstro tipo) {
        this.tipo = tipo;
    }

    public Monstros(int atq, int def, int hp, int mp, int xp) {
        this.atq = atq;
        this.def = def;
        this.hp = hp;
        this.mp = mp;
        this.xp = xp;
    }

//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/* 
                                                                 Monstro Metodo Atacar
    O monstro sempre que não morrer em um primeiro ataque, vai ter a opção de contra atacar
    assim como defender e usar magia 
     */
    @Override
    public void atacar(Jogador jogador, Monstros inimigo, Arena arena) {
        /*
        Se  a def do jogador for menor que o ataque  do monstro neste caso
        o dano aplicado será o Atq menos o HP 
         */
        if (jogador.getDef() < inimigo.getAtq()) {
            System.out.println("Monstro [" + inimigo.getNome() + "]  atacou! \n ");
            //Mostra os dados de Atq do inimigo e Def do jogador
            System.out.println("Atq : " + inimigo.getAtq() + " Def jogador: " + jogador.getDef());
            int diferenca = jogador.getHp() - inimigo.getAtq();
            jogador.setHp(diferenca);
            System.out.println("Dano: "+diferenca);
            System.out.println("Jogador possui: " + jogador.getHp() + " HP");
            // Se zerar os pontos de Hp do jogador Fim de jogo.
            if (jogador.getHp() < 0) {
                System.out.println("GAME OVER");
                arena.chamaMenuJogo(jogador, inimigo);
            } else {
                arena.chamaMenuJogo(jogador, inimigo);
            }
            //Se jogador tiver + def que inimigo tem de atq
        } else if (jogador.getDef() > inimigo.getAtq()) {
            System.out.println("Jogador [" + jogador.getNome() + "] Defendeu!");
            arena.menuDuelo(jogador, inimigo, arena);
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    //                                          Monstro Metodo Defender       

    @Override
    public void defender(Jogador jogador, Monstros inimigo, Arena arena) {
        System.out.println("Jogador [" + jogador.getNome() + "] vai atacar");
        //Se o jogador tiver + Atq que a Def do inimigo
        if (jogador.getAtq() > inimigo.getDef()) {
            Random random = new Random();
            //Random vai escolher uma das opções e chamar o menuDuelo
            int sorte = random.nextInt(3) + 1;
            switch (sorte) {
                case 1:
                    System.out.println("Defendeu!");
                    break;
                case 2:
                    System.out.println("Esquivou!");
                    break;
                case 3:
                    //Caso esta opção, o inimigo falha em defender e recebe a diferença do dano entre atq - def
                    System.out.println("Falha ao bloquear ataque!");
                    int diferenca = jogador.getAtq() - inimigo.getDef();
                    inimigo.setHp(inimigo.getHp()-diferenca);
                    System.out.println("Dano: "+diferenca);
                    System.out.println("Inimigo possui: " + inimigo.getHp() + " HP");

                    //Se o monstro morrer ao tentar se defender chamo outros métodos para calcular a xp
                    if (inimigo.getHp() <= 0) {
                        System.out.println(jogador.getNivelExp());
                        int xp = inimigo.getXp();
                        jogador.setNivelExp(xp);
                        //verifica
                        jogador.verificarXp(jogador);
                        //Mostra o status
                        System.out.println("| Nível: |" + jogador.getNivel() + " | HP: " + jogador.getHp() + " |Atq: " + jogador.getAtq() + " |Def: " + jogador.getDef() + " |MP: " + jogador.getMp() + " |\n");
                        //Chama o Menu do Jogo
                        arena.chamaMenuJogo(jogador, inimigo);
                    } else {
                        System.out.println("Inimigo [" + inimigo.getNome() + "] ainda possui: " + (inimigo.getHp()) + " Hp");
                        arena.chamarDuelo(jogador, inimigo, arena);
                    }
                    break;
            }
            //Chama menu Duelo
            arena.menuDuelo(jogador, inimigo, arena);
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    //Monstro Metodo usar Magia

    @Override
    public void usarMagia(Jogador jogador, Monstros inimigo, Arena arena) {
        System.out.println("=-=-=-=--=  Monstro usa Magia  =--=--=-=");
        int diferenca = jogador.getHp() - (inimigo.getMp()/2);
        jogador.setHp(diferenca);
        System.out.println("Dano: "+diferenca);
        System.out.println("Jogador possui: " + jogador.getHp() + " HP");
        if (jogador.getHp() <= 0) {
            System.out.println("GAME OVER");
            arena.chamaMenuJogo(jogador, inimigo);
        } else {
            arena.menuDuelo(jogador, inimigo, arena);
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    @Override
    public int getXp() {
        return xp;
    }

    @Override
    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public TipoMonstro getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(TipoMonstro tipo) {
        this.tipo = tipo;
    }

    @Override
    public int getAtq() {
        return atq;
    }

    @Override
    public void setAtq(int atq) {
        this.atq = atq;
    }

    @Override
    public int getDef() {
        return def;
    }

    @Override
    public void setDef(int def) {
        this.def = def;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public void setMp(int mp) {
        this.mp = mp;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

}
