package server;

public interface Searcher<Solution> {
	public Solution search(Searchable s);
	public int getNumberOfNodesEvaluated();
}
