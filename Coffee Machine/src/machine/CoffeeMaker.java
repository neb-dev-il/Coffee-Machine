package machine;

import java.util.Scanner;

class CoffeeMaker {
    private final static Scanner SCANNER = new Scanner(System.in);

    static int currentWater = 400;
    static int currentMilk = 540;
    static int currentCoffeeBeans = 120;
    static int disposableCups = 9;
    static int money = 550;
    static String action = "";

    static void takeAction() {
        while (!action.equals("exit")) {
            State.changeState(State.PRINTING_ACTIONS);
            action = SCANNER.next();
            switch (action) {
                case "buy" -> {
                    State.changeState(State.PRINTING_COFFEE_MENU);
                    buyCoffee();
                }
                case "fill" -> {
                    State.changeState(State.FILLING_WATER);
                    refillWater(Integer.parseInt(SCANNER.next()));
                    State.changeState(State.FILLING_MILK);
                    refillMilk(Integer.parseInt(SCANNER.next()));
                    State.changeState(State.ADDING_COFFEE_BEANS);
                    refillCoffeeBeans(Integer.parseInt(SCANNER.next()));
                    State.changeState(State.ADDING_CUPS);
                    refillCups(Integer.parseInt(SCANNER.next()));
                }
                case "take" -> {
                    State.changeState(State.WITHDRAWING_MONEY);
                    takeMoney();
                }
                case "remaining" -> State.changeState(State.PRINTING_RESOURCES);
            }
        }
    }

    static void refillWater(int addedWater) {
        currentWater += addedWater;
    }

    static void refillMilk(int addedMilk) {
        currentMilk += addedMilk;
    }

    static void refillCoffeeBeans(int addedCoffeeBeans) {
        currentCoffeeBeans += addedCoffeeBeans;
    }

    static void refillCups(int addedCups) {
        disposableCups += addedCups;
        System.out.println();
    }

    static void takeMoney() {
        money = 0;
        System.out.println();
    }

    static void buyCoffee() {
        String coffeeType = SCANNER.next();

        switch (coffeeType) {
            case "1" -> CoffeeType.ESPRESSO.checkResources();
            case "2" -> CoffeeType.LATTE.checkResources();
            case "3" -> CoffeeType.CAPPUCCINO.checkResources();
            case "back" -> {
                System.out.println();
                takeAction();
            }
        }
    }
}