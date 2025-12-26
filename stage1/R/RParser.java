// Generated from ./R.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class RParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, FLOAT=40, INT=41, ID=42, NL=43, WS=44;
	public static final int
		RULE_prog = 0, RULE_expr_or_assign = 1, RULE_expr = 2, RULE_exprlist = 3, 
		RULE_formlist = 4, RULE_form = 5, RULE_sublist = 6, RULE_sub = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "expr_or_assign", "expr", "exprlist", "formlist", "form", "sublist", 
			"sub"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'<-'", "'='", "'<<-'", "'[['", "']]'", "'['", "']'", "'::'", "':::'", 
			"'$'", "'@'", "'^'", "'-'", "'+'", "':'", "'*'", "'/'", "'>'", "'>='", 
			"'<'", "'<='", "'=='", "'!='", "'!'", "'&'", "'&&'", "'|'", "'||'", "'~'", 
			"'->'", "'('", "')'", "'{'", "'}'", "'if'", "'else'", "'function'", "';'", 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "FLOAT", "INT", "ID", "NL", "WS"
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
	public String getGrammarFileName() { return "R.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RParser.EOF, 0); }
		public List<TerminalNode> NL() { return getTokens(RParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(RParser.NL, i);
		}
		public List<Expr_or_assignContext> expr_or_assign() {
			return getRuleContexts(Expr_or_assignContext.class);
		}
		public Expr_or_assignContext expr_or_assign(int i) {
			return getRuleContext(Expr_or_assignContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16675764199424L) != 0)) {
				{
				{
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7879671177216L) != 0)) {
					{
					setState(16);
					expr_or_assign();
					}
				}

				setState(19);
				match(NL);
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_or_assignContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_or_assignContext expr_or_assign() {
			return getRuleContext(Expr_or_assignContext.class,0);
		}
		public Expr_or_assignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_or_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterExpr_or_assign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitExpr_or_assign(this);
		}
	}

	public final Expr_or_assignContext expr_or_assign() throws RecognitionException {
		Expr_or_assignContext _localctx = new Expr_or_assignContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr_or_assign);
		try {
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				expr(0);
				setState(28);
				match(T__0);
				setState(29);
				expr_or_assign();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				expr(0);
				setState(32);
				match(T__1);
				setState(33);
				expr_or_assign();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(35);
				expr(0);
				setState(36);
				match(T__2);
				setState(37);
				expr_or_assign();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(39);
				expr(0);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public SublistContext sublist() {
			return getRuleContext(SublistContext.class,0);
		}
		public TerminalNode INT() { return getToken(RParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(RParser.FLOAT, 0); }
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(RParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(RParser.NL, i);
		}
		public FormlistContext formlist() {
			return getRuleContext(FormlistContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(43);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(44);
				expr(18);
				}
				break;
			case 2:
				{
				setState(45);
				match(T__23);
				setState(46);
				expr(13);
				}
				break;
			case 3:
				{
				setState(47);
				match(T__28);
				setState(48);
				expr(10);
				}
				break;
			case 4:
				{
				setState(49);
				match(ID);
				setState(50);
				match(T__30);
				setState(51);
				sublist();
				setState(52);
				match(T__31);
				}
				break;
			case 5:
				{
				setState(54);
				match(ID);
				}
				break;
			case 6:
				{
				setState(55);
				match(INT);
				}
				break;
			case 7:
				{
				setState(56);
				match(FLOAT);
				}
				break;
			case 8:
				{
				setState(57);
				match(T__30);
				setState(58);
				expr(0);
				setState(59);
				match(T__31);
				}
				break;
			case 9:
				{
				setState(61);
				match(T__32);
				setState(62);
				exprlist();
				setState(63);
				match(T__33);
				}
				break;
			case 10:
				{
				setState(65);
				match(T__34);
				setState(66);
				match(T__30);
				setState(67);
				expr(0);
				setState(68);
				match(T__31);
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(69);
					match(NL);
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(75);
				expr(0);
				setState(90);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(76);
						match(NL);
						}
						}
						setState(81);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(82);
					match(T__35);
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(83);
						match(NL);
						}
						}
						setState(88);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(89);
					expr(0);
					}
					break;
				}
				}
				break;
			case 11:
				{
				setState(92);
				match(T__36);
				setState(93);
				match(T__30);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(94);
					formlist();
					}
				}

				setState(97);
				match(T__31);
				setState(98);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(143);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(141);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(101);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(102);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(103);
						expr(22);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(104);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(105);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(106);
						expr(21);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(107);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(108);
						match(T__11);
						setState(109);
						expr(20);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(110);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(111);
						match(T__14);
						setState(112);
						expr(18);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(113);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(114);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(115);
						expr(17);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(116);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(117);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(118);
						expr(16);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(119);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(120);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16515072L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(121);
						expr(15);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(122);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(123);
						_la = _input.LA(1);
						if ( !(_la==T__24 || _la==T__25) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(124);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(125);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(126);
						_la = _input.LA(1);
						if ( !(_la==T__26 || _la==T__27) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(127);
						expr(12);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(128);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(129);
						match(T__29);
						setState(130);
						expr(10);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(131);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(132);
						match(T__3);
						setState(133);
						sublist();
						setState(134);
						match(T__4);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(136);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(137);
						match(T__5);
						setState(138);
						sublist();
						setState(139);
						match(T__6);
						}
						break;
					}
					} 
				}
				setState(145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlistContext extends ParserRuleContext {
		public List<Expr_or_assignContext> expr_or_assign() {
			return getRuleContexts(Expr_or_assignContext.class);
		}
		public Expr_or_assignContext expr_or_assign(int i) {
			return getRuleContext(Expr_or_assignContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(RParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(RParser.NL, i);
		}
		public ExprlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterExprlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitExprlist(this);
		}
	}

	public final ExprlistContext exprlist() throws RecognitionException {
		ExprlistContext _localctx = new ExprlistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exprlist);
		int _la;
		try {
			int _alt;
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(146);
					match(NL);
					}
					}
					setState(151);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(152);
				expr_or_assign();
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(154); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(153);
								_la = _input.LA(1);
								if ( !(_la==T__37 || _la==NL) ) {
								_errHandler.recoverInline(this);
								}
								else {
									if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
									_errHandler.reportMatch(this);
									consume();
								}
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(156); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						setState(159);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7879671177216L) != 0)) {
							{
							setState(158);
							expr_or_assign();
							}
						}

						}
						} 
					}
					setState(165);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(166);
					match(NL);
					}
					}
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(172);
					match(NL);
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class FormlistContext extends ParserRuleContext {
		public List<FormContext> form() {
			return getRuleContexts(FormContext.class);
		}
		public FormContext form(int i) {
			return getRuleContext(FormContext.class,i);
		}
		public FormlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterFormlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitFormlist(this);
		}
	}

	public final FormlistContext formlist() throws RecognitionException {
		FormlistContext _localctx = new FormlistContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_formlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			form();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(181);
				match(T__38);
				setState(182);
				form();
				}
				}
				setState(187);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FormContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitForm(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_form);
		try {
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				match(ID);
				setState(190);
				match(T__1);
				setState(191);
				expr(0);
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class SublistContext extends ParserRuleContext {
		public List<SubContext> sub() {
			return getRuleContexts(SubContext.class);
		}
		public SubContext sub(int i) {
			return getRuleContext(SubContext.class,i);
		}
		public SublistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sublist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterSublist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitSublist(this);
		}
	}

	public final SublistContext sublist() throws RecognitionException {
		SublistContext _localctx = new SublistContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sublist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			sub();
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(195);
				match(T__38);
				setState(196);
				sub();
				}
				}
				setState(201);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SubContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public SubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitSub(this);
		}
	}

	public final SubContext sub() throws RecognitionException {
		SubContext _localctx = new SubContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sub);
		try {
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				match(ID);
				setState(204);
				match(T__1);
				setState(205);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 21);
		case 1:
			return precpred(_ctx, 20);
		case 2:
			return precpred(_ctx, 19);
		case 3:
			return precpred(_ctx, 17);
		case 4:
			return precpred(_ctx, 16);
		case 5:
			return precpred(_ctx, 15);
		case 6:
			return precpred(_ctx, 14);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		case 9:
			return precpred(_ctx, 9);
		case 10:
			return precpred(_ctx, 23);
		case 11:
			return precpred(_ctx, 22);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001,\u00d2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0003\u0000\u0012\b\u0000\u0001\u0000\u0005\u0000\u0015\b\u0000"+
		"\n\u0000\f\u0000\u0018\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001)\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002G\b\u0002\n\u0002\f\u0002J\t\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002N\b\u0002\n\u0002\f\u0002Q\t\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002U\b\u0002\n\u0002\f\u0002X\t\u0002\u0001\u0002\u0003\u0002[\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002`\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002d\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0005\u0002\u008e\b\u0002\n\u0002\f\u0002\u0091\t\u0002\u0001"+
		"\u0003\u0005\u0003\u0094\b\u0003\n\u0003\f\u0003\u0097\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0004\u0003\u009b\b\u0003\u000b\u0003\f\u0003\u009c\u0001"+
		"\u0003\u0003\u0003\u00a0\b\u0003\u0005\u0003\u00a2\b\u0003\n\u0003\f\u0003"+
		"\u00a5\t\u0003\u0001\u0003\u0005\u0003\u00a8\b\u0003\n\u0003\f\u0003\u00ab"+
		"\t\u0003\u0001\u0003\u0005\u0003\u00ae\b\u0003\n\u0003\f\u0003\u00b1\t"+
		"\u0003\u0003\u0003\u00b3\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004\u00b8\b\u0004\n\u0004\f\u0004\u00bb\t\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005\u00c1\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006\u00c6\b\u0006\n\u0006\f\u0006\u00c9\t\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00d0"+
		"\b\u0007\u0001\u0007\u0000\u0001\u0004\b\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0000\b\u0001\u0000\r\u000e\u0001\u0000\b\t\u0001\u0000\n\u000b"+
		"\u0001\u0000\u0010\u0011\u0001\u0000\u0012\u0017\u0001\u0000\u0019\u001a"+
		"\u0001\u0000\u001b\u001c\u0002\u0000&&++\u00f5\u0000\u0016\u0001\u0000"+
		"\u0000\u0000\u0002(\u0001\u0000\u0000\u0000\u0004c\u0001\u0000\u0000\u0000"+
		"\u0006\u00b2\u0001\u0000\u0000\u0000\b\u00b4\u0001\u0000\u0000\u0000\n"+
		"\u00c0\u0001\u0000\u0000\u0000\f\u00c2\u0001\u0000\u0000\u0000\u000e\u00cf"+
		"\u0001\u0000\u0000\u0000\u0010\u0012\u0003\u0002\u0001\u0000\u0011\u0010"+
		"\u0001\u0000\u0000\u0000\u0011\u0012\u0001\u0000\u0000\u0000\u0012\u0013"+
		"\u0001\u0000\u0000\u0000\u0013\u0015\u0005+\u0000\u0000\u0014\u0011\u0001"+
		"\u0000\u0000\u0000\u0015\u0018\u0001\u0000\u0000\u0000\u0016\u0014\u0001"+
		"\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0019\u0001"+
		"\u0000\u0000\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0019\u001a\u0005"+
		"\u0000\u0000\u0001\u001a\u0001\u0001\u0000\u0000\u0000\u001b\u001c\u0003"+
		"\u0004\u0002\u0000\u001c\u001d\u0005\u0001\u0000\u0000\u001d\u001e\u0003"+
		"\u0002\u0001\u0000\u001e)\u0001\u0000\u0000\u0000\u001f \u0003\u0004\u0002"+
		"\u0000 !\u0005\u0002\u0000\u0000!\"\u0003\u0002\u0001\u0000\")\u0001\u0000"+
		"\u0000\u0000#$\u0003\u0004\u0002\u0000$%\u0005\u0003\u0000\u0000%&\u0003"+
		"\u0002\u0001\u0000&)\u0001\u0000\u0000\u0000\')\u0003\u0004\u0002\u0000"+
		"(\u001b\u0001\u0000\u0000\u0000(\u001f\u0001\u0000\u0000\u0000(#\u0001"+
		"\u0000\u0000\u0000(\'\u0001\u0000\u0000\u0000)\u0003\u0001\u0000\u0000"+
		"\u0000*+\u0006\u0002\uffff\uffff\u0000+,\u0007\u0000\u0000\u0000,d\u0003"+
		"\u0004\u0002\u0012-.\u0005\u0018\u0000\u0000.d\u0003\u0004\u0002\r/0\u0005"+
		"\u001d\u0000\u00000d\u0003\u0004\u0002\n12\u0005*\u0000\u000023\u0005"+
		"\u001f\u0000\u000034\u0003\f\u0006\u000045\u0005 \u0000\u00005d\u0001"+
		"\u0000\u0000\u00006d\u0005*\u0000\u00007d\u0005)\u0000\u00008d\u0005("+
		"\u0000\u00009:\u0005\u001f\u0000\u0000:;\u0003\u0004\u0002\u0000;<\u0005"+
		" \u0000\u0000<d\u0001\u0000\u0000\u0000=>\u0005!\u0000\u0000>?\u0003\u0006"+
		"\u0003\u0000?@\u0005\"\u0000\u0000@d\u0001\u0000\u0000\u0000AB\u0005#"+
		"\u0000\u0000BC\u0005\u001f\u0000\u0000CD\u0003\u0004\u0002\u0000DH\u0005"+
		" \u0000\u0000EG\u0005+\u0000\u0000FE\u0001\u0000\u0000\u0000GJ\u0001\u0000"+
		"\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IK\u0001"+
		"\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KZ\u0003\u0004\u0002\u0000"+
		"LN\u0005+\u0000\u0000ML\u0001\u0000\u0000\u0000NQ\u0001\u0000\u0000\u0000"+
		"OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PR\u0001\u0000\u0000"+
		"\u0000QO\u0001\u0000\u0000\u0000RV\u0005$\u0000\u0000SU\u0005+\u0000\u0000"+
		"TS\u0001\u0000\u0000\u0000UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000"+
		"\u0000VW\u0001\u0000\u0000\u0000WY\u0001\u0000\u0000\u0000XV\u0001\u0000"+
		"\u0000\u0000Y[\u0003\u0004\u0002\u0000ZO\u0001\u0000\u0000\u0000Z[\u0001"+
		"\u0000\u0000\u0000[d\u0001\u0000\u0000\u0000\\]\u0005%\u0000\u0000]_\u0005"+
		"\u001f\u0000\u0000^`\u0003\b\u0004\u0000_^\u0001\u0000\u0000\u0000_`\u0001"+
		"\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0005 \u0000\u0000bd\u0003"+
		"\u0004\u0002\u0001c*\u0001\u0000\u0000\u0000c-\u0001\u0000\u0000\u0000"+
		"c/\u0001\u0000\u0000\u0000c1\u0001\u0000\u0000\u0000c6\u0001\u0000\u0000"+
		"\u0000c7\u0001\u0000\u0000\u0000c8\u0001\u0000\u0000\u0000c9\u0001\u0000"+
		"\u0000\u0000c=\u0001\u0000\u0000\u0000cA\u0001\u0000\u0000\u0000c\\\u0001"+
		"\u0000\u0000\u0000d\u008f\u0001\u0000\u0000\u0000ef\n\u0015\u0000\u0000"+
		"fg\u0007\u0001\u0000\u0000g\u008e\u0003\u0004\u0002\u0016hi\n\u0014\u0000"+
		"\u0000ij\u0007\u0002\u0000\u0000j\u008e\u0003\u0004\u0002\u0015kl\n\u0013"+
		"\u0000\u0000lm\u0005\f\u0000\u0000m\u008e\u0003\u0004\u0002\u0014no\n"+
		"\u0011\u0000\u0000op\u0005\u000f\u0000\u0000p\u008e\u0003\u0004\u0002"+
		"\u0012qr\n\u0010\u0000\u0000rs\u0007\u0003\u0000\u0000s\u008e\u0003\u0004"+
		"\u0002\u0011tu\n\u000f\u0000\u0000uv\u0007\u0000\u0000\u0000v\u008e\u0003"+
		"\u0004\u0002\u0010wx\n\u000e\u0000\u0000xy\u0007\u0004\u0000\u0000y\u008e"+
		"\u0003\u0004\u0002\u000fz{\n\f\u0000\u0000{|\u0007\u0005\u0000\u0000|"+
		"\u008e\u0003\u0004\u0002\r}~\n\u000b\u0000\u0000~\u007f\u0007\u0006\u0000"+
		"\u0000\u007f\u008e\u0003\u0004\u0002\f\u0080\u0081\n\t\u0000\u0000\u0081"+
		"\u0082\u0005\u001e\u0000\u0000\u0082\u008e\u0003\u0004\u0002\n\u0083\u0084"+
		"\n\u0017\u0000\u0000\u0084\u0085\u0005\u0004\u0000\u0000\u0085\u0086\u0003"+
		"\f\u0006\u0000\u0086\u0087\u0005\u0005\u0000\u0000\u0087\u008e\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\n\u0016\u0000\u0000\u0089\u008a\u0005\u0006\u0000"+
		"\u0000\u008a\u008b\u0003\f\u0006\u0000\u008b\u008c\u0005\u0007\u0000\u0000"+
		"\u008c\u008e\u0001\u0000\u0000\u0000\u008de\u0001\u0000\u0000\u0000\u008d"+
		"h\u0001\u0000\u0000\u0000\u008dk\u0001\u0000\u0000\u0000\u008dn\u0001"+
		"\u0000\u0000\u0000\u008dq\u0001\u0000\u0000\u0000\u008dt\u0001\u0000\u0000"+
		"\u0000\u008dw\u0001\u0000\u0000\u0000\u008dz\u0001\u0000\u0000\u0000\u008d"+
		"}\u0001\u0000\u0000\u0000\u008d\u0080\u0001\u0000\u0000\u0000\u008d\u0083"+
		"\u0001\u0000\u0000\u0000\u008d\u0088\u0001\u0000\u0000\u0000\u008e\u0091"+
		"\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0001\u0000\u0000\u0000\u0090\u0005\u0001\u0000\u0000\u0000\u0091\u008f"+
		"\u0001\u0000\u0000\u0000\u0092\u0094\u0005+\u0000\u0000\u0093\u0092\u0001"+
		"\u0000\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095\u0093\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0098\u0001"+
		"\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0098\u00a3\u0003"+
		"\u0002\u0001\u0000\u0099\u009b\u0007\u0007\u0000\u0000\u009a\u0099\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009f\u0001"+
		"\u0000\u0000\u0000\u009e\u00a0\u0003\u0002\u0001\u0000\u009f\u009e\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a1\u009a\u0001\u0000\u0000\u0000\u00a2\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a9\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a8\u0005+\u0000\u0000\u00a7\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a8\u00ab\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00b3\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ac\u00ae\u0005+\u0000"+
		"\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000"+
		"\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000"+
		"\u0000\u00b2\u0095\u0001\u0000\u0000\u0000\u00b2\u00af\u0001\u0000\u0000"+
		"\u0000\u00b3\u0007\u0001\u0000\u0000\u0000\u00b4\u00b9\u0003\n\u0005\u0000"+
		"\u00b5\u00b6\u0005\'\u0000\u0000\u00b6\u00b8\u0003\n\u0005\u0000\u00b7"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba"+
		"\t\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00c1"+
		"\u0005*\u0000\u0000\u00bd\u00be\u0005*\u0000\u0000\u00be\u00bf\u0005\u0002"+
		"\u0000\u0000\u00bf\u00c1\u0003\u0004\u0002\u0000\u00c0\u00bc\u0001\u0000"+
		"\u0000\u0000\u00c0\u00bd\u0001\u0000\u0000\u0000\u00c1\u000b\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c7\u0003\u000e\u0007\u0000\u00c3\u00c4\u0005\'\u0000"+
		"\u0000\u00c4\u00c6\u0003\u000e\u0007\u0000\u00c5\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\r\u0001\u0000\u0000\u0000"+
		"\u00c9\u00c7\u0001\u0000\u0000\u0000\u00ca\u00d0\u0003\u0004\u0002\u0000"+
		"\u00cb\u00cc\u0005*\u0000\u0000\u00cc\u00cd\u0005\u0002\u0000\u0000\u00cd"+
		"\u00d0\u0003\u0004\u0002\u0000\u00ce\u00d0\u0001\u0000\u0000\u0000\u00cf"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cf\u00cb\u0001\u0000\u0000\u0000\u00cf"+
		"\u00ce\u0001\u0000\u0000\u0000\u00d0\u000f\u0001\u0000\u0000\u0000\u0016"+
		"\u0011\u0016(HOVZ_c\u008d\u008f\u0095\u009c\u009f\u00a3\u00a9\u00af\u00b2"+
		"\u00b9\u00c0\u00c7\u00cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}