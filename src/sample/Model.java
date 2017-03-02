package sample;

import java.util.Arrays;

public class Model {
    private static final int F = 2;

    private int size;
    private double[] equals;
    private double[][] mas;
    private String[] operations;
    public double[] counts;
    private double mul1, mul2, x1, x2, equal, restrictions_x,
            restrictions_y, function_X, function_Y, max, min, xMax, yMax, xMin, yMin;
    private String restrictionsOperationX,
            restrictionOperationY, inequality, functionOperation;
    private int j = 0;

    public void save(int i, int count) {
        mas[i][count] = mul1;
        mas[i][count + 1] = mul2;
        equals[i] = equal;
//        operations[operationCount] = inequality;
//        System.out.println(Arrays.toString(mas));
        System.out.println(Arrays.toString(equals));
//        System.out.println(Arrays.toString(operations));
    }
    public void gs(){
       GaussianElimination g = new GaussianElimination();
        double[] x = g.gaussian(mas,equals);
        for (int i = 0; i < F; i++) {
            System.out.println(x[i]);
        }
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
        counts = new double[this.size*2];
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
