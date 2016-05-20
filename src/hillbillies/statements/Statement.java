package hillbillies.statements;

import hillbillies.expressions.Expression;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public abstract class Statement {

	public abstract void execute();

	public Statement(SourceLocation sourceLocation) {
		this.setSourceLocation(sourceLocation);

	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;

	}

	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}

	private SourceLocation sourceLocation;
	private Task task;

	public void setTask(Task task) {
		this.task = task;
	}

	public Task getTask() {
			return getFirstParentStatement().task;
	}

	public Unit getUnit() {
		return getTask().getAssignedUnit();
	}

	public boolean isAssignedToUnit() {
		return (getUnit() != null);
	}

	private boolean isExecuted = false;

	public boolean isExecuted() {
		return this.isExecuted;
	}

	public void setIsExecuted() {
		this.isExecuted = true;
		if(getTask().isExecuted()){
			getUnit().getFaction().getScheduler().removeTask(getTask());
		}
	}

	private boolean started = false;

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean getStarted() {
		return this.started;
	}

	private Statement parentStatement;

	public Statement getParentStatement() {
		return this.parentStatement;
	}

	public void setParentStatement(Statement parent) {
		this.parentStatement = parent;
	}

	public boolean hasParentStatement() {
		return (getParentStatement() != null);
	}

	public Statement getFirstParentStatement() {
		Statement current = this;
		Statement parent = this;
		while (parent != null) {
			current = parent;
			parent = parent.getParentStatement();
		}		
		return current;
	}

	public Statement getWhileStatement() {
		Statement current = this;

		while (current.hasParentStatement() && (current instanceof WhileStatement)) {
			current = current.getParentStatement();
		}
		return current;
	}

	public boolean hasWhileStatement() {

		return (this.getWhileStatement() != this);
	}

	public boolean isWellFormed() {

		if (this instanceof SequenceStatement) {
			for (Statement statement : ((SequenceStatement) this).getStatements()) {
				if (!statement.isWellFormed())
					return false;
			}
		}
		if (this instanceof WhileStatement) {
			if (!((WhileStatement) this).getBody().isWellFormed())
				return false;
		}

		if (this instanceof IfStatement) {
			if (((IfStatement) this).hasIfBody()) {
				if (!((IfStatement) this).getIfBody().isWellFormed())
					return false;
			}
			if (((IfStatement) this).hasElseBody()) {
				if (!((IfStatement) this).getElseBody().isWellFormed())
					return false;
			}
		}
		if (this instanceof BreakStatement) {
			if (!this.hasWhileStatement())
				return false;
		}
		return true;
	}

	public Expression<?> getExpression() {
		if (this instanceof AssignStatement) {
			return ((AssignStatement) this).getValue();
		}
		if (this instanceof AttackStatement) {
			return ((AttackStatement) this).getUnitToAttack();
		}
		if (this instanceof FollowStatement) {
			return ((FollowStatement) this).getUnitToFollow();
		}
		if (this instanceof IfStatement) {
			return ((IfStatement) this).getCondition();
		}
		if (this instanceof MoveToStatement) {
			return ((MoveToStatement) this).getPosition();
		}
		if (this instanceof PrintStatement) {
			return ((PrintStatement) this).getValue();
		}
		if (this instanceof WhileStatement) {
			return ((WhileStatement) this).getCondition();
		}
		if (this instanceof WorkStatement) {
			return ((WorkStatement) this).getPosition();
		}
		return null;

	}

}
