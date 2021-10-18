// Generated from C:/Users/danie/OneDrive/Documents/School/2021W1/CPSC410/MusicSheetGenerator/src/parser\MusicSheetLexer.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MusicSheetLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TITLE_START=1, PART_START=2, CLEF=3, NOTE_LETTER=4, ACCIDENTAL=5, DOTS=6, 
		DIVISION=7, KEYTYPE=8, TIME=9, MEASURE_START=10, MEASURE_END=11, LOOP_DECLARE=12, 
		LOOP_START=13, LOOP_END=14, OCTAVE=15, KEY_LETTER=16, WS=17, TEXT=18;
	public static final int
		TEXT_MODE=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "TEXT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TITLE_START", "PART_START", "CLEF", "NOTE_LETTER", "ACCIDENTAL", "DOTS", 
			"DIVISION", "KEYTYPE", "TIME", "MEASURE_START", "MEASURE_END", "LOOP_DECLARE", 
			"LOOP_START", "LOOP_END", "OCTAVE", "KEY_LETTER", "WS", "TEXT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "'['", "']'", 
			null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TITLE_START", "PART_START", "CLEF", "NOTE_LETTER", "ACCIDENTAL", 
			"DOTS", "DIVISION", "KEYTYPE", "TIME", "MEASURE_START", "MEASURE_END", 
			"LOOP_DECLARE", "LOOP_START", "LOOP_END", "OCTAVE", "KEY_LETTER", "WS", 
			"TEXT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MusicSheetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MusicSheetLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u0099\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\61\n\2\f\2\16\2"+
		"\64\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3?\n\3\f\3\16\3B\13\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4P\n\4\3\5\5\5S\n\5\3"+
		"\6\3\6\3\7\6\7X\n\7\r\7\16\7Y\3\b\3\b\6\b^\n\b\r\b\16\b_\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\n\6\no\n\n\r\n\16\np\3\n\3\n\6\n"+
		"u\n\n\r\n\16\nv\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u0083\n"+
		"\r\r\r\16\r\u0084\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\23\6\23\u0094\n\23\r\23\16\23\u0095\3\23\3\23\2\2\24\4\3\6"+
		"\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20 \21\""+
		"\22$\23&\24\4\2\3\n\5\2CITTci\4\2%%dd\3\2\63;\3\2\64;\3\2\62;\4\2CIci"+
		"\5\2\13\f\17\17\"\"\4\2\13\f\17\17\2\u00a1\2\4\3\2\2\2\2\6\3\2\2\2\2\b"+
		"\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2"+
		"\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2"+
		"\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\3&\3\2\2\2\4(\3\2\2\2\6"+
		"\67\3\2\2\2\bO\3\2\2\2\nR\3\2\2\2\fT\3\2\2\2\16W\3\2\2\2\20[\3\2\2\2\22"+
		"k\3\2\2\2\24n\3\2\2\2\26x\3\2\2\2\30z\3\2\2\2\32|\3\2\2\2\34\u0086\3\2"+
		"\2\2\36\u0088\3\2\2\2 \u008a\3\2\2\2\"\u008c\3\2\2\2$\u008e\3\2\2\2&\u0093"+
		"\3\2\2\2()\7V\2\2)*\7k\2\2*+\7v\2\2+,\7n\2\2,-\7g\2\2-.\7<\2\2.\62\3\2"+
		"\2\2/\61\5$\22\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2"+
		"\63\65\3\2\2\2\64\62\3\2\2\2\65\66\b\2\2\2\66\5\3\2\2\2\678\7R\2\289\7"+
		"c\2\29:\7t\2\2:;\7v\2\2;<\7<\2\2<@\3\2\2\2=?\5$\22\2>=\3\2\2\2?B\3\2\2"+
		"\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\b\3\2\2D\7\3\2\2\2EF\7v\2"+
		"\2FG\7t\2\2GH\7g\2\2HI\7d\2\2IJ\7n\2\2JP\7g\2\2KL\7d\2\2LM\7c\2\2MN\7"+
		"u\2\2NP\7u\2\2OE\3\2\2\2OK\3\2\2\2P\t\3\2\2\2QS\t\2\2\2RQ\3\2\2\2S\13"+
		"\3\2\2\2TU\t\3\2\2U\r\3\2\2\2VX\7\60\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2"+
		"YZ\3\2\2\2Z\17\3\2\2\2[]\7&\2\2\\^\t\4\2\2]\\\3\2\2\2^_\3\2\2\2_]\3\2"+
		"\2\2_`\3\2\2\2`\21\3\2\2\2ab\7o\2\2bc\7c\2\2cd\7l\2\2de\7q\2\2el\7t\2"+
		"\2fg\7o\2\2gh\7k\2\2hi\7p\2\2ij\7q\2\2jl\7t\2\2ka\3\2\2\2kf\3\2\2\2l\23"+
		"\3\2\2\2mo\t\4\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2r"+
		"t\7\61\2\2su\t\4\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\25\3\2\2"+
		"\2xy\7]\2\2y\27\3\2\2\2z{\7_\2\2{\31\3\2\2\2|}\7n\2\2}~\7q\2\2~\177\7"+
		"q\2\2\177\u0080\7r\2\2\u0080\u0082\3\2\2\2\u0081\u0083\t\5\2\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\33\3\2\2\2\u0086\u0087\7*\2\2\u0087\35\3\2\2\2\u0088\u0089\7+\2\2\u0089"+
		"\37\3\2\2\2\u008a\u008b\t\6\2\2\u008b!\3\2\2\2\u008c\u008d\t\7\2\2\u008d"+
		"#\3\2\2\2\u008e\u008f\t\b\2\2\u008f\u0090\3\2\2\2\u0090\u0091\b\22\3\2"+
		"\u0091%\3\2\2\2\u0092\u0094\n\t\2\2\u0093\u0092\3\2\2\2\u0094\u0095\3"+
		"\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\u0098\b\23\4\2\u0098\'\3\2\2\2\17\2\3\62@ORY_kpv\u0084\u0095\5\4\3\2"+
		"\2\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}