package hillbillies.expressions;

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

	private Task task;

	public void setTask(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return this.task;
	}

	public boolean isAssigned() {
		return (getTask() != null);
	}

	@Override
	public Expression<?> evaluate() throws IllegalArgumentException {//TODO andere exceoption kiezen wnt gn argument

		if (isAssigned()) {
			Expression<?> expression = ;
			expression.setVariableName(this.getVariablename());
			
			return expression;
		} else {
			throw new IllegalArgumentException("There is no task assigned to this variable.");
		}
	}

}
