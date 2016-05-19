package hillbillies.taskfactory;

import java.util.ArrayList;
import java.util.List;

import hillbillies.expressions.*;
import hillbillies.model.Task;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.AssignStatement;
import hillbillies.statements.AttackStatement;
import hillbillies.statements.FollowStatement;
import hillbillies.statements.IfStatement;
import hillbillies.statements.MoveToStatement;
import hillbillies.statements.PrintStatement;
import hillbillies.statements.SequenceStatement;
import hillbillies.statements.Statement;
import hillbillies.statements.WhileStatement;
import hillbillies.statements.WorkStatement;

public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {

		// TODO functie hiervan maken en kijken of beter kan

		List<Task> tasks = new ArrayList<>();

		if (selectedCubes != null) {
			for (int[] cube : selectedCubes) {
				Task task = new Task(name, priority, activity, cube);
				tasks.add(task);
			}

		} else {
			Task task = new Task(name, priority, activity, null);
			tasks.add(task);
		}

		
		System.out.println(tasks.size()+" taken aangemaakt");
		return tasks;

	}

	@Override
	public Statement createAssignment(String variableName, Expression<?> value, SourceLocation sourceLocation) {

		return new AssignStatement(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhile(Expression<?> condition, Statement body, SourceLocation sourceLocation) {

		return new WhileStatement((BooleanExpression) condition, body, sourceLocation);
	}

	@Override
	public Statement createIf(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {

		return new IfStatement((BooleanExpression) condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new SequenceStatement(statements, sourceLocation);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		//System.out.println("New MoveToStatement");
		System.out.println("position "+((PositionExpression)position));
		return new MoveToStatement((PositionExpression) position, sourceLocation);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		//System.out.println("New WorkStatement");
		return new WorkStatement((PositionExpression) position, sourceLocation);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new FollowStatement((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new AttackStatement((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadExpression(variableName, sourceLocation);
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		return new IsSolidExpression((PositionExpression) position, sourceLocation);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		return new IsPassebleExpression((PositionExpression) position, sourceLocation);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		return new IsFriendExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return new IsEnemyExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAliveExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItemExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public Expression<Boolean> createNot(Expression expression, SourceLocation sourceLocation) {
		return new NotExpression((BooleanExpression) expression, sourceLocation);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {

		return new AndExpression((BooleanExpression) left, (BooleanExpression) right, sourceLocation);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new OrExpression((BooleanExpression) left, (BooleanExpression) right, sourceLocation);
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new HerePositionExpression(sourceLocation);
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new LogPositionExpression(sourceLocation);
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderPositionExpression(sourceLocation);
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopPositionExpression(sourceLocation);
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedPositionExpression(sourceLocation);
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralPositionExpression(x, y, z, sourceLocation);
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		return new ThisExpression(sourceLocation);
	}

	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		return new FriendExpression(sourceLocation);
	}

	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression(sourceLocation);
	}

	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		return new AnyExpression(sourceLocation);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new TrueExpression(sourceLocation);
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new FalseExpression(sourceLocation);
	}

}
