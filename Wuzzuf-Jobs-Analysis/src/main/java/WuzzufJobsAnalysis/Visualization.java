package WuzzufJobsAnalysis;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.util.List;

public class Visualization {
    public static void barChart(List<String> keys, List<Integer> values, String title, String xlabel){
        // Create Chart
        CategoryChart chart =
                new CategoryChartBuilder()
                        .width(1000)
                        .height(800)
                        .title(title)
                        .xAxisTitle(xlabel)
                        .yAxisTitle("Counts")
                        .build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setXAxisLabelRotation(90);

        // Series
        chart.addSeries(xlabel, keys, values);

        // Display
        //new SwingWrapper<>(chart).displayChart();

        // Save the Visualization as a png
        try {
            BitmapEncoder.saveBitmap(chart,System.getProperty("user.dir")+"/Public/"+xlabel, BitmapEncoder.BitmapFormat.PNG);
        }
        catch (Exception e){
            System.out.println("NOT Found path");
        }
    }

    public static void pieChart(List<String> keys, List<Integer> values, String title, int limit) {

        // Create Chart
        PieChart chart = new PieChartBuilder().width(800).height(600).title(title).build();

        // Customize Chart
        Color[] sliceColors = new Color[] {
                new Color(200, 50, 50), new Color(170, 50, 50),
                new Color(150, 70, 70), new Color(130, 90, 90),
                new Color(110, 110, 110), new Color(90, 130, 130),
                new Color(70, 150, 150), new Color(50, 170, 170),
                new Color(30, 190, 190), new Color(10, 210, 210),
                new Color(10, 190, 100), new Color(10, 210, 130),
                new Color(10, 240, 50), new Color(10, 255, 70),
                new Color(30, 255, 0)
        };
        chart.getStyler().setSeriesColors(sliceColors);
        chart.getStyler().setAnnotationsFontColor(new Color(255, 255, 255));

        // Series
        for (int i=0; i<keys.size(); i++){
            chart.addSeries(keys.get(i), values.get(i));
        }

        // Display
        //new SwingWrapper<>(chart).displayChart();

        // Save the Visualization as a png
        try {
            BitmapEncoder.saveBitmap(chart,System.getProperty("user.dir")+"/Public/companies", BitmapEncoder.BitmapFormat.PNG);
        }
        catch (Exception e){
            System.out.println("NOT Found path");
        }
    }
}
