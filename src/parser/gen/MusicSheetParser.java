// Generated from C:/Users/danie/OneDrive/Documents/School/2021W1/CPSC410/MusicSheetGenerator/src/parser\MusicSheetParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MusicSheetParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TITLE_START=1, PART_START=2, CLEF=3, NOTE=4, KEYTYPE=5, TIME=6, MEASURE_START=7, 
		MEASURE_END=8, REST=9, WS=10, TEXT=11;
	public static final int
		RULE_program = 0, RULE_title = 1, RULE_part = 2, RULE_name = 3, RULE_sheet = 4, 
		RULE_key = 5, RULE_measure = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "title", "part", "name", "sheet", "key", "measure"
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

	@Override
	public String getGrammarFileName() { return "MusicSheetParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MusicSheetParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<PartContext> part() {
			return getRuleContexts(PartContext.class);
		}
		public PartContext part(int i) {
			return getRuleContext(PartContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			title();
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TEXT) {
				{
				{
				setState(15);
				part();
				}
				}
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TitleContext extends ParserRuleContext {
		public TerminalNode TITLE_START() { return getToken(MusicSheetParser.TITLE_START, 0); }
		public TerminalNode TEXT() { return getToken(MusicSheetParser.TEXT, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(TITLE_START);
			setState(22);
			match(TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public SheetContext sheet() {
			return getRuleContext(SheetContext.class,0);
		}
		public PartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartContext part() throws RecognitionException {
		PartContext _localctx = new PartContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_part);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			name();
			setState(25);
			sheet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(MusicSheetParser.TEXT, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SheetContext extends ParserRuleContext {
		public TerminalNode CLEF() { return getToken(MusicSheetParser.CLEF, 0); }
		public KeyContext key() {
			return getRuleContext(KeyContext.class,0);
		}
		public TerminalNode TIME() { return getToken(MusicSheetParser.TIME, 0); }
		public List<MeasureContext> measure() {
			return getRuleContexts(MeasureContext.class);
		}
		public MeasureContext measure(int i) {
			return getRuleContext(MeasureContext.class,i);
		}
		public SheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterSheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitSheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitSheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SheetContext sheet() throws RecognitionException {
		SheetContext _localctx = new SheetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(CLEF);
			setState(30);
			key();
			setState(31);
			match(TIME);
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				measure();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MEASURE_START );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode NOTE() { return getToken(MusicSheetParser.NOTE, 0); }
		public TerminalNode KEYTYPE() { return getToken(MusicSheetParser.KEYTYPE, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(NOTE);
			setState(38);
			match(KEYTYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MeasureContext extends ParserRuleContext {
		public TerminalNode MEASURE_START() { return getToken(MusicSheetParser.MEASURE_START, 0); }
		public TerminalNode MEASURE_END() { return getToken(MusicSheetParser.MEASURE_END, 0); }
		public List<TerminalNode> NOTE() { return getTokens(MusicSheetParser.NOTE); }
		public TerminalNode NOTE(int i) {
			return getToken(MusicSheetParser.NOTE, i);
		}
		public List<TerminalNode> REST() { return getTokens(MusicSheetParser.REST); }
		public TerminalNode REST(int i) {
			return getToken(MusicSheetParser.REST, i);
		}
		public MeasureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_measure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterMeasure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitMeasure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitMeasure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MeasureContext measure() throws RecognitionException {
		MeasureContext _localctx = new MeasureContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_measure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(MEASURE_START);
			setState(42); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				_la = _input.LA(1);
				if ( !(_la==NOTE || _la==REST) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(44); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NOTE || _la==REST );
			setState(46);
			match(MEASURE_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r\63\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\7\2\23\n\2\f\2\16"+
		"\2\26\13\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\6\6$\n\6\r"+
		"\6\16\6%\3\7\3\7\3\7\3\b\3\b\6\b-\n\b\r\b\16\b.\3\b\3\b\3\b\2\2\t\2\4"+
		"\6\b\n\f\16\2\3\4\2\6\6\13\13\2.\2\20\3\2\2\2\4\27\3\2\2\2\6\32\3\2\2"+
		"\2\b\35\3\2\2\2\n\37\3\2\2\2\f\'\3\2\2\2\16*\3\2\2\2\20\24\5\4\3\2\21"+
		"\23\5\6\4\2\22\21\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25"+
		"\3\3\2\2\2\26\24\3\2\2\2\27\30\7\3\2\2\30\31\7\r\2\2\31\5\3\2\2\2\32\33"+
		"\5\b\5\2\33\34\5\n\6\2\34\7\3\2\2\2\35\36\7\r\2\2\36\t\3\2\2\2\37 \7\5"+
		"\2\2 !\5\f\7\2!#\7\b\2\2\"$\5\16\b\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&"+
		"\3\2\2\2&\13\3\2\2\2\'(\7\6\2\2()\7\7\2\2)\r\3\2\2\2*,\7\t\2\2+-\t\2\2"+
		"\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\n\2\2\61"+
		"\17\3\2\2\2\5\24%.";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}