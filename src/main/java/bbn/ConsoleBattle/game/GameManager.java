package bbn.ConsoleBattle.game;

import bbn.ConsoleBattle.ability.AbilityService;
import bbn.ConsoleBattle.hero.Hero;
import bbn.ConsoleBattle.utils.InputUtils;

public class GameManager {

    private String heroName;
    private Hero hero;
    private final AbilityService abilityService;

    public GameManager() {
        this.abilityService = new AbilityService();
    }

    public void startGame() {
        System.out.println("Welcome to the ConsoleBattle game!");
        System.out.println("Please enter your name: ");

       heroName = InputUtils.scanner.nextLine();
       hero = new Hero(heroName);

        System.out.println("Hello " + hero.getName() + ", welcome to the ConsoleBattle game!\n" +
                "Let's start the game");

        abilityService.printAbilities(hero);
        abilityService.spendHeroAvailablePoints(hero);
    }
}
