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
		LOOP_START=13, LOOP_END=14, OCTAVE=15, WS=16, TEXT=17;
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
			"LOOP_START", "LOOP_END", "OCTAVE", "WS", "TEXT"
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
			"LOOP_DECLARE", "LOOP_START", "LOOP_END", "OCTAVE", "WS", "TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u0095\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2/\n\2\f\2\16\2\62\13\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4N\n\4\3\5\5\5Q\n\5\3\6\3\6\3\7\6"+
		"\7V\n\7\r\7\16\7W\3\b\3\b\6\b\\\n\b\r\b\16\b]\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\tj\n\t\3\n\6\nm\n\n\r\n\16\nn\3\n\3\n\6\ns\n\n\r\n"+
		"\16\nt\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u0081\n\r\r\r\16"+
		"\r\u0082\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\22\6\22\u0090"+
		"\n\22\r\22\16\22\u0091\3\22\3\22\2\2\23\4\3\6\4\b\5\n\6\f\7\16\b\20\t"+
		"\22\n\24\13\26\f\30\r\32\16\34\17\36\20 \21\"\22$\23\4\2\3\t\4\2CITT\4"+
		"\2%%dd\3\2\63;\3\2\64;\3\2\62;\5\2\13\f\17\17\"\"\4\2\13\f\17\17\2\u009d"+
		"\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2"+
		"\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2"+
		"\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\3$\3\2"+
		"\2\2\4&\3\2\2\2\6\65\3\2\2\2\bM\3\2\2\2\nP\3\2\2\2\fR\3\2\2\2\16U\3\2"+
		"\2\2\20Y\3\2\2\2\22i\3\2\2\2\24l\3\2\2\2\26v\3\2\2\2\30x\3\2\2\2\32z\3"+
		"\2\2\2\34\u0084\3\2\2\2\36\u0086\3\2\2\2 \u0088\3\2\2\2\"\u008a\3\2\2"+
		"\2$\u008f\3\2\2\2&\'\7V\2\2\'(\7k\2\2()\7v\2\2)*\7n\2\2*+\7g\2\2+,\7<"+
		"\2\2,\60\3\2\2\2-/\5\"\21\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3"+
		"\2\2\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\b\2\2\2\64\5\3\2\2\2\65\66\7"+
		"R\2\2\66\67\7c\2\2\678\7t\2\289\7v\2\29:\7<\2\2:>\3\2\2\2;=\5\"\21\2<"+
		";\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\b\3\2\2"+
		"B\7\3\2\2\2CD\7v\2\2DE\7t\2\2EF\7g\2\2FG\7d\2\2GH\7n\2\2HN\7g\2\2IJ\7"+
		"d\2\2JK\7c\2\2KL\7u\2\2LN\7u\2\2MC\3\2\2\2MI\3\2\2\2N\t\3\2\2\2OQ\t\2"+
		"\2\2PO\3\2\2\2Q\13\3\2\2\2RS\t\3\2\2S\r\3\2\2\2TV\7\60\2\2UT\3\2\2\2V"+
		"W\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\17\3\2\2\2Y[\7&\2\2Z\\\t\4\2\2[Z\3\2\2"+
		"\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^\21\3\2\2\2_`\7o\2\2`a\7c\2\2ab\7l\2"+
		"\2bc\7q\2\2cj\7t\2\2de\7o\2\2ef\7k\2\2fg\7p\2\2gh\7q\2\2hj\7t\2\2i_\3"+
		"\2\2\2id\3\2\2\2j\23\3\2\2\2km\t\4\2\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2n"+
		"o\3\2\2\2op\3\2\2\2pr\7\61\2\2qs\t\4\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2"+
		"tu\3\2\2\2u\25\3\2\2\2vw\7]\2\2w\27\3\2\2\2xy\7_\2\2y\31\3\2\2\2z{\7n"+
		"\2\2{|\7q\2\2|}\7q\2\2}~\7r\2\2~\u0080\3\2\2\2\177\u0081\t\5\2\2\u0080"+
		"\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2"+
		"\2\u0083\33\3\2\2\2\u0084\u0085\7*\2\2\u0085\35\3\2\2\2\u0086\u0087\7"+
		"+\2\2\u0087\37\3\2\2\2\u0088\u0089\t\6\2\2\u0089!\3\2\2\2\u008a\u008b"+
		"\t\7\2\2\u008b\u008c\3\2\2\2\u008c\u008d\b\21\3\2\u008d#\3\2\2\2\u008e"+
		"\u0090\n\b\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2"+
		"\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\b\22\4\2\u0094"+
		"%\3\2\2\2\17\2\3\60>MPW]int\u0082\u0091\5\4\3\2\2\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}