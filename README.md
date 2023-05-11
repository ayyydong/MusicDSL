CPSC 410 Project 1 2021w-t1

# MusicDSL
A domain-specific language for composing music using a variety of instruments.

Here is the grammar defined for this project:
```
program ::= title part*
title ::= 'Title:' TEXT
part ::= 'Part:' TEXT sheet
sheet ::= clef key time (loop|measure)+
key ::= noteLetter accidental? keyType
measure ::= '[' note+ ']'
note := noteLetter accidental? octave dots? division
loop ::= loopDeclare '(' measure+ ')'
clef ::= 'treble' | 'bass'
noteLetter ::= [A-G] | 'R'
accidental ::= '#' | 'b'
dots ::= '.'+
division ::= '$' [1-9]+
keyType ::= 'major' | 'minor'
time ::= [1-9]+ '/' [1-9]+
loopDeclare ::= 'loop' [2-9]+
octave ::= [0-9]
```
