package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test client uses of {@code MarbleSolitaireTextView} class.
 */
public class MarbleSolitaireTextViewTest {

  EnglishSolitaireModel g1;
  EnglishSolitaireModel g2;
  EnglishSolitaireModel g3;
  EnglishSolitaireModel g4;
  EnglishSolitaireModel g5;
  EnglishSolitaireModel g6;
  MarbleSolitaireTextView v1;
  MarbleSolitaireTextView v2;
  MarbleSolitaireTextView v3;

  @Before
  public void init() {
    this.g1 = new EnglishSolitaireModel();
    this.g2 = new EnglishSolitaireModel(3, 3);
    this.g3 = new EnglishSolitaireModel(3, 3, 3);
    this.g4 = new EnglishSolitaireModel(3, 4);
    this.g5 = new EnglishSolitaireModel(5);
    this.g6 = new EnglishSolitaireModel(5, 0, 4);
    this.v1 = new MarbleSolitaireTextView(this.g1);
    this.v2 = new MarbleSolitaireTextView(this.g4);
    this.v3 = new MarbleSolitaireTextView(this.g5);
  }

  //tests on different games of arm thickness 3
  @Test
  public void testToString3() {
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    //test on same game board of arm thickness 3 just initialized differently
    assertEquals(expected, this.v1.toString());
    this.v1 = new MarbleSolitaireTextView(this.g2);
    assertEquals(expected, this.v1.toString());
    this.v1 = new MarbleSolitaireTextView(this.g3);
    assertEquals(expected, this.v1.toString());

    expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, this.v2.toString());
  }

  //test on different games of arm thickness 5
  @Test
  public void testToString5() {
    String expected = "        O O O O O\n"
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

    assertEquals(expected, this.v3.toString());
    expected = "        _ O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";
    v3 = new MarbleSolitaireTextView(this.g6);
    assertEquals(expected, this.v3.toString());
  }

  @Test
  public void testInvalidViewConstructor() {
    try {
      new MarbleSolitaireTextView(null);
      fail("Constructor did not throw an Illegal Argument Exception, when it should have");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void testAfterMove() {
    String expected = "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    this.g1.move(1,3, 3,3);
    assertEquals(expected, this.v1.toString());
  }
}