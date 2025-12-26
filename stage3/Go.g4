grammar Go;

// --- 语法规则 (Syntax) ---

sourceFile : functionDecl+ EOF ;

functionDecl : 'func' ID '(' parameters? ')' block ;

parameters : parameterDecl (',' parameterDecl)* ;
parameterDecl : ID type ;

block : '{' statement* '}' ;

statement
    : varDecl
    | assignStat
    | ifStat
    | forStat
    | returnStat
    | exprStat
    | block
    ;

varDecl : 'var' ID type ('=' expression)? ';' ;

assignStat : ID '=' expression ';' ;

ifStat : 'if' expression block ('else' (ifStat | block))? ;

forStat : 'for' (initStmt? ';')? expression? (';' postStmt?)? block ;

initStmt : ID '=' expression ;
postStmt : ID '=' expression ;

returnStat : 'return' expression? ';' ;

exprStat : expression ';' ;

// 【修改点】去掉了后面的 # Label，这样 Java 代码里就能用 visitExpression 了
expression
    : expression ('*' | '/') expression
    | expression ('+' | '-') expression
    | expression ('==' | '!=' | '<' | '<=' | '>' | '>=') expression
    | ID '(' arguments? ')'
    | ID
    | INT
    | FLOAT
    | '(' expression ')'
    ;

arguments : expression (',' expression)* ;

type : 'int' | 'float' | 'void' ;

// --- 词法规则 (Lexer) ---

ID : [a-zA-Z_] [a-zA-Z0-9_]* ;

INT : [0-9]+ ;
FLOAT : [0-9]+ '.' [0-9]+ ;

WS : [ \t\r\n]+ -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;