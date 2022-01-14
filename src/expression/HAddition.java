package expression;

public class HAddition extends HExpression {
    HExpression left;
    HExpression right;

    public HAddition(HExpression left, HExpression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + " + " + right.toString();
    }
}
