package bbn.ConsoleBattle.ability;

public enum Ability {

    ATTACK(1, "Attack is the ability to deal damage. Final damage is also affected by dexterity and skill."),
    DEFENCE(1, "Defence is the ability to reduce damage. Final damage is also affected by dexterity."),
    DEXTERITY(1, "Dexterity is important for both attack and defence. It affects final damage and final damage reduction."),
    SKILL(1, "Skill is important for attack and also for critical hit chance."),
    LUCK(1, "Luck is important for critical hit chance."),
    HEALTH(5, "Health is the amount of damage you can take before you die. After each battle, health can be restored to full.");

    private final String description;
    private final int valuePerOnePoint;


    Ability( int valuePerOnePoint, String description) {
        this.description = description;
        this.valuePerOnePoint = valuePerOnePoint;
    }

    public String getDescription() {
        return description;
    }

    public int getValuePerOnePoint() {
        return valuePerOnePoint;
    }
}
