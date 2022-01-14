package expression;

public class HMultiplication extends HExpression {
    HExpression left;
    HExpression right;

    public HMultiplication(HExpression left, HExpression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return null;
    }
}
