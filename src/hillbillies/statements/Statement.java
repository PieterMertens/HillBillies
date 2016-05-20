package hillbillies.statements;

import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;

public abstract class Statement {

	public abstract void execute();

	public Statement(SourceLocation sourceLocation) {
		this.setSourceLocation(sourceLocation);
		//System.out.println("nieuw statement:"+this);

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
//		System.out.println("-----getTask: ----------");
//		if (hasParentStatement()) {
//			System.out.println("-getTask: getFirstparentstatement="+getFirstParentStatement()+" gettask="+getFirstParentStatement().getTask());
//			return getFirstParentStatement().getTask();
//		} else {
//			System.out.println("-getTask: this.task"+this.task);
			return getFirstParentStatement().task;
//		}
	}

	public Unit getUnit() {
		System.out.println("task in statement: "+getTask());
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
		System.out.println("--- SetIsExecuted this="+this);
		this.isExecuted = true;
		if(getTask().isExecuted()){
			System.out.println("--- SetIsExecuted removetask="+getTask());
			getUnit().getFaction().getScheduler().removeTask(getTask());
		}
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
		//System.out.println("nieuw parent "+parent+"van ouder "+getFirstParent());
	}

	public boolean hasParentStatement() {
		return (getParentStatement() != null);
	}

	public Statement getFirstParentStatement() {
		Statement current = this;
		Statement parent = this;
		//System.out.println("-voor while first parent:"+current);
		while (parent != null) {
			//System.out.println("-in while first parent:"+current);
			current = parent;
			parent = parent.getParentStatement();
		}
		//System.out.println("-na while first parent:"+current);
		return current;
	}
}
