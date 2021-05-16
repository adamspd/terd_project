package test;

import rogue0.utils.Position;
import rogue1.map.map.Draw;
import rogue1.map.map.Grille;
import rogue1.map.map.Salle;
import rogue2.entite.monstre.GoblinArcher;
import rogue2.entite.monstre.Monster;
import rogue2.entite.move.Move;
import rogue2.entite.player.Player;
import rogue3.artefact.Portal;

import java.util.ArrayList;

public class Portal_Test {
    static Draw draw = new Draw();
    static Grille grille;
    static int salleLenght = 4;
    static int salleWidth = 4;
    static Salle salleDepart = new Salle(salleLenght, salleWidth, new Position(0, 0));
    static Salle salleArrive = new Salle(salleLenght, salleWidth, new Position(6, 0));
    static Position positionPortailDepart;
    static Position positionPortailArrive;
    static Portal portailDepart;
    static Portal portailArrive;


    //A lancer dans Run
    public static void Start(){
        Grille1();
        System.out.println("JumpDown = " + JumpDown());
        Grille1();
        System.out.println("JumpUp = " + JumpUp());
        Grille1();
        System.out.println("JumpRight = " + JumpRight());
        Grille1();
        System.out.println("JumpLeft = " + JumpLeft() );

        Grille2();
        System.out.println("ifEmptyDown_JumpUp = " + ifEmptyDown_JumpUp());
        Grille2();
        System.out.println("ifEmptyRight_JumpLeft = " + ifEmptyRight_JumpLeft());

        Grille3();
        System.out.println("ifEmptyUp_JumpDown = " + ifEmptyUp_JumpDown());
        Grille3();
        System.out.println("ifEmptyLeft_JumpRight = " + ifEmptyLeft_JumpRight());

        Grille1();
        System.out.println("ifMonsterDown_KillHim = " + ifMonsterDown_KillHim());
        Grille1();
        System.out.println("ifMonsterUp_KillHim = " + ifMonsterUp_KillHim());
        Grille1();
        System.out.println("ifMonsterRight_KillHim = " +ifMonsterRight_KillHim());
        Grille1();
        System.out.println("ifMonsterLeft_KillHim = " + ifMonsterLeft_KillHim());

        grille = null;
    }



    public static boolean JumpDown() {
        Player player = new Player(new Position(positionPortailDepart.getX(), positionPortailDepart.getY()-1));
        grille.addEntite(player);
        Move.moveDown(grille, grille.getPlayer());
        return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX(),positionPortailArrive.getY()+1)) == player.getSymbol();
    }

    public static boolean JumpUp() {
        Player player = new Player(new Position(positionPortailDepart.getX(), positionPortailDepart.getY()+1));
        grille.addEntite(player);
        Move.moveUp(grille, grille.getPlayer());
        return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX(),positionPortailArrive.getY()-1)) == player.getSymbol();
    }

    public static boolean JumpRight() {
        Player player = new Player(new Position(positionPortailDepart.getX()-1, positionPortailDepart.getY()));
        grille.addEntite(player);
        Move.moveRight(grille, grille.getPlayer());
        return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX()+1,positionPortailArrive.getY())) == player.getSymbol();
    }

    public static boolean JumpLeft() {
        Player player = new Player(new Position(positionPortailDepart.getX()+1, positionPortailDepart.getY()));
        grille.addEntite(player);
        Move.moveLeft(grille, grille.getPlayer());
        return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX()-1,positionPortailArrive.getY())) == player.getSymbol();
    }



    public static boolean ifEmptyDown_JumpUp(){
        Player player = new Player(new Position(positionPortailDepart.getX(), positionPortailDepart.getY()-1));
        grille.addEntite(player);
        Move.moveDown(grille, grille.getPlayer());
        return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX(),positionPortailArrive.getY()-1)) == player.getSymbol();
    }
    public static boolean ifEmptyRight_JumpLeft(){
        Player player = new Player(new Position(positionPortailDepart.getX()-1, positionPortailDepart.getY()));
        grille.addEntite(player);
        Move.moveRight(grille, grille.getPlayer());
        return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX()-1,positionPortailArrive.getY())) == player.getSymbol();
    }


     public static boolean ifEmptyUp_JumpDown(){
         Player player = new Player(new Position(positionPortailDepart.getX(), positionPortailDepart.getY()+1));
         grille.addEntite(player);
         Move.moveUp(grille, grille.getPlayer());
         return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX(),positionPortailArrive.getY()+1)) == player.getSymbol();
    }
    public static boolean ifEmptyLeft_JumpRight() {
        Player player = new Player(new Position(positionPortailDepart.getX()+1, positionPortailDepart.getY()));
        grille.addEntite(player);
        Move.moveLeft(grille, grille.getPlayer());
        return grille.getSymbolAtCoord(new Position(positionPortailArrive.getX()+1, positionPortailArrive.getY())) == player.getSymbol();
    }


    public static boolean ifMonsterDown_KillHim() {
        Player player = new Player(new Position(positionPortailDepart.getX(), positionPortailDepart.getY()-1));
        grille.addEntite(player);
        Position positionMonstre = new Position(positionPortailArrive.getX(),positionPortailArrive.getY()+1);
        Monster monster = new GoblinArcher(positionMonstre);
        grille.addEntite(monster);
        Move.moveDown(grille, grille.getPlayer());
        return grille.getMonster(positionMonstre)==null && grille.getSymbolAtCoord(positionMonstre) == player.getSymbol();
    }

    public static boolean ifMonsterUp_KillHim() {
        Player player = new Player(new Position(positionPortailDepart.getX(), positionPortailDepart.getY()+1));
        grille.addEntite(player);
        Position positionMonstre = new Position(positionPortailArrive.getX(),positionPortailArrive.getY()-1);
        Monster monster = new GoblinArcher(positionMonstre);
        grille.addEntite(monster);
        Move.moveUp(grille, grille.getPlayer());
        return grille.getMonster(positionMonstre)==null && grille.getSymbolAtCoord(positionMonstre) == player.getSymbol();
    }

    public static boolean ifMonsterRight_KillHim() {
        Player player = new Player(new Position(positionPortailDepart.getX()-1, positionPortailDepart.getY()));
        grille.addEntite(player);
        Position positionMonstre = new Position(positionPortailArrive.getX()+1,positionPortailArrive.getY());
        Monster monster = new GoblinArcher(positionMonstre);
        grille.addEntite(monster);
        Move.moveRight(grille, grille.getPlayer());
        return grille.getMonster(positionMonstre)==null && grille.getSymbolAtCoord(positionMonstre) == player.getSymbol();
    }

    public static boolean ifMonsterLeft_KillHim() {
        Player player = new Player(new Position(positionPortailDepart.getX()+1, positionPortailDepart.getY()));
        grille.addEntite(player);
        Position positionMonstre = new Position(positionPortailArrive.getX()-1,positionPortailArrive.getY());
        Monster monster = new GoblinArcher(positionMonstre);
        grille.addEntite(monster);
        Move.moveLeft(grille, grille.getPlayer());
        return grille.getMonster(positionMonstre)==null && grille.getSymbolAtCoord(positionMonstre) == player.getSymbol();
    }




    public static void generationSalles(){
        grille = new Grille();
        //Salle de départ
        for (int i = salleDepart.getPos().getY(); i < salleDepart.getPos().getY() + salleDepart.getSalleLenght(); i++) {
            for (int j = salleDepart.getPos().getX(); j < salleDepart.getPos().getX() + salleDepart.getSalleWidth(); j++) {
                grille.addElement(new Position(j, i), grille.getSymbolSalle());
            }
        }
        grille.getListOfSalle().add(salleDepart);
        //Salle d'arrivée
        for (int i = salleArrive.getPos().getY(); i < salleArrive.getPos().getY() + salleArrive.getSalleLenght(); i++) {
            for (int j = salleArrive.getPos().getX(); j < salleArrive.getPos().getX() + salleArrive.getSalleWidth(); j++) {
                grille.addElement(new Position(j, i), grille.getSymbolSalle());
            }
        }
        grille.getListOfSalle().add(salleArrive);
    }

    //Iniatialisation des portails
    public static void Grille1() {
        generationSalles();
        positionPortailDepart = new Position(salleDepart.getPos().getX() + 1, salleDepart.getPos().getY() + 1);
        positionPortailArrive = new Position(salleArrive.getPos().getX() + 1, salleArrive.getPos().getY() + 1);
        portailDepart = new Portal(positionPortailDepart, Portal.getSymbole());
        portailArrive = new Portal(positionPortailArrive, Portal.getSymbole());
        grille.addElement(positionPortailDepart, Portal.getSymbole());
        grille.addElement(positionPortailArrive, Portal.getSymbole());
        grille.addPortailList(portailDepart);
        grille.addPortailList(portailArrive);
    }

    public static void Grille2(){
        generationSalles();
        positionPortailDepart = new Position(salleDepart.getPos().getX()+salleDepart.getSalleWidth()-1, salleDepart.getPos().getY()+salleDepart.getSalleLenght()-1);
        positionPortailArrive = new Position(salleArrive.getPos().getX()+salleArrive.getSalleWidth()-1, salleArrive.getPos().getY()+salleArrive.getSalleLenght()-1);
        portailDepart = new Portal(positionPortailDepart, Portal.getSymbole());
        portailArrive = new Portal(positionPortailArrive, Portal.getSymbole());
        grille.addElement(positionPortailDepart, Portal.getSymbole());
        grille.addElement(positionPortailArrive, Portal.getSymbole());
        grille.addPortailList(portailDepart);
        grille.addPortailList(portailArrive);
    }

    public static void Grille3() {
        generationSalles();
        positionPortailDepart = new Position(salleDepart.getPos().getX(), salleDepart.getPos().getY());
        positionPortailArrive = new Position(salleArrive.getPos().getX(), salleArrive.getPos().getY());
        portailDepart = new Portal(positionPortailDepart, Portal.getSymbole());
        portailArrive = new Portal(positionPortailArrive, Portal.getSymbole());
        grille.addElement(positionPortailDepart, Portal.getSymbole());
        grille.addElement(positionPortailArrive, Portal.getSymbole());
        grille.addPortailList(portailDepart);
        grille.addPortailList(portailArrive);
    }
}





