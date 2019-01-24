package info.jerrinot.testvdso;

import java.util.concurrent.TimeUnit;

public class NanoRunner implements Runnable {
    private final int durationSeconds;

    public NanoRunner(int durationSeconds) {
        this.durationSeconds = durationSeconds;

    }

    public void run() {
        long now = System.nanoTime();
        long deadlineNanos = now + TimeUnit.SECONDS.toNanos(durationSeconds);
        long i = 0;
        do {
            i++;
        } while (System.nanoTime() < deadlineNanos);

        Utils.report(durationSeconds, i);
    }
}
