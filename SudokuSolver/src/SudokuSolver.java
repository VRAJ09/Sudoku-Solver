import java.util.stream.IntStream;

/**
 * This solves for the missing values in a Sudoku puzzle utilizing
 * a backtracking algorithm. The Sudoku puzzle is created as a
 * 2d array and 0 represents an empty cell. 
 * @author Vraj Patel
 *
 */

public class SudokuSolver {
	
	private int MAX_NUM = 9;
	private int MIN_NUM = 1;
	
	/**
	 * Boolean method which checks each cell for the number 0. Then
	 *  if a cell contains the value 0, it checks all numbers from
	 *  1 to 9 to see which numbers correctly fits in that location
	 *  according to the isValid and solve methods.  
	 * @param nums, 2d array (Sudoku puzzle)
	 * @return 
	 */
	public boolean solve(int[][] nums){
		for(int row = 0; row < nums.length; row++){
			 for(int col = 0; col < nums[row].length; col++){
				 if(nums[row][col] == 0) {
					 for(int check = MIN_NUM; check <= MAX_NUM; check++) {
						 nums[row][col] = check;
					 if(isValid(nums, row, col) && solve(nums)) {
						 return true;
						 
					 	}
					 nums[row][col] = 0;
					 }
					 return false;
				 }
			 }
		}
		return true;
	}
	
	/**
	 * isValid method checks to see if a digit between 1 to 9
	 * fits in its cell location by meeting its row, column, and
	 * 3x3 cell requirements.
	 * @param nums, 2d array (Sudoku puzzle)
	 * @param row, a row of the puzzle
	 * @param col, a column of the puzzle
	 * @return
	 */
	public boolean isValid(int [][] nums, int row, int col) {
		return(checkRow(nums, row) && checkCol(nums, col) && checkBoxes(nums, row, col));
	}
	/**
	 * checkRow method checks a row of the puzzle to 
	 * see if all cells in that row have 1 to 9 digits
	 * with no repetitions.
	 * @param nums, 2d array (Sudoku puzzle)
	 * @param row, a row of the puzzle
	 * @return
	 */
	public boolean checkRow(int[][] nums, int row) {
		boolean[] value = new boolean[nums.length];
		return IntStream.range(0, nums.length).allMatch(col -> checkConstraint(nums, row, value, col));
	
	}
	
	/**
	 * checkCol method checks a column of the puzzle to 
	 * see if all cells in that column have 1 to 9 digits
	 * with no repetitions.
	 * @param nums, 2d array (Sudoku puzzle)
	 * @param col, a column of the puzzle
	 * @return
	 */
	public boolean checkCol(int[][] nums, int col) {
		boolean[] value = new boolean[nums[col].length];
		return IntStream.range(0, nums[col].length).allMatch(row -> checkConstraint(nums, row, value, col));
	}
	
	/**
	 * checkBoxes method checks to see if each box of
	 * 3x3 cells has a number form 1 to 9 with no repetitions.
	 * @param nums, 2d array (Sudoku puzzle)
	 * @param row, a row of the puzzle
	 * @param col, a column of the puzzle
	 * @return
	 */
	public boolean checkBoxes(int[][] nums, int row, int col) {
		boolean[] value = new boolean[nums.length];
		int rBoxStart = (row/3) * 3;
		int rBoxEnd = rBoxStart + 3;
		
		int cBoxStart = (col/3) * 3;
		int cBoxEnd = cBoxStart + 3;
		
		for(int r = rBoxStart; r < rBoxEnd; r++){
			 for(int c = cBoxStart; c < cBoxEnd; c++){
				 if(!checkConstraint(nums, r, value, c)) {
					 return false;
				 }
			 }
			 
		}
		return true;
	}
	
	/**
	 * checkConstraint method checks to see if all the
	 * digits in the cells are correctly places according to
	 * the constraint of the digit having to be between
	 * 1 and 9.
	 * @param nums, 2d array (Sudoku puzzle)
	 * @param row, a row of the puzzle
	 * @param value, array of boolean values
	 * @param col, a column of the puzzle
	 * @return
	 */
	public boolean checkConstraint(int[][] nums, int row, boolean[] value, int col) {
		
		if(nums[row][col] != 0) {
			if(!value[nums[row][col] - 1]) {
				value[nums[row][col] - 1] = true;
			}else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * printResult method prints the completed Sudoku puzzle.
	 * @param nums, 2d array (Sudoku puzzle)
	 */
	public void printResult(int[][] nums) {
		
		boolean result = solve(nums);
		if(result == true) {
			for(int row = 0; row < nums.length; row++) {
				for(int col = 0; col < nums[row].length; col++) {
					System.out.print(nums[row][col] + " ");
				}
				System.out.println();
			}
		}
		
	}
	
}