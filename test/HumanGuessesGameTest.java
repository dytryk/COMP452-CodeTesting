import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HumanGuessesGameTest {

    @Test
    void correctGuess() {
        HumanGuessesGame game = new HumanGuessesGame();

        GuessResult result = game.makeGuess(game.getTarget());

        assertEquals(GuessResult.CORRECT, result);

//        assertTrue(game.isDone());
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void LowGuess() {
        HumanGuessesGame game = new HumanGuessesGame();

        GuessResult result = game.makeGuess(game.getTarget() - 1);

        assertEquals(GuessResult.LOW, result);

        assertFalse(game.isDone());
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void highGuess() {
        HumanGuessesGame game = new HumanGuessesGame();

        GuessResult result = game.makeGuess(game.getTarget() + 1);

        assertEquals(GuessResult.HIGH, result);

        assertFalse(game.isDone());
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void manyGuesses() {
        HumanGuessesGame game = new HumanGuessesGame();

        GuessResult result1 = game.makeGuess(game.getTarget() + 100);
        assertEquals(GuessResult.HIGH, result1);
        GuessResult result2 = game.makeGuess(game.getTarget() - 200);
        assertEquals(GuessResult.LOW, result2);
        GuessResult result3 = game.makeGuess(game.getTarget() + 50);
        assertEquals(GuessResult.HIGH, result3);
        GuessResult result4 = game.makeGuess(game.getTarget() - 100);
        assertEquals(GuessResult.LOW, result4);

        assertFalse(game.isDone());
        assertEquals(4, game.getNumGuesses());
    }

    @Test
    void manyGuessesWithCorrect() {
        HumanGuessesGame game = new HumanGuessesGame();

        GuessResult result1 = game.makeGuess(game.getTarget() + 100);
        assertEquals(GuessResult.HIGH, result1);
        GuessResult result2 = game.makeGuess(game.getTarget() - 200);
        assertEquals(GuessResult.LOW, result2);
        GuessResult result3 = game.makeGuess(game.getTarget() + 50);
        assertEquals(GuessResult.HIGH, result3);
        GuessResult result4 = game.makeGuess(game.getTarget() - 100);
        assertEquals(GuessResult.LOW, result4);
        GuessResult resultFinal = game.makeGuess(game.getTarget());
        assertEquals(GuessResult.CORRECT, resultFinal);

        assertEquals(5, game.getNumGuesses());
//        assertTrue(game.isDone());
    }
}