package DataMunging;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Football {

    public static void main(String... args) {
        try {
            Path p = FileSystems.getDefault().getPath("data/football.dat");
            Football f = new Football(p);
            System.out.println(f.getSmallestGoalDifference().getTeam());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e);
        }
    }

    class TeamEntry {

        private final String team;
        private final int goalsScored;
        private final int goalsConceded;

        public TeamEntry(String team, int goalsScored, int goalsConceded) {
            this.team = team;
            this.goalsScored = goalsScored;
            this.goalsConceded = goalsConceded;
        }

        public int goalDifference() {
            return goalsScored - goalsConceded;
        }

        public String getTeam() {
            return team;
        }

    }

    private List<TeamEntry> teamData;

    public Football(Path path) throws IOException {
        this.teamData = readTeamData(path);
    }

    public List<TeamEntry> getTeamData() {
        return teamData;
    }

    public TeamEntry getSmallestGoalDifference() {
        return getTeamData().stream()
                .min((t1, t2) -> Math.abs(t1.goalDifference())
                               - Math.abs(t2.goalDifference()))
                .get();
    }

    public List<TeamEntry> readTeamData(Path path) throws IOException {
        return Files.lines(path)
                .filter(l -> l.split(" +")[1].matches("\\d+\\."))
                .map(processDataLine())
                .collect(Collectors.toList());
    }

    private Function<String, TeamEntry> processDataLine() {
        return new Function<String, TeamEntry>() {
            public TeamEntry apply(String line) {
                String[] lineElems = line.split(" +");

                String team = lineElems[2];
                int goalsScored = Integer.parseInt(lineElems[7]);
                int goalsConceded = Integer.parseInt(lineElems[9]);

                return new TeamEntry(team, goalsScored, goalsConceded);
            }
        };
    }
}
