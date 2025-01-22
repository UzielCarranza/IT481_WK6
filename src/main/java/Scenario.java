import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
class Scenario {
    private int numberOfRooms;
    private int numberOfCustomers;
    private List<Customer> customers;
    private DressingRoom dressingRoom;
    private String name = "";

    public Scenario(int numberOfRooms, int numberOfCustomers, int clothingItems) {

        this.initProperties(numberOfRooms, numberOfCustomers);

        for (int i = 0; i < numberOfCustomers; i++) {
            if(clothingItems == 0){
                customers.add(new Customer(this.dressingRoom));
            }else{
                customers.add(new Customer(clothingItems, this.dressingRoom));
            }
        }
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        if(Objects.equals(this.name, ""))
        {
            this.name = "Scenario";
        }
        return this.name;
    }

    private void initProperties(int numberOfRooms, int numberOfCustomers)
    {
        this.numberOfRooms = numberOfRooms;
        this.numberOfCustomers = numberOfCustomers;
        this.dressingRoom = new DressingRoom(numberOfRooms);
        this.customers = new ArrayList<>();
    }

    public void runScenario() {
        System.out.println("Running " + this.getName());
        long startTime = System.currentTimeMillis();

        for (Customer customer : this.customers) {
            customer.start();
        }

        for (Customer customer : this.customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Calculate statistics
        long totalWaitTime = customers.stream().mapToLong(Customer::getWaitTime).sum();
        double averageWaitTime = (totalWaitTime / (double) this.numberOfCustomers);

        long totalUsageTime = customers.stream().mapToLong(Customer::getUsageTime).sum();
        double averageUsageTime = (totalUsageTime / (double) this.numberOfCustomers);

        double avgNumOfItems = this.customers.stream().mapToInt(c -> c.numberOfItems).average().orElse(0);


        this.printResults(elapsedTime, averageWaitTime, averageUsageTime, avgNumOfItems);

    }



    private void printResults(long elapsedTime, double averageWaitTime, double averageUsageTime, double avgNumOfItems)
    {
        System.out.println(this.getName() + " Results:");
        System.out.println("Total Customers: " + this.numberOfCustomers);
        System.out.println("Total Rooms: " + this.numberOfRooms);
        System.out.println("Average Number of Items: " + avgNumOfItems);
        System.out.println("Average Usage Time (ms): " + averageUsageTime);
        System.out.println("Average Wait Time (ms): " + averageWaitTime);
        System.out.println("Total Elapsed Time (ms): " + elapsedTime);
    }
}