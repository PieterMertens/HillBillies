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
		condition.setStatement(this);
		System.out.println("ifst getstatment: condition"+condition+" stamtent"+condition.getStatement());
		if (hasIfBody()) {
			getIfBody().setParentStatement(this);
			//System.out.println("kind="+getIfBody()+" kind zn ouder="+getIfBody().getParentStatement()+" parent firstparent"+this.getFirstParentStatement());
		}
		if (hasElseBody()) {
			getElseBody().setParentStatement(this);
		}
		
		
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

	public boolean hasIfBody() {
		return (getIfBody() != null);
	}

	public boolean hasElseBody() {
		return (getElseBody() != null);
	}

	@Override
	public void execute() {

		if ((hasIfBody() && getIfBody().isExecuted()) || (hasElseBody() && getElseBody().isExecuted())) {
			setIsExecuted();
		} else {
			getCondition().setStatement(this);
	//		System.out.println("if unit"+getCondition().getStatement().getUnit());
//			get
			if (getCondition().evaluate()) {
				if (hasIfBody()) {
			
					getIfBody().execute();
				}
			} else {
				if (hasElseBody()) {
					getElseBody().execute();
				}
			}
		}

	}
}
