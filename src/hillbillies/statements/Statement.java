package hillbillies.statements;

import hillbillies.expressions.Expression;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public abstract class Statement {

	public abstract void execute();

	public Statement(SourceLocation sourceLocation) {
		this.setSourceLocation(sourceLocation);
		// System.out.println("nieuw statement:"+this);

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
		// System.out.println("-----getTask: ----------");
		// if (hasParentStatement()) {
		// System.out.println("-getTask:
		// getFirstparentstatement="+getFirstParentStatement()+"
		// gettask="+getFirstParentStatement().getTask());
		// return getFirstParentStatement().getTask();
		// } else {
		// System.out.println("-getTask: this.task"+this.task);
		return getFirstParentStatement().task;
		// }
	}

	public Unit getUnit() {
		System.out.println("task in statement: " + getTask());
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
		System.out.println("--- SetIsExecuted this=" + this);
		this.isExecuted = true;
		if (getTask().isExecuted()) {
			System.out.println("--- SetIsExecuted removetask=" + getTask());
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
		// System.out.println("nieuw parent "+parent+"van ouder
		// "+getFirstParent());
	}

	public boolean hasParentStatement() {
		return (getParentStatement() != null);
	}

	public Statement getFirstParentStatement() {
		Statement current = this;
		Statement parent = this;
		// System.out.println("-voor while first parent:"+current);
		while (parent != null) {
			// System.out.println("-in while first parent:"+current);
			current = parent;
			parent = parent.getParentStatement();
		}
		// System.out.println("-na while first parent:"+current);
		return current;
	}

	public Statement getWhileStatement() {
		Statement current = this;
		System.out.println("-voor while getWhileStatement:" + current);

		while (current.hasParentStatement() && (current instanceof WhileStatement)) {
			System.out.println("-in while getWhileStatement:t:" + current);
			current = current.getParentStatement();
		}
		System.out.println("-na while first parent:" + current);
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
