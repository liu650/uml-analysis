package setting;

import java.util.Random;
import java.util.Timer;

public class Construction {

    private int initialValue;
    private int maxValue;
    private int minValue;
    private int subtractValue;
    private static final Random RANDOM = new Random();

    public Construction(int value) {
        this(value, Integer.MAX_VALUE);
    }

    public Construction(int initialValue, int maxValue) {
        this.initialValue = initialValue;
        this.maxValue = maxValue;
        this.minValue = 0;
        this.subtractValue = 1;
    }

    public static int nextInt(int start, int end) {
        return start == end ? start : + RANDOM.nextInt(end - start);
    }

    public boolean health() {
        return initialValue > minValue;
    }

    public void max() {
        initialValue = maxValue;
    }

    public void subtract() {
        subtract(subtractValue);
    }

    public void subtract(int subtractValue) {
        int i = this.minValue - subtractValue;
        this.initialValue = i < minValue ? minValue : i;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public static boolean timerState() {
        return false;
    }

    public static void task(long period, TimerTask t) {
        Timer timer = new Timer();
        java.util.TimerTask timerTask = new java.util.TimerTask() {
            @Override
            public void run() {
                if (timerState()) {
                    timer.cancel();
                    return;
                }
                t.run();
            }
        };
        timer.schedule(timerTask, 0, period);
    }
}
