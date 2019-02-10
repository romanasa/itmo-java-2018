package expression;

public abstract class AbstractOperator extends AbstractExpression {

    private AbstractExpression first, second;

    public AbstractOperator(AbstractExpression first, AbstractExpression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract Object operator(Object first, Object second);

    protected Object evaluateImpl(Object x) {
        return operator(first.evaluate(x), second.evaluate(x));
    }

    protected int evaluateImpl(int x, int y, int z) {
        return (int)operator(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
