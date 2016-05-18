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
	}

	public BooleanExpression getCondition() {
		return this.condition;
	}

	public Statement getBody() {
		return this.body;
	}	

	@Override
	public void execute() {
		
		if(getCondition().evaluate()&&!isExecuted()){
			//TODO juist maken
			getBody().execute();
			
			
		}

	}

}
