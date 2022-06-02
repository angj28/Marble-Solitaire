package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the state of a {@code MarbleSolitaireModel} in a text view.
 * Uses the information from an {@code MarbleSolitaireModelState} object.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  /**
   * the {@code MarbleSolitaireModelState} game to be represented.
   */
  private final MarbleSolitaireModelState game;

  /**
   * initializes the marble solitaire game to be displayed.
   *
   * @param game an object of the {@code MarbleSolitaireModelState} class that represents the state
   *             of the game.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("A Marble Solitaire game must be given.");
    }
    this.game = game;
  }

  @Override
  public String toString() {
    int size = this.game.getBoardSize();
    int armLength = ((size - 1) / 3) + 1;
    String textView = "";
    //top half
    textView += this.toStringHelp(0,size - ((armLength * 2) - 1),
            0, (armLength * 2) - 1,(armLength * 2) - 1);
    //middle row
    textView += this.toStringHelp(armLength - 1,size - (armLength - 1),
            0, size, size);
    //bottom half
    textView += this.toStringHelp(size - (armLength - 1), size - 1, 0,
            (armLength * 2) - 1, (armLength * 2) - 1);

    //last line
    for (int col = 0; col < size - (armLength - 1); col++) {
      if (col != (size - (armLength - 1)) - 1) {
        textView += this.stringSlot(size - 1, col) + " ";
      } else {
        textView += this.stringSlot(size - 1, col);
      }
    }
    return textView;
  }

  /**
   * creates a String of with the given states as a String for a given section of the board.
   * @param rowStart the index of the starting row of desired positions
   * @param rowEnd the index of the ending row of the desired positions
   * @param colStart the index of the starting column of the desired positions
   * @param colEnd the index of the ending column of the desired positions
   * @param size the width of the game board, also acts like a limit to stop adding spaces
   * @return the state of the positions within the given range as a String
   */
  private String toStringHelp(int rowStart, int rowEnd, int colStart,
                              int colEnd, int size) {
    String text = "";
    for (int row = rowStart; row < rowEnd; row++) {
      for (int col = colStart; col < colEnd; col++) {
        if (col != size - 1) {
          text += this.stringSlot(row, col) + " ";
        } else {
          text += this.stringSlot(row, col);
        }
      }
      text += "\n";
    }
    return text;
  }

  /**
   * represents the slot state of the given position as a string.
   *
   * @param sRow the number of the row position
   * @param sCol the number of the col position
   * @return string representation of the slot state of the given position
   */
  private String stringSlot(int sRow, int sCol) {
    // "O" if there is a marble at the position
    if (this.game.getSlotAt(sRow, sCol) == MarbleSolitaireModelState.SlotState.Marble) {
      return "O";
      //" " if it is an invalid position
    } else if (this.game.getSlotAt(sRow, sCol) == MarbleSolitaireModelState.SlotState.Invalid) {
      return " ";
      //"_" if it is an empty valid position
    } else if (this.game.getSlotAt(sRow, sCol) == MarbleSolitaireModelState.SlotState.Empty) {
      return "_";
    } else {
      throw new IllegalArgumentException("Invalid Position.");
    }
  }
}
