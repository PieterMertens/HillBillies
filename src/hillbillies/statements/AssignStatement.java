package hillbillies.statements;

import hillbillies.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

public class AssignStatement extends Statement{
	
	
	private String variableName;
	private Expression<?> value;

	public AssignStatement(String variableName, Expression<?> value,SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
		value.setStatement(this);
	}
	
	public String getVariableName(){
		return this.variableName;
	}
	
	public Expression<?> getValue(){
		return this.value;
	}

	@Override
	public void execute() {

		getValue().addVariable(variableName, value);
		setIsExecuted();
		
	}

}
