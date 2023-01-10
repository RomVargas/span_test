import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class WinnerLeague {

    public static void main(String[] args)
    {
        getResults();
    }

    /**
     * It reads a file, splits the lines into two teams, and then calculates the points for each team
     * based on the scores
     */
    public static void getResults(){
        Map<String, Integer> scores = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("table_results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String team1 = parts[0].substring(0, parts[0].length() - 2).trim();
                int score1 = Integer.parseInt(parts[0].substring(parts[0].length() - 1, parts[0].length()));
                String team2 = parts[1].substring(0, parts[1].length() - 2).trim();
                int score2 = Integer.parseInt(parts[1].substring(parts[1].length() - 1, parts[1].length()));

                int[] points = calculatePoints(score1, score2);

                scores.put(team1, scores.getOrDefault(team1, 0) + points[0]);
                scores.put(team2, scores.getOrDefault(team2, 0) + points[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] sortedTeams = scores.keySet().stream()
                .sorted((team1, team2) -> -scores.get(team1).compareTo(scores.get(team2)))
                .toArray(String[]::new);

        for (int i = 0; i < sortedTeams.length; i++) {
            String team = sortedTeams[i];
            System.out.println((i + 1) + ". " + team + ", " + scores.get(team) + " pts");
        }
    }

    /**
     * If the scores are equal, return 1 point for each team. If the first team wins, return 3 points
     * for the first team and 0 points for the second team. Otherwise, return 0 points for the first
     * team and 3 points for the second team
     * 
     * @param score1 The score of the first team
     * @param score2 The score of the second team
     * @return An array of integers.
     */
    public static int[] calculatePoints(int score1, int score2) {
        if (score1 == score2) {
            return new int[]{1, 1};
        } else if (score1 > score2) {
            return new int[]{3, 0};
        } else {
            return new int[]{0, 3};
        }
    }
}


