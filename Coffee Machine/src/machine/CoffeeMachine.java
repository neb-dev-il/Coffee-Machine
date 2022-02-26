package machine;
import java.util.Scanner;

enum State {
    PRINTING_ACTIONS, PRINTING_RESOURCES, PRINTING_COFFEE_MENU, FILLING_WATER, FILLING_MILK, ADDING_COFFEE_BEANS, ADDING_CUPS, WITHDRAWING_MONEY;

    static void changeState(State state) {
        switch (state) {
            case PRINTING_ACTIONS -> System.out.println("Write action (buy, fill, take, remaining, exit):");
            case WITHDRAWING_MONEY -> System.out.printf("\nI gave you $%d\n", CoffeeMaker.money);
            case ADDING_CUPS -> System.out.println("Write how many ml of disposable cups of coffee you want to add:");
            case ADDING_COFFEE_BEANS -> System.out.println("Write how many ml of coffee beans you want to add:");
            case FILLING_MILK -> System.out.println("Write how many ml of milk you want to add:");
            case FILLING_WATER -> System.out.println("\nWrite how many ml of water you want to add:");
            case PRINTING_RESOURCES -> {
                System.out.println("\nThe coffee machine has:");
                System.out.printf("%d ml of water\n", CoffeeMaker.currentWater);
                System.out.printf("%d ml of milk\n", CoffeeMaker.currentMilk);
                System.out.printf("%d g of coffee beans\n", CoffeeMaker.currentCoffeeBeans);
                System.out.printf("%d disposable cups\n", CoffeeMaker.disposableCups);
                System.out.printf("$%d of money\n", CoffeeMaker.money);
                System.out.println();
            }
            case PRINTING_COFFEE_MENU -> System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        }
    }
}

enum CoffeeType {
    ESPRESSO (250, 0, 16, 4),
    LATTE (350, 75, 20, 7),
    CAPPUCCINO (200, 100, 12, 6);

    final int water;
    final int milk;
    final int coffeeBeans;
    final int price;

    CoffeeType(int water, int milk, int coffeeBeans, int price) {

        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.price = price;
    }

    void makeCoffee() {
        pourWater();
        pourMilk();
        addedCoffeeBeans();
        takeCup();
        pay();
    }

    void pourWater() {
        CoffeeMaker.currentWater -= this.water;
    }

    void pourMilk() {
        CoffeeMaker.currentMilk -= this.milk;
    }

    void addedCoffeeBeans() {
        CoffeeMaker.currentCoffeeBeans -= this.coffeeBeans;
    }

    void takeCup() {
        CoffeeMaker.disposableCups -= 1;
    }

    void pay() {
        CoffeeMaker.money += this.price;
    }

    void checkResources() {
        if (CoffeeMaker.currentWater >= this.water && CoffeeMaker.currentMilk >= this.milk && CoffeeMaker.currentCoffeeBeans >= this.coffeeBeans && CoffeeMaker.disposableCups > 0) {
            System.out.println("I have enough resources, making you a coffee!\n");
            makeCoffee();
        } else {
            System.out.println("Sorry, not enough water!\n");
        }
        CoffeeMaker.takeAction();
    }
}

public class CoffeeMachine {

    public static void main(String[] args) {
        CoffeeMaker.takeAction();
    }
}

class CoffeeMaker {
    final static Scanner scanner = new Scanner(System.in);

    static int currentWater = 400;
    static int currentMilk = 540;
    static int currentCoffeeBeans = 120;
    static int disposableCups = 9;
    static int money = 550;
    static String action = "";

    static void takeAction() {
        while (!action.equals("exit")) {
            State.changeState(State.PRINTING_ACTIONS);
            action = scanner.next();
            switch (action) {
                case "buy" -> {
                    State.changeState(State.PRINTING_COFFEE_MENU);
                    buyCoffee();
                }
                case "fill" -> {
                    State.changeState(State.FILLING_WATER);
                    refillWater(Integer.parseInt(scanner.next()));
                    State.changeState(State.FILLING_MILK);
                    refillMilk(Integer.parseInt(scanner.next()));
                    State.changeState(State.ADDING_COFFEE_BEANS);
                    refillCoffeeBeans(Integer.parseInt(scanner.next()));
                    State.changeState(State.ADDING_CUPS);
                    refillCups(Integer.parseInt(scanner.next()));
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
        String coffeeType = scanner.next();

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