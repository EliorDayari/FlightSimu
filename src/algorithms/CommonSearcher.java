package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class CommonSearcher<Solution> implements Searcher<Solution>
{
	protected PriorityQueue<State> openPriorityQueue;
	protected int finishedNodes;

	public CommonSearcher() {
		final Comparator<State> comp = new StateComparator();
		this.openPriorityQueue = new PriorityQueue<State>(comp);
		this.finishedNodes = 0;
	}

	protected State popOpenList() {
		++this.finishedNodes;
		return this.openPriorityQueue.poll();
	}

	protected Solution backTrace(final State goalState, final State initialState) {
		if (goalState.equals(initialState)) {
			return (Solution) initialState.getState();
		}
		return (Solution)(this.backTrace(goalState.getCameFrom(), initialState) + "->" + goalState.getState());
	}
}
