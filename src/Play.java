import java.util.Random;
import java.util.Scanner;

public class Play {
    public static int solde = 2000;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m"; 
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Welcome();
        Bet();
        Game();
    }

    public static void Welcome() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Veuillez entrer votre votre prénom");
        String name = sc.nextLine();
        System.out.println("Bonjour, " + name + " bienvenue dans notre jeu du Bandit-Manchot");
        System.out.println("Voici les règles :");
        System.out.println("- Au départ vous devrez miser une partie de votre argent !");
        System.out.println("- Si vous n'avez aucun symbole en commun vous perdez votre mise :'(");
        System.out.println("- Si vous tombez sur 2 fois le même symbole, vous re-gagner la moitié de votre mise ;)");
        System.out.println(
                "- Si vous tombez sur 3 fois le même symbole, vous gagnez votre mise = votre mise + mise * 2 :)))");
    }

    public static int Bet() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Voulez vous jouez ? 1 Oui/ 2 Non");
        int res = sc.nextInt();
        if (res == 1) {
            int solde = 2000;
            System.out.println("Commencons !");
            System.out.println("Vous disposez d'un solde de " + solde);
        } else if (res == 2) {
            System.out.println("D'accord, à la prochaine");
            System.exit(0);
        } else {
            System.out.println("Je n'ai pas compris votre réponse !");
        }
        return res;
    }

    public static void Game() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Lançement de la partie");
        System.out.print("Combien voulez-vous miser ? (Votre solde actuelle est de " + solde + ") ");
        int mise = sc.nextInt();
        double perte = mise * 0.5;
        System.out.print("Vous avez choisi de miser " + mise + "\n -Pour commencer taper 1 \n -Pour annuler taper 2");
        int res = sc.nextInt();
        if (res == 1) {
            String[] ch = { "\u2663 ", "\u2665 ", "\u2660 ", "\u2666 " };
            System.out.println(String.valueOf(ch));
            for (int i = 0; i < 1; i++) {
                Random random = new Random();
                int rand = random.nextInt(ch.length);
                System.out.print(ANSI_RED + ch[rand] + ANSI_RESET);
                Random random1 = new Random();
                int rand1 = random1.nextInt(ch.length);
                System.out.print(ANSI_BLUE + ch[rand1] + ANSI_RESET);
                Random random2 = new Random();
                int rand2 = random2.nextInt(ch.length);
                System.out.print(ANSI_YELLOW + ch[rand2] + ANSI_RESET);

                if (rand == rand1 && rand1 == rand2 && rand == rand2) {
                    solde = solde + (mise * 2);
                    System.out.println("Bravo :))) \n Vous avez gagné 2x votre mise : Ce qui fait " + mise * 2);
                    System.out.println(mise + " on été ajouté à votre solde");
                    System.out.println(" Votre nouveau solde est de " + solde);
                } else if (rand != rand1 && rand1 != rand2 && rand != rand2) {
                    solde = solde - mise;
                    System.out.println(" Dommage :'(( \n Vous avez perdu votre mise de " + mise);
                    System.out.println(mise + " on été retiré de votre solde");
                    System.out.println("Votre nouveau solde est de " + solde);
                } else   {
                    solde = solde - (mise / 2);
                    System.out.println(" Zut :( \n Vous avez perdu la moitié de votre mise : Ce qui fait " + perte);
                    System.out.println(perte + " on été retiré de votre solde ! ");
                    System.out.println(" Votre nouveau solde est de " + solde);
                } 
            }

            boolean isValid;
            do {
                if (solde == 0) {
                    System.out.println(" Vous n'avez plus rien sur votre solde , vous avez perdu :(, à la prochaine ");
                    System.exit(0);
                }
                isValid = true;
                Scanner userReply = new Scanner(System.in);
                System.out.println("Voulez vous rejouer ? (Y/N)");
                String answer = userReply.nextLine().toLowerCase();
                if ("y".equals(answer)) {
                    isValid = true;
                } else {
                    isValid = false;
                    System.out.println("Au revoir vous repartez avec " + solde);
                    System.exit(0);
                }               
                Game();
            } while (isValid);
        } else if (res == 2) {
            System.out.println("Annuler");
        }
    }
}
