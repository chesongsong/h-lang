
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.HlangLexer;
import antlr.HlangParser;
import expression.AntlrToExpression;
import expression.AntlrToProgram;
import expression.ExpressionProcessor;
import expression.HExpression;
import expression.HProgram;

class Calc {
    public static void main(String[] args) {
//        CharStream input = CharStreams.fromString("a=1+2\nb=a*2+1\nc=a*3+2*b\n");
        CharStream input = CharStreams.fromString("a=2*3\n");
//                CharStream input = CharStreams.fromString("a=true\n");
        // 词法分析->Token流->生成语法分析器对象
        HlangLexer lexer = new HlangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HlangParser parser = new HlangParser(tokens);
        // 启动语法分析，获取语法树(根节点)
        ParseTree tree = parser.prog();
        // 创建自定义的能进行四则运算的访问者类
//        AntlrToExpression antlrToExpression = new AntlrToExpression();
        AntlrToProgram antlr = new AntlrToProgram();
        // 访问这棵语法树，在访问同时即可进行计算获取结果
        HProgram result = antlr.visit(tree);
        ExpressionProcessor ep = new ExpressionProcessor(result.HExpressions);
        for(String evaluation: ep.getEvaluationResults()){
            System.out.println(evaluation);
        }
    }
}
