package server;

import java.util.Comparator;
import Algorithms.StateComparator;
import Algorithms.State;
import java.util.PriorityQueue;
import Algorithms.Searcher;

public abstract class CommonSearcher<Solution> implements Searcher<Solution>
{
	protected PriorityQueue<State> openList;
	protected int evluateNodes;

	public CommonSearcher() {
		final Comparator<State> comp = new StateComparator();
		this.openList = new PriorityQueue<State>(comp);
		this.evluateNodes = 0;
	}

	protected State popOpenList() {
		++this.evluateNodes;
		return this.openList.poll();
	}

	protected Solution backTrace(final State goalState, final State initialState) {
		if (goalState.equals(initialState)) {
			return (Solution) initialState.getState();
		}
		return (Solution)(this.backTrace(goalState.getCameFrom(), initialState) + "->" + goalState.getState());
	}
}
