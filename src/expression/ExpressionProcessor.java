package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
    List<HExpression> list;

    public Map<String,Integer> values;

    public ExpressionProcessor(List<HExpression> list){
        this.list = list;
        values = new HashMap<>();
    }

    public List<String> getEvaluationResults (){
        List<String> evaluations = new ArrayList<>();
        for(HExpression e: list){
            if(e instanceof HVariableDeclaration){
                HVariableDeclaration declaration = (HVariableDeclaration) e;
                values.put(declaration.id,declaration.value);
            }else{
                String input = e.toString();
                int result = getEvalResult(e);
                evaluations.add(input+"  is  "+result);
            }
        }

        return evaluations;
    }

    public int getEvalResult(HExpression e){
            int result = 0;
            if(e instanceof HNumber){
                HNumber num = (HNumber)e;
                result = num.num;
            }else if(e instanceof HVariable){
                HVariable var = (HVariable) e;
                result = values.get(var.id);
            }else if(e instanceof HAddition){
                HAddition add = (HAddition)e;
                int left = getEvalResult(add.left);
                int right = getEvalResult(add.right);
                result = left+right;
            }else if(e instanceof HMultiplication){
                HMultiplication multiplication = (HMultiplication)e;
                int left = getEvalResult(multiplication.left);
                int right = getEvalResult(multiplication.right);
                result = left*right;
            }
            return result;
    }
}

