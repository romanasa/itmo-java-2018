package expression;

public class Variable extends AbstractExpression {
    private String name;

    Variable(String name) {
        this.name = name;
    }

    protected Object evaluateImpl(Object x) {
        assert(name.equals("x"));
        return x;
    }

    protected int evaluateImpl(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                assert (name.equals("z"));
                return z;
        }
    }
}
