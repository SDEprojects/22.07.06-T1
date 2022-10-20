package com.caged;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameControl {

    private boolean playGame = true;
    Scanner in = new Scanner(System.in);
    TextParser textParser = new TextParser();

    public void runGame() {
//        Console.clear();
//         research clear method
//        SplashScreen.show();
//        Intro.show();
//        Intro.startOption();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Player player =  mapper.readValue(new File("./resources/player.yml"), Player.class);
            System.out.println("New player: " + player.getName());
            playGame(player);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void playGame(Player player){
        while (playGame){
            System.out.println("User input:");
            String userChoice = in.nextLine();
            String lowUser = userChoice.toLowerCase();
            if (lowUser.equals("quit")) {
                playGame = false;
            }
            else {
                String action[] = textParser.textParser(lowUser);
                player.playerActions(action[0],action[1]);
            }
        }
    }

}