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
	private Unit unit;
	private Task task;
	
	public void setTask(Task task){
		this.task = task;
	}
	
	public Task getTask(){
		return this.task;
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

}
