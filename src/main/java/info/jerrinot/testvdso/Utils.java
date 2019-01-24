package info.jerrinot.testvdso;

import java.util.concurrent.TimeUnit;

public final class Utils {
    public static void report(int durationSeconds, long iteration) {
        long totalMicroseconds = TimeUnit.SECONDS.toMicros(durationSeconds);
        long microsPerIteration = totalMicroseconds / iteration;
        System.out.println("Iterations: " + iteration + " this means each iteration took " + microsPerIteration
                + " microseconds");
    }
}
