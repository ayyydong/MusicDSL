package test;

import inst.SawtoothInst;
import jm.audio.Instrument;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.View;
import jm.util.Write;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

import static jm.constants.Durations.MINIM;
import static jm.constants.Pitches.C4;


// add jm files to be generated here using Jmusic

class ExpectedOutputTest {

    // @BeforeEach
    void setUp() {
    }

    // @Test
    void main() {
        Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
        Write.midi(score, "expected_output");
        Instrument inst = new SawtoothInst(44100);
        Write.au(score, "expected_output_au", inst);
        View.sketch(score);
    }
}