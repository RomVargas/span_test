import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScoreTest {
    private final String FILE_NAME = "table_results.txt";
    private final String TEAM_NAME_1 = "Team1";
    private final String TEAM_NAME_2 = "Team2";
    private final String TEAM_NAME_3 = "Team3";
    private final int SCORE_1 = 2;
    private final int SCORE_2 = 0;
    private final int SCORE_3 = 1;
    private final int[] POINTS_DRAW = {1, 1};
    private final int[] POINTS_WIN = {3, 0};
    private final int[] POINTS_LOSS = {0, 3};

    @Before
    public void setUp() throws IOException {
        String fileData = TEAM_NAME_1 + " 2" + " - " + TEAM_NAME_2 + " 0" + "\n";
        fileData += TEAM_NAME_1 + " 1" + " - " + TEAM_NAME_3 + " 1" + "\n";
        Path filePath = Paths.get(FILE_NAME);
        Files.write(filePath, fileData.getBytes());
    }

    @Test
    public void testCalculatePoints() {
        assertArrayEquals(POINTS_DRAW, Score.calculatePoints(SCORE_1, SCORE_1));
        assertArrayEquals(POINTS_WIN, Score.calculatePoints(SCORE_1, SCORE_2));
        assertArrayEquals(POINTS_LOSS, Score.calculatePoints(SCORE_2, SCORE_1));
    }

    @Test
    public void testGetResults() {
        Score.getResults();
        Map<String, Integer> scores = Score.getScores();
        assertNotNull(scores);
        assertEquals(3, scores.size());
        assertEquals(3, scores.get(TEAM_NAME_1).intValue());
        assertEquals(0, scores.get(TEAM_NAME_2).intValue());
        assertEquals(1, scores.get(TEAM_NAME_3).intValue());
    }
}
