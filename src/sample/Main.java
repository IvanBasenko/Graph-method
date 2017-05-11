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
    BresenhamsAlg bresenhamsAlg = new BresenhamsAlg();
    private double[] mas = new double[model.getSize() * 3];
    private int k = ZERO;
    public static final int ZERO = 0;
    @Override
    public void start(Stage stage) {
        for (int i = 0; i < mas.length; i++) {
            mas[i] = model.counts[k];
            mas[i + 1] = bresenhamsAlg.drawBresenhamsLine(model.counts[k], ZERO, ZERO, model.counts[k + 1], 1);
            mas[i + 2] = bresenhamsAlg.drawBresenhamsLine(model.counts[k], ZERO, ZERO, model.counts[k + 1], 0);
            i += 2;
            k += 2;
        }
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Designation");
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
                xAxis, yAxis);

        lineChart.setTitle("Line Chart");
        XYChart.Series<Number, Number> func1 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> func2 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> func3 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> func4 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> funcX = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> funcY = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> vector = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> max = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> min = new XYChart.Series<Number, Number>();
        func1.setName("f(x)1");
        func2.setName("f(x)2");
        func3.setName("f(x)3");
        func4.setName("f(x)4");
        funcX.setName("X");
        funcY.setName("Y");
        vector.setName("Result function");
        max.setName("F(max)");
        min.setName("F(min)");

//        populating the series with data
        for (int i = 0; i < model.getSize(); i++) {
            switch (i) {
                case 0:
                    if (model.counts[1] < ZERO)
                        func1.getData().add(new XYChart.Data<Number, Number>(ZERO, model.counts[1]));
                    else
                        func1.getData().add(new XYChart.Data<Number, Number>(mas[0], ZERO));
                    func1.getData().add(new XYChart.Data<Number, Number>(mas[1], mas[2]));
                    break;
                case 1:
                    if (model.counts[3] < ZERO)
                        func2.getData().add(new XYChart.Data<Number, Number>(ZERO, model.counts[3]));
                    else
                        func2.getData().add(new XYChart.Data<Number, Number>(mas[3], ZERO));
                    func2.getData().add(new XYChart.Data<Number, Number>(mas[4], mas[5]));
                    break;
                case 2:
                    if (model.counts[5] < ZERO)
                        func3.getData().add(new XYChart.Data<Number, Number>(ZERO, model.counts[5]));
                    else
                        func3.getData().add(new XYChart.Data<Number, Number>(mas[6], ZERO));
                    func3.getData().add(new XYChart.Data<Number, Number>(mas[7], mas[8]));
                    break;
                case 3:
                    if (model.counts[7] < ZERO)
                        func4.getData().add(new XYChart.Data<Number, Number>(ZERO, model.counts[7]));
                    else
                        func4.getData().add(new XYChart.Data<Number, Number>(mas[9], ZERO));
                    func4.getData().add(new XYChart.Data<Number, Number>(mas[10], mas[11]));
                    break;
            }
        }

        funcX.getData().addAll();
        if (model.getRestrictions_x() != ZERO) {
            funcX.getData().add(new XYChart.Data<Number, Number>(model.getRestrictions_x(), ZERO));
            funcX.getData().add(new XYChart.Data<Number, Number>(model.getRestrictions_x(), 15));
        }
        if (model.getRestrictions_y() != ZERO) {
            funcY.getData().add(new XYChart.Data<Number, Number>(ZERO, model.getRestrictions_y()));
            funcY.getData().add(new XYChart.Data<Number, Number>(15, model.getRestrictions_y()));
        }
        vector.getData().add(new XYChart.Data<Number, Number>(ZERO, ZERO));
        vector.getData().add(new XYChart.Data<Number, Number>(model.getFunction_X(), model.getFunction_Y()));
//
//        max.getData().add(new XYChart.Data<Number, Number>(0, mas[7]));
//        min.getData().add(new XYChart.Data<Number, Number>(0, mas[7]));


        Scene scene = new Scene(lineChart, 800, 800);
        lineChart.getData().add(func1);
        lineChart.getData().add(func2);
        lineChart.getData().add(func3);
        lineChart.getData().add(func4);
        lineChart.getData().add(funcX);
        lineChart.getData().add(funcY);
        lineChart.getData().add(vector);
        lineChart.getData().add(max);
        lineChart.getData().add(min);
        stage.setTitle("Graphical method of solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество огранечений");
        model.setSize(sc.nextInt());
        int count = ZERO, operationCount = ZERO;
        for (int i = 0; i < model.getSize(); i++) {
            controller(model);
            model.save(count, operationCount);
            count++;

        }
        staticFunction(model);
        model.count();
        try {
            model.gs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.maxMinFunction();
        launch(args);
    }

    public static void controller(Model model) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите множитель х1*");
        model.setMul1(sc.nextDouble());
        sc.nextLine();
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
        System.out.print("Введите прямое ограничение x2*");
        model.setRestrictions_y(sc.nextDouble());
        System.out.print("Введите целевую функию x1*");
        model.setFunction_X(sc.nextDouble());
        System.out.print("Введите целевую функию x2*");
        model.setFunction_Y(sc.nextDouble());
        sc.nextLine();
    }
}