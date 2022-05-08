package net.edubovit.kube.x2;

public enum Color {

    WHITE('W'),
    RED('R'),
    BLUE('B'),
    ORANGE('O'),
    GREEN('G'),
    YELLOW('Y');

    private final char symbol;

    Color(char symbol) {
        this.symbol = symbol;
    }

    public static Color of(char c) {
        char upper = Character.toUpperCase(c);
        return switch (upper) {
            case 'W' -> WHITE;
            case 'R' -> RED;
            case 'B' -> BLUE;
            case 'O' -> ORANGE;
            case 'G' -> GREEN;
            case 'Y' -> YELLOW;
            default -> throw new IllegalArgumentException();
        };
    }

    public char getSymbol() {
        return symbol;
    }
}
