package hillbillies.taskfactory;

import java.util.List;

import expressions.AndExpression;
import expressions.BooleanExpression;
import expressions.Expression;
import expressions.FalseExpression;
import expressions.NotExpression;
import expressions.OrExpression;
import expressions.TrueExpression;
import hillbillies.expressions.*;
import hillbillies.model.Task;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.AssignStatement;
import hillbillies.statements.IfStatement;
import hillbillies.statements.WhileStatement;
import statements.Statement;

public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {

		return null;
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {

		return new AssignStatement(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {

		return new WhileStatement(condition, body, sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {

		return new IfStatement(condition,ifBody,elseBody,sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {

		return new ReadExpression(variableName,sourceLocation);
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createNot(Expression expression, SourceLocation sourceLocation) {

		return new NotExpression((BooleanExpression)expression, sourceLocation);
	}

	@Override
	public Expression<?> createAnd(Expression left, Expression right, SourceLocation sourceLocation) {

		return new AndExpression((BooleanExpression) left,(BooleanExpression)  right, sourceLocation);
	}

	@Override
	public Expression<?> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {

		return new OrExpression((BooleanExpression) left,(BooleanExpression) right,sourceLocation);
	}

	@Override
	public Expression<?> createHerePosition(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createLogPosition(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createBoulderPosition(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createWorkshopPosition(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createSelectedPosition(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createNextToPosition(Expression position, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createThis(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createFriend(SourceLocation sourceLocation) {

		return null;
	}

	@Override
	public Expression<?> createEnemy(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression<?> createAny(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression<?> createTrue(SourceLocation sourceLocation) {
		return new TrueExpression(sourceLocation);
	}

	@Override
	public Expression<?> createFalse(SourceLocation sourceLocation) {
		return new FalseExpression(sourceLocation);
	}


}
