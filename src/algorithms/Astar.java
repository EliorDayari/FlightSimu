

package algorithms;

import java.util.ArrayList;
import java.util.HashSet;

public class Astar<Solution, heuristic> extends CommonSearcher<Solution>
{
	Heuristic h;

	public Astar(final Heuristic h) {
		this.h = h;
	}

	@Override
	public Solution search(final Searchable s) {
		this.openList.add(s.getInitialState());
		final HashSet<State> closedSet = new HashSet<State>();
		while (this.openList.size() > 0) {
			final State n = this.popOpenList();
			closedSet.add(n);
			final ArrayList<State> successors = s.getAllPossibleStates(n);
			n.setCost(n.getCost() + this.h.cost(n, s.getGoalState()));
			if (n.equals(s.getGoalState())) {
				return this.backTrace(n, s.getInitialState());
			}
			for (final State state : successors) {
				state.setCost(state.getCost() + this.h.cost(state, s.getGoalState()));
				if (!closedSet.contains(state) && !this.openList.contains(state)) {
					state.setCameFrom(n);
					this.openList.add(state);
				}
				else {
					if (n.getCost() + (state.getCost() - state.getCameFrom().getCost()) >= state.getCost()) {
						continue;
					}
					if (this.openList.contains(state)) {
						state.setCameFrom(n);
					}
					else {
						state.setCameFrom(n);
						closedSet.remove(state);
						this.openList.add(state);
					}
				}
			}
		}
		return this.backTrace(s.getGoalState(), s.getInitialState());
	}

	@Override
	public int getNumberOfNodesEvaluated() {
		return this.evluateNodes;
	}

	public interface Heuristic
	{
		double cost(final State source, final State p1);

	}
}
