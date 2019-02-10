package expression;

public class Const extends AbstractExpression {
    private Object value;

    Const(Object x) {
        value = x;
    }

    protected int evaluateImpl(int x, int y, int z) {
        return (int)value;
    }

    protected Object evaluateImpl(Object x) {
        if (x instanceof Integer) {
            return value;
        }
        return Double.valueOf(value.toString());
    }
}
