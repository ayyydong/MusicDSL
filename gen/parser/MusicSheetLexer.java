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
		TITLE_START=1, PART_START=2, CLEF=3, NOTE=4, KEYTYPE=5, TIME=6, MEASURE_START=7, 
		MEASURE_END=8, REST=9, WS=10, TEXT=11;
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
			"TITLE_START", "PART_START", "CLEF", "NOTE", "KEYTYPE", "TIME", "MEASURE_START", 
			"MEASURE_END", "REST", "WS", "TEXT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TITLE_START", "PART_START", "CLEF", "NOTE", "KEYTYPE", "TIME", 
			"MEASURE_START", "MEASURE_END", "REST", "WS", "TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\r\u0089\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2#\n\2\f\2\16\2&"+
		"\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\5\3\5\5\5E\n\5"+
		"\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\3\5\6\5O\n\5\r\5\16\5P\5\5S\n\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6_\n\6\3\7\6\7b\n\7\r\7\16\7c\3\7"+
		"\3\7\6\7h\n\7\r\7\16\7i\3\b\3\b\3\t\3\t\3\n\3\n\3\n\7\ns\n\n\f\n\16\n"+
		"v\13\n\3\n\6\ny\n\n\r\n\16\nz\5\n}\n\n\3\13\3\13\3\13\3\13\3\f\6\f\u0084"+
		"\n\f\r\f\16\f\u0085\3\f\3\f\2\2\r\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24"+
		"\13\26\f\30\r\4\2\3\7\3\2CI\4\2%%dd\3\2\63;\5\2\13\f\17\17\"\"\4\2\13"+
		"\f\17\17\2\u0095\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3"+
		"\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2"+
		"\2\3\30\3\2\2\2\4\32\3\2\2\2\6)\3\2\2\2\b@\3\2\2\2\nB\3\2\2\2\f^\3\2\2"+
		"\2\16a\3\2\2\2\20k\3\2\2\2\22m\3\2\2\2\24o\3\2\2\2\26~\3\2\2\2\30\u0083"+
		"\3\2\2\2\32\33\7V\2\2\33\34\7k\2\2\34\35\7v\2\2\35\36\7n\2\2\36\37\7g"+
		"\2\2\37 \7<\2\2 $\3\2\2\2!#\5\26\13\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2"+
		"$%\3\2\2\2%\'\3\2\2\2&$\3\2\2\2\'(\b\2\2\2(\5\3\2\2\2)*\7R\2\2*+\7c\2"+
		"\2+,\7t\2\2,-\7v\2\2-\61\3\2\2\2.\60\5\26\13\2/.\3\2\2\2\60\63\3\2\2\2"+
		"\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\b\3\2\2\65"+
		"\7\3\2\2\2\66\67\7v\2\2\678\7t\2\289\7g\2\29:\7d\2\2:;\7n\2\2;A\7g\2\2"+
		"<=\7d\2\2=>\7c\2\2>?\7u\2\2?A\7u\2\2@\66\3\2\2\2@<\3\2\2\2A\t\3\2\2\2"+
		"BD\t\2\2\2CE\t\3\2\2DC\3\2\2\2DE\3\2\2\2ER\3\2\2\2FJ\7,\2\2GI\7\60\2\2"+
		"HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LJ\3\2\2\2MO\t\4\2\2"+
		"NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RF\3\2\2\2RS\3\2\2\2"+
		"S\13\3\2\2\2TU\7o\2\2UV\7c\2\2VW\7l\2\2WX\7q\2\2X_\7t\2\2YZ\7o\2\2Z[\7"+
		"k\2\2[\\\7p\2\2\\]\7q\2\2]_\7t\2\2^T\3\2\2\2^Y\3\2\2\2_\r\3\2\2\2`b\t"+
		"\4\2\2a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2de\3\2\2\2eg\7\61\2\2fh"+
		"\t\4\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\17\3\2\2\2kl\7]\2\2"+
		"l\21\3\2\2\2mn\7_\2\2n\23\3\2\2\2o|\7T\2\2pt\7,\2\2qs\7\60\2\2rq\3\2\2"+
		"\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2ux\3\2\2\2vt\3\2\2\2wy\t\4\2\2xw\3\2\2"+
		"\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2\2\2|p\3\2\2\2|}\3\2\2\2}\25\3\2"+
		"\2\2~\177\t\5\2\2\177\u0080\3\2\2\2\u0080\u0081\b\13\3\2\u0081\27\3\2"+
		"\2\2\u0082\u0084\n\6\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\f"+
		"\4\2\u0088\31\3\2\2\2\22\2\3$\61@DJPR^citz|\u0085\5\4\3\2\2\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}