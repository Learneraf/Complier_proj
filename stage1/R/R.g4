grammar R;

prog : (expr_or_assign? NL)* EOF ;

expr_or_assign
    : expr '<-' expr_or_assign
    | expr '=' expr_or_assign
    | expr '<<-' expr_or_assign
    | expr
    ;

expr : expr '[[' sublist ']]'
     | expr '[' sublist ']'
     | expr ('::'|':::') expr
     | expr ('$'|'@') expr
     | expr '^' expr
     | ('-'|'+') expr
     | expr ':' expr
     | expr ('*'|'/') expr
     | expr ('+'|'-') expr
     | expr ('>'|'>='|'<'|'<='|'=='|'!=') expr
     | '!' expr
     | expr ('&'|'&&') expr
     | expr ('|'|'||') expr
     | '~' expr
     | expr '->' expr
     | ID '(' sublist ')'
     | ID
     | NUMBER
     | '(' expr ')'
     | '{' exprlist '}'
     | 'if' '(' expr ')' NL* expr (NL* 'else' NL* expr)?
     | 'function' '(' formlist? ')' expr
     ;

exprlist : NL* expr_or_assign ((';'|NL)+ expr_or_assign?)* NL* | NL* ;

formlist : form (',' form)* ;
form : ID | ID '=' expr ;

sublist : sub (',' sub)* ;
sub : expr | ID '=' expr | ;

NUMBER : 
    [0-9]+ ('.' [0-9]*)? ([Ee] [+\-]? [0-9]+)?  
    | '.' [0-9]+ ([Ee] [+\-]? [0-9]+)?
    ;

ID : [a-zA-Z] [a-zA-Z0-9_.]* | '.' [a-zA-Z_.] [a-zA-Z0-9_.]* ;
NL : '\r'? '\n' ;
WS : [ \t]+ -> skip ;