import objdata.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;
import rasterops.Liner;
import rasterops.TrivialLiner;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 *
 * @author PGRF FIM UHK
 * @version 2020
 */

public class Canvas {
    List<Point> vrcholy = new ArrayList<>();
    List<Point> vrcholyTrojuhelnik = new ArrayList<>();
    private JFrame frame;
    private JPanel panel;
    private final @NotNull RasterImage<Integer> img;
    private final @NotNull Presentable<Graphics> presenter;
    private final @NotNull Liner<Integer> liner;
    private int c1, r1, c2, r2;
    private int mouseX;
    private int mouseY;

    public Canvas(int width, int height) {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final @NotNull RasterImageBI auxRasterImageBI = new RasterImageBI(width, height);
        img = auxRasterImageBI;
        presenter = auxRasterImageBI;
        liner = new TrivialLiner<>();

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };


        panel.setPreferredSize(new Dimension(width, height));
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        panel.requestFocus();
        panel.requestFocusInWindow();

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                vrcholy.clear();
                clear();
                liner.drawDashedLine(img, c1, r1, e.getX(), e.getY(), 0x0000ff);
                present();

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseY = e.getY();
                mouseX = e.getX();
                if (vrcholyTrojuhelnik.size() == 2) {
                    vrcholy.clear();
                    clear();
                    Point firstPoint = vrcholyTrojuhelnik.get(0);
                    Point lastPoint = vrcholyTrojuhelnik.get(1);
                    liner.drawLine(img, lastPoint.getX(), lastPoint.getY(), firstPoint.getX(), firstPoint.getY(), 0x0180aa);
                    liner.drawLine(img, firstPoint.getX(), firstPoint.getY(), mouseX, mouseY, 0x0180aa);
                    liner.drawLine(img, mouseX, mouseY, lastPoint.getX(), lastPoint.getY(), 0x0180aa);

                    present();
                }
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_T) {
                    Point point = new Point(mouseX, mouseY);
                    vrcholyTrojuhelnik.add(point);
                    if (vrcholyTrojuhelnik.size() == 2) {
                        clear();
                        Point firstPoint = vrcholyTrojuhelnik.get(0);
                        Point lastPoint = vrcholyTrojuhelnik.get(1);
                        liner.drawLine(img, lastPoint.getX(), lastPoint.getY(), firstPoint.getX(), firstPoint.getY(), 0x0180aa);
                        present();
                    }

                }

            }

            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_C) {
                    clear();
                    vrcholy.clear();
                    vrcholyTrojuhelnik.clear();
                }

            }


        });


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (vrcholyTrojuhelnik.size() == 2) {
                        vrcholyTrojuhelnik.clear();
                        clear();
                    }
                    Point p = new Point(e.getX(), e.getY());
                    vrcholy.add(p);
                    if (vrcholy.size() != 1) {
                        Point preLastPoint = vrcholy.get(vrcholy.size() - 2);
                        Point lastPoint = vrcholy.get(vrcholy.size() - 1);
                        liner.drawLine(img, preLastPoint.getX(), preLastPoint.getY(), lastPoint.getX(), lastPoint.getY(), 0x0180aa);
                        present();
                    }
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    if (vrcholyTrojuhelnik.size() == 2) {
                        vrcholyTrojuhelnik.clear();
                        clear();
                    }
                    Point firstPoint = vrcholy.get(0);
                    Point lastPoint = vrcholy.get(vrcholy.size() - 1);
                    liner.drawLine(img, lastPoint.getX(), lastPoint.getY(), firstPoint.getX(), firstPoint.getY(), 0x0180aa);
                    present();
                } else {
                    c1 = e.getX();
                    r1 = e.getY();
                }
            }
        });
    }

    public void clear() {
        img.clear(0x2f2f2f);
    }

    public void present(final @NotNull Graphics graphics) {
        presenter.present(graphics);
    }

    public void present() {
        final @Nullable Graphics g = panel.getGraphics();
        if (g != null) {
            presenter.present(g);
        }
    }

    public void start() {
        clear();
//		liner.drawLine(img, 10, 10, 20, 10, 0xffff00);
        present();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Canvas(800, 600).start());
    }

}