package info.jerrinot.testvdso;

import java.util.concurrent.locks.LockSupport;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public final class ParkRunner implements Runnable {
    private static final int PARK_NANOS = 2000;

    private final long iterationCount;

    public ParkRunner(long iterationCount) {
        this.iterationCount = iterationCount;
    }

    @Override
    public void run() {
        long startNanos = System.nanoTime();
        for (long i = 0; i < iterationCount; i++) {
            LockSupport.parkNanos(PARK_NANOS);
        }
        long durationNanos = System.nanoTime() - startNanos;
        long durationMillis = NANOSECONDS.toMillis(durationNanos);
        System.out.println(iterationCount + " iterations in " + durationMillis + " ms.");

        long microsPerIteration = NANOSECONDS.toMicros(durationNanos) / iterationCount;
        System.out.println("This means each iteration took " + microsPerIteration + " microseconds");
    }
}
