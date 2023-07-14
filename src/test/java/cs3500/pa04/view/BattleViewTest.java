package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringReader;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests and examples for the BattleView class
 */
public class BattleViewTest {

  private StringBuilder output;

  /**
   * Sets up examples for the BattleView tests
   */
  @BeforeEach
  public void setup() {
    output = new StringBuilder();
  }

  /**
   * Tests the write method
   */
  @Test
  public void testWrite() {
    Readable reader = new StringReader("");
    View view = new BattleView(reader, output);
    view.write("hello world");
    assertEquals("hello world\n", output.toString());
    view.write("");
    assertEquals("hello world\n\n", output.toString());
    View invalid = new BattleView(reader, new MockAppendable());
    RuntimeException e  = assertThrows(RuntimeException.class, () -> invalid.write("hello world"));
    assertEquals("Mock throwing an error", e.getMessage());
  }

  /**
   * Tests the method getNext
   */
  @Test
  public void testGetNext() {
    Readable reader = new StringReader("a b");
    View view = new BattleView(reader, output);
    assertEquals("a", view.getNext());
    assertEquals("b", view.getNext());
    NoSuchElementException e = assertThrows(NoSuchElementException.class,
        () -> view.getNext());
    assertEquals("No next item found in Scanner", e.getMessage());
  }

  /**
   * Tests the method skipLine
   */
  @Test
  public void testSkipLine() {
    Readable reader = new StringReader("a b\nc d");
    View view = new BattleView(reader, output);
    view.skipLine();
    assertEquals("c", view.getNext());
    assertEquals("d", view.getNext());
    view.skipLine();
    NoSuchElementException e = assertThrows(NoSuchElementException.class,
        () -> view.getNext());
    assertEquals("No next item found in Scanner", e.getMessage());
  }

}