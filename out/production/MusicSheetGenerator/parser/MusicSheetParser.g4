parser grammar MusicSheetParser;
options { tokenVocab=MusicSheetLexer; }

program: title part*;
title: TITLE_START TEXT;
part: PART_START name sheet;
name: TEXT;
sheet: CLEF key TIME measure+;
key: NOTE KEYTYPE;
measure: MEASURE_START submeasure+ MEASURE_END;
submeasure: (NOTE | REST) ;