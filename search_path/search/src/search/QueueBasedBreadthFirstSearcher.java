package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

  /**
   * QueueBasedBreadthFirstSearcher.
   * @param searchProblem : search problem
   */
  public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
	Queue<T> queue = new LinkedList<T>();
	List<T> pred = new ArrayList<T>();
	List<T> visited = new ArrayList<T>();
	T initialState = searchProblem.getInitialState();
	pred.add(initialState); 
	visited.add(initialState);
	T curr;
	queue.add(initialState);
	while (!(queue.isEmpty())) {
		curr = queue.remove();
		pred.add(curr);
		if (searchProblem.isGoal(curr)) {
			List<T> solutionSet = new ArrayList<T>();
			for (int i = pred.size()-1; i >- 1; i--) {
				solutionSet.add(pred.get(i));
			}
			return solutionSet;
		}
		for(T state : searchProblem.getSuccessors(curr)) {
			if (!(visited.contains(state))) {
				visited.add(state);
				if (searchProblem.isGoal(state)) {
					pred.add(curr);
					List<T> solutionSet = new ArrayList<T>();
					for (int i = pred.size()-1; i >- 1; i--) {
						solutionSet.add(pred.get(i));
					}
					return solutionSet;
				} else {
					queue.add(state);
				}
			}
		}
		
	}
    return null;
  }
}
