class ThreadDemo extends Thread {
    public ThreadDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - Iteration " + i);

            if (getName().equals("Thread-Yield") && i == 3) {
                System.out.println("--> " + getName() + " yielding execution CPU control...");
                Thread.yield(); // Hints CPU to pause and give execution chance to other threads
            }

            try {
                Thread.sleep(500); // Pauses execution for 500ms
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
        }
    }
}

public class Lab07_ThreadMethods {
    @SuppressWarnings("removal")
    public static void main(String[] args) {
        System.out.println("=== Lab Experiment 07: yield(), sleep(), and stop() Methods ===");

        ThreadDemo t1 = new ThreadDemo("Thread-Sleep");
        ThreadDemo t2 = new ThreadDemo("Thread-Yield");
        ThreadDemo t3 = new ThreadDemo("Thread-Stop");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1000); // Allow threads to run for 1 second
            System.out.println("\n[Main Thread] Stopping Thread-Stop using stop() method...");
            t3.stop(); // Stops thread execution explicitly
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}