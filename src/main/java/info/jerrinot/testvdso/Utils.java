package info.jerrinot.testvdso;

import com.sun.media.sound.MidiOutDeviceProvider;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.spi.MidiDeviceProvider;
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

    public static void windowsTimerHack_midi() {
        MidiOutDeviceProvider provider = new MidiOutDeviceProvider();
        MidiDevice.Info[] deviceInfo = provider.getDeviceInfo();
        if (deviceInfo.length == 0) {
            // no midi, no hack
            return;
        }
        try {
            provider.getDevice(deviceInfo[0]).open();
        } catch (MidiUnavailableException e) {
            // ignored, hack is not applied
        }
    }
}
