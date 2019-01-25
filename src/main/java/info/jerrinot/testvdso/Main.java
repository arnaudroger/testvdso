package info.jerrinot.testvdso;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Main {
    private static final int DEFAULT_ITERATION_COUNT = 1;

    public static void main(String[] args) throws Exception {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Unknown parameters " + Arrays.toString(args));
        }
        Runnable r = createRunner(args);

        int iterations = (args.length == 3) ? parseInt(args[2]) : DEFAULT_ITERATION_COUNT;
        run(r, iterations);
    }

    private static void run(Runnable r, int totalIteration) {
        for (int i = 0; i < totalIteration; i++) {
            r.run();
        }
    }

    private static Runnable createRunner(String[] args) {
        Mode mode = Mode.valueOf(args[0]);
        int runnerArgument = parseInt(args[1]);

        Runnable r;
        switch (mode) {
            case MILLIS:
                r = new CurrentMillisRunner(runnerArgument);
                break;
            case NANO:
                r = new NanoRunner(runnerArgument);
                break;
            case PARK:
                r = new ParkRunner(runnerArgument);
                break;
            default:
                throw new AssertionError("Unknown mode: " + mode);
        }
        return r;
    }
}
