grammar Cymbol;

file : (functionDecl | varDecl)+ ;

varDecl : type initDecl (',' initDecl)* ';' ;
initDecl : ID ('=' expr)? ;
type : 'float' | 'int' | 'void' ;

functionDecl : type ID '(' formalParameters? ')' block ;
formalParameters : formalParameter (',' formalParameter)* ;
formalParameter : type ID ;

block : '{' stat* '}' ;

stat : block
     | varDecl
     | 'if' expr 'then' stat ('else' stat)?
     | 'return' expr? ';'
     | expr '=' expr ';'
     | expr ';'
     ;

expr : ID '(' exprList? ')'      # Call
     | expr '[' expr ']'         # Index
     | '-' expr                  # Negate
     | '!' expr                  # Not
     | expr ('*'|'/') expr       # Mult  
     | expr ('+'|'-') expr       # AddSub
     | expr '==' expr            # Equal
     | ID                        # Var
     | INT                       # Int
     | '(' expr ')'              # Parens
     ;

exprList : expr (',' expr)* ;

ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
WS : [ \t\n\r]+ -> skip ;
SL_COMMENT : '//' .*? '\n' -> skip ;