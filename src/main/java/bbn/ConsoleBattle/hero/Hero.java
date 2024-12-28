package bbn.ConsoleBattle.hero;

import bbn.ConsoleBattle.ability.Ability;
import bbn.ConsoleBattle.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class Hero {


    private String name;
    private final Map<Ability, Integer> abilities;
    private int heroAbilityPoints;
    private int level;

    public Hero() {
        this.abilities = getInitialAbilities();
        this.heroAbilityPoints = Constant.START_ABILITY_POINTS;
        this.level = Constant.INITIAL_HERO_LEVEL;
    }


    public String getName() {
        return name;
    }

    private Map<Ability, Integer> getInitialAbilities() {
        return new HashMap<>(Map.of(Ability.ATTACK, 1, Ability.DEFENCE, 1, Ability.DEXTERITY, 1, Ability.SKILL, 1, Ability.LUCK, 1, Ability.HEALTH, 50));
    }

    public Map<Ability, Integer> getAbilities() {
        return abilities;
    }

    public int getHeroAbilityPoints() {
        return heroAbilityPoints;
    }


    public void upgradeAbility(Ability selectedAbility) {
        this.abilities.put(selectedAbility, this.abilities.get(selectedAbility) + selectedAbility.getValuePerOnePoint());
        this.heroAbilityPoints--;
        System.out.println("You have upgraded " + selectedAbility + "!");
    }

    public void setName(String name) {
        this.name = name;
    }
}
