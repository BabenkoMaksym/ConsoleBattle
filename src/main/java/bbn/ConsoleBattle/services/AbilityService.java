package bbn.ConsoleBattle.services;

import bbn.ConsoleBattle.ability.Ability;
import bbn.ConsoleBattle.domain.Hero;
import bbn.ConsoleBattle.utils.InputUtils;

import java.util.Map;

public class AbilityService {


    public void printAbilities(Hero hero) {
        System.out.println("Your abilities:");
        for (Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
        }
        System.out.println();
    }

    public void upgradeAbilities(Hero hero) {
        System.out.println("Your abilities are:");
        for (Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
        }
        System.out.println();
        System.out.println("0. Back");
        System.out.println("1. Spend points (" + hero.getHeroAbilityPoints() + " points left)");
        System.out.println("2. Remove points (" + hero.getHeroUpgradedAbilityPoints() + " points)");

        try {
            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> {}
                case 1 -> {
                    printAbilities(hero);
                    spendHeroAvailablePoints(hero);
                }
                case 2 -> hero.removeAbilities();
                default -> System.out.println("Invalid choice");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }


    public void spendHeroAvailablePoints(Hero hero) {
        if (hero.getHeroAbilityPoints() == 0) {
            System.out.println("You don't have enough points to spend heroes!");
        }

        while (hero.getHeroAbilityPoints() > 0) {
            Ability selectedAbility = selectAbility(hero);
            if (selectedAbility != null) {
                hero.upgradeAbility(selectedAbility);
            }
            printAbilities(hero);
        }
    }

    private Ability selectAbility(Hero hero) {

        Ability ability = null;

        System.out.println("You have " + hero.getHeroAbilityPoints() + " points to spend." + "\n" +
                "Choice ability to upgrade:" + "\n" +
                "0. Explain abilities" + "\n" +
                "1. Attack" + "\n" +
                "2. Defence" + "\n" +
                "3. Dexterity" + "\n" +
                "4. Skill" + "\n" +
                "5. Luck" + "\n" +
                "6. Health" + "\n"
        );
        try {
            int choice = InputUtils.readInt();
            Map<Ability, Integer> abilities = hero.getAbilities();
            switch (choice) {
                case 0:
                    explainAbilities(abilities);
                    break;
                case 1:
                    ability = Ability.ATTACK;
                    break;
                case 2:
                    ability = Ability.DEFENCE;
                    break;
                case 3:
                    ability = Ability.DEXTERITY;
                    break;
                case 4:
                    ability = Ability.SKILL;
                    break;
                case 5:
                    ability = Ability.LUCK;
                    break;
                case 6:
                    ability = Ability.HEALTH;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return ability;
    }

    private void explainAbilities(Map<Ability, Integer> abilities) {
        System.out.println("Ability description: ");
        for (Map.Entry<Ability, Integer> entry : abilities.entrySet()) {
            Ability ability = entry.getKey();

            System.out.println(ability + " ".repeat(10 - ability.toString().length()) +
                    ": " + ability.getDescription());
        }
    }


}