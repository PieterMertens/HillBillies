package hillbillies.statements;

import hillbillies.expressions.BooleanExpression;
import hillbillies.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

public class WhileStatement extends Statement {

	// TODO tests toevoegen voor valid condition, body enz?

	private BooleanExpression condition;
	private Statement body;

	public WhileStatement(BooleanExpression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
		body.setParentStatemen(this);
		condition.setStatement(this);
	}

	public BooleanExpression getCondition() {
		return this.condition;
	}

	public Statement getBody() {
		return this.body;
	}

	@Override
	public void execute() {
		
		System.out.println("execute while");

		if (getCondition().evaluate() && !isExecuted()) {
			

			if (getBody().isExecuted()) {
				setIsExecuted();
			} else {
				getBody().execute();
			}

		}

	}

}
