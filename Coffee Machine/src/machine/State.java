package machine;

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
            case PRINTING_COFFEE_MENU ->
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        }
    }
}