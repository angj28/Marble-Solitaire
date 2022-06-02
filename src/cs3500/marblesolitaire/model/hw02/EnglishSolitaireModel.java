package cs3500.marblesolitaire.model.hw02;

/**
 * The {@code EnglishSolitaireModel} class represents the state of
 * an English Solitaire model of a Marble Solitaire game.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  /**
   * represents the game board as a 2D array where each position on the board has a corresponding
   * row and column location.
   */
  private SlotState[][] board;

  /**
   * represents the longest length of the English Solitaire game board, which is the same as its
   * width.
   */
  private int width;

  /**
   * initializes the game board with an arm thickness of 3 and the empty slot at the center.
   */
  public EnglishSolitaireModel() {
    generateBoard(3, 3, 3);
  }

  /**
   * initializes the game board with an arm thickness of 3 and the empty slot at the given
   * row and column.
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   * @throws IllegalArgumentException if the given empty slot position is invalid or out of the
   *                                  bounds of the game board
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    generateBoard(3, sRow, sCol);
  }

  /**
   * initializes the game board with a given arm thickness and the empty slot in the middle.
   *
   * @param armThickness the arm thickness of the game board.
   * @throws IllegalArgumentException if the arm thickness is <= 1
   *                                  OR is not odd
   *                                  OR if the empty slot position is invalid.
   *                                  OR if the empty slot position is out of the bounds
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness <= 1) {
      throw new IllegalArgumentException("Arm thickness must be odd and > 1");
    }
    generateBoard(armThickness, (armThickness + (armThickness / 2)) - 1,
            (armThickness + (armThickness / 2)) - 1);
  }

  /**
   * initializes game board with given arm length and the empty slot in the given row and column.
   *
   * @param armThickness the arm thickness of the game board.
   * @param sRow         the row of the empty slot.
   * @param sCol         the column of the empty slot.
   * @throws IllegalArgumentException if the arm thickness is <= 1
   *                                  OR is not odd
   *                                  OR if the empty slot position is invalid.
   *                                  OR if the empty slot position is out of the bounds
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness <= 1) {
      throw new IllegalArgumentException("Arm thickness must be odd and > 1");
    }
    generateBoard(armThickness, sRow, sCol);
  }

  /**
   * initializes the game board based on a given arm thickness and places the empty slot at the
   * given row and column.
   *
   * @param armThickness the arm thickness of the game board.
   * @param sRow         the row of the empty slot.
   * @param sCol         the column of the empty slot.
   */
  private void generateBoard(int armThickness, int sRow, int sCol) {
    this.width = ((armThickness - 1) * 3) + 1;
    board = new SlotState[this.width][this.width];

    //throw illegal argument exception if given positions are out of bounds
    if (sRow < 0 || sRow > this.width || sCol < 0 || sCol > this.width) {
      throw new IllegalArgumentException("Empty slot position is out of the bounds of the game.");
    }

    //vertical line
    for (int row = 0; row < this.width; row++) {
      for (int col = armThickness - 1; col < this.width - (armThickness - 1); col++) {
        this.board[row][col] = SlotState.Marble;
      }
    }
    //horizontal line not repeating vertical line
    for (int col = 0; col < this.width; col++) {
      for (int row = armThickness - 1; row < this.width - (armThickness - 1); row++) {
        this.board[row][col] = SlotState.Marble;
      }
    }
    //rest is invalid
    for (int row = 0; row < this.width; row++) {
      for (int col = 0; col < this.width; col++) {
        if (board[row][col] == null) {
          board[row][col] = SlotState.Invalid;
        }
      }
    }
    //place the empty slot
    if (board[sRow][sCol] != SlotState.Invalid) {
      board[sRow][sCol] = SlotState.Empty;
    } else {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + sRow + ", " + sCol + ")");
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (fromRow < 0 || fromRow >= this.width
            || fromCol < 0 || fromCol >= this.width
            || toRow < 0 || toRow >= this.width
            || toCol < 0 || toCol >= this.width) {
      throw new IllegalArgumentException("One or more of the given positions is out of the bounds"
              + " of the game board.");
    }
    //from position has a marble and to position is empty
    if (this.board[fromRow][fromCol] == SlotState.Marble) {
      //to position is 2 positions up, down, left, or right of from position
      //there is a marble in between the from position and to position
      if (this.validMove(fromRow, fromCol, toRow, toCol)) {
        //from position becomes empty
        this.board[fromRow][fromCol] = SlotState.Empty;
        //to position becomes a marble
        this.board[toRow][toCol] = SlotState.Marble;
        //position in between becomes empty
        this.emptyMiddle(fromRow, fromCol, toRow, toCol);
      } else {
        //throw exception if not a valid move
        throw new IllegalArgumentException("This move cannot be made.");
      }
    } else {
      //throw exception if no marble in the from position
      throw new IllegalArgumentException("This move cannot be made.");
    }
  }

  /**
   * updates the slot state in between the from and to positions of a move to empty.
   *
   * @param fromRow the row number of position to be moved from (starting at 0)
   * @param fromCol the col number of the position to be moved from (starting at 0)
   * @param toRow   the row number of the position to be moved to (starting at 0)
   * @param toCol   the col number of the position to be moved to (starting at 0)
   */
  private void emptyMiddle(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow - fromRow == 2) {
      //remove marble to the right
      this.board[fromRow + 1][fromCol] = SlotState.Empty;
    } else if (toRow - fromRow == -2) {
      //remove marble to the left
      this.board[fromRow - 1][fromCol] = SlotState.Empty;
    } else if (toCol - fromCol == 2) {
      //remove marble on the top
      this.board[fromRow][fromCol + 1] = SlotState.Empty;
    } else {
      //remove marble on the bottom
      this.board[fromRow][fromCol - 1] = SlotState.Empty;
    }
  }

  @Override
  public boolean isGameOver() {
    //game is over if there is only one marble left on the board
    if (this.getScore() <= 1) {
      return true;
    }
    //check all marbles for valid moves
    //game is not over if at least one marble has a valid move
    for (int row = 0; row < this.board.length; row++) {
      for (int col = 0; col < this.board.length; col++) {
        if (this.board[row][col] == SlotState.Marble) {
          if (this.canMove(row, col)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * determines if the given position can move.
   *
   * @param sRow the number of the row position
   * @param sCol the number of the column position
   * @return true if the given position has a valid position to move to, false if not
   */
  private boolean canMove(int sRow, int sCol) {
    return (sRow < (this.width - 2) && validMove(sRow, sCol, sRow + 2, sCol))
            || (sRow > 1 && validMove(sRow, sCol, sRow - 2, sCol))
            || (sCol < (this.width - 2) && validMove(sRow, sCol, sRow, sCol + 2))
            || (sCol > 1 && validMove(sRow, sCol, sRow, sCol - 2));
  }

  /**
   * determines if the move from one position to another is valid.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the col number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the to number of the position to be moved to (starts at 0)
   * @return true if the position to be moved from is two away from the to position and there is a
   *         marble in between the two
   */
  private boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    //throw exception if given positions are out of bounds
    if (toRow < 0 || toRow >= this.width || toCol < 0 || toCol >= this.width) {
      throw new IllegalArgumentException("Slot position is out of the bounds of the game board.");
    }
    //invalid move if the to position is not empty
    if (this.board[toRow][toCol] != SlotState.Empty) {
      return false;
    }
    // move down
    if (toRow - fromRow == 2 && toCol - fromCol == 0 && fromRow + 1 < this.width) {
      return this.board[fromRow + 1][fromCol] == SlotState.Marble;
      //move up
    } else if (toRow - fromRow == -2 && toCol - fromCol == 0) {
      return this.board[fromRow - 1][fromCol] == SlotState.Marble;
      //move right
    } else if (toRow - fromRow == 0 && toCol - fromCol == 2 && fromCol + 1 < this.width) {
      return this.board[fromRow][fromCol + 1] == SlotState.Marble;
      //move left
    } else if (toRow - fromRow == 0 && toCol - fromCol == -2) {
      return this.board[fromRow][fromCol - 1] == SlotState.Marble;
    } else {
      return false;
    }
  }

  @Override
  public int getBoardSize() {
    return this.width;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row >= this.width || col >= this.width) {
      throw new IllegalArgumentException("Slot position is beyond the dimension of the game.");
    } else {
      return this.board[row][col];
    }
  }

  @Override
  public int getScore() {
    int count = 0;
    for (int col = 0; col < this.width; col++) {
      for (int row = 0; row < this.width; row++) {
        if (this.board[row][col] == SlotState.Marble) {
          count++;
        }
      }
    }
    return count;
  }
}
