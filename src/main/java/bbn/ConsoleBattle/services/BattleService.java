package bbn.ConsoleBattle.services;

import bbn.ConsoleBattle.ability.Ability;
import bbn.ConsoleBattle.domain.Enemy;
import bbn.ConsoleBattle.domain.Hero;
import bbn.ConsoleBattle.utils.InputUtils;

import java.util.Map;
import java.util.Random;

public class BattleService {

    private final Random random;

    private final AbilityService abilityService;

    public BattleService(AbilityService abilityService) {
        this.abilityService = abilityService;
        this.random = new Random();
    }

    public boolean isHeroReadyToBattle(Hero hero, Enemy enemy) {
        System.out.println(hero.getName() + " VS " + enemy.getName());
        System.out.print("View your abilities: ");
        abilityService.printAbilities(hero);
        System.out.print("View enemy abilities: ");
        abilityService.printAbilities(enemy);
        System.out.println("Are you ready to battle?");
        System.out.println("0. No");
        System.out.println("1. Yes");

        try {
            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> {
                    System.out.println("You have escape to battle");
                    return false;
                }
                case 1 -> {
                    System.out.println("Let's the battle begin");
                    return true;
                }
                default -> {
                    System.out.println("Invalid choice");
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean battle(Hero hero, Enemy enemy) {
        final Map<Ability, Integer> heroAbilities = hero.getAbilities();
        final Map<Ability, Integer> enemyAbilities = enemy.getAbilities();

        System.out.println("You start the battle first");

        boolean isHeroTurn = true;

        while (true) {
            final int heroLife = heroAbilities.get(Ability.HEALTH);
            final int enemyLife = enemyAbilities.get(Ability.HEALTH);

            System.out.println("Your life: " + heroLife);
            System.out.println("Enemy life: " + enemyLife);

            if (isHeroTurn) {
                //TODO battle
                isHeroTurn = false;
            } else {
                //TODO battle
                isHeroTurn = true;
            }

            if (heroLife <= 0) {
                return false;
            } else if (enemyLife <= 0) {
                return true;
            }
        }
    }
}
