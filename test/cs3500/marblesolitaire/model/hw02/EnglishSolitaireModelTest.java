package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test client uses of {@code EnglishSolitaireModel} class.
 */
public class EnglishSolitaireModelTest {
  //valid examples
  private EnglishSolitaireModel g1;
  private EnglishSolitaireModel g2;
  private EnglishSolitaireModel g3;
  private EnglishSolitaireModel g4;
  private EnglishSolitaireModel g5;
  private EnglishSolitaireModel g6;
  private EnglishSolitaireModel g7;
  private EnglishSolitaireModel g8;

  @Before
  public void init() {
    this.g1 = new EnglishSolitaireModel();
    this.g2 = new EnglishSolitaireModel(3, 3);
    this.g3 = new EnglishSolitaireModel(3);
    this.g4 = new EnglishSolitaireModel(3, 3, 3);
    this.g5 = new EnglishSolitaireModel(2, 3);
    this.g6 = new EnglishSolitaireModel(5);
    this.g7 = new EnglishSolitaireModel(5, 4, 3);
    this.g8 = new EnglishSolitaireModel(9);
  }

  @Test
  //tests valid constructions through text view
  public void testValidEnglishSolitaireModels() {
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(this.g1);
    assertEquals(expected, v1.toString());
    v1 = new MarbleSolitaireTextView(this.g2);
    assertEquals(expected, v1.toString());
    v1 = new MarbleSolitaireTextView(this.g3);
    assertEquals(expected, v1.toString());
    v1 = new MarbleSolitaireTextView(this.g4);
    assertEquals(expected, v1.toString());

    expected = "    O O O\n"
            + "    O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    v1 = new MarbleSolitaireTextView(this.g5);
    assertEquals(expected, v1.toString());

    expected = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";
    v1 = new MarbleSolitaireTextView(this.g6);
    assertEquals(expected, v1.toString());
  }

  @Test
  public void testInvalidEnglishSolitaireModels() {
    try {
      new EnglishSolitaireModel(0, 0);
      fail("Constructor did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }

    try {
      new EnglishSolitaireModel(0, 7);
      fail("Constructor did not throw an Array Index Out Of Bounds Exception, when it should have");
    } catch (ArrayIndexOutOfBoundsException e) {
      return;
    }

    try {
      new EnglishSolitaireModel(1);
      fail("Constructor did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }

    try {
      new EnglishSolitaireModel(2);
      fail("Constructor did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }

    try {
      new EnglishSolitaireModel(6, 3, 3);
      fail("Constructor did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }

    try {
      new EnglishSolitaireModel(3, 0, 0);
      fail("Constructor did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }

    try {
      new EnglishSolitaireModel(3, 10, 2);
      fail("Constructor did not throw an Array Index Out Of Bounds Exception, when it should have");
    } catch (ArrayIndexOutOfBoundsException e) {
      return;
    }
  }

  @Test
  public void testInvalidMove() {
    try {
      this.g1.move(-1, 2, 3, 3);
      fail("Method did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }
    try {
      this.g1.move(0, 0, 3, 3);
      fail("Method did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }
    try {
      this.g1.move(5, 3, 4, 3);
      fail("Method did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }
    try {
      this.g1.move(7, 7, 3, 3);
      fail("Method did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }
    try {
      this.g1.move(7, 7, 7, 7);
      fail("Method did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }
    //moves one marble so that:
    // - there is a move where a marble skips over an empty slot to another empty slot
    // - there is a move where a marble jumps over another marble to an empty spot diagonally
    this.g1.move(1, 3, 3, 3);
    this.testStates(1, 3, 3, 3, 1, "vertical", this.g1,
            MarbleSolitaireModelState.SlotState.Empty, MarbleSolitaireModelState.SlotState.Marble);
    try {
      //try jumping over empty slot
      this.g1.move(0, 3, 2, 3);
      fail("Method did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }
    try {
      //try valid move but diagonal
      this.g1.move(4, 5, 2, 3);
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void testValidMove() {
    //make sure empty slot at the center
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.g1.getSlotAt(3, 3));
    //before move - checks if from and in between position are marbles and to position is empty
    this.testStates(5, 3, 3, 3, -1, "vertical", this.g1,
            MarbleSolitaireModelState.SlotState.Marble, MarbleSolitaireModelState.SlotState.Empty);

    this.g1.move(5, 3, 3, 3);

    //after move - checks if from and in between position are empty and to position is a marble
    this.testStates(5, 3, 3, 3, -1, "vertical", this.g1,
            MarbleSolitaireModelState.SlotState.Empty, MarbleSolitaireModelState.SlotState.Marble);

    this.g1.move(2, 3, 4, 3);
    this.testStates(2, 3, 4, 3, 1, "vertical", this.g1,
            MarbleSolitaireModelState.SlotState.Empty, MarbleSolitaireModelState.SlotState.Marble);

    //before move - checks if from and in between position have marbles and to position is empty
    this.testStates(3, 1, 3, 3, 1, "horizontal", this.g2,
            MarbleSolitaireModelState.SlotState.Marble, MarbleSolitaireModelState.SlotState.Empty);

    this.g2.move(3, 1, 3, 3);

    //after move - checks if from and in between position are empty and to position is a marble
    this.testStates(3, 1, 3, 3, 1, "horizontal", this.g2,
            MarbleSolitaireModelState.SlotState.Empty, MarbleSolitaireModelState.SlotState.Marble);
  }

  /**
   * helper method to test moves.
   *
   * @param fr          the from row position
   * @param fc          the from col position
   * @param tr          the to row position
   * @param tc          the to col position
   * @param change      +1 if moving to a more positive position, -1 if moving to a more
   *                    negative one
   * @param orientation the direction of the move
   * @param game        the game being played on
   * @param state1      the state of the from position and the position in between the from position
   *                    and the to position
   * @param state2      the state of the to position
   */
  private void testStates(int fr, int fc, int tr, int tc, int change, String orientation,
                          EnglishSolitaireModel game,
                          MarbleSolitaireModelState.SlotState state1,
                          MarbleSolitaireModelState.SlotState state2) {
    assertEquals(state1, game.getSlotAt(fr, fc));
    if (orientation.equals("vertical")) {
      assertEquals(state1, game.getSlotAt(fr + change, fc));
    } else {
      assertEquals(state1, game.getSlotAt(fr, fc + change));
    }
    assertEquals(state2, game.getSlotAt(tr, tc));
  }

  @Test
  public void testIsGameOver() {
    assertEquals(false, this.g1.isGameOver());
    this.g1.move(5, 3, 3, 3);
    this.g1.move(4, 5, 4, 3);
    this.g1.move(3, 3, 5, 3);
    this.g1.move(1, 3, 3, 3);
    this.g1.move(2, 5, 4, 5);
    this.g1.move(4, 6, 4, 4);
    this.g1.move(2, 1, 2, 3);
    this.g1.move(4, 1, 4, 3);
    this.g1.move(2, 4, 2, 2);
    this.g1.move(4, 4, 4, 2);
    this.g1.move(3, 3, 3, 5);
    this.g1.move(3, 6, 3, 4);
    assertEquals(false, this.g1.isGameOver());
    this.g1.move(6, 4, 4, 4);
    this.g1.move(4, 4, 2, 4);
    this.g1.move(3, 1, 3, 3);
    this.g1.move(1, 2, 3, 2);
    this.g1.move(1, 4, 3, 4);
    this.g1.move(4, 2, 2, 2);
    this.g1.move(5, 2, 5, 4);
    this.g1.move(6, 2, 6, 4);
    this.g1.move(3, 4, 3, 2);
    this.g1.move(2, 2, 4, 2);
    assertEquals(false, this.g1.isGameOver());
    this.g1.move(6, 4, 4, 4);
    assertEquals(true, this.g1.isGameOver());

    assertEquals(true, this.g1.isGameOver());
    assertEquals(false, this.g6.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    //tests if different constructions of the same size return the same size
    assertEquals(7, this.g1.getBoardSize());
    assertEquals(7, this.g2.getBoardSize());
    assertEquals(7, this.g3.getBoardSize());
    assertEquals(7, this.g4.getBoardSize());
    assertEquals(7, this.g5.getBoardSize());
    assertEquals(13, this.g6.getBoardSize());
    assertEquals(13, this.g7.getBoardSize());
    assertEquals(25, this.g8.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    // test horizontal marble positions
    this.testMarble(2, 5, 0, 7, 3);

    //test vertical marble positions
    this.testMarble(0, 7, 2, 5, 3);

    //test invalid positions
    this.testInvalid(0, 2, 0, 2);
    this.testInvalid(5, 7, 0, 2);
    this.testInvalid(5, 7, 0, 2);
    this.testInvalid(5, 7, 5, 7);

    //test empty slot position
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.g1.getSlotAt(3, 3));
  }

  /**
   * tests if a position has the correct marble state.
   *
   * @param row1   the number of the first row to be checked
   * @param row2   the number of the last row to be checked
   * @param col1   the number of the first col to be checked
   * @param col2   the number of the last col to be checked
   * @param center the number of the center position
   */
  private void testMarble(int row1, int row2, int col1, int col2, int center) {
    for (int row = row1; row < row2; row++) {
      for (int col = col1; col < col2; col++) {
        if (row != center && col != center) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                  this.g1.getSlotAt(row, col));
        }
      }
    }
  }

  /**
   * tests if a position has the correct invalid state.
   *
   * @param row1 the number of the first row to be checked
   * @param row2 the number of the last row to be checked
   * @param col1 the number of the first col to be checked
   * @param col2 the number of the last col to be checked
   */
  private void testInvalid(int row1, int row2, int col1, int col2) {
    for (int row = row1; row < row2; row++) {
      for (int col = col1; col < col2; col++) {
        assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
                this.g1.getSlotAt(row, col));
      }
    }
  }

  @Test
  public void testGetScore() {
    //check before any moves
    assertEquals(32, this.g1.getScore());
    this.g1.move(5, 3, 3, 3);
    assertEquals(31, this.g1.getScore());
    assertEquals(104, this.g6.getScore());
  }
}