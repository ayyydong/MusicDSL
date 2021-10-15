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
		TITLE_START=1, PART_START=2, CLEF=3, NOTE_LETTER=4, ACCIDENTAL=5, DOTS=6, 
		DIVISION=7, KEYTYPE=8, TIME=9, MEASURE_START=10, MEASURE_END=11, MEASURE_GROUP_START=12, 
		MEASURE_GROUP_END=13, WS=14, TEXT=15;
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
			"DIVISION", "KEYTYPE", "TIME", "MEASURE_START", "MEASURE_END", "MEASURE_GROUP_START", 
			"MEASURE_GROUP_END", "WS", "TEXT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "'['", "']'", 
			"'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TITLE_START", "PART_START", "CLEF", "NOTE_LETTER", "ACCIDENTAL", 
			"DOTS", "DIVISION", "KEYTYPE", "TIME", "MEASURE_START", "MEASURE_END", 
			"MEASURE_GROUP_START", "MEASURE_GROUP_END", "WS", "TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u0084\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\5\4J\n\4\3\5\5\5M\n\5\3\6\3\6\3\7\6\7R\n\7\r\7\16\7S\3\b"+
		"\6\bW\n\b\r\b\16\bX\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\te\n\t\3"+
		"\n\6\nh\n\n\r\n\16\ni\3\n\3\n\6\nn\n\n\r\n\16\no\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20\6\20\177\n\20\r\20\16\20\u0080"+
		"\3\20\3\20\2\2\21\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32"+
		"\16\34\17\36\20 \21\4\2\3\7\4\2CITT\4\2%%dd\3\2\63;\5\2\13\f\17\17\"\""+
		"\4\2\13\f\17\17\2\u008b\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2"+
		"\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26"+
		"\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\3 \3\2\2"+
		"\2\4\"\3\2\2\2\6\61\3\2\2\2\bI\3\2\2\2\nL\3\2\2\2\fN\3\2\2\2\16Q\3\2\2"+
		"\2\20V\3\2\2\2\22d\3\2\2\2\24g\3\2\2\2\26q\3\2\2\2\30s\3\2\2\2\32u\3\2"+
		"\2\2\34w\3\2\2\2\36y\3\2\2\2 ~\3\2\2\2\"#\7V\2\2#$\7k\2\2$%\7v\2\2%&\7"+
		"n\2\2&\'\7g\2\2\'(\7<\2\2(,\3\2\2\2)+\5\36\17\2*)\3\2\2\2+.\3\2\2\2,*"+
		"\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\b\2\2\2\60\5\3\2\2\2\61\62"+
		"\7R\2\2\62\63\7c\2\2\63\64\7t\2\2\64\65\7v\2\2\65\66\7<\2\2\66:\3\2\2"+
		"\2\679\5\36\17\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<"+
		":\3\2\2\2=>\b\3\2\2>\7\3\2\2\2?@\7v\2\2@A\7t\2\2AB\7g\2\2BC\7d\2\2CD\7"+
		"n\2\2DJ\7g\2\2EF\7d\2\2FG\7c\2\2GH\7u\2\2HJ\7u\2\2I?\3\2\2\2IE\3\2\2\2"+
		"J\t\3\2\2\2KM\t\2\2\2LK\3\2\2\2M\13\3\2\2\2NO\t\3\2\2O\r\3\2\2\2PR\7\60"+
		"\2\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\17\3\2\2\2UW\t\4\2\2VU\3"+
		"\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\21\3\2\2\2Z[\7o\2\2[\\\7c\2\2\\"+
		"]\7l\2\2]^\7q\2\2^e\7t\2\2_`\7o\2\2`a\7k\2\2ab\7p\2\2bc\7q\2\2ce\7t\2"+
		"\2dZ\3\2\2\2d_\3\2\2\2e\23\3\2\2\2fh\t\4\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2"+
		"\2\2ij\3\2\2\2jk\3\2\2\2km\7\61\2\2ln\t\4\2\2ml\3\2\2\2no\3\2\2\2om\3"+
		"\2\2\2op\3\2\2\2p\25\3\2\2\2qr\7]\2\2r\27\3\2\2\2st\7_\2\2t\31\3\2\2\2"+
		"uv\7*\2\2v\33\3\2\2\2wx\7+\2\2x\35\3\2\2\2yz\t\5\2\2z{\3\2\2\2{|\b\17"+
		"\3\2|\37\3\2\2\2}\177\n\6\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\b\20\4\2\u0083"+
		"!\3\2\2\2\16\2\3,:ILSXdio\u0080\5\4\3\2\2\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}