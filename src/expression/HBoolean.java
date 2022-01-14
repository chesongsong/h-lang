package expression;

public class HBoolean extends HExpression{
    boolean s;

    public HBoolean(boolean s){
        this.s = s;
    }

    @Override
    public String toString() {
        return Boolean.toString(s);
    }
}
