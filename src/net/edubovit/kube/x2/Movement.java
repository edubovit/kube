package net.edubovit.kube.x2;

import java.util.function.Function;

public enum Movement {

    TOP_FORWARD(kube -> new Kube(
            kube.top().turnClockwise(),
            new Side(kube.right().topLeft(), kube.right().topRight(), kube.front().bottomLeft(), kube.front().bottomRight()),
            new Side(kube.rear().topLeft(), kube.rear().topRight(), kube.right().bottomLeft(), kube.right().bottomRight()),
            new Side(kube.left().topLeft(), kube.left().topRight(), kube.rear().bottomLeft(), kube.rear().bottomRight()),
            new Side(kube.front().topLeft(), kube.front().topRight(), kube.left().bottomLeft(), kube.left().bottomRight()),
            kube.bottom()
    )),
    TOP_BACKWARD(kube -> new Kube(
            kube.top().turnCounterClockwise(),
            new Side(kube.left().topLeft(), kube.left().topRight(), kube.front().bottomLeft(), kube.front().bottomRight()),
            new Side(kube.front().topLeft(), kube.front().topRight(), kube.right().bottomLeft(), kube.right().bottomRight()),
            new Side(kube.right().topLeft(), kube.right().topRight(), kube.rear().bottomLeft(), kube.rear().bottomRight()),
            new Side(kube.rear().topLeft(), kube.rear().topRight(), kube.left().bottomLeft(), kube.left().bottomRight()),
            kube.bottom()
    )),
    RIGHT_FORWARD(kube -> new Kube(
            new Side(kube.top().topLeft(), kube.front().topRight(), kube.top().bottomLeft(), kube.front().bottomRight()),
            new Side(kube.front().topLeft(), kube.bottom().topRight(), kube.front().bottomLeft(), kube.bottom().bottomRight()),
            kube.right().turnClockwise(),
            new Side(kube.top().bottomRight(), kube.rear().topRight(), kube.top().topRight(), kube.rear().bottomRight()),
            kube.left(),
            new Side(kube.bottom().topLeft(), kube.rear().bottomLeft(), kube.bottom().bottomLeft(), kube.rear().topLeft())
    )),
    RIGHT_BACKWARD(kube -> new Kube(
            new Side(kube.top().topLeft(), kube.rear().bottomLeft(), kube.top().bottomLeft(), kube.rear().topLeft()),
            new Side(kube.front().topLeft(), kube.top().topRight(), kube.front().bottomLeft(), kube.top().bottomRight()),
            kube.right().turnCounterClockwise(),
            new Side(kube.bottom().bottomRight(), kube.rear().topRight(), kube.bottom().topRight(), kube.rear().bottomRight()),
            kube.left(),
            new Side(kube.bottom().topLeft(), kube.front().topRight(), kube.bottom().bottomLeft(), kube.front().bottomRight())
    )),
    REAR_FORWARD(kube -> new Kube(
            new Side(kube.left().bottomLeft(), kube.left().topLeft(), kube.top().bottomLeft(), kube.top().bottomRight()),
            kube.front(),
            new Side(kube.right().topLeft(), kube.top().topLeft(), kube.right().bottomLeft(), kube.top().topRight()),
            kube.rear().turnCounterClockwise(),
            new Side(kube.bottom().bottomLeft(), kube.left().topRight(), kube.bottom().bottomRight(), kube.left().bottomRight()),
            new Side(kube.bottom().topLeft(), kube.bottom().topRight(), kube.right().bottomRight(), kube.right().topRight())
    )),
    REAR_BACKWARD(kube -> new Kube(
            new Side(kube.right().topRight(), kube.right().bottomRight(), kube.top().bottomLeft(), kube.top().bottomRight()),
            kube.front(),
            new Side(kube.right().topLeft(), kube.bottom().bottomRight(), kube.right().bottomLeft(), kube.bottom().bottomLeft()),
            kube.rear().turnClockwise(),
            new Side(kube.top().topRight(), kube.left().topRight(), kube.top().topLeft(), kube.left().bottomRight()),
            new Side(kube.bottom().topLeft(), kube.bottom().topRight(), kube.left().topLeft(), kube.left().bottomLeft())
    ));

    private final Function<Kube, Kube> transform;

    Movement(Function<Kube, Kube> transform) {
        this.transform = transform;
    }

    public Kube move(Kube kube) {
        return transform.apply(kube);
    }

}
