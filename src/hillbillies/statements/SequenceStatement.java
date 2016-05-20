package hillbillies.statements;

import java.util.List;
import hillbillies.part3.programs.SourceLocation;

public class SequenceStatement extends Statement {

	private List<Statement> statements;

	public SequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
//		System.out.println("------------sequencestatement---------");
		for (Statement statement : statements) {
			System.out.println("statement in seq: "+statement);
			statement.setParentStatement(this);
//			System.out.println("kind="+statement+" kind zn ouder="+statement.getParentStatement()+ "kind eerset ouder"+statement.getFirstParentStatement());
		}
//		System.out.println("------------/sequencestatement---------");
		
	}

	public List<Statement> getStatements() {
		return this.statements;
	}

	public Statement getNextUnexecutedStatement() {

		for (Statement statement : getStatements()) {
			if (!statement.isExecuted()) {
				return statement;
			}
		}

		return null;

	}

	public boolean allStatementsExecuted() {
		return (getNextUnexecutedStatement() == null) ? true : false;
	}

	@Override
	public void execute() {
//		System.out.println("execute sequencest");
		if (allStatementsExecuted()) {
			setIsExecuted();
		} else {
			//getNextUnexecutedStatement().setParentStatement(this);
			getNextUnexecutedStatement().execute();
		}

	}

}
