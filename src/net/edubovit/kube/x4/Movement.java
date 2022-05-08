package net.edubovit.kube.x4;

import java.util.function.Function;

import static net.edubovit.kube.x4.Side.*;
import static net.edubovit.kube.x4.Utils.*;

public enum Movement {

    TOP_1_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateHorizontalBackward(kube.matrix(), result.matrix(), 0);
        rotateOuterForward(kube.matrix(), result.matrix(), TOP);
        rotateInnerForward(kube.matrix(), result.matrix(), TOP);
        return result;
    }),

    TOP_2_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateHorizontalBackward(kube.matrix(), result.matrix(), 0);
        rotateHorizontalBackward(kube.matrix(), result.matrix(), 1);
        rotateOuterForward(kube.matrix(), result.matrix(), TOP);
        rotateInnerForward(kube.matrix(), result.matrix(), TOP);
        return result;
    }),

    TOP_3_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateHorizontalBackward(kube.matrix(), result.matrix(), 0);
        rotateHorizontalBackward(kube.matrix(), result.matrix(), 1);
        rotateHorizontalBackward(kube.matrix(), result.matrix(), 2);
        rotateOuterForward(kube.matrix(), result.matrix(), TOP);
        rotateInnerForward(kube.matrix(), result.matrix(), TOP);
        return result;
    }),

    TOP_1_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateHorizontalForward(kube.matrix(), result.matrix(), 0);
        rotateOuterBackward(kube.matrix(), result.matrix(), TOP);
        rotateInnerBackward(kube.matrix(), result.matrix(), TOP);
        return result;
    }),

    TOP_2_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateHorizontalForward(kube.matrix(), result.matrix(), 0);
        rotateHorizontalForward(kube.matrix(), result.matrix(), 1);
        rotateOuterBackward(kube.matrix(), result.matrix(), TOP);
        rotateInnerBackward(kube.matrix(), result.matrix(), TOP);
        return result;
    }),

    TOP_3_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateHorizontalForward(kube.matrix(), result.matrix(), 0);
        rotateHorizontalForward(kube.matrix(), result.matrix(), 1);
        rotateHorizontalForward(kube.matrix(), result.matrix(), 2);
        rotateOuterBackward(kube.matrix(), result.matrix(), TOP);
        rotateInnerBackward(kube.matrix(), result.matrix(), TOP);
        return result;
    }),

    RIGHT_1_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateVerticalBackward(kube.matrix(), result.matrix(), 3);
        rotateOuterForward(kube.matrix(), result.matrix(), RIGHT);
        rotateInnerForward(kube.matrix(), result.matrix(), RIGHT);
        return result;
    }),

    RIGHT_2_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateVerticalBackward(kube.matrix(), result.matrix(), 3);
        rotateVerticalBackward(kube.matrix(), result.matrix(), 2);
        rotateOuterForward(kube.matrix(), result.matrix(), RIGHT);
        rotateInnerForward(kube.matrix(), result.matrix(), RIGHT);
        return result;
    }),

    RIGHT_3_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateVerticalBackward(kube.matrix(), result.matrix(), 3);
        rotateVerticalBackward(kube.matrix(), result.matrix(), 2);
        rotateVerticalBackward(kube.matrix(), result.matrix(), 1);
        rotateOuterForward(kube.matrix(), result.matrix(), RIGHT);
        rotateInnerForward(kube.matrix(), result.matrix(), RIGHT);
        return result;
    }),

    RIGHT_1_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateVerticalForward(kube.matrix(), result.matrix(), 3);
        rotateOuterBackward(kube.matrix(), result.matrix(), RIGHT);
        rotateInnerBackward(kube.matrix(), result.matrix(), RIGHT);
        return result;
    }),

    RIGHT_2_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateVerticalForward(kube.matrix(), result.matrix(), 3);
        rotateVerticalForward(kube.matrix(), result.matrix(), 2);
        rotateOuterBackward(kube.matrix(), result.matrix(), RIGHT);
        rotateInnerBackward(kube.matrix(), result.matrix(), RIGHT);
        return result;
    }),

    RIGHT_3_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateVerticalForward(kube.matrix(), result.matrix(), 3);
        rotateVerticalForward(kube.matrix(), result.matrix(), 2);
        rotateVerticalForward(kube.matrix(), result.matrix(), 1);
        rotateOuterBackward(kube.matrix(), result.matrix(), RIGHT);
        rotateInnerBackward(kube.matrix(), result.matrix(), RIGHT);
        return result;
    }),

    REAR_1_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateCircleForward(kube.matrix(), result.matrix(), 3);
        rotateOuterBackward(kube.matrix(), result.matrix(), REAR);
        rotateInnerBackward(kube.matrix(), result.matrix(), REAR);
        return result;
    }),

    REAR_2_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateCircleForward(kube.matrix(), result.matrix(), 3);
        rotateCircleForward(kube.matrix(), result.matrix(), 2);
        rotateOuterBackward(kube.matrix(), result.matrix(), REAR);
        rotateInnerBackward(kube.matrix(), result.matrix(), REAR);
        return result;
    }),

    REAR_3_FORWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateCircleForward(kube.matrix(), result.matrix(), 3);
        rotateCircleForward(kube.matrix(), result.matrix(), 2);
        rotateCircleForward(kube.matrix(), result.matrix(), 1);
        rotateOuterBackward(kube.matrix(), result.matrix(), REAR);
        rotateInnerBackward(kube.matrix(), result.matrix(), REAR);
        return result;
    }),

    REAR_1_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateCircleBackward(kube.matrix(), result.matrix(), 3);
        rotateOuterForward(kube.matrix(), result.matrix(), REAR);
        rotateInnerForward(kube.matrix(), result.matrix(), REAR);
        return result;
    }),

    REAR_2_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateCircleBackward(kube.matrix(), result.matrix(), 3);
        rotateCircleBackward(kube.matrix(), result.matrix(), 2);
        rotateOuterForward(kube.matrix(), result.matrix(), REAR);
        rotateInnerForward(kube.matrix(), result.matrix(), REAR);
        return result;
    }),

    REAR_3_BACKWARD(kube -> {
        var result = new Kube(copyMatrix(kube.matrix()));
        rotateCircleBackward(kube.matrix(), result.matrix(), 3);
        rotateCircleBackward(kube.matrix(), result.matrix(), 2);
        rotateCircleBackward(kube.matrix(), result.matrix(), 1);
        rotateOuterForward(kube.matrix(), result.matrix(), REAR);
        rotateInnerForward(kube.matrix(), result.matrix(), REAR);
        return result;
    });

    private final Function<Kube, Kube> transform;

    Movement(Function<Kube, Kube> transform) {
        this.transform = transform;
    }

    public Kube move(Kube kube) {
        return transform.apply(kube);
    }

}
