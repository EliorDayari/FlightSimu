

package algorithms;

import java.util.ArrayList;
import java.util.HashSet;

public class Astar<Solution, heuristic> extends CommonSearcher<Solution>
{
	Heuristic heuristic;

	public Astar(final Heuristic heuristic) {
		this.heuristic = heuristic;
	}

	@Override
	public Solution search(final Searchable s) {
		this.openPriorityQueue.add(s.getInitialState());
		final HashSet<State> closedSet = new HashSet<State>();
		while (this.openPriorityQueue.size() > 0) {
			final State n = this.popOpenList();
			closedSet.add(n);
			final ArrayList<State> successors = s.getAllPossibleStates(n);
			n.setCost(n.getCost() + this.heuristic.cost(n, s.getGoalState()));
			if (n.equals(s.getGoalState())) {
				return this.backTrace(n, s.getInitialState());
			}
			for (final State state : successors) {
				state.setCost(state.getCost() + this.heuristic.cost(state, s.getGoalState()));
				if (!closedSet.contains(state) && !this.openPriorityQueue.contains(state)) {
					state.setCameFrom(n);
					this.openPriorityQueue.add(state);
				}
				else {
					if (n.getCost() + (state.getCost() - state.getCameFrom().getCost()) >= state.getCost()) {
						continue;
					}
					if (this.openPriorityQueue.contains(state)) {
						state.setCameFrom(n);
					}
					else {
						state.setCameFrom(n);
						closedSet.remove(state);
						this.openPriorityQueue.add(state);
					}
				}
			}
		}
		return this.backTrace(s.getGoalState(), s.getInitialState());
	}

	@Override
	public int getNumberOfNodesEvaluated() {
		return this.finishedNodes;
	}

	public interface Heuristic
	{
		double cost(final State source, final State p1);

	}
}
