package net.edubovit.kube.x4;

import net.edubovit.kube.utils.Coord;

import java.util.Arrays;

public final class Utils {

    private Utils() {}

    public static Coord coordByNumber(int number) {
        if (number < 16) {
            return new Coord(number / 4, 4 + number % 4);
        } else if (number < 64) {
            return new Coord(4 + (number - 16) / 12, (number - 16) % 12);
        } else {
            return new Coord(8 + (number - 64) / 4, 4 + (number - 64) % 4);
        }
    }

    public static Color[][] copyMatrix(Color[][] original) {
        var result = new Color[original.length][];
        for (int i = 0; i < result.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    public static void rotateVerticalForward(Color[][] original, Color[][] result, int ring) {
        for (int i = 0; i < 16; i++) {
            var from = coordByVerticalRingNumber(ring, (12 + i) % 16);
            var to = coordByVerticalRingNumber(ring, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateVerticalBackward(Color[][] original, Color[][] result, int ring) {
        for (int i = 0; i < 16; i++) {
            var from = coordByVerticalRingNumber(ring, (4 + i) % 16);
            var to = coordByVerticalRingNumber(ring, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateHorizontalForward(Color[][] original, Color[][] result, int ring) {
        for (int i = 0; i < 16; i++) {
            var from = coordByHorizontalRingNumber(ring, (12 + i) % 16);
            var to = coordByHorizontalRingNumber(ring, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateHorizontalBackward(Color[][] original, Color[][] result, int ring) {
        for (int i = 0; i < 16; i++) {
            var from = coordByHorizontalRingNumber(ring, (4 + i) % 16);
            var to = coordByHorizontalRingNumber(ring, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateCircleForward(Color[][] original, Color[][] result, int ring) {
        for (int i = 0; i < 16; i++) {
            var from = coordByCircleRingNumber(ring, (12 + i) % 16);
            var to = coordByCircleRingNumber(ring, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateCircleBackward(Color[][] original, Color[][] result, int ring) {
        for (int i = 0; i < 16; i++) {
            var from = coordByCircleRingNumber(ring, (4 + i) % 16);
            var to = coordByCircleRingNumber(ring, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateOuterForward(Color[][] original, Color[][] result, Side side) {
        for (int i = 0; i < 12; i++) {
            var from = coordByRotatingOuterRingNumber(side, (9 + i) % 12);
            var to = coordByRotatingOuterRingNumber(side, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateOuterBackward(Color[][] original, Color[][] result, Side side) {
        for (int i = 0; i < 12; i++) {
            var from = coordByRotatingOuterRingNumber(side, (3 + i) % 12);
            var to = coordByRotatingOuterRingNumber(side, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateInnerForward(Color[][] original, Color[][] result, Side side) {
        for (int i = 0; i < 4; i++) {
            var from = coordByRotatingInnerRingNumber(side, (3 + i) % 4);
            var to = coordByRotatingInnerRingNumber(side, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static void rotateInnerBackward(Color[][] original, Color[][] result, Side side) {
        for (int i = 0; i < 4; i++) {
            var from = coordByRotatingInnerRingNumber(side, (1 + i) % 4);
            var to = coordByRotatingInnerRingNumber(side, i);
            result[to.i()][to.j()] = original[from.i()][from.j()];
        }
    }

    public static boolean checkSide(Side side, Color[][] matrix) {
        var offset = offsetBySide(side);
        var color = matrix[offset.i()][offset.j()];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (color != matrix[offset.i() + i][offset.j() + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Coord offsetBySide(Side side) {
        int i = switch (side) {
            case TOP -> 0;
            case LEFT, FRONT, RIGHT -> 4;
            case BOTTOM -> 8;
            case REAR -> 12;
        };
        int j = switch (side) {
            case LEFT -> 0;
            case TOP, FRONT, BOTTOM, REAR -> 4;
            case RIGHT -> 8;
        };
        return new Coord(i, j);
    }

    private static Coord coordByVerticalRingNumber(int ring, int number) {
        return new Coord(number, 4 + ring);
    }

    private static Coord coordByHorizontalRingNumber(int ring, int number) {
        if (number < 12) {
            return new Coord(4 + ring, number);
        } else {
            return new Coord(15 - ring, 19 - number);
        }
    }

    private static Coord coordByCircleRingNumber(int ring, int number) {
        if (number < 4) {
            return new Coord(3 - ring, 4 + number);
        } else if (number < 8) {
            return new Coord(number, 8 + ring);
        } else if (number < 12) {
            return new Coord(8 + ring, 15 - number);
        } else {
            return new Coord(19 - number, 3 - ring);
        }
    }

    private static Coord coordByRotatingOuterRingNumber(Side side, int number) {
        var offset = offsetBySide(side);
        int iOffset = offset.i();
        int jOffset = offset.j();
        if (number < 4) {
            return new Coord(iOffset, jOffset + number);
        } else if (number < 7) {
            return new Coord(iOffset + number - 3, jOffset + 3);
        } else if (number < 10) {
            return new Coord(iOffset + 3, jOffset + 9 - number);
        } else {
            return new Coord(iOffset + 12 - number, jOffset);
        }
    }

    private static Coord coordByRotatingInnerRingNumber(Side side, int number) {
        var offset = offsetBySide(side);
        int iOffset = offset.i() + 1;
        int jOffset = offset.j() + 1;
        return switch (number) {
            case 0 -> new Coord(iOffset, jOffset);
            case 1 -> new Coord(iOffset, jOffset + 1);
            case 2 -> new Coord(iOffset + 1, jOffset + 1);
            case 3 -> new Coord(iOffset + 1, jOffset);
            default -> throw new IllegalArgumentException();
        };
    }

}
