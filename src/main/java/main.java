import java.util.Scanner;

public class main {
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            boolean keepRunning = true;
            while(keepRunning) {


                System.out.println("What clothing items value do you want? (Enter 0 for a random number)");
                int clothingItemVal = scanner.nextInt();


                System.out.println("How many customer do you want");
                int numberOfCustomers = scanner.nextInt();

                System.out.println("How many dressing rooms do you want");
                int numberOfRooms = scanner.nextInt();

                // Scenario 1: 3 rooms, 10 customers, random items
                Scenario scenario = new Scenario(numberOfRooms, numberOfCustomers, clothingItemVal);
                scenario.setName("Scenario");
                scenario.runScenario();
                // Your program logic here
                System.out.println("Do you want to continue? Enter true to continue, or false to stop:");

                if (scanner.hasNextBoolean()) {
                    keepRunning = scanner.nextBoolean();
                } else {
                    System.out.println("Invalid input. Please enter true or false.");
                    scanner.next(); // Clear invalid input
                }

            }

            System.out.println("---------------------------------");
            System.out.println("Running scenarios to examine different possibilities");

            // Scenario 1: 3 rooms, 10 customers, random items
            Scenario scenario1 = new Scenario(3, 10, 0);
            scenario1.setName("Scenario 01");
            scenario1.runScenario();

            // Scenario 2: 4 rooms, 20 customers, random items
            Scenario scenario2 = new Scenario(4, 20, 0);
            scenario1.setName("Scenario 02");
            scenario2.runScenario();

            // Scenario 3: 5 rooms, 20 customers, 5 items each
            Scenario scenario3 = new Scenario(5, 20, 5);
            scenario1.setName("Scenario 03");
            scenario3.runScenario();

            System.out.println("Program has stopped. Goodbye!");
            scanner.close();

    }
}
