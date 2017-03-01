import javax.swing.*;
import java.awt.*;
//import java.awt.*;
//import java.awt.image.ImageObserver;
//import java.text.AttributedCharacterIterator;


/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Msn extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 640;
    public static final int CANVAS_HEIGHT = 480;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;

    // Constructor to set up the GUI components and event handlers
    public Msn() {
        canvas = new DrawCanvas();

        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);
        // or "setContentPane(canvas);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    private class DrawCanvas extends JPanel {
        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
////            setBackground(Color.BLACK);  // set background color for this JPanel
            g.drawLine(20, 220, 20, 350);
            g.drawLine(20, 350, 360, 350);
            g.drawString("Y", 25, 230);
            g.drawString("X", 350, 346);
//            int[] xArray = {20, 40, 60, 80, 100, 120, 130, 140, 280, 332};
//            int[] yArray = {350, 345, 340, 310, 290, 280, 275, 273, 271, 269};
//            int nPoint = 10;
////            g.setColor(newColor);
//            g.drawPolyline(xArray, yArray, nPoint);
////            g.setColor(oldColor);
//            g.drawString("y = f(x)", 180, 267);
//            // Your custom painting codes. For example,
//            // Drawing primitive shapes
            int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;
            int xstart,ystart,yend,xend;
            xstart=ystart=0;
            xend=yend=120;
            dx = xend - xstart;//проекция на ось икс
            dy = yend - ystart;//проекция на ось игрек

            incx = sign(dx);

            incy = sign(dy);


            if (dx < 0) dx = -dx;//далее мы будем сравнивать: "if (dx < dy)"
            if (dy < 0) dy = -dy;//поэтому необходимо сделать dx = |dx|; dy = |dy|
            //эти две строчки можно записать и так: dx = Math.abs(dx); dy = Math.abs(dy);

            if (dx > dy)
            //определяем наклон отрезка:
            {
                pdx = incx;	pdy = 0;
                es = dy;	el = dx;
            }
            else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
            {
                pdx = 0;	pdy = incy;
                es = dx;	el = dy;//тогда в цикле будем двигаться по y
            }

            x = xstart;
            y = ystart;
            err = el/2;
            g.drawLine (x, y, x, y);//ставим первую точку
            //все последующие точки возможно надо сдвигать, поэтому первую ставим вне цикла

            for (int t = 0; t < el*5; t++)//идём по всем точкам, начиная со второй и до последней
            {
                err -= es;
                if (err < 0)
                {
                    err += el;
                    x += incx;//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
                    y += incy;//или сместить влево-вправо, если цикл проходит по y
                }
                else
                {
                    x += pdx;//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
                    y += pdy;//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
                }

                g.drawLine (x, y, x, y);
            }
        }

        private int sign (int x) {
            return (x > 0) ? 1 : (x < 0) ? -1 : 0;
            //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
        }



    }

    // The entry main method
    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Msn(); // Let the constructor do the job
            }
        });
    }
}