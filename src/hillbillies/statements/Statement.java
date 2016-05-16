package hillbillies.statements;

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

	public void setUnit(Unit unit) {// TODO unit moet mss niet in hoofdklasse vn
									// statement...
		this.unit = unit;
	}

	public Unit getUnit() {
		return this.unit;
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
