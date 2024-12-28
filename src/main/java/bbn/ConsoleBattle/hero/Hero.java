package bbn.ConsoleBattle.hero;

import bbn.ConsoleBattle.ability.Ability;

import java.util.HashMap;
import java.util.Map;

public class Hero {
    private final String name;
    private final Map<Ability, Integer> abilities;
    private int abilityPoint;

    public Hero(String name) {
        this.name = name;
        this.abilities = getInitialAbilities();
        this.abilityPoint = 7;
    }

    public String getName() {
        return name;
    }

    private Map<Ability, Integer> getInitialAbilities() {
        return new HashMap<>(Map.of(
                Ability.ATTACK, 1,
                Ability.DEFENCE, 1,
                Ability.DEXTERITY, 1,
                Ability.SKILL, 1,
                Ability.LUCK, 1,
                Ability.HEALTH, 50
                ));
    }

    public Map<Ability, Integer> getAbilities() {
        return abilities;
    }

    public int getAbilityPoint() {
        return abilityPoint;
    }


    public void upgradeAbility(Ability selectedAbility) {
        this.abilities.put(selectedAbility, this.abilities.get(selectedAbility) + selectedAbility.getValuePerOnePoint());
        this.abilityPoint--;
        System.out.println("You have upgraded " + selectedAbility + "!");
    }
}
