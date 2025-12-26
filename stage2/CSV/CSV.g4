grammar CSV;

file : hdr row+ ;
hdr : row ;
// 把'\r'? '\n' 改成了 NL
// Parser 就能认出 Lexer 生产的令牌了
row : field (',' field)* NL ;

field
    : TEXT   # Text
    | STRING # String
    |        # Empty
    ;

// 加入了小数点、下划线、减号，这样就能识别 199.99 了
TEXT : [a-zA-Z0-9._-]+ ; 
STRING : '"' ( '""' | ~'"' )* '"' ; 
NL : '\r'? '\n' ;
WS : [ \t]+ -> skip ;