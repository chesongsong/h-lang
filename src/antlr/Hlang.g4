grammar Hlang;
@header {
   package antlr;
}
prog: (decl | expr)+ ;

decl: ID EQ expr;

expr
    : expr op = (MUL | DIV ) expr   # MulDivExpr
    | expr op = ( ADD | SUB ) expr   # AddSubExpr
    | NUM                       # NumExpr
    | ID                        # IDExpr
    | LPAREN expr RPAREN        # ParenExpr
    ;

MUL     : '*' ;
DIV     : '/' ;
ADD     : '+' ;
SUB     : '-' ;
LPAREN  : '(' ;
RPAREN  : ')' ;

ID      : LETTER (LETTER | DIGIT)*  ;
NUM     : [0-9]+ ;
EQ      : '=' ;
COMMENT : '//' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN);
WS      : [ \r\n\t]+ -> channel(HIDDEN);

fragment
LETTER  : [a-zA-Z];
fragment
DIGIT   : [0-9] ;
