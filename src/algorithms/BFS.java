

package algorithms;

import java.util.ArrayList;
import java.util.HashSet;

public class BFS<Solution> extends CommonSearcher<Solution>
{
	@Override
	public Solution search(final Searchable s) {
		this.openList.add(s.getInitialState());
		final HashSet<State> closedSet = new HashSet<State>();
		while (this.openList.size() > 0) {
			final State n = this.popOpenList();
			closedSet.add(n);
			final ArrayList<State> successors = s.getAllPossibleStates(n);
			if (n.equals(s.getGoalState())) {
				return this.backTrace(n, s.getInitialState());
			}
			for (final State state : successors) {
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
}
