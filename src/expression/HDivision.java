package expression;

public class Division extends Expression{
    Expression left;
    Expression right;

    public Division(Expression left,Expression right){
        this.left = left;
        this.right = right;
    }
}
