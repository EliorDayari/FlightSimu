package interpreter;

import java.util.Observer;
import java.util.Observable;

public class CustomVar extends Observable implements Observer
{
	double value;
	String name;
	String location;

	@Override
	public void update(final Observable o, final Object arg) {
		final Double d = new Double(0.0);
		if (arg.getClass() == d.getClass() && this.value != (double)arg) {
			this.setV((double)arg);
			this.setChanged();
			this.notifyObservers(arg + "");
		}
	}

	@Override
	public String toString() {
		return this.location;
	}

	public CustomVar(final double v) {
		this.value = v;
		this.location = null;
	}

	public CustomVar() {
	}

	public CustomVar(final String loc) {
		this.location = loc;
	}

	public double getV() {
		return this.value;
	}

	public void setV(final double v) {
		if (this.value != v) {
			this.value = v;
			this.setChanged();
			this.notifyObservers(v);
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String loc) {
		this.location = loc;
	}
}
