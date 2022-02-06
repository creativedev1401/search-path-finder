package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

  /**
   * StackBasedDepthFirstSearcher.
   * @param searchProblem : search problem
   */
  public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
	Stack<T> stack = new Stack<T>();
	stack.push(searchProblem.getInitialState());
	List<T> visited = new ArrayList<T>();
	visited.add(searchProblem.getInitialState());
	while (!(stack.isEmpty())) {
		T curr = stack.pop();
		for (T state: searchProblem.getSuccessors(curr)) {
			if (searchProblem.isGoal(state)) {
				stack.push(state);
				List<T> solutionSet = new ArrayList<T>();
				while (!(stack.isEmpty())) {
					solutionSet.add(stack.pop());
				}
				solutionSet.add(searchProblem.getInitialState());
				return solutionSet;
			}
			if (!visited.contains(state)) {
				stack.push(state);
				visited.add(state);
			}
		}
	}
    return null;
  }
}
