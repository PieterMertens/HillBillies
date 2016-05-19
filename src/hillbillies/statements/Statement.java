package hillbillies.statements;

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
		if (hasParentStatement()) {
			return getParentStatement().getTask();
		} else {
			return this.task;
		}
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
	}

	private boolean started;

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

}
