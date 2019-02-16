package info.jerrinot.testvdso;

import java.util.concurrent.locks.LockSupport;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public final class ParkRunner implements Runnable {
    private static final int PARK_NANOS = 1;

    private final long iterations;

    public ParkRunner(long iterationCount) {
        this.iterations = iterationCount;
    }

    @Override
    public void run() {
        Utils.windowsTimerHack_midi();
        long startNanos = System.nanoTime();
        for (long i = 0; i < iterations; i++) {
            LockSupport.parkNanos(PARK_NANOS);
        }
        long durationNanos = System.nanoTime() - startNanos;
        long durationMillis = NANOSECONDS.toMillis(durationNanos);
        System.out.println(iterations + " iterations in " + durationMillis + " ms.");

        long microsPerIteration = NANOSECONDS.toMicros(durationNanos) / iterations;
        System.out.println("This means each iteration took " + microsPerIteration + " microseconds");
    }
}
