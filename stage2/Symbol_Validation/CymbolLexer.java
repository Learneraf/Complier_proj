// Generated from ./Cymbol.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CymbolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, ID=22, INT=23, WS=24, SL_COMMENT=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "ID", "INT", "WS", "SL_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "';'", "'float'", "'int'", "'void'", "'('", "')'", "','", 
			"'{'", "'}'", "'if'", "'then'", "'else'", "'return'", "'-'", "'!'", "'*'", 
			"'/'", "'+'", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "ID", "INT", 
			"WS", "SL_COMMENT"
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


	public CymbolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Cymbol.g4"; }

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
		"\u0004\u0000\u0019\u0094\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0005\u0015w\b\u0015\n\u0015\f\u0015z\t"+
		"\u0015\u0001\u0016\u0004\u0016}\b\u0016\u000b\u0016\f\u0016~\u0001\u0017"+
		"\u0004\u0017\u0082\b\u0017\u000b\u0017\f\u0017\u0083\u0001\u0017\u0001"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u008c"+
		"\b\u0018\n\u0018\f\u0018\u008f\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u008d\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u0019\u0001\u0000\u0004\u0002"+
		"\u0000AZaz\u0004\u000009AZ__az\u0001\u000009\u0003\u0000\t\n\r\r  \u0097"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00013\u0001\u0000"+
		"\u0000\u0000\u00035\u0001\u0000\u0000\u0000\u00057\u0001\u0000\u0000\u0000"+
		"\u0007=\u0001\u0000\u0000\u0000\tA\u0001\u0000\u0000\u0000\u000bF\u0001"+
		"\u0000\u0000\u0000\rH\u0001\u0000\u0000\u0000\u000fJ\u0001\u0000\u0000"+
		"\u0000\u0011L\u0001\u0000\u0000\u0000\u0013N\u0001\u0000\u0000\u0000\u0015"+
		"P\u0001\u0000\u0000\u0000\u0017S\u0001\u0000\u0000\u0000\u0019X\u0001"+
		"\u0000\u0000\u0000\u001b]\u0001\u0000\u0000\u0000\u001dd\u0001\u0000\u0000"+
		"\u0000\u001ff\u0001\u0000\u0000\u0000!h\u0001\u0000\u0000\u0000#j\u0001"+
		"\u0000\u0000\u0000%l\u0001\u0000\u0000\u0000\'n\u0001\u0000\u0000\u0000"+
		")q\u0001\u0000\u0000\u0000+t\u0001\u0000\u0000\u0000-|\u0001\u0000\u0000"+
		"\u0000/\u0081\u0001\u0000\u0000\u00001\u0087\u0001\u0000\u0000\u00003"+
		"4\u0005=\u0000\u00004\u0002\u0001\u0000\u0000\u000056\u0005;\u0000\u0000"+
		"6\u0004\u0001\u0000\u0000\u000078\u0005f\u0000\u000089\u0005l\u0000\u0000"+
		"9:\u0005o\u0000\u0000:;\u0005a\u0000\u0000;<\u0005t\u0000\u0000<\u0006"+
		"\u0001\u0000\u0000\u0000=>\u0005i\u0000\u0000>?\u0005n\u0000\u0000?@\u0005"+
		"t\u0000\u0000@\b\u0001\u0000\u0000\u0000AB\u0005v\u0000\u0000BC\u0005"+
		"o\u0000\u0000CD\u0005i\u0000\u0000DE\u0005d\u0000\u0000E\n\u0001\u0000"+
		"\u0000\u0000FG\u0005(\u0000\u0000G\f\u0001\u0000\u0000\u0000HI\u0005)"+
		"\u0000\u0000I\u000e\u0001\u0000\u0000\u0000JK\u0005,\u0000\u0000K\u0010"+
		"\u0001\u0000\u0000\u0000LM\u0005{\u0000\u0000M\u0012\u0001\u0000\u0000"+
		"\u0000NO\u0005}\u0000\u0000O\u0014\u0001\u0000\u0000\u0000PQ\u0005i\u0000"+
		"\u0000QR\u0005f\u0000\u0000R\u0016\u0001\u0000\u0000\u0000ST\u0005t\u0000"+
		"\u0000TU\u0005h\u0000\u0000UV\u0005e\u0000\u0000VW\u0005n\u0000\u0000"+
		"W\u0018\u0001\u0000\u0000\u0000XY\u0005e\u0000\u0000YZ\u0005l\u0000\u0000"+
		"Z[\u0005s\u0000\u0000[\\\u0005e\u0000\u0000\\\u001a\u0001\u0000\u0000"+
		"\u0000]^\u0005r\u0000\u0000^_\u0005e\u0000\u0000_`\u0005t\u0000\u0000"+
		"`a\u0005u\u0000\u0000ab\u0005r\u0000\u0000bc\u0005n\u0000\u0000c\u001c"+
		"\u0001\u0000\u0000\u0000de\u0005-\u0000\u0000e\u001e\u0001\u0000\u0000"+
		"\u0000fg\u0005!\u0000\u0000g \u0001\u0000\u0000\u0000hi\u0005*\u0000\u0000"+
		"i\"\u0001\u0000\u0000\u0000jk\u0005/\u0000\u0000k$\u0001\u0000\u0000\u0000"+
		"lm\u0005+\u0000\u0000m&\u0001\u0000\u0000\u0000no\u0005=\u0000\u0000o"+
		"p\u0005=\u0000\u0000p(\u0001\u0000\u0000\u0000qr\u0005!\u0000\u0000rs"+
		"\u0005=\u0000\u0000s*\u0001\u0000\u0000\u0000tx\u0007\u0000\u0000\u0000"+
		"uw\u0007\u0001\u0000\u0000vu\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000"+
		"\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y,\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000{}\u0007\u0002\u0000\u0000|{\u0001"+
		"\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000"+
		"~\u007f\u0001\u0000\u0000\u0000\u007f.\u0001\u0000\u0000\u0000\u0080\u0082"+
		"\u0007\u0003\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0006\u0017\u0000\u0000\u00860\u0001\u0000\u0000\u0000\u0087\u0088\u0005"+
		"/\u0000\u0000\u0088\u0089\u0005/\u0000\u0000\u0089\u008d\u0001\u0000\u0000"+
		"\u0000\u008a\u008c\t\u0000\u0000\u0000\u008b\u008a\u0001\u0000\u0000\u0000"+
		"\u008c\u008f\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000"+
		"\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0090\u0001\u0000\u0000\u0000"+
		"\u008f\u008d\u0001\u0000\u0000\u0000\u0090\u0091\u0005\n\u0000\u0000\u0091"+
		"\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0006\u0018\u0000\u0000\u0093"+
		"2\u0001\u0000\u0000\u0000\u0005\u0000x~\u0083\u008d\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}