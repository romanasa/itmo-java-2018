package expression;

public abstract class AbstractExpression implements Expression, DoubleExpression, TripleExpression {
    protected abstract Object evaluateImpl(Object x);

    protected abstract int evaluateImpl(int x, int y, int z);

    public Object evaluate(Object x) {
        return evaluateImpl(x);
    }

    public int evaluate(int x) {
        return (int)evaluateImpl(x);
    }

    public double evaluate(double x) {
        return ((double) evaluateImpl(x));
    }

    public int evaluate(int x, int y, int z) {
        return evaluateImpl(x, y, z);
    }
}
