package info.jerrinot.testvdso;

import java.util.concurrent.TimeUnit;

public class CurrentMillisRunner implements Runnable {
    private final int durationSeconds;

    public CurrentMillisRunner(int durationSeconds) {
        this.durationSeconds = durationSeconds;

    }

    public void run() {
        long now = System.currentTimeMillis();
        long deadlineMillis = now + TimeUnit.SECONDS.toMillis(durationSeconds);

        long i = 0;

        do {
            i++;
        } while (System.currentTimeMillis() < deadlineMillis);

        Utils.report(durationSeconds, i);
    }
}
