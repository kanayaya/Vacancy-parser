package Parser;

import java.util.NoSuchElementException;

public class PageThread extends Thread {
        private volatile Viewable page = null;
        private volatile boolean started = false;

        private final PageParser fabric;

        public PageThread(PageParser fabric) {
            this.fabric = fabric;
            this.setPriority(2);
        }
        public Viewable getPage() {
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
                    this.page = fabric.getPage();
                } catch (NoSuchElementException e) {
                    System.out.println("Error in PageThread. Maybe wrong key/value or target changed attribute:  " + e);
                }
            }
        }
    }

