package net.edubovit.kube.x4;

import java.util.Arrays;
import java.util.Objects;

public record Step(Kube state, Movement[] movements) {

    public Step move(Movement direction) {
        int wave = this.movements.length + 1;
        var movements = Arrays.copyOf(this.movements, wave);
        movements[wave - 1] = direction;
        return new Step(wave <= Main.REMEMBER_WAVES ? direction.move(state) : state, movements);
    }

    public Kube reproduceState() {
        var result = state;
        for (int i = Main.REMEMBER_WAVES; i < movements.length; i++) {
            result = movements[i].move(result);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return reproduceState().equals(step.reproduceState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(reproduceState());
    }

}
