parser grammar MusicSheetParser;
options { tokenVocab=MusicSheetLexer; }

program: title part*;
title: TITLE_START name;
part: PART_START name sheet;
name: TEXT;
sheet: CLEF key TIME (loop|measure)+;
key: NOTE_LETTER ACCIDENTAL? KEYTYPE;
measure: MEASURE_START note+ MEASURE_END;
note: NOTE_LETTER ACCIDENTAL? OCTAVE DOTS? DIVISION;
loop: LOOP_DECLARE LOOP_START measure+ LOOP_END;

