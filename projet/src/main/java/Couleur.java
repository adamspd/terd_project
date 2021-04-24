public abstract class Couleur {

    /*
    Utilisation: System.out.print(Couleur.COULEUR + "message" + Couleur.RESET);

    FOREGROUND Color
    \033[38;2;<R>;<G>;<B>m

    BACKGROUND Color
    \033[48;2;<R>;<G>;<B>m
    */



    public static final String RESET = "\033[0m";
    public static final String VIOLET = "\033[38;2;204;86;255m";
    public static final String BLEU = "\033[38;2;0;110;230m";
    public static final String SARCELLE = "\033[38;2;0;175;175m";
    public static final String CYAN = "\033[38;2;0;255;255m";
    public static final String VERT = "\033[38;2;0;255;0m";
    public static final String JAUNE = "\033[38;2;255;255;0m";
    public static final String ORANGE = "\033[38;2;255;165;0m";
    public static final String ROUGE = "\033[38;2;255;0;0m";
    public static final String MARRON = "\033[38;2;175;103;37m";
    public static final String BLANC = "\033[38;2;255;255;255m";

}
