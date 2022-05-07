import java.util.Objects;

public record Kube(Side top, Side front, Side right, Side rear, Side left, Side bottom) {

    public Kube(String string) {
        this(
                new Side(Color.of(string.charAt(0)), Color.of(string.charAt(1)), Color.of(string.charAt(2)), Color.of(string.charAt(3))),
                new Side(Color.of(string.charAt(4)), Color.of(string.charAt(5)), Color.of(string.charAt(6)), Color.of(string.charAt(7))),
                new Side(Color.of(string.charAt(8)), Color.of(string.charAt(9)), Color.of(string.charAt(10)), Color.of(string.charAt(11))),
                new Side(Color.of(string.charAt(12)), Color.of(string.charAt(13)), Color.of(string.charAt(14)), Color.of(string.charAt(15))),
                new Side(Color.of(string.charAt(16)), Color.of(string.charAt(17)), Color.of(string.charAt(18)), Color.of(string.charAt(19))),
                new Side(Color.of(string.charAt(20)), Color.of(string.charAt(21)), Color.of(string.charAt(22)), Color.of(string.charAt(23)))
        );
    }

    public boolean isCorrect() {
        return top.isCorrect() && front.isCorrect() && right.isCorrect()
                && rear.isCorrect() && left.isCorrect() && bottom.isCorrect();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kube kube = (Kube) o;
        return top.equals(kube.top) && front.equals(kube.front) && right.equals(kube.right)
                && rear.equals(kube.rear) && left.equals(kube.left) && bottom.equals(kube.bottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, front, right, rear, left, bottom);
    }

}
