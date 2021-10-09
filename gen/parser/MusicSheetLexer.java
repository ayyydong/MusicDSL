// Generated from /Users/andydong/Desktop/Project1Group22/src/parser/MusicSheetLexer.g4 by ANTLR 4.9.1
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
		TITLE_START=1, PART_START=2, CLEF=3, NOTE_LETTER=4, ACCIDENTAL=5, DURATION_START=6, 
		DOTS=7, DIVISION=8, KEYTYPE=9, TIME=10, MEASURE_START=11, MEASURE_END=12, 
		WS=13, TEXT=14;
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
			"TITLE_START", "PART_START", "CLEF", "NOTE_LETTER", "ACCIDENTAL", "DURATION_START", 
			"DOTS", "DIVISION", "KEYTYPE", "TIME", "MEASURE_START", "MEASURE_END", 
			"WS", "TEXT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'*'", null, null, null, null, "'['", 
			"']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TITLE_START", "PART_START", "CLEF", "NOTE_LETTER", "ACCIDENTAL", 
			"DURATION_START", "DOTS", "DIVISION", "KEYTYPE", "TIME", "MEASURE_START", 
			"MEASURE_END", "WS", "TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20\u0080\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\7\2)\n\2\f\2\16\2,\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\7\3\67\n\3\f\3\16\3:\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4H\n\4\3\5\5\5K\n\5\3\6\3\6\3\7\3\7\3\b\6\bR\n\b\r\b\16\bS\3\t"+
		"\6\tW\n\t\r\t\16\tX\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\ne\n\n\3"+
		"\13\6\13h\n\13\r\13\16\13i\3\13\3\13\6\13n\n\13\r\13\16\13o\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\17\6\17{\n\17\r\17\16\17|\3\17\3\17\2\2\20"+
		"\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20"+
		"\4\2\3\7\4\2CITT\4\2%%dd\3\2\63;\5\2\13\f\17\17\"\"\4\2\13\f\17\17\2\u0087"+
		"\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2"+
		"\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2"+
		"\2\32\3\2\2\2\2\34\3\2\2\2\3\36\3\2\2\2\4 \3\2\2\2\6/\3\2\2\2\bG\3\2\2"+
		"\2\nJ\3\2\2\2\fL\3\2\2\2\16N\3\2\2\2\20Q\3\2\2\2\22V\3\2\2\2\24d\3\2\2"+
		"\2\26g\3\2\2\2\30q\3\2\2\2\32s\3\2\2\2\34u\3\2\2\2\36z\3\2\2\2 !\7V\2"+
		"\2!\"\7k\2\2\"#\7v\2\2#$\7n\2\2$%\7g\2\2%&\7<\2\2&*\3\2\2\2\')\5\34\16"+
		"\2(\'\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,*\3\2\2\2-.\b\2"+
		"\2\2.\5\3\2\2\2/\60\7R\2\2\60\61\7c\2\2\61\62\7t\2\2\62\63\7v\2\2\63\64"+
		"\7<\2\2\648\3\2\2\2\65\67\5\34\16\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2"+
		"\2\289\3\2\2\29;\3\2\2\2:8\3\2\2\2;<\b\3\2\2<\7\3\2\2\2=>\7v\2\2>?\7t"+
		"\2\2?@\7g\2\2@A\7d\2\2AB\7n\2\2BH\7g\2\2CD\7d\2\2DE\7c\2\2EF\7u\2\2FH"+
		"\7u\2\2G=\3\2\2\2GC\3\2\2\2H\t\3\2\2\2IK\t\2\2\2JI\3\2\2\2K\13\3\2\2\2"+
		"LM\t\3\2\2M\r\3\2\2\2NO\7,\2\2O\17\3\2\2\2PR\7\60\2\2QP\3\2\2\2RS\3\2"+
		"\2\2SQ\3\2\2\2ST\3\2\2\2T\21\3\2\2\2UW\t\4\2\2VU\3\2\2\2WX\3\2\2\2XV\3"+
		"\2\2\2XY\3\2\2\2Y\23\3\2\2\2Z[\7o\2\2[\\\7c\2\2\\]\7l\2\2]^\7q\2\2^e\7"+
		"t\2\2_`\7o\2\2`a\7k\2\2ab\7p\2\2bc\7q\2\2ce\7t\2\2dZ\3\2\2\2d_\3\2\2\2"+
		"e\25\3\2\2\2fh\t\4\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2jk\3\2\2"+
		"\2km\7\61\2\2ln\t\4\2\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2p\27\3"+
		"\2\2\2qr\7]\2\2r\31\3\2\2\2st\7_\2\2t\33\3\2\2\2uv\t\5\2\2vw\3\2\2\2w"+
		"x\b\16\3\2x\35\3\2\2\2y{\n\6\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2"+
		"\2}~\3\2\2\2~\177\b\17\4\2\177\37\3\2\2\2\16\2\3*8GJSXdio|\5\4\3\2\2\3"+
		"\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}