import java.util.HashMap;

import antlr.HlangBaseVisitor;
import antlr.HlangParser;

public class ExprVisitor extends HlangBaseVisitor<Integer> {
    private HashMap<String, Integer> memory = new HashMap<String, Integer>();
    @Override
    public Integer visitPrintExpr(HlangParser.PrintExprContext ctx) {
        Integer res = visit(ctx.expr());
        String exprString = ctx.expr().getText();
        System.out.println(exprString+':'+res);
        return res;
    }


    // 赋值语句
    // 1. 给谁赋值
    // 2. 赋值的内容
    // 3. 返回值
    // 4. 赋值完成之后是存储在内存中，程序结束才会释放内存
    @Override
    public Integer visitAssign(HlangParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Integer value = visit(ctx.expr());
        this.memory.put(id,value);
        return value;
    }

    @Override
    public Integer visitBlank(HlangParser.BlankContext ctx) {
        return super.visitBlank(ctx);
    }

    @Override
    public Integer visitParens(HlangParser.ParensContext ctx) {
        System.out.println("括号里的结果:"+visit(ctx.expr()));
        return visit(ctx.expr());
    }

    @Override
    public Integer visitMultiplication(HlangParser.MultiplicationContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        return left * right;
    }

    @Override
    public Integer visitAddition(HlangParser.AdditionContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        return left + right;
    }

    @Override
    public Integer visitSubtraction(HlangParser.SubtractionContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        return left - right;
    }

    @Override
    public Integer visitDivision(HlangParser.DivisionContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        return left / right;
    }

    @Override
    public Integer visitId(HlangParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if(this.memory.get(id)!=null){
            return this.memory.get(id);
        }
        return 0;
    }

    @Override
    public Integer visitInt(HlangParser.IntContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitBooleanExpr(HlangParser.BooleanExprContext ctx) {
        System.out.println(ctx.BooleanLiteral().getText());
        return 1;
    }
}
