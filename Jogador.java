package Personagens.jogador;

import Duelo.Arena;
import Personagens.Inimigos.InterfaceAcoes;
import Personagens.Inimigos.Monstros;
import java.util.Random;
import java.util.Scanner;

public class Jogador extends Personagem.Abst_Personagem implements InterfaceAcoes {
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    private int nivelExp;
    private int proximoNivel;
    private Arena arena;

//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    //                                                  Jogador Metodo Atacar
    @Override
    public void atacar(Jogador jogador, Monstros inimigo, Arena arena) {
        /*
        Se o inimigo tiver a Def menor que o Atq do jogador
        inimigo recebe o dano - Atq - Hp
         */
        if (inimigo.getDef() < jogador.getAtq()) {
            System.out.println("Jogador [" + jogador.getNome() + "]  atacou! \n ");
            System.out.println("| Atq : " + jogador.getAtq() + " Def inimigo: " + inimigo.getDef() + " |");
            int diferenca = inimigo.getHp() - jogador.getAtq();
            inimigo.setHp(diferenca);
            System.out.println("Dano: "+diferenca);
            System.out.println("inimigo [" + inimigo.getNome() + "] tem: " + (inimigo.getHp()) + " de HP");

//Se o monstro morrer chamo outros métodos para calcular a xp
            if (inimigo.getHp() <= 0) {
                System.out.println("=-=-=--INIMIGO DERROTADO!--==-=-=");
                System.out.println(jogador.getNivelExp());
                int xp = inimigo.getXp();
                jogador.setNivelExp(xp);
                jogador.verificarXp(jogador);
                
                //-------------------imprime os novos atributos no jogo do jogador----------------------------------------------    
                System.out.println("| Nível: |" + jogador.getNivel() + " | HP: " + jogador.getHp()
                        + " |Atq: " + jogador.getAtq() + " |Def: " + jogador.getDef() + " |MP: " + jogador.getMp() + " |\n");
                //------------------------------------------------------------------------------------------------------------------- 
                arena.chamaMenuJogo(jogador, inimigo);
            }
//------------------Caso o jogador não zere os pontos de vida do inimigo, ele vai contra atacar------------------------------------- 
            Random random = new Random();
            int sorte = random.nextInt(3) + 1;
            switch (sorte) {
                case 1:
                    System.out.println("Monstro Ataca!");
                    inimigo.atacar(jogador, inimigo, arena);
                    break;
                case 2:
                    System.out.println("Monstro Defende!");
                    inimigo.defender(jogador, inimigo, arena);
                    break;
                case 3:
                    System.out.println("Monstro Usa Magia!");
                    inimigo.usarMagia(jogador, inimigo, arena);
                    break;
            }
//-------------------------------------------------------------------------------------------------------------------          
        } else if (inimigo.getDef() > jogador.getAtq()) {
            System.out.println("Defendeu!");
        } else {
            /*
                Se o monstro não morrer no Atq o random vai escolher 
                a proxima ação do monstro.
             */
            Random random = new Random();
            int sorte = random.nextInt(3) + 1;
            switch (sorte) {

                case 1:
                    System.out.println("Inimigo vai Atacar ");
                    inimigo.atacar(jogador, inimigo, arena);
                    break;
                case 2:
                    System.out.println("Inimigo  tenta Defender");
                    inimigo.defender(jogador, inimigo, arena);
                    break;
                case 3:
                    System.out.println("Inimigo usa Magia");
                    inimigo.usarMagia(jogador, inimigo, arena);
                    break;
            }
            System.out.println(inimigo.getHp());
            arena.menuDuelo(jogador, inimigo, arena);

            /*
            Se a Def do Inimigo for maior que o Atq do jogador
            o inimigo perde a Def relativa ao Atq do jogador
            porem o inimigo revida com o atacar ou usar magia
            escolha feita pelo random.
             */
        }
        if (inimigo.getDef() > jogador.getAtq()) {
            System.out.println("inimigo [" + inimigo.getNome() + "] Defendeu!");
            int quebraDef = (jogador.getAtq() - inimigo.getDef());
            inimigo.setDef(quebraDef);
            System.out.println("inimigo tem: " + inimigo.getDef() + " defesa!");
            Random random = new Random();
            int revida = random.nextInt(2) + 1;
            switch (revida) {
                case 1:
                    inimigo.atacar(jogador, inimigo, arena);
                    break;
                case 2:
                    inimigo.usarMagia(jogador, inimigo, arena);
                    break;
            }
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//                                                  Jogador Metodo defender

    @Override
    public void defender(Jogador jogador, Monstros inimigo, Arena arena) {
        System.out.println("Inimigo [" + inimigo.getNome() + "] vai atacar");
        /*
          Se o inimigo tiver + Atq que a Def do jogador
         */
        if (inimigo.getAtq() > jogador.getDef()) {
            Random random = new Random();
            int sorte = random.nextInt(3) + 1;
            /*
            random vai escolher sua sorte
             */
            switch (sorte) {
                case 1:
                    System.out.println("Defendeu!");
                    arena.menuDuelo(jogador, inimigo, arena);
                    break;
                case 2:
                    System.out.println("Esquivou!");
                    arena.menuDuelo(jogador, inimigo, arena);
                    break;
                case 3:
                    System.out.println("Falha ao bloquear ataque!");
                    int falhaDef = (inimigo.getAtq() - jogador.getDef());
                    jogador.setDef(falhaDef);
                    System.out.println("Jogador [" + jogador.getNome() + "] tem: " + jogador.getDef() + " Hp");
                    arena.menuDuelo(jogador, inimigo, arena);
                    break;
            }
        } else {
            System.out.println("Defendeu!");
            arena.menuDuelo(jogador, inimigo, arena);
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//                                             Jogador Metodo usarMagia

    @Override
    public void usarMagia(Jogador jogador, Monstros inimigo, Arena arena) {
        /*
        jogadores só podem usar magia acima do level 5
         */
        if (jogador.getNivel() <= 4) {
            System.out.println("Magias são permitidas apenas para jogadores de Nível 5+");
            System.out.println("Ainda não possui Magia suficiente ou Habilidades!");
//caso tentar usar magia fora do nivel recomendado passa a vez para o inimigo.
            Random random = new Random();
            int sorte = random.nextInt(3) + 1;
            switch (sorte) {
                case 1:
                    System.out.println("Monstro Ataca!");
                    inimigo.atacar(jogador, inimigo, arena);
                    break;
                case 2:
                    System.out.println("Monstro Defende!");
                    inimigo.defender(jogador, inimigo, arena);
                    break;
                case 3:
                    System.out.println("Monstro Usa Magia!");
                    inimigo.usarMagia(jogador, inimigo, arena);
                    break;
            }

        } else if (jogador.getNivel() >= 5) {
            /*
            O dano da magia é baseado na quantidade de MP do jogador, o mesmo funciona para o inimigo
             */
            System.out.println("| Bola de Fogo |");
            System.out.println("Dano da Magia =" + jogador.getMp() / 2);

            int danoMagia = (jogador.getMp() / 2);
            inimigo.setHp(danoMagia - inimigo.getHp());
            System.out.println("Dano: "+danoMagia);
            System.out.println("Hp inimigo = " + inimigo.getHp());

            if (inimigo.getHp() <= 0) {
                 System.out.println("=-=-=--INIMIGO DERROTADO!--==-=-=");
                System.out.println(jogador.getNivelExp());
                jogador.setNivelExp(inimigo.getXp());
                jogador.getNivelExp();
                jogador.verificarXp(jogador);
                System.out.println("| Nível: |" + jogador.getNivel() + "|HP: " + jogador.getHp() + " |Atq: " + jogador.getAtq() + " |Def: " + jogador.getDef() + " |MP: " + jogador.getMp() + "|\n");
                arena.chamaMenuJogo(jogador, inimigo);
            }
        }
        arena.menuDuelo(jogador, inimigo, arena);
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/*
    sempre que um inimigo é derrotado chamamos esse método para verificar 
    se o personagem upou de nivel apos a batalha vencida, se for maior que 100
    aumentam os atributos e o nivel do jogador.
     */
    public void verificarXp(Jogador jogador) {
        if (jogador.getNivelExp() != 0) {
            // soma nivel
            int i = 1;
            // soma atq e def
            int x = 6;
            //soma hp e mp
            int y = 150;
            jogador.setNivel(i + jogador.getNivel());
            System.out.println("Parabéns!! você upou de Nível\n");
            jogador.setAtq(x + jogador.getAtq());
            jogador.setDef(x + jogador.getDef());
            jogador.setHp(y + jogador.getHp());
            jogador.setMp(y + jogador.getMp());
            //zero o atributo que recebe xp
            jogador.setNivelExp(0);
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/*
    Criação de personagem retorna um jogador com atributos
    escolhidos pelo random, com exceção do nivel que sempre
    começa no nivel 1.
     */
    public Jogador criarPersonagem(Jogador jogador) {
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        System.out.print("Qual o nome do seu personagem: ");
        try {
            jogador.setNome(s.nextLine());
            while (jogador.getNome().length() > 8) {
                System.err.println("Nome não pode conter mais de 8 caracteres");
                System.out.println("Digite o nome novamente: ");
                jogador.setNome(s.nextLine());
            }
        } catch (Exception ex) {
            if (jogador.getNome().length() > 8) {
                System.err.println("É permitido aprenas 8 caracteres");
                System.out.println(ex.getMessage());
            }
            jogador.criarPersonagem(jogador);
        }
        jogador.setNivel(1);
        jogador.setAtq(r.nextInt(20) + 1);
        jogador.setDef(r.nextInt(20) + 1);
        jogador.setHp(r.nextInt(500) + 1);
        jogador.setMp(r.nextInt(200) + 1);
        System.out.println("");
        System.out.println("Os atributos do seu personagem são: ");
        System.out.println("|HP: " + jogador.getHp() + " |Atq: " + jogador.getAtq() + " |Def: " + jogador.getDef() + " |MP: " + jogador.getMp() + "|\n");
        return jogador;
    }

//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public int getNivelExp() {
        return nivelExp;
    }

    public void setNivelExp(int nivelExp) {
        this.nivelExp = nivelExp;
    }

    public int getProximoNivel() {
        return proximoNivel;
    }

    public void setProximoNivel(int proximoNivel) {
        this.proximoNivel = proximoNivel;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;

    }
}
