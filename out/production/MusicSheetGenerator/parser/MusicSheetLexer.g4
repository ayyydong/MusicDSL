lexer grammar MusicSheetLexer;

// (DEFAULT_MODE)
TITLE_START: 'Title:' WS* -> mode(TEXT_MODE);
PART_START: 'Part:' WS* -> mode(TEXT_MODE);
CLEF: 'treble' | 'bass';
NOTE: [A-G] ('#' | 'b')? ('*' '.'* [1-9]+)? ;
KEYTYPE: 'major' | 'minor';
TIME: [1-9]+ '/' [1-9]+;
MEASURE_START: '[';
MEASURE_END: ']';
REST: 'R' ('*' '.'* [1-9]+)?;

// White space Handling
WS : [\n\t\r ] -> channel(HIDDEN);
// Modes
mode TEXT_MODE;
TEXT: ~[\n\t\r]+ -> mode(DEFAULT_MODE);
