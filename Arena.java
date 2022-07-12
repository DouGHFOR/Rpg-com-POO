package Duelo;

import Personagens.Inimigos.Monstros;
import Personagens.Inimigos.TipoMonstro;
import Personagens.jogador.Jogador;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Arena {
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public void chamarDuelo(Jogador jogador, Monstros inimigo, Arena arena) {

        Random random = new Random();
        List<Monstros> listaInimigo = new ArrayList();
        // Instâncio cada tipo de monstro e adiciono em uma lista:

        Monstros raposa = new Monstros(TipoMonstro.RAPOSA);
        raposa.setNome("Raposa Escarlate");
        listaInimigo.add(raposa);
        Monstros lobo = new Monstros(TipoMonstro.LOBO);
        lobo.setNome("Lobo Ancestral");
        listaInimigo.add(lobo);
        Monstros urso = new Monstros(TipoMonstro.URSO);
        urso.setNome("Urso De Armadura");
        listaInimigo.add(urso);
        Monstros tigre = new Monstros(TipoMonstro.TIGRE);
        tigre.setNome("Tigre Celestial");
        listaInimigo.add(tigre);

        //Uso um For each para percorrer a lista
        // E um Random para escolher um inimigo aleatorio dentro do tamanho do meu array
        for (Monstros lista : listaInimigo) {
            //Atribuo o inimigo escolhido a um novo que copia os dados deste inimigo
            inimigo = listaInimigo.get(random.nextInt(3));
        }

        //E recebe atributos aleatorios.
        System.out.println("Prepare-se! [ " + inimigo.getTipo() + "] Vai Duelar!!\n");
        //----------------------------------------------------------------------------------------------------     
        inimigo.setAtq(random.nextInt(10) + 1);
        inimigo.setDef(random.nextInt(10) + 1);
        inimigo.setHp(random.nextInt(300) + 1);
        inimigo.setMp(random.nextInt(200) + 1);
        inimigo.setXp(random.nextInt(99) + 1);
//----------------------------------------------------------------------------------------------------
        System.out.println("    =-=-=-=-=-=-=Status Inimigo=-=-=-=-=-=-= ");
        System.out.println("|Nome: " + inimigo.getNome() + "| HP: " + inimigo.getHp() + "| MP: " + inimigo.getMp() + " |Atq: " + inimigo.getAtq() + " |Def: " + inimigo.getDef() + "|\n");

        while (inimigo.getHp() >= 0) {
            arena.menuDuelo(jogador, inimigo, arena);
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/*
    Menu das opções de batalha:
     */
    public void menuDuelo(Jogador jogador, Monstros inimigo, Arena arena) {
        try {
            if(jogador.getHp()<=0){
                System.out.println("É necessário criar outro personagem:\n");
                jogador.criarPersonagem(jogador);
            }
            Scanner s = new Scanner(System.in);
            System.out.println("                          =-==-=-=-=-=-=-=-=-=-=Menu Duelo=-==-=-=-=-=-=-=-=-=-=");
            System.out.println("| [ 1 ]-Atacar | [ 2 ] - Defender | [ 3 ] - Usar Magia |"
                    + "| [ 4] - Status Jogador  | [ 5 ] - Status Inimigo | [ 6] - Sair  |\n");
            int num = s.nextInt();
            switch (num) {
                case 1:
                    jogador.atacar(jogador, inimigo, this);
                    break;
                case 2:
                    jogador.defender(jogador, inimigo, this);
                    break;
                case 3:
                    jogador.usarMagia(jogador, inimigo, this);
                    break;
                case 4:
                    System.out.println("Jogador Nome: " + jogador.getNome());
                    System.out.println("Jogador Nível: " + jogador.getNivel());
                    System.out.println("Jogador HP: " + jogador.getHp());
                    System.out.println("Jogador MP: " + jogador.getMp());
                    System.out.println("Jogador ATQ: " + jogador.getAtq());
                    System.out.println("Jogador DEF: " + jogador.getDef());
                    System.out.println("Jogador NívelXp: " + jogador.getNivelExp());
                    break;
                case 5:
                    System.out.println("Inimigo Nome: " + inimigo.getNome());
                    System.out.println("Inimigo HP: " + inimigo.getHp());
                    System.out.println("Inimigo MP: " + inimigo.getMp());
                    System.out.println("Inimigo ATQ: " + inimigo.getAtq());
                    System.out.println("Inimigo DEF: " + inimigo.getDef());
                    arena.menuDuelo(jogador, inimigo, arena);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    arena.chamaMenuJogo(jogador, inimigo);
                    break;
                default:
                    /*
                Caso não seja nenhuma das opçoes gera um novo duelo chamando menuDuelo
                     */
                    System.err.println("Opção Inválida!!");
                    System.out.println("Gerando novo Duelo...\n");
                    arena.chamarDuelo(jogador, inimigo, arena);
                    break;
            }
        } catch (Exception ex) {
            System.err.println("Escolha Apenas os Números [1],[2],[3],[4],[5],[6]");
            ex.getMessage();
            ex.getLocalizedMessage();
        }
    }

//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=       
    /*
    Menu para o fim das batalhas.
     */
    public void chamaMenuJogo(Jogador jogador, Monstros inimigo) {

        Scanner s = new Scanner(System.in);
        System.out.println("==-=-=-=-=-=-=--=-=-=-=-MENU=-=-=-=-=-==--=-=-=-=-=-=");
        System.out.println("| [ 1 ] - NovoDuelo |  | [ 2 ] - Novo Personagem  | | [ 3 ] - Salvar | [ 4 ] - Sair |");

        int resp = s.nextInt();
        switch (resp) {
            case 1:
                /*
                cria um novo duelo
                 */
                Arena arena = new Arena();
                arena.chamarDuelo(jogador, inimigo, arena);
                break;
            case 2:
                Scanner novo = new Scanner(System.in);
                System.out.println("Tem certeza que deseja criar um novo personagem?: [1] sim | [2] não\n");
                String novoPersonagem = novo.nextLine();
                if (novoPersonagem.equalsIgnoreCase("1")) {
                    jogador.criarPersonagem(jogador);
                } else {
                    break;
                }
                break;
            case 3:
                /*
                não tenho idéia de como vou fazer isso ainda
                 */
                System.out.println("IMAGINE AQUI O COMANDO PARA SALVAR NO BANCO DE DADOS");
                this.chamaMenuJogo(jogador, inimigo);
                break;
            case 4:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Escolha entre as opções [ 1 ] , [ 2 ] , [ 3 ], [ 4 ] ");
                this.chamaMenuJogo(jogador, inimigo);
        }
    }
//=--=-=-=-==-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=     

}
