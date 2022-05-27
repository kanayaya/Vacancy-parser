package Parser;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class PageThread extends Thread {
        private volatile Viewable page = null;
        private volatile boolean started = false;

        private final Supplier<Viewable> fabric;

        public PageThread(Supplier<Viewable> fabric) {
            this.fabric = fabric;
            this.setPriority(2);
        }
        public Viewable getPage() {
            while (page == null) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return page;
        }
        public boolean isStarted() {
            return started;
        }
        @Override
        public void run() {
            started = true;
            synchronized (this) {
                try {
                    System.out.println("Taking page...");
                    this.page = fabric.get();
                } catch (NoSuchElementException e) {
                    System.out.println("Error in PageThread. Maybe wrong key/value or target changed attribute:  " + e);
                }
            }
        }
    }

