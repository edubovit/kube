import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        if (args[0].length() != 24) {
            System.out.println("Incorrect input string");
            return;
        }
        var initialStep = new Step(new Kube(args[0]), null, null, 0);
        var stepsPool = new HashSet<Step>();
        var lastWave = new ArrayList<Step>();
        stepsPool.add(initialStep);
        lastWave.add(initialStep);
        var winningStep = initialStep.state().isCorrect() ? initialStep : null;
        while (winningStep == null) {
            var nextWave = new ArrayList<Step>(lastWave.size() * 6);
            for (var step : lastWave) {
                for (var direction : Movement.values()) {
                    var result = step.move(direction);
                    if (stepsPool.add(result)) {
                        nextWave.add(result);
                        if (winningStep == null && result.state().isCorrect()) {
                            winningStep = result;
                        }
                    }
                }
            }
            lastWave = nextWave;
        }

        var solution = new ArrayList<Movement>(winningStep.stepNumber() + 1);
        while (winningStep.prevStep() != null) {
            solution.add(winningStep.lastMovement());
            winningStep = winningStep.prevStep();
        }
        System.out.println("Solution:");
        for (int i = 0; i < solution.size(); i++) {
            System.out.println((i + 1) + ". " + solution.get(solution.size() - 1 - i));
        }
    }

}