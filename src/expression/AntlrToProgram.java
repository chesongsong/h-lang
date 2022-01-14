package expression;

import antlr.HlangBaseVisitor;
import antlr.HlangParser;

public class    AntlrToProgram extends HlangBaseVisitor<HProgram> {
    @Override
    public HProgram visitProg(HlangParser.ProgContext ctx) {
        HProgram program = new HProgram();

        AntlrToExpression antlrToExpression = new AntlrToExpression();

        for (int i=0;i<ctx.getChildCount();i++){
            if(i==ctx.getChildCount()-1){
                //EOF 结束
            }else{
                program.addExpression(antlrToExpression.visit(ctx.getChild(i)));
            }
        }
        return program;
    }
}
