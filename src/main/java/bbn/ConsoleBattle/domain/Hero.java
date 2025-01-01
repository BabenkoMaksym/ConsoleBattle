package bbn.ConsoleBattle.domain;

import bbn.ConsoleBattle.ability.Ability;
import bbn.ConsoleBattle.constant.Constant;

import java.util.HashMap;
import java.util.Map;

public class Hero extends GameCharacter{


    private int heroAbilityPoints;
    private int heroUpgradedAbilityPoints;
    private int currentGameLevel;

    public int getCurrentGameLevel() {
        return currentGameLevel;
    }

    public void upCurrentGameLevel() {
        this.currentGameLevel++;
    }

    public void addHeroAbilityPoints(int points) {
        this.heroAbilityPoints += points;
    }

    public Hero() {
        this.abilities = getInitialAbilities();
        this.heroAbilityPoints = Constant.START_ABILITY_POINTS;
        this.heroUpgradedAbilityPoints = 0;
        this.currentGameLevel = Constant.INITIAL_GAME_LEVEL;
    }

    private Map<Ability, Integer> getInitialAbilities() {
        return new HashMap<>(Map.of(Ability.ATTACK, 1, Ability.DEFENCE, 1, Ability.DEXTERITY, 1, Ability.SKILL, 1, Ability.LUCK, 1, Ability.HEALTH, 50));
    }

    public void upgradeAbility(Ability selectedAbility) {
        this.abilities.put(selectedAbility, this.abilities.get(selectedAbility) + selectedAbility.getValuePerOnePoint());
        this.heroAbilityPoints--;
        this.heroUpgradedAbilityPoints++;
        System.out.println("You have upgraded " + selectedAbility + "!");
    }

    public void restoreHealth(int healthBeforeBattle) {
        abilities.put(Ability.HEALTH, healthBeforeBattle);
    }


    public int getHeroAbilityPoints() {
        return heroAbilityPoints;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getHeroUpgradedAbilityPoints() {
        return heroUpgradedAbilityPoints;
    }

    public void removeAbilities() {
        this.abilities = getInitialAbilities();
        this.heroAbilityPoints = this.heroUpgradedAbilityPoints + this.heroAbilityPoints;
        this.heroUpgradedAbilityPoints = 0;
    }

    public void setAbilities(Map<Ability, Integer> abilities) {
        this.abilities = abilities;
    }

    public void setHeroAbilityPoints(int heroAbilityPoints) {
        this.heroAbilityPoints = heroAbilityPoints;
    }

    public void setHeroUpgradedAbilityPoints(int heroUpgradedAbilityPoints) {
        this.heroUpgradedAbilityPoints = heroUpgradedAbilityPoints;
    }

    public void setCurrentGameLevel(int currentGameLevel) {
        this.currentGameLevel = currentGameLevel;
    }

}
