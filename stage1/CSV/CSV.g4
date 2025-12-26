grammar CSV;

file : hdr row+ ;
hdr : row ;

row : field (',' field)* '\r'? '\n' ;

field
    : TEXT
    | STRING
    | // empty field
    ;

TEXT : [a-zA-Z0-9 ]+ ; 
STRING : '"' ( '""' | ~'"' )* '"' ; 

NL : '\r'? '\n' ;
WS : [ \t]+ -> skip ;