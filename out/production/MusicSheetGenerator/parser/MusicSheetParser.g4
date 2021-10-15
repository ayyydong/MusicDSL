parser grammar MusicSheetParser;
options { tokenVocab=MusicSheetLexer; }

program: title part*;
title: TITLE_START TEXT;
part: PART_START name sheet;
name: TEXT;
sheet: CLEF key TIME measure+;
key: note KEYTYPE;
measure: MEASURE_START note+ MEASURE_END;
note: NOTE_LETTER ACCIDENTAL? DOTS? DIVISION;