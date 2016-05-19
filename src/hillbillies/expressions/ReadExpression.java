package hillbillies.expressions;

import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public class ReadExpression extends Expression<Expression<?>> {

	private String variableName;

	public ReadExpression(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}

	public String getVariablename() {
		return this.variableName;
	}

	@Override
	public Expression<?> evaluate() throws IllegalArgumentException {

		if (isVariable(variableName)) {
			return getVariable(variableName);
		} else {
			throw new NoSuchElementException("No variable found.");
		}

	}

}
