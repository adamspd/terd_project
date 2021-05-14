package rogue1.map.map;

import rogue0.utils.DFS;
import rogue0.utils.Position;
import rogue0.utils.Utils;
import rogue2.entite.monstre.Monster;
import rogue2.entite.monstre.MonsterFactory;
import rogue2.entite.player.Player;
import rogue3.artefact.*;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    private final int Length = 30;
    private final int width = 80;
    private String EMPTYSTRING = "  ";
    private String[][] grille = new String[Length][width];
    private final int ESPACE_MINIMUM_ENTRE_SALLE = 4;
    private int nombreSalle = Utils.randInt(3, 6);
    private final int ESPACE_MINIMUM_SALLE_X = 4;
    private final int ESPACE_MINIMUM_SALLE_Y = 5;
    private final int ESPACE_MAXIMUM_SALLE_X = 10;
    private final int ESPACE_MAXIMUM_SALLE_Y = 12;
    private ArrayList<Salle> listOfSalle = new ArrayList<>();
    private ArrayList <Player> listPlayer = new ArrayList<>();
    private ArrayList <Monster> listMonster = new ArrayList<>();
    private ArrayList <Artefact> listArtefact = new ArrayList<>();
    private ArrayList <Key> listKey = new ArrayList<>();
    private ArrayList <Event> listEvent = new ArrayList<>();
    private ArrayList <Portal> listPortail = new ArrayList<>();
    private ArrayList <Coffre> listCoffre = new ArrayList<>();
    private ArrayList <Potion> listPotion = new ArrayList<>();


    public Grille(){
        for (int i = 0 ; i < getLength(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                grille[i][j] = EMPTYSTRING;
            }
        }
    }

    public void addPoint(Position position){
        grille[position.getY()][position.getX()] = "* ";
    }

    public void addElement(Position position, String symbole) {
        grille[position.getY()][position.getX()] = symbole;
    }

    public void addSalle(Salle salle){
        listOfSalle.add(salle);
        Position position = new Position(0, 0);
        for (int i=0; i<salle.getSalleWidth(); i++){
            for (int j=0; j<salle.getSalleLenght(); j++){
                position.setPos(salle.getPos().getX() + i,  salle.getPos().getY() + j);
                addPoint(position);
            }
        }
    }

    public void addEntite(Player player) {
        grille[player.getPosition().getY()][player.getPosition().getX()] = player.getSymbol();
        listPlayer.add(player);
    }

    public void addEntite(Monster monster) {
        grille[monster.getPosition().getY()][monster.getPosition().getX()] = monster.getSymbol();
        listMonster.add(monster);
    }
    public void addEntite(Artefact artefact) {
        grille[artefact.getPosition().getY()][artefact.getPosition().getX()] = artefact.getSymbol();
        listArtefact.add(artefact);
    }

    public void initialiseSalle(Grille grille) {
        int salleGenere = 0;
        while (salleGenere<nombreSalle){
            Salle salle = generateSalles(grille);
            if (grille.isEnoughFar(salle, salle.getPos())){
                grille.addSalle(salle);
                salleGenere++;
            }
        }
    }

    public void initialiseMonstre(Grille grille){
        Player player = getPlayer();
        ArrayList<Salle> listeSalle = grille.getListOfSalle();
        if (player != null) {
            for (Salle salle : listeSalle) {
                final int maxMonster = 3;
                final int distancePlayerMonster = 2;
                int choixNombreDeMonstre = (int) (Math.random() * (maxMonster + 1));
                ArrayList<Position> coord = initialiseEntite(grille, choixNombreDeMonstre, salle, player, distancePlayerMonster);

                if (coord.size() != 0) {
                    MonsterFactory factory = MonsterFactory.getInstance();
                    while (coord.size() != 0) {
                        int choix = Utils.randInt(3);
                        if (choix == 0) {
                            Monster monster = factory.generate(coord.remove(0), "goblin_archer");
                            grille.addEntite(monster);
                        } else if (choix == 1) {
                            Monster monster = factory.generate(coord.remove(0), "orc_warrior");
                            grille.addEntite(monster);
                        } else {
                            Monster monster = factory.generate(coord.remove(0), "rogue");
                            grille.addEntite(monster);
                        }

                    }
                }
            }
        }
    }
    public void initialiseArtefact(Grille grille){
        Player player = getPlayer();
        ArrayList<Salle> listeSalle = grille.getListOfSalle();

        for (Salle salle : listeSalle) {
            final int maxMonster = 4;
            final int distancePlayerMonster = 2;
            int choixNombreDeMonstre = (int) (Math.random() * (maxMonster + 1));
            ArrayList<Position> coord = initialiseEntite(grille, choixNombreDeMonstre, salle, player, distancePlayerMonster);

            if (coord.size() != 0) {
                ArtefactFactory factory = ArtefactFactory.getInstance();
                while (coord.size() != 0) {
                    int choix = Utils.randInt(3);
                    if (choix == 0){
                        Artefact artefact = factory.generate(coord.remove(0), "¤ ");
                        grille.addEntite(artefact);
                        listCoffre.add((Coffre) artefact);
                    } else if (choix == 1){
                        Artefact artefact = factory.generate(coord.remove(0), "K ");
                        grille.addEntite(artefact);
                        listKey.add((Key) artefact);
                    }
                    else if (choix == 2) {
                        Artefact artefact = factory.generate(coord.remove(0), "! ");
                        grille.addEntite(artefact);
                        listPotion.add((Potion) artefact);
                    }
                }
            }
        }
    }


    public static ArrayList<Position> initialiseEntite(
            Grille grille, int choix, Salle salle, Player player, int distancePlayerMonster) {
        ArrayList<Position> tab = new ArrayList<>();
        for(int a = 0 ; a < choix; a++) {
            int[] coord = Utils.getRandomCoordSalle(salle);
            int coordSalleRandomX = coord[0];
            int coordSalleRandomY = coord[1];
            Position position = new Position(coordSalleRandomX, coordSalleRandomY);

            boolean isEnoughFarPlayer = Utils.estAssezLoinDuJoueur(
                    position, distancePlayerMonster, grille, player
            );
            while (! grille.isInSalle(position) && !isEnoughFarPlayer) {
                coord = Utils.getRandomCoordSalle(salle);
                coordSalleRandomX = coord[0];
                coordSalleRandomY = coord[1];
                position.setPos(coordSalleRandomX, coordSalleRandomY);
                isEnoughFarPlayer = Utils.estAssezLoinDuJoueur(
                        position, distancePlayerMonster, grille, player
                );
            }
            tab.add(position);
        }
        return tab;
    }

    public boolean isEnoughFar(Salle salle, Position position) {
        try {
            for (int i =  position.getX()-ESPACE_MINIMUM_ENTRE_SALLE;
                 i < salle.getSalleWidth()+ESPACE_MINIMUM_ENTRE_SALLE+ position.getX();
                 i++)
            {
                for (int j =  position.getY() -ESPACE_MINIMUM_ENTRE_SALLE;
                     j < salle.getSalleLenght()+ESPACE_MINIMUM_ENTRE_SALLE+ position.getY();
                     j++)
                {
                    if(isInSalle(new Position(i, j))) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public boolean isInSalle(Position position)
        { return grille[position.getY()][position.getX()].equals(getSymbolSalle()); }

    public boolean isInCouloir(Position position)
    { return grille[position.getY()][position.getX()].equals(getSymbolCouloir()); }

    public boolean isPotionThere(Position position) {
        return grille[position.getY()][position.getX()].equals("! ");
    }

    public boolean isSafeThere(Position position) {
        return grille[position.getY()][position.getX()].equals("¤ ");
    }

    public boolean isPortalThere(Position position) {
        return grille[position.getY()][position.getX()].equals("P ");
    }

    public boolean isMonsterThere(Position position) {
        boolean answer = false;
        if (grille[position.getY()][position.getX()].equals("R ")||
                grille[position.getY()][position.getX()].equals("O ")||
                grille[position.getY()][position.getX()].equals("G ")){
            answer = true;
        }
        return answer;
    }

    public boolean isStairsThere(Position position){
        return grille[position.getY()][position.getX()]==Event.stairs_symbol;
    }

    public void relierSalle(Grille grille) {
        ArrayList<Salle> listeSalle = getListOfSalle();
        Couloir[] tabCouloir = new Couloir[listeSalle.size()];
        int cpt = 0;
        for (Salle salle : listeSalle)
        {
            int salleAleatoire = (int) (Math.random() * listeSalle.size());
            Salle salle2 = listeSalle.get(salleAleatoire);
            while(salle2.equals(salle))
            {
                salleAleatoire = (int) (Math.random() * listeSalle.size());
                salle2 = listeSalle.get(salleAleatoire);
            }
            tabCouloir[cpt] = new Couloir(salle,salle2);
            cpt++;

        }
        for (Couloir couloir : tabCouloir)
        {
            ajoutCouloirGrille(couloir,grille);
        }
    }

    public Salle generateSalles(Grille grille) {
        int salleLenght =(int) (ESPACE_MINIMUM_SALLE_Y + Math.random() * (ESPACE_MAXIMUM_SALLE_Y - ESPACE_MINIMUM_SALLE_Y));
        int salleWidth = (int) (ESPACE_MINIMUM_SALLE_X + Math.random() * (ESPACE_MAXIMUM_SALLE_X - ESPACE_MINIMUM_SALLE_X));
        int[] randoms = new int[2];
        while (true){
            int y = (int) ((Math.random() * grille.getLength()) - salleWidth);
            int x = (int) ((Math.random() * grille.getWidth()) - salleWidth);
            if (x >= 0 && y >= 0){
                randoms[0] = x;
                randoms[1] = y;
                break;
            }
        }
        Salle salle = new Salle(salleLenght, salleWidth, new Position(randoms[0], randoms[1]));
        return salle;
    }

    public void reset(Map niveau, Player joueur)
    {
        Grille nouvelle_grille = niveau.generateSalle();
        for (int i = 0 ; i < Length; i++) {
            for (int j = 0; j < width; j++) {
                grille[i][j] = nouvelle_grille.getSymbolAtCoord(new Position(j,i));
            }
        }

        joueur.setPosition(nouvelle_grille.getPlayer().getPosition());

        listOfSalle = nouvelle_grille.getListOfSalle();
        //listeEntite = nouvelle_grille.getListeEntite();
        //entiteAbstraitArrayList = nouvelle_grille.getListeEntiteAbstrait();
        listMonster = nouvelle_grille.getListMonster();
        listPotion = nouvelle_grille.getListPotion();
        listCoffre = nouvelle_grille.getListCoffre();
        listPortail = nouvelle_grille.getListPortail();
    }

    public String[][] getGrille() {
        return grille;
    }

    public ArrayList getListOfSalle() {
        return listOfSalle;
    }

    public ArrayList<Monster> getListMonster() {
        return listMonster;
    }

    public String getSymbolAtCoord(Position position) {
        return grille[position.getY()][position.getX()];
    }

    public int getLength() {
        return Length;
    }

    public int getWidth() {
        return width;
    }

    public String getSymbolSalle(){
        return "* ";
    }

    public String getSymbolCouloir(){
        return "# ";
    }
    public String getSymbolGrille() {
        return EMPTYSTRING;
    }

    public ArrayList<Potion> getListPotion() {
        return listPotion;
    }

    public Player getPlayer() {
        return listPlayer.get(0);
    }
    public ArrayList<Artefact> getListArtefact() {
        return listArtefact;
    }

    public ArrayList<Key> getListKey() {
        return listKey;
    }

    public ArrayList<Event> getListEvent() {
        return listEvent;
    }

    public ArrayList<Portal> getListPortail() {
        return listPortail;
    }

    public ArrayList<Coffre> getListCoffre() {
        return listCoffre;
    }

    public void setListPotion(ArrayList<Potion> listPotion) {
        this.listPotion = listPotion;
    }

    public Monster getMonster(Position position) {
        Monster returnMonster = null;
        for (Monster monster : listMonster) {
            if (monster.getPosition().getX() ==  position.getX() &&
            monster.getPosition().getY() == position.getY()){
                returnMonster = monster;
            }
        }
        return returnMonster;
    }

    private void ajoutCouloirGrille(Couloir couloir,Grille grille) {
        String symboleCouloir = couloir.getSYMBOLE();
        int[] salle1Coord = couloir.getCoordDepart();
        int[] salle2Coord = couloir.getCoordArrive();

        int salle1X = salle1Coord[0];
        int salle1Y = salle1Coord[1];

        int salle2X = salle2Coord[0];
        int salle2Y = salle2Coord[1];

        // choix de relier vers le haut si 0, relier vers le côté si 1
        int choixDepart=(int) (Math.random() * 2);

        if(choixDepart==0) {
            if(salle1X<salle2X) {
                for(int i = salle1X; i < salle2X;i++) {
                    if(! grille.isInSalle(new Position(i,salle1Y))) {
                        grille.getGrille()[salle1Y][i]= symboleCouloir;
                    }
                }
            }
            else if (salle1X> salle2X) {
                for(int i = salle1X; i > salle2X;i--) {
                    if(! grille.isInSalle(new Position(i,salle1Y))) {
                        grille.getGrille()[salle1Y][i]= symboleCouloir;
                    }
                }
            }

            if(salle1Y < salle2Y) {
                for(int i = salle1Y; i < salle2Y;i++) {
                    if(! grille.isInSalle(new Position(salle2X,i))) {
                        grille.getGrille()[i][salle2X]= symboleCouloir;
                    } else {}
                }
            }
            else if(salle1Y > salle2Y) {
                for(int i = salle1Y; i > salle2Y;i--) {
                    if(! grille.isInSalle(new Position(salle2X,i))) {
                        grille.getGrille()[i][salle2X]= symboleCouloir;
                    }
                }
            }
        }
        else {
            if(salle1Y < salle2Y) {
                for(int i = salle1Y; i < salle2Y;i++) {
                    if(! grille.isInSalle(new Position(salle1X,i))) {
                        grille.getGrille()[i][salle1X]= symboleCouloir;
                    }
                }
            }
            else if(salle1Y > salle2Y) {
                for(int i = salle1Y; i > salle2Y;i--) {
                    if(! grille.isInSalle(new Position(salle1X,i))) {
                        grille.getGrille()[i][salle1X]= symboleCouloir;
                    }
                }
            }
            if(salle1X<salle2X) {
                for(int i = salle1X; i < salle2X;i++) {
                    if(! grille.isInSalle(new Position(i,salle2Y))) {
                        grille.getGrille()[salle2Y][i]= symboleCouloir;
                    }
                }
            }
            else if (salle1X> salle2X) {
                for(int i = salle1X; i > salle2X;i--) {
                    if(! grille.isInSalle(new Position(i,salle2Y))) {
                        grille.getGrille()[salle2Y][i]= symboleCouloir;
                    }
                }
            }
        }
    }

    public void addCorridor(Grille grille){
        ArrayList<Salle> visited = new ArrayList<Salle>();

    }

    public void addPortailList(Portal portail) {
        getListPortail().add(portail);
    }

    public boolean isInsCouloirGauche(Position position) {return isInCouloir(new Position(position.getX()-1,position.getY()));}
    public boolean isInsCouloirDroite(Position position) {return isInCouloir(new Position(position.getX()+1,position.getY()));}
    public boolean isInsCouloirHaut(Position position) {return isInCouloir(new Position(position.getX(),position.getY()-1));}
    public boolean isInsCouloirBas(Position position) {return isInCouloir(new Position(position.getX(),position.getY()+1));}

    public void attack(Grille grille, Player player){
        ArrayList<Monster> monstres = grille.getListMonster();;
        for (Monster monstre : monstres) {
            boolean estEnCombat = !Utils.estAssezLoinDuJoueur(
                    monstre.getPosition(),
                    1,grille,player);
            if (estEnCombat) {
                attack(player, monstre);
            }
        }
    }

    public static void attack(Player player, Monster monster){
        player.setHitPoints(player.getHitPoints() - monster.getDamages());
    }
    public void addSymbolMonster(Position position,Monster monster){
        grille[position.getY()][position.getX()] = monster.getSymbol();
    }
    public void SearchPlayer1(Grille grille,Monster monster){
        List<Position> path = new ArrayList<>();
        DFS dfs = new DFS(grille);
        int [][] matrix = dfs.createMatrix();
        DFS.searchPath(matrix, monster.getPosition().getX(),  monster.getPosition().getY(), path);
        int size = path.size();
        //dfs.printPosition(monster.getPosition());
        Player player = grille.getPlayer();
        Position positionPlayer = new Position(player.getPosition().getX(),player.getPosition().getY());

        if (positionPlayer.getDistance(monster.getPosition()) > 1) {

            grille.addPoint(monster.getPosition());

            for (int i = 0; i < size - 1; i++) {
                monster.setPosition(path.get(i));
            }
            grille.addSymbolMonster(monster.getPosition(), monster);
        }
        //dfs.printPosition(monster.getPosition());
    }

    public void SearchPlayer(Grille grille,Monster monster){
        List<Position> path = new ArrayList<>();
        DFS dfs = new DFS(grille);
        int [][] matrix = dfs.createMatrix();
        DFS.searchPath(matrix,(int) monster.getPosition().getX(), (int) monster.getPosition().getY(), path);
        int size = path.size();
        dfs.printPosition(monster.getPosition());
        Player player = grille.getPlayer();
        Position positionPlayer = new Position(player.getPosition().getX(),player.getPosition().getY());

        if (positionPlayer.getDistance(monster.getPosition()) > 1) {
            //grille.addPoint(monster.getPosition());
            //Position positionInitial = monster.getPosition();
            boolean isINSALLE = true;
            ArrayList<Salle> listeSalle = grille.getListOfSalle();
            for (Salle salle : listeSalle){
                if ((salle.getPos().getX() <= monster.getPosition().getX() &&
                        monster.getPosition().getX() <= (salle.getPos().getX() + salle.getSalleWidth())) &&
                        (salle.getPos().getY() <= monster.getPosition().getY() &&
                                monster.getPosition().getY() <= (salle.getPos().getY() + salle.getSalleLenght()))){
                    isINSALLE = false;
                }
                if (isINSALLE) {
                    grille.addElement(monster.getPosition(), "# ");
                }
                else {grille.addElement(monster.getPosition(), "* ");}
            }
            for (int i = 0; i < size - 1; i++) {

                monster.setPosition(path.get(i));

            }
            isINSALLE = true;

            //positionInitial = monster.getPosition();


            grille.addSymbolMonster(monster.getPosition(), monster);
        }
        System.out.println();
        dfs.printPosition(monster.getPosition());
    }
}
