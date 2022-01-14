package expression;

public class HVariableDeclaration extends HExpression {
    public String id;
    public String type;
    public int value;

    public HVariableDeclaration(String id, String type, int value){
        this.id = id;
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return null;
    }
}
