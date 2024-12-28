package bbn.ConsoleBattle.game;

import bbn.ConsoleBattle.ability.AbilityService;
import bbn.ConsoleBattle.hero.Hero;
import bbn.ConsoleBattle.utils.InputUtils;

import java.util.InputMismatchException;

public class GameManager {

    private static final int INITIAL_GAME_LEVEL = 1;

    private final Hero hero = new Hero();
    private final AbilityService abilityService;
    private int currentGameLevel = INITIAL_GAME_LEVEL;

    public GameManager() {
        this.abilityService = new AbilityService(hero);
    }

    public void startGame() {
        this.initGame();

        while (currentGameLevel <= 5) {
            System.out.println("0. Fight " + "level " + this.currentGameLevel);
            System.out.println("1. Upgrade ability (" + hero.getHeroAbilityPoints() + " points to spend!)");
            System.out.println("2. Save game");
            System.out.println("3. Exit game");

            try {
                final int choice = InputUtils.readInt();
                switch (choice) {
                    case 0 -> {
                        //TODO fight
                        this.currentGameLevel++;
                    }
                    case 1 -> abilityService.upgradeAbilities();

                    case 2 -> {
                        //Save game
                    }
                    case 3 -> {
                        System.out.println("Are you sure you want to exit the game?");
                        System.out.println("0. Yes");
                        System.out.println("1. No");
                        final int exitChoice = InputUtils.readInt();
                        if (exitChoice == 0) {
                            System.out.println("Bye!");
                            System.exit(0);
                        } else {
                            System.out.println("Continue...");
                        }
                    }
                    default -> System.out.println("Please enter a valid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("You have win to game! Congratulations!");
    }

    private void initGame() {
        System.out.println("Welcome to the ConsoleBattle game!");
        System.out.println("Please enter your name: ");

        String heroName = InputUtils.readString();
        hero.setName(heroName);

        System.out.println("Hello " + hero.getName() + ", welcome to the ConsoleBattle game!\n" +
                "Let's start the game");

        abilityService.printAbilities();
        abilityService.spendHeroAvailablePoints();
    }
}
