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

    @Override
    public void start(Stage stage) {

        double[] mas = new double[6];
        mas[0] = model.counts[0];
//        mas[1] = model.counts[1];
//        mas[2] = model.counts[2];
//        mas[3] = model.counts[3];
//        mas[4] = model.counts[4];
//        mas[5] = model.counts[5];
        mas[1] = drawBresenhamsLine(model.counts[0], 0, 0, model.counts[1], 1);
        mas[2] = drawBresenhamsLine(model.counts[0], 0, 0, model.counts[1], 0);


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
        series.getData().add(new XYChart.Data<Number, Number>(mas[1], mas[2]));
//        series2.getData().add(new XYChart.Data<Number, Number>(mas[2], 0));
//        series2.getData().add(new XYChart.Data<Number, Number>(0, mas[3]));
//        series3.getData().add(new XYChart.Data<Number, Number>(mas[4], 0));
//        series3.getData().add(new XYChart.Data<Number, Number>(0, mas[5]));
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество огранечений");
        model.setSize(sc.nextInt());
        int count = 0, operationCount = 0;
        for (int i = 0; i < model.getSize(); i++) {
            controller(model);
            model.save(count, operationCount);
            count++;
//            operationCount++;
        }

        model.count();
        model.gs();
        staticFunction(model);
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
        System.out.print("Введите операцию ");
        model.setInequality(sc.nextLine()); // 2
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

    private int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    private double drawBresenhamsLine(double xStart, double yStart, double xEnd, double yEnd, int s) {
        double x, y, dx, dy, incX, incY, pdx, pdy, es, el, err;
        dx = xEnd - xStart;//проекция на ось икс
        dy = yEnd - yStart;//проекция на ось игрек

        incX = sign((int) dx);

        incY = sign((int) dy);


        if (dx < 0) dx = -dx;//далее мы будем сравнивать: "if (dx < dy)"
        if (dy < 0) dy = -dy;//поэтому необходимо сделать dx = |dx|; dy = |dy|
        //эти две строчки можно записать и так: dx = Math.abs(dx); dy = Math.abs(dy);

        if (dx > dy)
        //определяем наклон отрезка:
        {
            pdx = incX;
            pdy = 0;
            es = dy;
            el = dx;
        } else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;
            pdy = incY;
            es = dx;
            el = dy;//тогда в цикле будем двигаться по y
        }

        x = xStart;
        y = yStart;
        err = el / 2;
        //все последующие точки возможно надо сдвигать, поэтому первую ставим вне цикла

        for (int t = 0; t < el * 2; t++)//идём по всем точкам, начиная со второй и до последней
        {
            err -= es;
            if (err < 0) {
                err += el;
                x += incX;//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
                y += incY;//или сместить влево-вправо, если цикл проходит по y
            } else {
                x += pdx;//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
                y += pdy;//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
            }
        }
        if (s == 1) return x;
        else return y;
    }
}