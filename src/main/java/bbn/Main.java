package bbn;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ConsoleBattle game!");
        System.out.println("Please enter your name: ");
        final String name = scanner.nextLine();
        final Hero hero = new Hero(name);
        System.out.println("Hello " + hero.getName() + ", welcome to the ConsoleBattle game!\n Let's start the game");

    }
}