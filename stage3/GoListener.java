// Generated from Go.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GoParser}.
 */
public interface GoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GoParser#sourceFile}.
	 * @param ctx the parse tree
	 */
	void enterSourceFile(GoParser.SourceFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#sourceFile}.
	 * @param ctx the parse tree
	 */
	void exitSourceFile(GoParser.SourceFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(GoParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(GoParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(GoParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(GoParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#parameterDecl}.
	 * @param ctx the parse tree
	 */
	void enterParameterDecl(GoParser.ParameterDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#parameterDecl}.
	 * @param ctx the parse tree
	 */
	void exitParameterDecl(GoParser.ParameterDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(GoParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(GoParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GoParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GoParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(GoParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(GoParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(GoParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(GoParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(GoParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(GoParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#forStat}.
	 * @param ctx the parse tree
	 */
	void enterForStat(GoParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#forStat}.
	 * @param ctx the parse tree
	 */
	void exitForStat(GoParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#initStmt}.
	 * @param ctx the parse tree
	 */
	void enterInitStmt(GoParser.InitStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#initStmt}.
	 * @param ctx the parse tree
	 */
	void exitInitStmt(GoParser.InitStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#postStmt}.
	 * @param ctx the parse tree
	 */
	void enterPostStmt(GoParser.PostStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#postStmt}.
	 * @param ctx the parse tree
	 */
	void exitPostStmt(GoParser.PostStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(GoParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(GoParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#exprStat}.
	 * @param ctx the parse tree
	 */
	void enterExprStat(GoParser.ExprStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#exprStat}.
	 * @param ctx the parse tree
	 */
	void exitExprStat(GoParser.ExprStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GoParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GoParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(GoParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(GoParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GoParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GoParser.TypeContext ctx);
}