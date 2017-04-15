package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class Model {
    private static final int F = 2;

    private int size;
    private double[] equals;
    private double[][] mas;
    private String[] operations;
    public double[][] points;
    public double[] points2;
    public double[] counts;
    public ArrayList<Double> result = new ArrayList<Double>();
    private double mul1, mul2, x1, x2, equal, restrictions_x,
            restrictions_y, function_X, function_Y, max, min, xMax, yMax, xMin, yMin;
    private String restrictionsOperationX,
            restrictionOperationY, inequality, functionOperation;
    private int j = 0;

    public void save(int i, int count) {
        mas[i][count] = mul1;
        mas[i][count + 1] = mul2;
        equals[i] = equal;
        System.out.println(Arrays.toString(equals));
    }

    public void gs() {
        GaussianElimination g = new GaussianElimination();

        for (int i = 2; i <= equals.length; i++) {
            switch (i) {
                case 2:
                    points = new double[][]{{mas[0][0], mas[0][1]}, {mas[1][0], mas[1][1]}}; // при двух функциях
                    points2 = new double[]{equals[0], equals[1]};
                    double[] x = g.gaussian(points, points2);
                        result.add(x[0]);
                        result.add(x[1]);
                    break;

                case 3:
                    points = new double[][]{{mas[0][0], mas[0][1]}, {mas[2][0], mas[2][1]}}; // при трех функциях
                    points2 = new double[]{equals[0], equals[2]};
                    x = g.gaussian(points, points2);
                    result.add(x[0]);
                    result.add(x[1]);
                    points = new double[][]{{mas[1][0], mas[1][1]}, {mas[2][0], mas[2][1]}};
                    points2 = new double[]{equals[1], equals[2]};
                    x = g.gaussian(points, points2);
                    result.add(x[0]);
                    result.add(x[1]);
                    break;
                case 4:
                    points = new double[][]{{mas[0][0], mas[0][1]}, {mas[3][0], mas[3][1]}};
                    points2 = new double[]{equals[0], equals[3]};
                    x = g.gaussian(points, points2);
                    result.add(x[0]);
                    result.add(x[1]);
                    points = new double[][]{{mas[1][0], mas[1][1]}, {mas[3][0], mas[3][1]}};
                    points2 = new double[]{equals[1], equals[3]};
                    x = g.gaussian(points, points2);
                    result.add(x[0]);
                    result.add(x[1]);
                    points = new double[][]{{mas[2][0], mas[2][1]}, {mas[3][0], mas[3][1]}};
                    points2 = new double[]{equals[2], equals[3]};
                    x = g.gaussian(points, points2);
                    result.add(x[0]);
                    result.add(x[1]);
                    break;
            }
        }
        for (int i = 2; i <= equals.length; i++) {
            switch (i) {
                case 2:
                    points = new double[][]{{mas[0][0], mas[0][1]}, {1, 0}};
                    points2 = new double[]{equals[0], restrictions_x};
                    resultXY(points, points2, g);
                    points = new double[][]{{mas[0][0], mas[0][1]}, {0, 1}};
                    points2 = new double[]{equals[0], restrictions_y};
                    resultXY(points, points2, g);
                    points = new double[][]{{mas[1][0], mas[1][1]}, {1, 0}};
                    points2 = new double[]{equals[1], restrictions_x};
                    resultXY(points, points2, g);
                    points = new double[][]{{mas[1][0], mas[1][1]}, {0, 1}};
                    points2 = new double[]{equals[1], restrictions_y};
                    resultXY(points, points2, g);
                    break;
                case 3:
                    points = new double[][]{{mas[2][0], mas[2][1]}, {1, 0}};
                    points2 = new double[]{equals[2], restrictions_x};
                    resultXY(points, points2, g);
                    points = new double[][]{{mas[2][0], mas[2][1]}, {0, 1}};
                    points2 = new double[]{equals[2], restrictions_y};
                    resultXY(points, points2, g);
                    break;
                case 4:
                    points = new double[][]{{mas[3][0], mas[3][1]}, {1, 0}};
                    points2 = new double[]{equals[3], restrictions_x};
                    resultXY(points, points2, g);
                    points = new double[][]{{mas[3][0], mas[3][1]}, {0, 1}};
                    points2 = new double[]{equals[3], restrictions_y};
                    resultXY(points, points2, g);
                    break;
            }
        }

        System.out.println(result.toString());

    }

    private void resultXY(double[][] points, double[] points2, GaussianElimination g) {
        double[] x = g.gaussian(points, points2);
        if (x[0] % 0.1 != 0)
            result.add(x[0]);
        if (x[1] % 0.1 != 0)
            result.add(x[1]);
    }

    public void count() {
        for (int i = 0; i < this.size; i++) {
            for (int k = 0; k < F; k++) {
                counts[j] = equals[i] / mas[i][k];
                j++;
            }
        }
        System.out.println(Arrays.toString(counts));

    }


    public String getInequality() {
        return inequality;
    }

    public void setInequality(String inequality) {
        this.inequality = inequality;
    }

    public String getFunctionOperation() {
        return functionOperation;
    }

    public void setFunctionOperation(String functionOperation) {
        this.functionOperation = functionOperation;
    }

    public double getRestrictions_x() {

        return restrictions_x;
    }

    public void setRestrictions_x(double restrictions_x) {
        this.restrictions_x = restrictions_x;
    }

    public double getRestrictions_y() {
        return restrictions_y;
    }

    public void setRestrictions_y(double restrictions_y) {
        this.restrictions_y = restrictions_y;
    }

    public double getFunction_X() {
        return function_X;
    }

    public void setFunction_X(double function_X) {
        this.function_X = function_X;
    }

    public double getFunction_Y() {
        return function_Y;
    }

    public void setFunction_Y(double function_Y) {
        this.function_Y = function_Y;
    }

    public String getRestrictionsOperationX() {
        return restrictionsOperationX;
    }

    public void setRestrictionsOperationX(String restrictionsOperationX) {
        this.restrictionsOperationX = restrictionsOperationX;
    }

    public String getRestrictionOperationY() {
        return restrictionOperationY;
    }

    public void setRestrictionOperationY(String restrictionOperationY) {
        this.restrictionOperationY = restrictionOperationY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        mas = new double[this.size][F];
        equals = new double[this.size];
        operations = new String[this.size];
        counts = new double[this.size * 2];
    }

    public double[][] getMas() {
        return mas;
    }

    public void setMas(double[][] mas) {
        this.mas = mas;
    }

    public double getMul1() {
        return mul1;
    }

    public void setMul1(double mul1) {
        this.mul1 = mul1;

    }

    public double getMul2() {
        return mul2;
    }

    public void setMul2(double mul2) {
        this.mul2 = mul2;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getEqual() {
        return equal;
    }

    public void setEqual(double equal) {
        this.equal = equal;
    }
}
