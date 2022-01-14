package expression;

public class Addition extends Expression{
    Expression left;
    Expression right;

    public Addition(Expression left,Expression right){
        this.left = left;
        this.right = right;
    }
}
