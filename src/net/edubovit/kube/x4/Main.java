package net.edubovit.kube.x4;

import net.edubovit.kube.utils.Holder;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final int REMEMBER_WAVES = getRememberWaves();

    private static final int REMEMBER_WAVES_DEFAULT = 5;

    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        String input = String.join("", args);
        if (input.length() != 96) {
            System.out.println("Incorrect input string");
            return;
        }

        System.out.println("REMEMBER_WAVES is " + REMEMBER_WAVES);

        var initialStep = new Step(Kube.ofString(input), new Movement[]{});
        var stepsPool = new ConcurrentHashMap<Integer, Boolean>();
        var lastWave = new ArrayList<Step>();
        stepsPool.put(initialStep.hashCode(), Boolean.TRUE);
        lastWave.add(initialStep);
        var winningStep = new Holder<>(initialStep.reproduceState().isCorrect() ? initialStep : null);
        int stepNumber = 1;
        long time = System.currentTimeMillis();
        while (winningStep.getValue() == null) {
            var nextWave = new ArrayList<Step>(lastWave.size() * Movement.values().length);
            var counter = new AtomicInteger(0);
            int stepsInWave = lastWave.size();
            long waveTime = System.currentTimeMillis();
            lastWave.parallelStream()
                    .forEach(step -> {
                        int counterIncremented = counter.getAndIncrement();
                        if (counterIncremented % 100_000 == 0) {
                            System.out.println("Processing " + counterIncremented + " / " + stepsInWave);
                        }
                        for (var direction : Movement.values()) {
                            var result = step.move(direction);
                            if (!stepsPool.containsKey(result.hashCode())) {
                                stepsPool.put(result.hashCode(), Boolean.TRUE);
                                synchronized (nextWave) {
                                    nextWave.add(result);
                                }
                                if (result.reproduceState().isCorrect()) {
                                    winningStep.setValue(result);
                                }
                            }
                        }
                    });
            System.out.println("Wave " + (stepNumber++) + " processed, took " + (System.currentTimeMillis() - waveTime) + "ms");
            System.gc();
            System.out.println("Memory: " + ((runtime.totalMemory() - runtime.freeMemory()) >> 20) + " / " + (runtime.maxMemory() >> 20) + "mb");
            lastWave = nextWave;
        }
        System.out.println("Solution is ready. Took " + (System.currentTimeMillis() - time) + "ms");

        System.out.println("Solution:");
        for (int i = 0; i < winningStep.getValue().movements().length; i++) {
            System.out.println((i + 1) + ". " + winningStep.getValue().movements()[i]);
        }
    }

    private static int getRememberWaves() {
        try {
            String env = System.getenv("REMEMBER_WAVES");
            if (env != null && !env.isBlank()) {
                return Integer.parseInt(env);
            } else {
                return REMEMBER_WAVES_DEFAULT;
            }
        } catch (Exception e) {
            return REMEMBER_WAVES_DEFAULT;
        }
    }

}