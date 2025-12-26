grammar DOT;

graph : STRICT? (GRAPH | DIGRAPH) id? '{' stmt_list '}' ;
stmt_list : (stmt ';'?)* ;

stmt : node_stmt
     | edge_stmt
     | attr_stmt
     | id '=' id
     | subgraph
     ;

attr_stmt : (GRAPH | NODE | EDGE) attr_list ;
attr_list : ('[' a_list? ']')+ ;
a_list : (id ('=' id)? ','?)+ ;

edge_stmt : (node_id | subgraph) edgeRHS attr_list? ;
edgeRHS : (edgeop (node_id | subgraph))+ ;
edgeop : '->' | '--' ;

node_stmt : node_id attr_list? ;
node_id : id port? ;
port : ':' id (':' id)? ;

subgraph : (SUBGRAPH id?)? '{' stmt_list '}' ;

id : ID | STRING | HTML_STRING | NUMBER ;

// 词法规则：处理忽略大小写
STRICT : [Ss][Tt][Rr][Ii][Cc][Tt] ;
GRAPH : [Gg][Rr][Aa][Pp][Hh] ;
DIGRAPH : [Dd][Ii][Gg][Rr][Aa][Pp][Hh] ;
NODE : [Nn][Oo][Dd][Ee] ;
EDGE : [Ee][Dd][Gg][Ee] ;
SUBGRAPH : [Ss][Uu][Bb][Gg][Rr][Aa][Pp][Hh] ;

ID : [a-zA-Z_] [a-zA-Z0-9_]* ; 
STRING : '"' ( '\\"' | . )*? '"' ;
HTML_STRING : '<' (TAG | ~[<>])* '>' ;
fragment TAG : '<' .*? '>' ;
NUMBER : '-'? ('.' [0-9]+ | [0-9]+ ('.' [0-9]*)?) ;
WS : [ \t\n\r]+ -> skip ;
PREPROC : '#' .*? '\n' -> skip ;