package com.kodilla.rps;

import java.util.Scanner;

public class MainMenu {
    public void showWelcomeScreen(String playerName, boolean expandedVersion) {
        System.out.println("Witaj "+playerName+"!");
        if (expandedVersion) {
            System.out.println("Zasady gry:\n" +
                    "1. Możesz wybierać spośród 5 narzędzi: papieru, kamienia, nożyc, jaszczurki i spocka.\n" +
                    "2. Papier pokonuje kamień, oraz udowadnia błąd spocka.\n" +
                    "3. Kamień tępi nożyce i miażdży jaszczurkę.\n" +
                    "4. Nożyce tną papier i ranią jaszczurkę.\n" +
                    "5. Jaszczurka zjada papier i zatruwa spocka.\n" +
                    "6. Spock łamie nożyce i kruszy kamień.");
        } else {
            System.out.println("Zasady gry:\n" +
                    "1. Możesz wybierać spośród 3 narzędzi: papieru, kamienia i nożyc.\n" +
                    "2. Papier pokonuje kamień.\n" +
                    "3. Kamień tępi nożyce.\n" +
                    "4. Nożyce tną papier.");
        }
        System.out.println("Naciśnij ENTER aby rozpocząć grę.");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    public String getUserLogin() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj swój login:");
        String playerName = input.nextLine();
        while(playerName.trim().length() == 0) {
            System.out.println("Błąd - pusta nazwa użytkownika. Podaj swój login ponownie:");
            playerName = input.nextLine();
        }
        return playerName;
    }

    public boolean wantToPlayExtendedVersion() {
        Scanner input = new Scanner(System.in);
        System.out.println("Czy chcesz zagrać w wersję rozszerzoną gry papier-kamień-nożyce?\nWprowadź [t] dla TAK, lub [n] dla NIE.");
        String playerChoice = input.nextLine();
        while(playerChoice.trim().length() == 0 || playerChoice.length() > 1 || (playerChoice.charAt(0) != 't' && playerChoice.charAt(0) != 'T' && playerChoice.charAt(0) != 'n' && playerChoice.charAt(0) != 'N')) {
            System.out.println("Błąd - wybierz [t] dla TAK, lub [n] dla NIE.");
            playerChoice = input.nextLine();
        }
        return playerChoice.charAt(0) == 't' || playerChoice.charAt(0) == 'T';
    }

    public boolean wantToPlayMediumDifficulty() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wybierz poziom trudności: [m] dla normalnego i [h] dla wysokiego.");
        String playerChoice = input.nextLine();
        while(playerChoice.trim().length() == 0 || playerChoice.length() > 1 || (playerChoice.charAt(0) != 'm' && playerChoice.charAt(0) != 'M' && playerChoice.charAt(0) != 'h' && playerChoice.charAt(0) != 'H')) {
            System.out.println("Błąd - wybierz [m] dla normalnego i [h] dla wysokiego poziomu trudności.");
            playerChoice = input.nextLine();
        }
        return playerChoice.charAt(0) != 'm' && playerChoice.charAt(0) != 'M';
    }

    public int getRoundsCount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do ilu rund gramy? Podaj cyfrę od 1 do 9.");
        String playerChoice = input.nextLine();
        while(playerChoice.trim().length() == 0 || playerChoice.length() > 1 || (playerChoice.charAt(0) != '1' && playerChoice.charAt(0) != '2' && playerChoice.charAt(0) != '3' && playerChoice.charAt(0) != '4' && playerChoice.charAt(0) != '5' && playerChoice.charAt(0) != '6' && playerChoice.charAt(0) != '7' && playerChoice.charAt(0) != '8' && playerChoice.charAt(0) != '9')) {
            System.out.println("Błąd. Podaj cyfrę od 1 do 9.");
            playerChoice = input.nextLine();
        }
        return Integer.parseInt(playerChoice);
    }

    public boolean endScreen() {
        System.out.println("Wybierz [x] aby wyjść z gry, lub [n] aby rozegrać nową grę.");
        Scanner input = new Scanner(System.in);
        String userChoice = input.nextLine();
        while (userChoice.trim().length() == 0 || userChoice.length() > 1 || (userChoice.charAt(0) != 'x' && userChoice.charAt(0) != 'X' && userChoice.charAt(0) != 'n' && userChoice.charAt(0) != 'N')) {
            System.out.println("Nie rozumiem. Wybierz [x] aby wyjść z gry, lub [n] aby rozegrać nową grę.");
            input = new Scanner(System.in);
            userChoice = input.nextLine();
        }
        return userChoice.charAt(0) != 'x' && userChoice.charAt(0) != 'X';
    }
}