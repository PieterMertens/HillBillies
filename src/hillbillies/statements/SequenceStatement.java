package hillbillies.statements;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.model.Task;
import hillbillies.part3.programs.SourceLocation;

public class SequenceStatement extends Statement {

	private List<Statement> statements;

	public SequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;

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
		System.out.println("execute sequencest");
		if (allStatementsExecuted()) {
			setIsExecuted();
		} else {
			getNextUnexecutedStatement().execute();
		}

	}

}
