package bbn.ConsoleBattle.game;

import bbn.ConsoleBattle.ability.Ability;
import bbn.ConsoleBattle.domain.Enemy;
import bbn.ConsoleBattle.services.AbilityService;
import bbn.ConsoleBattle.domain.Hero;
import bbn.ConsoleBattle.services.BattleService;
import bbn.ConsoleBattle.services.HeroService;
import bbn.ConsoleBattle.utils.EnemyGenerator;
import bbn.ConsoleBattle.utils.InputUtils;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class GameManager {


    private Hero hero;
    private final AbilityService abilityService;
    private final HeroService heroService;
    private final Map<Integer, Enemy> enemiesByLevel;
    private final BattleService battleService;

    public GameManager() {
        this.hero = new Hero();
        this.abilityService = new AbilityService();
        this.heroService = new HeroService();
        this.enemiesByLevel = EnemyGenerator.createEnemies();
        this.battleService = new BattleService(this.abilityService);
    }

    public void startGame() {
        this.initGame();

        while (hero.getCurrentGameLevel() <= enemiesByLevel.size()) {
            Enemy enemy = enemiesByLevel.get(hero.getCurrentGameLevel());
            System.out.println("0. Fight " + enemy.getName() + "(level " + hero.getCurrentGameLevel() + ")");
            System.out.println("1. Upgrade ability (" + hero.getHeroAbilityPoints() + " points to spend!)");
            System.out.println("2. Save game");
            System.out.println("3. Exit game");

            try {
                final int choice = InputUtils.readInt();
                switch (choice) {
                    case 0 -> {
                        if (battleService.isHeroReadyToBattle(this.hero, enemy)) {
                            final int heroHealthBeforeBattle = this.hero.getAbilities().get(Ability.HEALTH);

                            //TODO battle
                            hero.upCurrentGameLevel();
                        }
                    }
                    case 1 -> abilityService.upgradeAbilities(hero);

                    case 2 -> {
                        saveGame();
                    }
                    case 3 -> {
                        System.out.println("Are you sure you want to exit the game?");
                        System.out.println("0. No");
                        System.out.println("1. Yes");
                        try {
                            int exitChoice = InputUtils.readInt();
                            if (exitChoice == 1) {
                                System.out.println("Bye!");
                                System.exit(0);
                            } else {
                                System.out.println("Continue...");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
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
        while (true) {
            System.out.println("0. Start new game.");
            System.out.println("1. Load game");
            try {
                final int choice = InputUtils.readInt();
                switch (choice) {
                    case 0 -> {
                        System.out.println("Please enter your name: ");

                        String heroName = InputUtils.readString();
                        hero.setName(heroName);

                        System.out.println("Hello " + hero.getName() + ", welcome to the ConsoleBattle game!\n" +
                                "Let's start the game");
                        break;
                    }
                    case 1 -> {
                        if (loadGame()) {
                            break;
                        }
                        continue;
                    }
                    default -> {
                        System.out.println("Please enter a valid choice");
                        continue;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
        abilityService.printAbilities(hero);
        abilityService.spendHeroAvailablePoints(hero);
    }

    private boolean loadGame() {
        final File[] savedFiles = new File("save-games").listFiles();
        if (savedFiles == null || savedFiles.length == 0) {
            System.out.println("There are no saved games!");
            return false;
        }

        while (true) {

            System.out.println("Enter number of save you want to load: ");
            for (int i = 0; i < savedFiles.length; i++) {
                System.out.println(i + ". " + savedFiles[i].getName());
            }
            try {
                final int choice = InputUtils.readInt();
                if (choice >= 0 && choice < savedFiles.length) {
                    final File savedFile = savedFiles[choice];
                    if (savedFile.exists()) {
                        BufferedReader reader = new BufferedReader(new FileReader(savedFile));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        String loadGameStr = stringBuilder.toString();
                        this.hero = heroService.stringToHero(loadGameStr);
                    }
                    break;
                } else {
                    System.out.println("Please enter a valid choice");
                    continue;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }


    private void saveGame() {
        while (true) {
            System.out.println("How do you want to save the game?");
            final String saveGameName = InputUtils.readString();
            final String path = "save-games/" + saveGameName + ".txt";
            File file = new File(path);

            if (file.exists()) {
                System.out.println("Game with name " + saveGameName + " already saved! Do you want to re-save it?");
                System.out.println("0. No");
                System.out.println("1. Yes");
                final int choice = InputUtils.readInt();
                switch (choice) {
                    case 0 -> {
                        continue;
                    }
                    case 1 -> {
                    }
                    default -> System.out.println("Please enter a valid choice");
                }
            }

            boolean ignored = file.getParentFile().mkdirs();
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(heroService.heroToString(hero));
                writer.close();
                System.out.println("Game saved with name " + saveGameName + " successfully!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        }
    }
}

