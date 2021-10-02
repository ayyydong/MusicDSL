parser grammar MusicSheetParser;
options { tokenVocab=MusicSheetLexer; }

program: title part*;
title: TITLE_START TEXT;
part: name sheet;
name: TEXT;
sheet: CLEF key TIME measure+;
key: NOTE KEYTYPE;
measure: MEASURE_START (NOTE | REST)+ MEASURE_END;