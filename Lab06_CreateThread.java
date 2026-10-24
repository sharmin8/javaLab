// Method 1: Extending Thread Class
class MyThreadClass extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Extending Thread Class] Running - Step " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

// Method 2: Implementing Runnable Interface
class MyRunnableClass implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("[Implementing Runnable] Running - Step " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

public class Lab06_CreateThread {
    public static void main(String[] args) {
        System.out.println("=== Lab Experiment 06: Creating Thread Class ===\n");

        // Method 1: Extending Thread
        MyThreadClass t1 = new MyThreadClass();

        // Method 2: Implementing Runnable
        MyRunnableClass runnable = new MyRunnableClass();
        Thread t2 = new Thread(runnable);

        // Start both threads
        t1.start();
        t2.start();
    }
}