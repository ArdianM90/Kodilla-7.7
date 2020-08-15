package com.kodilla;

import com.kodilla.rps.MainMenu;
import com.kodilla.rps.RpcGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KodillaCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(KodillaCourseApplication.class, args);
        boolean dontWantToQuit = true;
        MainMenu menu = new MainMenu();
        String userLogin = menu.getUserLogin();
        while(dontWantToQuit) {
            boolean expandedVersion = menu.wantToPlayExtendedVersion();
            boolean hardVersion = menu.wantToPlayMediumDifficulty();
            int roundsCount = menu.getRoundsCount();
            menu.showWelcomeScreen(userLogin, expandedVersion);
            RpcGame theGame = new RpcGame(userLogin, roundsCount, expandedVersion, hardVersion);
            theGame.play();
            dontWantToQuit = menu.endScreen();
        }
        System.exit(0);
    }
}
