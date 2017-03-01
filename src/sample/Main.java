package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
   static Model model = new Model();
//    static double[] mas ;
    @Override
    public void start(Stage stage) {

        double [] mas = new double[6];
        mas[0]=model.counts[0];
        mas[1]=model.counts[1];
        mas[2]=model.counts[2];
        mas[3]=model.counts[3];
        mas[4]=model.counts[4];
        mas[5]=model.counts[5];

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
                xAxis, yAxis);

        lineChart.setTitle("Line Chart");
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> series4 = new XYChart.Series<Number, Number>();
        series.setName("f(x)1");
        series2.setName("f(x)2");
        series3.setName("f(x)3");

//        populating the series with data
        series.getData().add(new XYChart.Data<Number, Number>(mas[0], 0));
        series.getData().add(new XYChart.Data<Number, Number>(0, mas[1]));
        series2.getData().add(new XYChart.Data<Number, Number>(mas[2], 0));
        series2.getData().add(new XYChart.Data<Number, Number>(0, mas[3]));
        series3.getData().add(new XYChart.Data<Number, Number>(mas[4], 0));
        series3.getData().add(new XYChart.Data<Number, Number>(0, mas[5]));
//        series4.getData().add(new XYChart.Data<Number, Number>(mas[6], 0));
//        series4.getData().add(new XYChart.Data<Number, Number>(0, mas[7]));


        Scene scene = new Scene(lineChart, 600, 600);
        lineChart.getData().add(series);
        lineChart.getData().add(series2);
        lineChart.getData().add(series3);
        lineChart.getData().add(series4);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        Model model = new Model();
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество огранечений");
        model.setSize(sc.nextInt());
        int count = 0, operationCount = 0;
        for (int i = 0; i < model.getSize() / 3; i++) {
            controller(model);
            model.save(count, operationCount);
            count += 3;
            operationCount += 2;
        }
        model.count();

//        staticFunction(model);
        launch(args);
    }

    public static void controller(Model model) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите множитель х1*");
        model.setMul1(sc.nextDouble());
        sc.nextLine();
//        System.out.print("Введите операцию ");
//        model.setOperation(sc.nextLine());  // 1
        System.out.print("Введите множитель х2*");
        model.setMul2(sc.nextDouble());
        sc.nextLine();
//        System.out.print("Введите операцию ");
//        model.setInequality(sc.nextLine()); // 2
        System.out.print("Введите результат функции");
        model.setEqual(sc.nextDouble());
        sc.nextLine();
    }

    public static void staticFunction(Model model) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите прямое ограничение x1*");
        model.setRestrictions_x(sc.nextDouble());
        sc.nextLine();
        System.out.print("Введите операцию "); // 3
        model.setRestrictionsOperationX(sc.nextLine());
        System.out.print("Введите прямое ограничение x2*");
        model.setRestrictions_y(sc.nextDouble());
        sc.nextLine();
        System.out.print("Введите операцию "); // 4
        model.setRestrictionOperationY(sc.nextLine());
        System.out.print("Введите целевую функию x1*");
        model.setFunction_X(sc.nextDouble());
        sc.nextLine();
        System.out.print("Введите операцию "); // 4
        model.setFunctionOperation(sc.nextLine());
        System.out.print("Введите целевую функию x2*");
        model.setFunction_Y(sc.nextDouble());
        sc.nextLine();
    }
}