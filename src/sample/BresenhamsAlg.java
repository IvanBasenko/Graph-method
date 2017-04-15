package sample;

public class BresenhamsAlg {
    private int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    public double drawBresenhamsLine(double xStart, double yStart, double xEnd, double yEnd, int s) {
        double x, y, dx, dy, incX, incY, pdx, pdy, es, el, err;
        if (yEnd < 0) {
            dx = (xEnd - xStart) / -1;//проекция на ось икс
            dy = (yEnd - yStart) / -1;
            incX = sign((int) dx);
            incY = sign((int) dy);
        } else {
            dx = xEnd - xStart;//проекция на ось икс
            dy = yEnd - yStart;//проекция на ось игрек
            incX = sign((int) dx);
            incY = sign((int) dy);
        }

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
