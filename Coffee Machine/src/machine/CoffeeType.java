package machine;

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