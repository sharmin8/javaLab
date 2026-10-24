class PriorityThread extends Thread {
    public PriorityThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " with Priority [" + getPriority() + "] is executing.");
    }
}

public class Lab08_ThreadPriority {
    public static void main(String[] args) {
        System.out.println("=== Lab Experiment 08: Thread Priority Operations ===\n");

        PriorityThread minPriorityThread = new PriorityThread("Low-Priority-Thread");
        PriorityThread normPriorityThread = new PriorityThread("Normal-Priority-Thread");
        PriorityThread maxPriorityThread = new PriorityThread("High-Priority-Thread");

        minPriorityThread.setPriority(3);
        normPriorityThread.setPriority(Thread.NORM_PRIORITY);
        maxPriorityThread.setPriority(Thread.MAX_PRIORITY);

        System.out.println(minPriorityThread.getName() + " Priority: " + minPriorityThread.getPriority());
        System.out.println(normPriorityThread.getName() + " Priority: " + normPriorityThread.getPriority());
        System.out.println(maxPriorityThread.getName() + " Priority: " + maxPriorityThread.getPriority());
        System.out.println("----------------------------------------------\n");

        minPriorityThread.start();
        normPriorityThread.start();
        maxPriorityThread.start();
    }
}