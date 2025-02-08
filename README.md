CPSC 410 Project 1 2021w-t1

# MusicDSL

A domain-specific language for composing music using a variety of instruments.

## About the Project

This GUI provides musicians, as well as beginners with no prior musical knowledge, an efficient way to compose and store
sheet music. By entering text, the program converts it into individual musical notes. This feature allows users to store
music in text files, offering a convenient alternative to traditional graphical sheet music (modern staff notation)
commonly found online. Text files eliminate the need for scanning documents or dealing with compatibility issues, while
also saving space compared to PDF files. Once the user has finished inputting text, they can generate the
music sheet as a MIDI and MusicXML file by clicking 'Generate.' 

![image](https://github.com/user-attachments/assets/5bf0ffde-143d-4a2c-a63d-663899952d72)
![image](https://github.com/user-attachments/assets/ae803262-46c5-43f6-b429-6fd65be6628d)

Pressing 'Play' will play the generated music
sheet. Make sure your computer speakers are on!



Here is the grammar defined for the project:

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

For examples of how to compose a musical sheet, please refer to the *test_sheet* text files, such as *test_sheet2.txt* (
which plays "Happy Birthday").
```
Title: Test Sheet 2
Part: Piano
treble G major 3/4 [D4$4 D4$8 E4$4 D4$8][G4$4 F#4$4 R4$8 R4$8]
[D4$4 D4$8 E4$4 D4$8][A4$4 G4$4 R4$8 R4$8]
[D4$4 D4$4 D5$8 B4$8][G4$4 F#4$4 E4$8 R4$8]
[C5$4 C5$4 B4$8 G4$8][A4$4 G4$4 R4$8 R4$8]
```
## Getting Started
1. Clone the repo
   ```sh
   git clone https://github.com/ayyydong/MusicDSL.git
   ```
2. Add this to the VM Options/JVM arguments 
   (Inside configuration settings of project -> project dependencies)
   ```sh
   --module-path ${USER PATH}$/lib/javafx-sdk-17.0.0.1/lib --add-modules=javafx.controls,javafx.fxml
   ```
   or if you are using macOS
   ```sh
   --module-path ${USER PATH}$/lib/mac-javafx-sdk-17.0.0.1/lib --add-modules=javafx.controls
   ```
3. Run the *Gui.java* class

## Built With

[Java](https://www.java.com)  
[ANTLR](https://www.antlr.org/)  
[jMusic](https://explodingart.com/jmusic/)

