grammar CSV;

file : rows EOF ;

rows
    : row (NL row)*  // 至少一行，然后可以有多行
    |                // 或者空文件
    ;

row : field (',' field)* ;

field
    : TEXT
    | STRING
    |
    ;

TEXT : ~[,\r\n"]+ ;
STRING : '"' ('""' | ~'"')* '"' ;
NL : '\r'? '\n' ;