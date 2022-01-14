package expression;

import java.util.HashMap;

import antlr.HlangBaseVisitor;
import antlr.HlangParser;

public class AntlrToExpression extends HlangBaseVisitor<HExpression> {

    // 存放所有的变量 以及其值
    private final HashMap<String, HExpression> memory = new HashMap<String, HExpression>();

    @Override
    public HExpression visitPrintExpr(HlangParser.PrintExprContext ctx) {
        HExpression res = visit(ctx.expr());
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
    public HExpression visitAssign(HlangParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        HExpression value = visit(ctx.expr());
        this.memory.put(id,value);
        return value;
    }

    @Override
    public HExpression visitBlank(HlangParser.BlankContext ctx) {
        return super.visitBlank(ctx);
    }

    @Override
    public HExpression visitParens(HlangParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public HExpression visitMultiplication(HlangParser.MultiplicationContext ctx) {
        HExpression left = visit(ctx.expr(0));
        HExpression right = visit(ctx.expr(1));
        return new HMultiplication(left,right);
    }

    @Override
    public HExpression visitAddition(HlangParser.AdditionContext ctx) {
        HExpression left = visit(ctx.expr(0));
        HExpression right = visit(ctx.expr(1));
        return new HAddition(left,right);
    }

    @Override
    public HExpression visitSubtraction(HlangParser.SubtractionContext ctx) {
        HExpression left = visit(ctx.expr(0));
        HExpression right = visit(ctx.expr(1));
        return new HSubtraction(left,right);
    }

    @Override
    public HExpression visitDivision(HlangParser.DivisionContext ctx) {
        HExpression left = visit(ctx.expr(0));
        HExpression right = visit(ctx.expr(1));
        return new HDivision(left,right);
    }

    @Override
    public HExpression visitId(HlangParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if(this.memory.get(id)!=null){
            return this.memory.get(id);
        }
        return new HNull();
    }

    @Override
    public HExpression visitInt(HlangParser.IntContext ctx) {
        return new HNumber(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public HExpression visitBooleanExpr(HlangParser.BooleanExprContext ctx) {
        return  new HBoolean(Boolean.parseBoolean(ctx.BooleanLiteral().getText()));
    }
}
