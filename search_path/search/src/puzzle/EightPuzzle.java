package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * The spaces in an 8-puzzle are indexed as follows:
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

  /**
   * Creates a new instance of the 8 puzzle with the given starting values.
   * The values are indexed as described above, and should contain exactly the
   * nine integers from 0 to 8.
   * 
   * @param startingValues
   *            the starting values, 0 -- 8
   * @throws IllegalArgumentException
   *             if startingValues is invalid
   */
	List<Integer> values;
  public EightPuzzle(List<Integer> startingValues) {
	  if (!(startingValues.size() == 9)) {
		  throw new IllegalArgumentException();
	  }
	  for (int i = 0; i < 9 ; i++) {
		  if (startingValues.contains(i)) {
			  continue;
		  } else  {
			  throw new IllegalArgumentException();
		  }
	  }
	  values = startingValues;
  }
  
  public List<Integer> swap(List<Integer> original, int int1, int int2) {
	  List<Integer> returnList = new ArrayList<Integer>();
	  if (int2 > int1) {
		  int count = 0;
		  while (count < int1) {
			  returnList.add(original.get(count));
			  count++;
		  }
		  returnList.add(original.get(int2));
		  count++;
		  while (count < int2) {
			  returnList.add(original.get(count));
			  count++;
		  }
		  returnList.add(original.get(int1));
		  count++;
		  while (count < original.size()) {
			  returnList.add(original.get(count));
			  count++;
		  }
	  } else  {
		  // int2 > int1
		  int count = 0;
		  while (count < int2) {
			  returnList.add(original.get(count));
			  count++;
		  }
		  returnList.add(original.get(int1));
		  count++;
		  while (count < int1) {
			  returnList.add(original.get(count));
			  count++;
		  }
		  returnList.add(original.get(int2));
		  count++;
		  while (count < original.size()) {
			  returnList.add(original.get(count));
			  count++;
		  } 
	  }
	  return returnList;
  }

  @Override
  public List<Integer> getInitialState() {
    // TODO
    return values;
  }

  @Override
  public List<List<Integer>> getSuccessors(List<Integer> currentState) {
    // TODO
	List<List<Integer>> successors = new ArrayList<List<Integer>>();
	List<Integer> cur = currentState;
	int blankIndex = cur.indexOf(0);
	if (blankIndex > 3) {
		successors.add(swap(cur, blankIndex, blankIndex - 3));
	}
	if (blankIndex < 6) {
		successors.add(swap(cur, blankIndex, blankIndex + 3));
	}
	if (blankIndex % 3 != 2) {
		successors.add(swap(cur, blankIndex, blankIndex + 1));
	}
	if (blankIndex % 3 != 0) {
		successors.add(swap(cur, blankIndex, blankIndex - 1));
	}
    return successors;
  }


  @Override
  public boolean isGoal(List<Integer> state) {
    // TODO
	List<Integer> goalState = new ArrayList<Integer>();
	for (int i = 1; i < 9; i++) {
		goalState.add(i);
	}
	goalState.add(0);
    return (state.equals(goalState));
  }

  /**
   * supporting man method.
   */
  public static void main(String[] args) {
    EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
        4, 0, 6, 7, 5, 8 }));

    List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
    for (List<Integer> l : r) {
      System.out.println(l);
    }
  }
}
