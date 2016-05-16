package hillbillies.statements;

import java.util.List;

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

	@Override
	public void execute() {
		for (Statement statement : getStatements()) {
			statement.execute();
		}

	}

}
