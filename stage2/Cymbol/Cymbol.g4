grammar Cymbol;

file : (functionDecl | varDecl)+ ;

varDecl
    : type ID ('=' expr)? ';'
    ;

type : 'float' | 'int' | 'void' ; 

functionDecl
    : type ID '(' formalParameters? ')' block
    ;

formalParameters
    : formalParameter (',' formalParameter)*
    ;

formalParameter
    : type ID
    ;

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
    : ID '(' exprList? ')' # Call      // 函数调用，标签为 Call
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

ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
WS : [ \t\n\r]+ -> skip ;
SL_COMMENT : '//' .*? '\n' -> skip ;