import java.util.Objects;

public record Step(Kube state, Movement lastMovement, Step prevStep, int stepNumber) {

    public Step move(Movement direction) {
        return new Step(direction.move(state), direction, this, stepNumber + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return state.equals(step.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

}
