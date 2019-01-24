package info.jerrinot.testvdso;

public class Main {

    public static void main(String[] args) {
        Mode mode = Mode.valueOf(args[0]);
        int duration = Integer.parseInt(args[1]);

        Runnable r;
        switch (mode) {
            case MILLIS:
                r = new CurrentMillisRunner(duration);
                break;
            case NANO:
                r = new NanoRunner(duration);
                break;
            default:
                throw new AssertionError("Unknown mode: " + mode);
        }
        r.run();
    }
}
