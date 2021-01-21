

package algorithms;

import java.util.ArrayList;
import java.util.HashSet;

public class BFS<Solution> extends CommonSearcher<Solution>
{
	@Override
	public Solution search(final Searchable s) {
		this.openPriorityQueue.add(s.getInitialState());
		final HashSet<State> closedSet = new HashSet<State>();
		while (this.openPriorityQueue.size() > 0) {
			final State node = this.popOpenList();
			closedSet.add(node);
			final ArrayList<State> successors = s.getAllPossibleStates(node);
			if (node.equals(s.getGoalState())) {
				return this.backTrace(node, s.getInitialState());
			}
			for (final State state : successors) {
				if (!closedSet.contains(state) && !this.openPriorityQueue.contains(state)) {
					state.setCameFrom(node);
					this.openPriorityQueue.add(state);
				}
				else {
					if (node.getCost() + (state.getCost() - state.getCameFrom().getCost()) >= state.getCost()) {
						continue;
					}
					if (this.openPriorityQueue.contains(state)) {
						state.setCameFrom(node);
					}
					else {
						state.setCameFrom(node);
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
}
