// Generated from Go.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GoParser#sourceFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceFile(GoParser.SourceFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(GoParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(GoParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#parameterDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDecl(GoParser.ParameterDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(GoParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(GoParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(GoParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#assignStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(GoParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#ifStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(GoParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#forStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStat(GoParser.ForStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#initStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitStmt(GoParser.InitStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#postStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostStmt(GoParser.PostStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#returnStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(GoParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#exprStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStat(GoParser.ExprStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(GoParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(GoParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GoParser.TypeContext ctx);
}