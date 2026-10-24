class MyThread implements Runnable {
    private volatile boolean suspended = false;
    private volatile boolean stopped = false;

    @Override
    public void run() {
        int count = 1;

        while (!stopped) {
            try {
                synchronized (this) {
                    while (suspended && !stopped) {
                        wait();
                    }
                }

                if (stopped) break;

                System.out.println("Thread running... Count: " + count++);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
                if (stopped) break;
            }
        }

        System.out.println("Thread stopped successfully.");
    }

    public synchronized void mySuspend() {
        suspended = true;
        System.out.println("\n[Action] Thread Suspended...\n");
    }

    public synchronized void myResume() {
        suspended = false;
        notifyAll();
        System.out.println("\n[Action] Thread Resumed...\n");
    }

    public synchronized void myStop() {
        stopped = true;
        suspended = false;
        notifyAll();
        System.out.println("\n[Action] Thread Stopping...\n");
    }
}

public class Lab_5_ThreadControlDemo {
    public static void main(String[] args) {
        MyThread runnable = new MyThread();
        Thread thread = new Thread(runnable);

        System.out.println("--- Starting Thread Execution ---");
        thread.start();

        try {
            Thread.sleep(2000);
            runnable.mySuspend();

            Thread.sleep(2000);
            runnable.myResume();

            Thread.sleep(2000);
            runnable.myStop();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}