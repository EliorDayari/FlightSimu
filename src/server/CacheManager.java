package server;

public interface CacheManager<Problem,Solution> {
	public Boolean check(Problem in);
	public Solution extract(Problem in);
	public void save(Problem in, Solution out);
}
