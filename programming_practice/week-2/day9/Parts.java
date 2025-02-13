public enum Parts {

    COMPUTE("+-------+\n|COMPUTE|\n+-------+\n"),
    DISPLAY("+-------+\n|       |\n|DISPLAY|\n|       |\n+-------+"),
    DRV("    [DRV]\n");

    private final String part;

    Parts(String part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return this.part;
    }

}
