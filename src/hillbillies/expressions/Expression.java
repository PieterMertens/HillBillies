package hillbillies.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public abstract class Expression<T> {

	public Expression(SourceLocation sourceLocation) {
		this.setSourceLocation(sourceLocation);

	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;

	}

	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}

	private SourceLocation sourceLocation;

	public abstract T evaluate();

	private static Map<String, Expression<?>> expressionsWithVariableName = new HashMap<>();
	private Statement statement;

	public Map<String, Expression<?>> getVariableMap() {
		return Expression.expressionsWithVariableName;
	}

	public void addVariable(String variableName, Expression<?> value) {

		getVariableMap().put(variableName, value);

	}

	public Expression<?> getVariable(String variableName) {
		if (isVariable(variableName)) {
			return getVariableMap().get(variableName);
		} else {
			throw new NoSuchElementException("There is no variable with this name.");
		}
	}

	public boolean isVariable(String variableName) throws NoSuchElementException {
		Map<String, Expression<?>> map = getVariableMap();
		if (map.isEmpty()) {
			return false;
		} else if (getVariable(variableName) != null) {
			return true;
		}
		return false;
	}

	public void setStatement(Statement statement) {
		// System.out.println("setStatement for this="+this+" to
		// statement="+statement);
		this.statement = statement;
	}

	public Statement getStatement() {
		return this.statement;
	}

	public Unit getUnit() {
		System.out.println("getunit from first parent:    stamtent");
		System.out.println(getStatement());
		System.out.println("getfirstparent" + getStatement().getFirstParentStatement());
		return getStatement().getUnit();
	}

	public Task getTask() {
		return getStatement().getTask();
	}

	public World getWorld() {
		return getUnit().getWorld();
	}

	public boolean isWellFormed() {

		if (this instanceof BinaryExpression) {
			if (!((BinaryExpression) this).getLeft(getStatement()).isWellFormed()
					|| !((BinaryExpression) this).getRight(getStatement()).isWellFormed()
					|| ((BinaryExpression) this).getLeft(getStatement()).getClass() != ((BinaryExpression) this)
							.getRight(getStatement()).getClass())
				return false;
		}
		if (this instanceof CubeExpression) {
			if (!(((CubeExpression) this).getPositionExpression((getStatement())).isWellFormed()))
				return false;
		}
		if (this instanceof NextToPositionExpression) {
			if (!((NextToPositionExpression) this).getPositionExpression(getStatement()).isWellFormed())
				return false;
		}
		if (this instanceof PositionOfExpression) {
			if (!((PositionOfExpression) this).getUnitForPositionOf(getStatement()).isWellFormed())
				return false;
		}
		if (this instanceof UnaryExpression) {
			if (!((UnaryExpression) this).getExpression(getStatement()).isWellFormed())
				return false;
		}
		if (this instanceof UnitExaminationExpression) {
			if (!((UnitExaminationExpression) this).getUnitToExamineExpression(getStatement()).isWellFormed())
				return false;
		}

		return true;

	}

}
