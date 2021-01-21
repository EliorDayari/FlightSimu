package server;

public interface Solver<Problem,Solution> {
	public Solution Solve(Problem p);
}
