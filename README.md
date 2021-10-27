# Project1Group22
## Grammar
```
program: title part*;
title: TITLE_START name;
part: PART_START name sheet;
name: TEXT;
sheet: CLEF key TIME (loop|measure)+;
key: NOTE_LETTER ACCIDENTAL? KEYTYPE;
measure: MEASURE_START note+ MEASURE_END;
note: NOTE_LETTER ACCIDENTAL? OCTAVE DOTS? DIVISION;
loop: LOOP_DECLARE LOOP_START measure+ LOOP_END;
TITLE_START: 'Title:'
PART_START: 'Part:' 
TEXT: ~[\n\t\r]+;
CLEF: 'treble' | 'bass';
NOTE_LETTER: [A-G] | 'R';
ACCIDENTAL: '#' | 'b';
DOTS: '.'+;
DIVISION: '$' [1-9]+;
KEYTYPE: 'major' | 'minor';
TIME: [1-9]+ '/' [1-9]+;
MEASURE_START: '[';
MEASURE_END: ']';
LOOP_DECLARE: 'loop' [2-9]+;
LOOP_START: '(';
LOOP_END: ')';
OCTAVE: [0-9];
```
