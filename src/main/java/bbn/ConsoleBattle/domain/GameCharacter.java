package bbn.ConsoleBattle.domain;

import bbn.ConsoleBattle.ability.Ability;

import java.util.Map;

public abstract class GameCharacter {

    protected String name;
    protected Map<Ability, Integer> abilities;

    public GameCharacter(String name, Map<Ability, Integer> abilities) {
        this.name = name;
        this.abilities = abilities;
    }

    protected GameCharacter() {
    }

    public String getName() {
        return name;
    }

    public Map<Ability, Integer> getAbilities() {
        return abilities;
    }
}
