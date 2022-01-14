package expression;

import java.util.ArrayList;
import java.util.List;

public class HProgram {
    public List<HExpression> HExpressions;

    public HProgram(){
        this.HExpressions = new ArrayList<HExpression>();
    }

    public void addExpression(HExpression e){
        this.HExpressions.add(e);
    }
}
