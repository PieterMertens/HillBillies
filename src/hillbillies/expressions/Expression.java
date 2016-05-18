package hillbillies.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.statements.Statement;

public abstract class Expression<T> {

	public Expression(SourceLocation sourceLocation) {
		this.setSourceLocation(sourceLocation);

	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;

	}

	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}

	private SourceLocation sourceLocation;

	public abstract T evaluate();

	Map<String, Expression<?>> expressionsWithVariableName = new HashMap<>();
	private Statement statement;

	public Map<String, Expression<?>> getVariableMap() {
		return this.expressionsWithVariableName;
	}

	public void addVariable(String variableName, Expression<?> value) {

		getVariableMap().put(variableName, value);

	}

	public Expression<?> getVariable(String variableName) {
		if (isVariable(variableName)) {
			return getVariableMap().get(variableName);
		} else {
			throw new NoSuchElementException("There is no variable with this name.");
		}
	}

	public boolean isVariable(String variableName) throws NoSuchElementException{
		Map<String, Expression<?>> map = getVariableMap();
		if (map.isEmpty()) {
			return false;
		} else if (getVariable(variableName) != null) {
			return true;
		}
		return false;
	}
	
	public void setStatement(Statement statement){
		this.statement = statement;
	}
	
	public Statement getStatement(){
		return this.statement;
	}
	
	public Unit getUnit(){
		return getStatement().getUnit();
	}
	
	public World getWorld(){
		return getUnit().getWorld();
	}

}
