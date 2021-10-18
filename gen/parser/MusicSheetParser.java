// Generated from /Users/andydong/Desktop/Project1Group22/src/parser/MusicSheetParser.g4 by ANTLR 4.9.1
package parser;
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
		TITLE_START=1, PART_START=2, CLEF=3, NOTE_LETTER=4, ACCIDENTAL=5, DOTS=6, 
		DIVISION=7, KEYTYPE=8, TIME=9, MEASURE_START=10, MEASURE_END=11, LOOP_DECLARE=12, 
		LOOP_START=13, LOOP_END=14, OCTAVE=15, WS=16, TEXT=17;
	public static final int
		RULE_program = 0, RULE_title = 1, RULE_part = 2, RULE_name = 3, RULE_sheet = 4, 
		RULE_key = 5, RULE_measure = 6, RULE_note = 7, RULE_loop = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "title", "part", "name", "sheet", "key", "measure", "note", 
			"loop"
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
			setState(18);
			title();
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PART_START) {
				{
				{
				setState(19);
				part();
				}
				}
				setState(24);
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
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
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
			setState(25);
			match(TITLE_START);
			setState(26);
			name();
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
		public TerminalNode PART_START() { return getToken(MusicSheetParser.PART_START, 0); }
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
			setState(28);
			match(PART_START);
			setState(29);
			name();
			setState(30);
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
			setState(32);
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
		public List<LoopContext> loop() {
			return getRuleContexts(LoopContext.class);
		}
		public LoopContext loop(int i) {
			return getRuleContext(LoopContext.class,i);
		}
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
			setState(34);
			match(CLEF);
			setState(35);
			key();
			setState(36);
			match(TIME);
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(39);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOOP_DECLARE:
					{
					setState(37);
					loop();
					}
					break;
				case MEASURE_START:
					{
					setState(38);
					measure();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MEASURE_START || _la==LOOP_DECLARE );
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
		public TerminalNode NOTE_LETTER() { return getToken(MusicSheetParser.NOTE_LETTER, 0); }
		public TerminalNode KEYTYPE() { return getToken(MusicSheetParser.KEYTYPE, 0); }
		public TerminalNode ACCIDENTAL() { return getToken(MusicSheetParser.ACCIDENTAL, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(NOTE_LETTER);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ACCIDENTAL) {
				{
				setState(44);
				match(ACCIDENTAL);
				}
			}

			setState(47);
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
		public List<NoteContext> note() {
			return getRuleContexts(NoteContext.class);
		}
		public NoteContext note(int i) {
			return getRuleContext(NoteContext.class,i);
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
			setState(49);
			match(MEASURE_START);
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50);
				note();
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NOTE_LETTER );
			setState(55);
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

	public static class NoteContext extends ParserRuleContext {
		public TerminalNode NOTE_LETTER() { return getToken(MusicSheetParser.NOTE_LETTER, 0); }
		public TerminalNode OCTAVE() { return getToken(MusicSheetParser.OCTAVE, 0); }
		public TerminalNode DIVISION() { return getToken(MusicSheetParser.DIVISION, 0); }
		public TerminalNode ACCIDENTAL() { return getToken(MusicSheetParser.ACCIDENTAL, 0); }
		public TerminalNode DOTS() { return getToken(MusicSheetParser.DOTS, 0); }
		public NoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_note; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterNote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitNote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitNote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteContext note() throws RecognitionException {
		NoteContext _localctx = new NoteContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_note);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(NOTE_LETTER);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ACCIDENTAL) {
				{
				setState(58);
				match(ACCIDENTAL);
				}
			}

			setState(61);
			match(OCTAVE);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOTS) {
				{
				setState(62);
				match(DOTS);
				}
			}

			setState(65);
			match(DIVISION);
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

	public static class LoopContext extends ParserRuleContext {
		public TerminalNode LOOP_DECLARE() { return getToken(MusicSheetParser.LOOP_DECLARE, 0); }
		public TerminalNode LOOP_START() { return getToken(MusicSheetParser.LOOP_START, 0); }
		public TerminalNode LOOP_END() { return getToken(MusicSheetParser.LOOP_END, 0); }
		public List<MeasureContext> measure() {
			return getRuleContexts(MeasureContext.class);
		}
		public MeasureContext measure(int i) {
			return getRuleContext(MeasureContext.class,i);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MusicSheetParserListener ) ((MusicSheetParserListener)listener).exitLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MusicSheetParserVisitor ) return ((MusicSheetParserVisitor<? extends T>)visitor).visitLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(LOOP_DECLARE);
			setState(68);
			match(LOOP_START);
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				measure();
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MEASURE_START );
			setState(74);
			match(LOOP_END);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23O\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\7\2"+
		"\27\n\2\f\2\16\2\32\13\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\6\6*\n\6\r\6\16\6+\3\7\3\7\5\7\60\n\7\3\7\3\7\3\b\3\b\6\b\66"+
		"\n\b\r\b\16\b\67\3\b\3\b\3\t\3\t\5\t>\n\t\3\t\3\t\5\tB\n\t\3\t\3\t\3\n"+
		"\3\n\3\n\6\nI\n\n\r\n\16\nJ\3\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2"+
		"\2\2M\2\24\3\2\2\2\4\33\3\2\2\2\6\36\3\2\2\2\b\"\3\2\2\2\n$\3\2\2\2\f"+
		"-\3\2\2\2\16\63\3\2\2\2\20;\3\2\2\2\22E\3\2\2\2\24\30\5\4\3\2\25\27\5"+
		"\6\4\2\26\25\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\3\3"+
		"\2\2\2\32\30\3\2\2\2\33\34\7\3\2\2\34\35\5\b\5\2\35\5\3\2\2\2\36\37\7"+
		"\4\2\2\37 \5\b\5\2 !\5\n\6\2!\7\3\2\2\2\"#\7\23\2\2#\t\3\2\2\2$%\7\5\2"+
		"\2%&\5\f\7\2&)\7\13\2\2\'*\5\22\n\2(*\5\16\b\2)\'\3\2\2\2)(\3\2\2\2*+"+
		"\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\13\3\2\2\2-/\7\6\2\2.\60\7\7\2\2/.\3\2\2"+
		"\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\n\2\2\62\r\3\2\2\2\63\65\7\f\2\2"+
		"\64\66\5\20\t\2\65\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\2"+
		"89\3\2\2\29:\7\r\2\2:\17\3\2\2\2;=\7\6\2\2<>\7\7\2\2=<\3\2\2\2=>\3\2\2"+
		"\2>?\3\2\2\2?A\7\21\2\2@B\7\b\2\2A@\3\2\2\2AB\3\2\2\2BC\3\2\2\2CD\7\t"+
		"\2\2D\21\3\2\2\2EF\7\16\2\2FH\7\17\2\2GI\5\16\b\2HG\3\2\2\2IJ\3\2\2\2"+
		"JH\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM\7\20\2\2M\23\3\2\2\2\n\30)+/\67=AJ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}