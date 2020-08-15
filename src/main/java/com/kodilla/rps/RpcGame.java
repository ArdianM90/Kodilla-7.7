package com.kodilla.rps;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RpcGame {
    private final String playerName;
    private final int roundsCount;
    private final boolean expandedVersion;
    private final boolean hardDifficulty;
    private boolean isWinner = false;
    private int roundsCounter = 0;
    private int playerPoints = 0;
    private int rndPoints = 0;

    //konstruktor
    public RpcGame(String playerName, int roundsCount, boolean expandedVersion, boolean hardDifficulty) {
        this.playerName = playerName;
        this.roundsCount = roundsCount;
        this.expandedVersion = expandedVersion;
        this.hardDifficulty = hardDifficulty;
    }

    public void play() {
        while(!isWinner) {
            roundsCounter++;
            if(roundsCounter <= roundsCount) {
                System.out.println();
                System.out.println("RUNDA " + roundsCounter + "/" + roundsCount);
            } else {
                System.out.println();
                System.out.println("===DOGRYWKA===");
            }
            char playerChoice;
            playerChoice = getUserChoiceRpcTools(expandedVersion);
            int rndChoice;
            if(hardDifficulty) {
                rndChoice = computeAiDeccision(expandedVersion, playerChoice);
            } else {
                Random rnd = new Random();
                if (expandedVersion) {
                    rndChoice = rnd.nextInt(5);
                } else {
                    rndChoice = rnd.nextInt(3);
                }
            }
            System.out.println("Przeciwnik wybiera "+EnumTools.getToolNameByIndex(rndChoice));
            if (toolABeatsToolB(EnumTools.getToolNameByAbbrev(playerChoice), EnumTools.getToolNameByIndex(rndChoice))) {
                System.out.println("Pokonujesz przeciwnika");
                playerPoints++;
            } else if (toolABeatsToolB(EnumTools.getToolNameByIndex(rndChoice), EnumTools.getToolNameByAbbrev(playerChoice))) {
                System.out.println("Tracisz punkt");
                rndPoints++;
            } else {
                System.out.println("Remis");
            }
            System.out.println("===================");
            System.out.println("PUNKTACJA: "+playerName.toUpperCase()+" ["+playerPoints+"] - ["+rndPoints+"] KOMPUTER");
            System.out.println("===================");
            if (roundsCounter >= roundsCount) {
                if (playerPoints > rndPoints) {
                    System.out.println("JESTEŚ ZWYCIĘZCĄ!");
                    System.out.println();
                    isWinner = true;
                } else if (rndPoints > playerPoints) {
                    System.out.println("Koniec gry. PRZEGRAŁEŚ!");
                    System.out.println();
                    isWinner = true;
                }
            }
        }
    }

    private char getUserChoiceRpcTools(boolean expandedVersion) {
        Scanner input = new Scanner(System.in);
        if (expandedVersion) {
            System.out.println("Wybierz: papier [p], kamień [k], nożyce [n], jaszczurka [j], spock [s].");
        } else {
            System.out.println("Wybierz: papier [p], kamień [k], nożyce [n],");
        }
        String playerChoice = input.nextLine();
        while(userChoiceIsNotCorrect(playerChoice, expandedVersion)) {
            System.out.println("Wprowadzona wartość jest nieprawidłowa.");
            if (expandedVersion) {
                System.out.println("Wybierz [p] dla papieru, [k] dla kamienia, [n] dla nożyc,\n" +
                        "[j] dla jaszczurki lub [s] dla spocka.");
            } else {
                System.out.println("Wybierz [p] dla papieru, [k] dla kamienia lub [n] dla nożyc.");
            }
            playerChoice = input.nextLine();
        }
        return playerChoice.charAt(0);
    }

    private boolean userChoiceIsNotCorrect(String var, boolean expandedVersion) {
        if (expandedVersion) {
            return !(var.length() == 1 && (var.charAt(0) == 'p' || var.charAt(0) == 'P' || var.charAt(0) == 'k' || var.charAt(0) == 'n' || var.charAt(0) == 'N' || var.charAt(0) == 'j' || var.charAt(0) == 'J' || var.charAt(0) == 's' || var.charAt(0) == 'S'));
        } else {
            return !(var.length() == 1 && (var.charAt(0) == 'p' || var.charAt(0) == 'P' || var.charAt(0) == 'k' || var.charAt(0) == 'n' || var.charAt(0) == 'N'));
        }
    }

    private int computeAiDeccision(boolean expandedVersion, char playerChoosedToolAbbrev) {
        int exitToolIndex;
        Random rnd = new Random();
        boolean playToWin = rnd.nextBoolean();
        if (expandedVersion) {
            int i = rnd.nextInt(2);
            if (playToWin) {
                String[] playerIsBeatenBy = EnumTools.getWhoBeatsTool(EnumTools.getToolNameByAbbrev(playerChoosedToolAbbrev));
                exitToolIndex = EnumTools.stream()
                        .filter(o -> o.name().equals(playerIsBeatenBy[i]))
                        .map(o -> o.toolIndex)
                        .collect(Collectors.toList())
                        .get(0);
            } else {
                String[] playerBeats = EnumTools.getWhoToolBeats(EnumTools.getToolNameByAbbrev(playerChoosedToolAbbrev));
                exitToolIndex = EnumTools.stream()
                        .filter(o -> o.name().equals(playerBeats[i]))
                        .map(o -> o.toolIndex)
                        .collect(Collectors.toList())
                        .get(0);
            }
        } else {
            String playerChoosedToolName = EnumTools.getToolNameByAbbrev(playerChoosedToolAbbrev);
            if (playToWin) {
                exitToolIndex = EnumTools.stream()
                        .filter(o -> Arrays.stream(o.isBeatenBy).anyMatch(n -> n.equals(playerChoosedToolName)))
                        .filter(o -> o.toolIndex < 3)
                        .map(o -> o.toolIndex)
                        .collect(Collectors.toList())
                        .get(0);
            } else {
                exitToolIndex = EnumTools.stream()
                        .filter(o -> Arrays.stream(o.beats).anyMatch(n -> n.equals(playerChoosedToolName)))
                        .filter(o -> o.toolIndex < 3)
                        .map(o -> o.toolIndex)
                        .collect(Collectors.toList())
                        .get(0);
            }
        }
        return exitToolIndex;
    }

    private boolean toolABeatsToolB(String toolA, String toolB) {
        return Arrays.asList(EnumTools.getWhoToolBeats(toolA)).contains(toolB);
    }
}