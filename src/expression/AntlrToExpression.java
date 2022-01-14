import java.util.HashMap;

import antlr.HlangBaseVisitor;
import antlr.HlangParser;

public class EvalVisitor extends HlangBaseVisitor<Integer> {

    private HashMap<String, Integer> memory = new HashMap<String, Integer>();

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
    public Integer visitMulDiv(HlangParser.MulDivContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        if (ctx.op.getType() == HlangParser.MUL){
            System.out.println("乘法结果:"+(left * right));
            return left * right;
        }else{
            System.out.println("除法结果:"+(left / right));
            return left / right;
        }
    }

    @Override
    public Integer visitInt(HlangParser.IntContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }


    // 读变量的值
    @Override
    public Integer visitId(HlangParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if(this.memory.get(id)!=null){
            return this.memory.get(id);
        }
        return 0;
    }

    @Override
    public Integer visitParens(HlangParser.ParensContext ctx) {
        System.out.println("括号里的结果:"+visit(ctx.expr()));
        return visit(ctx.expr());
    }

    @Override
    public Integer visitAddSub(HlangParser.AddSubContext ctx) {
        Integer left = visit(ctx.expr(0));
        Integer right = visit(ctx.expr(1));
        if (ctx.op.getType() == HlangParser.ADD){
            System.out.println("加法结果:"+(left + right));
            return left + right;
        }else{
            System.out.println("减法结果:"+(left - right));
            return left - right;
        }
    }

    @Override
    public Integer visitPrintExpr(HlangParser.PrintExprContext ctx) {
        Integer res = visit(ctx.expr());
        String exprString = ctx.expr().getText();
        System.out.println(exprString+':'+res);
        return res;
    }

    @Override
    public Integer visitBooleanExpr(HlangParser.BooleanExprContext ctx) {
        System.out.println(ctx.BooleanLiteral().getText());
        return 1;
    }
}
