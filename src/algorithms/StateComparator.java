package algorithms;

import java.util.Comparator;

public class StateComparator implements Comparator<State>
{
	@Override
	public int compare(final State s1, final State s2) {
		if (s1.getCost() < s2.getCost()) {
			return -1;
		}
		if (s1.getCost() == s2.getCost()) {
			return 0;
		}
		return 1;
	}
}
