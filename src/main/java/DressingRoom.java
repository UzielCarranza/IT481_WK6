import java.util.concurrent.Semaphore;

class DressingRoom {
    private Semaphore semaphore = null;

    // Default constructor using three rooms as the default
    public DressingRoom() {
        int defaultNumOfNums = 3;
        this.initSemaphore(defaultNumOfNums);
    }

    // Constructor that takes a number of rooms as a parameter
    public DressingRoom(int numberOfRooms) {
        this.initSemaphore(numberOfRooms);
    }

    private void initSemaphore(int numOfRooms){
        if(this.semaphore == null)
        {
            this.semaphore = new Semaphore(numOfRooms);
        }
    }

    // Method to request access to a room
    public void requestRoom(Customer customer) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        this.semaphore.acquire(); // Wait for an available room
        customer.setWaitTime(System.currentTimeMillis() - startTime);
    }

    // Method to release a room
    public void releaseRoom() {
        this.semaphore.release();
    }
}

