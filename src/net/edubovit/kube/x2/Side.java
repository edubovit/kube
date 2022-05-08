package net.edubovit.kube.x2;

import java.util.Objects;

public record Side(Color topLeft, Color topRight, Color bottomLeft, Color bottomRight) {

    public Side turnClockwise() {
        return new Side(bottomLeft, topLeft, bottomRight, topRight);
    }

    public Side turnCounterClockwise() {
        return new Side(topRight, bottomRight, topLeft, bottomLeft);
    }

    public boolean isCorrect() {
        return topLeft == topRight && topLeft == bottomLeft && topLeft == bottomRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Side side = (Side) o;
        return topLeft == side.topLeft && topRight == side.topRight
                && bottomLeft == side.bottomLeft && bottomRight == side.bottomRight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, topRight, bottomLeft, bottomRight);
    }

}
