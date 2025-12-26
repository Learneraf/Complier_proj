grammar JSON;

json : value ;

object
    : '{' pair (',' pair)* '}'
    | '{' '}'
    ;

pair : STRING ':' value ;

array
    : '[' value (',' value)* ']'
    | '[' ']'
    ;

value
    : STRING
    | NUMBER
    | object
    | array
    | 'true'
    | 'false'
    | 'null'
    ;

STRING : '"' (ESC | ~["\\])* '"' ;

// 手动重复 4 次 [0-9a-fA-F]，代替 {4}
fragment ESC : '\\' (["\\/bfnrt] | 'u' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F]) ;

NUMBER : '-'? INT '.' INT EXP?
       | '-'? INT EXP
       | '-'? INT
       ;
fragment INT : '0' | [1-9] [0-9]* ;
fragment EXP : [Ee] [+\-]? [0-9]+ ;

WS : [ \t\n\r]+ -> skip ;