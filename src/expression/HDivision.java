package expression;

public class HDivision extends HExpression {
    HExpression left;
    HExpression right;

    public HDivision(HExpression left, HExpression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return null;
    }
}
