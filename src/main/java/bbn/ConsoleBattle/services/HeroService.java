package bbn.ConsoleBattle.services;

import bbn.ConsoleBattle.ability.Ability;
import bbn.ConsoleBattle.domain.Hero;

import java.util.HashMap;
import java.util.Map;

public class HeroService {

    public String heroToString (Hero hero) {
        final StringBuilder sb = new StringBuilder();
        sb.append(hero.getCurrentGameLevel()).append("\n");
        sb.append(hero.getName()).append("\n");
        sb.append(hero.getHeroAbilityPoints()).append("\n");
        sb.append(hero.getHeroUpgradedAbilityPoints()).append("\n");
        for (Ability ability : Ability.values()) {
            sb.append(ability).append(":").append(hero.getAbilities().get(ability)).append("\n");
        }
        return sb.toString();
    }

    public Hero stringToHero (String heroString) {
        final Hero hero = new Hero();
        String[] split = heroString.split("\n");
        hero.setCurrentGameLevel(Integer.parseInt(split[0]));
        hero.setName(split[1]);
        hero.setHeroAbilityPoints(Integer.parseInt(split[2]));
        hero.setHeroUpgradedAbilityPoints(Integer.parseInt(split[3]));
        Map< Ability, Integer> abilities = new HashMap<>();
        for (int i = 4; i < split.length; i++) {
            String[] abilitySplit = split[i].split(":");
            abilities.put(Ability.valueOf(abilitySplit[0]), Integer.parseInt(abilitySplit[1]));
        }
        hero.setAbilities(abilities);
        return hero;
    }

}
