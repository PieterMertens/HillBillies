package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

public class IfStatement extends Statement {

	private BooleanExpression condition;
	private Statement ifBody;
	private Statement elseBody;

	public IfStatement(BooleanExpression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
		// TODO Auto-generated constructor stub
	}

	public BooleanExpression getCondition() {
		return this.condition;

	}

	public Statement getIfBody() {
		return this.ifBody;
	}

	public Statement getElseBody() {
		return this.elseBody;
	}

	@Override
	public void execute() {

		if (getCondition().evaluate()) {
			getIfBody().execute();
		} else {
			getElseBody().execute();
		}

	}
}
