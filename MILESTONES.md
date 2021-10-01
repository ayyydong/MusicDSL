Use this file to commit information clearly documenting your milestones'
content. If you want to store more information/details besides what's required
for the milestones that's fine too. Make sure that your TA has had a chance to
sign off on your milestones each week (before the deadline); typically you
should discuss your material with them before finalizing it here.


### Milestone 1

##### Brief description of your planned DSL idea
Our DSL will codify modern staff notation used to represent music into a
textual format.
Use cases: 
* Modern staff notation used in sheet music is graphical, and might be unnecessarily
difficult to store/transfer digitally (e.g. Requiring scans of paper documents,
storage in graphical formats, compatibility issues).
* Conversion to text allows easier ability to gather metadata about a piece of music.
* For composers, having music in a textual format allows an easy way of version
controlling compositions.
* In terms of future expansion, the abstraction allows easy construction of custom
formatting and style options for converting the text back to a graphical format.

##### Notes of any important changes/feedback from TA discussion:
* TA suggest a useful feature could be to implement loops, such as for defining
repeating elements of a piece without having to manually write every segment.
*  As someone who did not know much about music, TA liked our idea.

##### Any planned follow-up tasks or features still to design:
* An important feature to design and implement would be to take a text input in the DSL
and generate a file (perhaps a pdf or an image) of the sheet music





### Milestone 2

##### Planned division of main responsibilities between team members:
Andy: 1
Bill: 2
Daniel: 3
Amman: 4

##### Project Roadmap:
* Feature brainstorming (1234) (ongoing)
* Pre-implementation user study (12) (Sept 27)
* Feature refinement (from user study) (1234) (Sept 30)
* Finalize lexer grammar (23) (Sept 30)
* Finalize parser grammar (14) (Set 30)
* Implementation Phases
  * Tokenization (4) (Oct 7)
  * Parsing (34) (Oct 7)
  * AST Conversion (1) (Oct 7)
  * Static checks (1) (Oct 7)
  * Evaluation (23) (Oct 7)
  * Dynamic checks (2) (Oct 7)
* Develop tests/debugging (1234) (Oct 11)
* Post-implementation user study (34) (Oct 13)
* Further implementations/refinements (1234) (Oct 15)
* Demo video production (14) (Oct 17)

##### Progress Summary:
We have established the primary features of our DSL we’re planning to implement, written a draft grammar, established responsibilities and deadlines for our project roadmap, and have designed and scheduled our pre-implementation user-studies.

##### Draft Grammar:
```
program: title part+;
part: clef key time measure+;
clef: “treble” | “bass”;
key: keynote keytype;
time: number “/” division;
measure: “|” (note | rest)+;
keynote: [A-G] accidental?;
keytype: “major” | “minor”;
number: [1-9]+;
division: [1-9]+ , where the number equals 2^n, n>=0;
note: [A-G] accidental? duration;
rest: “R” duration;
accidental: “#” | “b”;
duration: “.”* division;
```



### Milestone 3

##### Mockup grammar used for User Study
```
program: title part+;
part: clef key time measure+;
clef: “treble” | “bass”;
key: keynote keytype;
time: number “/” division;
measure: “[” (note | rest)+ "]";
keynote: [A-G] accidental?;
keytype: “major” | “minor”;
number: [1-9]+;
division: [1-9]+ , number equals 2^n, n>=0;
note: [A-G] accidental? duration;
rest: “R” duration;
accidental: “#” | “b”;
duration: “.”* division;
```

##### Notes from first User Study
* Language generally clear.
* It would be extremely helpful to be able to specify how many measures are in a line,
and write the language with measures separated by new lines, making it more readable.
* Would be nice to include beams between 8th notes and smaller.
* Would be nice to include articulations.
* Would be nice to allow for key/time signature changes within a piece.

##### Changes to original design
* Inclusion of beams and articulations.
* Not necessarily a change, but language should be flexible to newlines being accepted as
a continuation of the previous line. I don't think we'll be allowing users to specify how
many measures are in a line, as that could run into a lot of formatting issues when
generating sheet music. (Though perhaps maybe our sheet music could regenerate a formatted
copy of our DSL with the measures correctly formatted?)
* Allowing for key/time signature changes with a new declaration of those variables.