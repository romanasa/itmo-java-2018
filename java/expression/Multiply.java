package expression;

public class Multiply extends AbstractOperator {
    Multiply(AbstractExpression first, AbstractExpression second) {
        super(first, second);
    }

    protected Object operator(Object a, Object b) {
        if (a instanceof Integer) {
            return (int)a * (int)b;
        }
        return (double)a * (double)b;
    }
}
