package expression;

public class HSubtraction extends HExpression {
    HExpression left;
    HExpression right;

    public HSubtraction(HExpression left, HExpression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return null;
    }
}
