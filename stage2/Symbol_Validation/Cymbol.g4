grammar Cymbol;

file : (functionDecl | varDecl)+ ;

varDecl : type ID ('=' expr)? ';' ;
type : 'float' | 'int' | 'void' ; 

functionDecl : type ID '(' formalParameters? ')' block ;
formalParameters : formalParameter (',' formalParameter)* ;
formalParameter : type ID ;

block : '{' stat* '}' ;

stat
    : block
    | varDecl
    | 'if' expr 'then' stat ('else' stat)?
    | 'return' expr? ';' 
    | expr '=' expr ';' 
    | expr ';' 
    ;

expr
    : ID '(' exprList? ')' # Call
    | '-' expr             # Negate
    | '!' expr             # Not
    | expr '*' expr        # Mult
    | expr '/' expr        # Div
    | expr '+' expr        # Add
    | expr '-' expr        # Sub
    | expr '==' expr       # Equal
    | expr '!=' expr       # NotEqual
    | ID                   # Var
    | INT                  # Int
    | '(' expr ')'         # Parens
    ;

exprList : expr (',' expr)* ;

// 【修正点】支持下划线和数字，例如 g_score, var1
ID : [a-zA-Z] [a-zA-Z0-9_]* ;

INT : [0-9]+ ;
WS : [ \t\n\r]+ -> skip ;
SL_COMMENT : '//' .*? '\n' -> skip ;