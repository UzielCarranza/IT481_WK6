import java.util.Random;

class Customer extends Thread {
    public int numberOfItems;
    private long waitTime;
    private long usageTime;

    // program classes
    private DressingRoom dressingRoom = null;

    // util classes
    private Random random = null;

    public Customer(Integer numberOfItems, DressingRoom dressingRoom) {
        this.initDressingRoom(dressingRoom);
        this.numberOfItems = numberOfItems;

    }

    public Customer(DressingRoom dressingRoom)
    {
        this.initDressingRoom(dressingRoom);
        Random random = this.getRandom();
        this.numberOfItems = 1 + random.nextInt(6); // Each customer may take up to six clothing items into a dressing room.
    }

    private void initDressingRoom(DressingRoom dressingRoom)
    {
        if(this.dressingRoom == null)
        {
            this.dressingRoom = dressingRoom;
        }
    }

    private Random getRandom()
    {
        if(this.random == null)
        {
            this.random = new Random();
        }
        return this.random;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public long getWaitTime() {
        return this.waitTime;
    }

    public long getUsageTime() {
        return this.usageTime;
    }

    @Override
    public void run() {
        try {
            this.dressingRoom.requestRoom(this);

            // Simulate trying on clothes
            long startUsageTime = currentTimeMillis();
            Random random = this.getRandom();

            for (int i = 0; i < this.numberOfItems; i++) {
                // Simulated processing time instead of actual waiting
               this.usageTime += (1000 + random.nextInt(2000));
            }

            this.usageTime = currentTimeMillis() - startUsageTime;
            this.dressingRoom.releaseRoom();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Long currentTimeMillis()
    {
        return System.currentTimeMillis();
    }
}
