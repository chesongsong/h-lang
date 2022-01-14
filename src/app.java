
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.HlangLexer;
import antlr.HlangParser;


class Calc {
    public static void main(String[] args) {
        String input1 = "a=1+2\nb=a*2+1\nc=a*3+2*b\n";
        String input2 = "a=2*3\\n";
        String input3 = "a=true\\n";
        CharStream input = CharStreams.fromString(input1);
        // 词法分析->Token流->生成语法分析器对象
        HlangLexer lexer = new HlangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HlangParser parser = new HlangParser(tokens);
        // 启动语法分析，获取语法树(根节点)
        ParseTree tree = parser.prog();
        // 创建自定义的能进行四则运算的访问者类
//        AntlrToExpression antlrToExpression = new AntlrToExpression();
        ExprVisitor exprVisitor = new ExprVisitor();
        // 访问这棵语法树，在访问同时即可进行计算获取结果
        Integer result = exprVisitor.visit(tree);
        System.out.println("计算结果:"+result);
    }
}
