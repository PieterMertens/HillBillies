package hillbillies.statements;

import hillbillies.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

public class PrintStatement extends Statement {

	private Expression<?> value;

	public PrintStatement(Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
		value.setStatement(this);
	}

	public Expression<?> getValue() {
		return this.value;
	}

	@Override
	public void execute() {
		System.out.println(getValue().evaluate());
		setIsExecuted();

	}
}
