grammar JSON;

json : value ;

obj : '{' pair (',' pair)* '}' # AnObject
    | '{' '}'                  # EmptyObject
    ;

pair : STRING ':' value ;

array : '[' value (',' value)* ']' # ArrayOfValues
      | '[' ']'                    # EmptyArray
      ;

value
    : STRING    # String
    | NUMBER    # Atom
    | obj       # ObjectValue
    | array     # ArrayValue
    | 'true'    # Atom
    | 'false'   # Atom
    | 'null'    # Atom
    ;

// --- 词法规则 (保持不变) ---
STRING : '"' (ESC | ~["\\])* '"' ;
fragment ESC : '\\' (["\\/bfnrt] | 'u' [0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]) ;
NUMBER : '-'? INT '.' INT EXP? | '-'? INT EXP | '-'? INT ;
fragment INT : '0' | [1-9] [0-9]* ;
fragment EXP : [Ee] [+\-]? [0-9]+ ;
WS : [ \t\n\r]+ -> skip ;