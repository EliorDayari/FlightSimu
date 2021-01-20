
package Algorithms;

public class State<T>
{
	protected T state;
	private double cost;
	private State cameFrom;

	public State(final T state) {
		this.state = state;
	}

	public State() {
		this.state = null;
		this.cost = 0.0;
		this.cameFrom = null;
	}

	public boolean equals(final State s) {
		return this.state.equals(s.getState());
	}

	public T getState() {
		return this.state;
	}

	public void setState(final T state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "State [state=" + this.state + ", cost=" + this.cost + ", cameFrom=" + this.cameFrom + "]";
	}

	public double getCost() {
		if (this.getCameFrom() != null) {
			return this.getCameFrom().getCost() + this.cost;
		}
		return this.cost;
	}

	public void setCost(final double cost) {
		this.cost = cost;
	}

	public State getCameFrom() {
		return this.cameFrom;
	}

	public void setCameFrom(final State cameFrom) {
		this.cameFrom = cameFrom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = 31 * result + ((this.state == null) ? 0 : this.state.hashCode());
		return result;
	}
}
