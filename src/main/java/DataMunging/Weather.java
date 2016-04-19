package DataMunging;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Weather {

    public static void main(String args[]) {
        try {
            Path p = FileSystems.getDefault().getPath("data/weather.dat");
            Weather w = new Weather(p);
            System.out.println(w.getSmallestTemperatureSpread().getDay());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e);
        }
    }

    class WeatherEntry {

        private final int day;
        private final double maxTemp;
        private final double minTemp;

        public WeatherEntry(int day, double maxTemp, double minTemp) {
            this.day = day;
            this.maxTemp = maxTemp;
            this.minTemp = minTemp;
        }

        public double tempSpread() {
            return maxTemp - minTemp;
        }

        public int getDay() {
            return day;
        }
    }

    private List<WeatherEntry> weatherData;

    public Weather(Path path) throws IOException {
        this.weatherData = readWeatherData(path);
    }

    public List<WeatherEntry> getWeatherData() {
        return weatherData;
    }

    public WeatherEntry getSmallestTemperatureSpread() {
        return getWeatherData().stream()
                .min((w1, w2) -> (int) Math.floor(w1.tempSpread() - w2.tempSpread()))
                .get();
    }

    private List<WeatherEntry> readWeatherData(Path path) throws IOException {
        return Files.lines(path)
                .filter(filterDataLine())
                .map(processDataLine())
                .collect(Collectors.toList());
    }

    private Function<String, WeatherEntry> processDataLine() {
        return new Function<String, WeatherEntry>() {
            public WeatherEntry apply(String line) {
                String[] lineElems = line.split("[ *]+");

                int day = Integer.parseInt(lineElems[1]);
                double maxTemp = Double.parseDouble(lineElems[2]);
                double minTemp = Double.parseDouble(lineElems[3]);

                return new WeatherEntry(day, maxTemp, minTemp);
            }
        };
    }

    private Predicate<String> filterDataLine() {
        return new Predicate<String>() {
            public boolean test(String line) {
                String[] elems = line.split(" +");
                if (elems.length <= 1) {
                    return false;
                } else if (elems[1].matches("\\d+")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
}
