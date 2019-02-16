package info.jerrinot.testvdso;

import java.util.concurrent.TimeUnit;

public final class Utils {
    public static void report(int durationSeconds, long iteration) {
        long totalMicroseconds = TimeUnit.SECONDS.toMicros(durationSeconds);
        long microsPerIteration = totalMicroseconds / iteration;
        System.out.println("Iterations: " + iteration + " this means each iteration took " + microsPerIteration
                + " microseconds");
    }

    public static void windowsTimerHack() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                // a delicious interrupt, omm, omm
            }
        });
        t.setDaemon(true);
        t.start();
    }
}
