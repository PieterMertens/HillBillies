package hillbillies.expressions;

import hillbillies.part3.programs.SourceLocation;

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

	private String variableName;

	public String getVariableName() {
		return this.variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;

	}

}
