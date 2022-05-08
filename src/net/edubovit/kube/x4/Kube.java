package net.edubovit.kube.x4;

import java.util.Arrays;

import static net.edubovit.kube.x4.Side.*;
import static net.edubovit.kube.x4.Utils.*;

public record Kube(Color[][] matrix) {

    public static Kube ofString(String string) {
        var matrix = new Color[16][12];
        for (int i = 0; i < string.length(); i++) {
            var coord = Utils.coordByNumber(i);
            matrix[coord.i()][coord.j()] = Color.of(string.charAt(i));
        }
        return new Kube(matrix);
    }

    public boolean isCorrect() {
        return checkSide(TOP, matrix) && checkSide(LEFT, matrix) && checkSide(FRONT, matrix)
                && checkSide(RIGHT, matrix) && checkSide(BOTTOM, matrix) && checkSide(REAR, matrix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kube kube = (Kube) o;
        return Arrays.deepEquals(matrix, kube.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    private Color getColorByNumber(int number) {
        var coord = Utils.coordByNumber(number);
        return matrix[coord.i()][coord.j()];
    }

}
