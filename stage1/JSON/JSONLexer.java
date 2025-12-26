// Generated from ./JSON.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class JSONLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		STRING=10, NUMBER=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"STRING", "ESC", "NUMBER", "INT", "EXP", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "':'", "'['", "']'", "'true'", "'false'", 
			"'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "STRING", 
			"NUMBER", "WS"
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


	public JSONLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JSON.g4"; }

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
		"\u0004\u0000\f}\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0005\t?\b\t\n\t\f\t"+
		"B\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0003\nM\b\n\u0001\u000b\u0003\u000bP\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000bV\b\u000b\u0001\u000b\u0003"+
		"\u000bY\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b_\b\u000b\u0001\u000b\u0003\u000bb\b\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0005\fg\b\f\n\f\f\fj\t\f\u0003\fl\b\f\u0001\r\u0001\r\u0003\rp\b\r"+
		"\u0001\r\u0004\rs\b\r\u000b\r\f\rt\u0001\u000e\u0004\u000ex\b\u000e\u000b"+
		"\u000e\f\u000ey\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u0000\u0017\u000b\u0019\u0000\u001b\u0000\u001d"+
		"\f\u0001\u0000\b\u0002\u0000\"\"\\\\\b\u0000\"\"//\\\\bbffnnrrtt\u0003"+
		"\u000009AFaf\u0001\u000019\u0001\u000009\u0002\u0000EEee\u0002\u0000+"+
		"+--\u0003\u0000\t\n\r\r  \u0087\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u001d\u0001"+
		"\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0003!\u0001\u0000"+
		"\u0000\u0000\u0005#\u0001\u0000\u0000\u0000\u0007%\u0001\u0000\u0000\u0000"+
		"\t\'\u0001\u0000\u0000\u0000\u000b)\u0001\u0000\u0000\u0000\r+\u0001\u0000"+
		"\u0000\u0000\u000f0\u0001\u0000\u0000\u0000\u00116\u0001\u0000\u0000\u0000"+
		"\u0013;\u0001\u0000\u0000\u0000\u0015E\u0001\u0000\u0000\u0000\u0017a"+
		"\u0001\u0000\u0000\u0000\u0019k\u0001\u0000\u0000\u0000\u001bm\u0001\u0000"+
		"\u0000\u0000\u001dw\u0001\u0000\u0000\u0000\u001f \u0005{\u0000\u0000"+
		" \u0002\u0001\u0000\u0000\u0000!\"\u0005,\u0000\u0000\"\u0004\u0001\u0000"+
		"\u0000\u0000#$\u0005}\u0000\u0000$\u0006\u0001\u0000\u0000\u0000%&\u0005"+
		":\u0000\u0000&\b\u0001\u0000\u0000\u0000\'(\u0005[\u0000\u0000(\n\u0001"+
		"\u0000\u0000\u0000)*\u0005]\u0000\u0000*\f\u0001\u0000\u0000\u0000+,\u0005"+
		"t\u0000\u0000,-\u0005r\u0000\u0000-.\u0005u\u0000\u0000./\u0005e\u0000"+
		"\u0000/\u000e\u0001\u0000\u0000\u000001\u0005f\u0000\u000012\u0005a\u0000"+
		"\u000023\u0005l\u0000\u000034\u0005s\u0000\u000045\u0005e\u0000\u0000"+
		"5\u0010\u0001\u0000\u0000\u000067\u0005n\u0000\u000078\u0005u\u0000\u0000"+
		"89\u0005l\u0000\u00009:\u0005l\u0000\u0000:\u0012\u0001\u0000\u0000\u0000"+
		";@\u0005\"\u0000\u0000<?\u0003\u0015\n\u0000=?\b\u0000\u0000\u0000><\u0001"+
		"\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000"+
		"@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AC\u0001\u0000\u0000"+
		"\u0000B@\u0001\u0000\u0000\u0000CD\u0005\"\u0000\u0000D\u0014\u0001\u0000"+
		"\u0000\u0000EL\u0005\\\u0000\u0000FM\u0007\u0001\u0000\u0000GH\u0005u"+
		"\u0000\u0000HI\u0007\u0002\u0000\u0000IJ\u0007\u0002\u0000\u0000JK\u0007"+
		"\u0002\u0000\u0000KM\u0007\u0002\u0000\u0000LF\u0001\u0000\u0000\u0000"+
		"LG\u0001\u0000\u0000\u0000M\u0016\u0001\u0000\u0000\u0000NP\u0005-\u0000"+
		"\u0000ON\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000QR\u0003\u0019\f\u0000RS\u0005.\u0000\u0000SU\u0003\u0019"+
		"\f\u0000TV\u0003\u001b\r\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000"+
		"\u0000\u0000Vb\u0001\u0000\u0000\u0000WY\u0005-\u0000\u0000XW\u0001\u0000"+
		"\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0003"+
		"\u0019\f\u0000[\\\u0003\u001b\r\u0000\\b\u0001\u0000\u0000\u0000]_\u0005"+
		"-\u0000\u0000^]\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0001"+
		"\u0000\u0000\u0000`b\u0003\u0019\f\u0000aO\u0001\u0000\u0000\u0000aX\u0001"+
		"\u0000\u0000\u0000a^\u0001\u0000\u0000\u0000b\u0018\u0001\u0000\u0000"+
		"\u0000cl\u00050\u0000\u0000dh\u0007\u0003\u0000\u0000eg\u0007\u0004\u0000"+
		"\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000hf\u0001\u0000"+
		"\u0000\u0000hi\u0001\u0000\u0000\u0000il\u0001\u0000\u0000\u0000jh\u0001"+
		"\u0000\u0000\u0000kc\u0001\u0000\u0000\u0000kd\u0001\u0000\u0000\u0000"+
		"l\u001a\u0001\u0000\u0000\u0000mo\u0007\u0005\u0000\u0000np\u0007\u0006"+
		"\u0000\u0000on\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pr\u0001"+
		"\u0000\u0000\u0000qs\u0007\u0004\u0000\u0000rq\u0001\u0000\u0000\u0000"+
		"st\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000"+
		"\u0000u\u001c\u0001\u0000\u0000\u0000vx\u0007\u0007\u0000\u0000wv\u0001"+
		"\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000"+
		"yz\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{|\u0006\u000e\u0000"+
		"\u0000|\u001e\u0001\u0000\u0000\u0000\u000e\u0000>@LOUX^ahkoty\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}