import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasterdata.Presentable;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;
import rasterops.Liner;
import rasterops.TrivialLiner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

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

	private JFrame frame;
	private JPanel panel;
	private final @NotNull RasterImage<Integer> img;
	private final @NotNull Presentable<Graphics> presenter;
	private final @NotNull Liner<Integer> liner;
	private int c1, r1, c2, r2;

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

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				clear();
				liner.drawLine(img, c1, r1, e.getX(), e.getY(), 0x0000ff);
				present();
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				c1 = e.getX();
				r1 = e.getY();
			}
		});

		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
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