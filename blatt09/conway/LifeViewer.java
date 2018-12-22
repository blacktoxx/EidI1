import javax.swing.*;
import java.util.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

public class LifeViewer extends JPanel implements MouseListener {

    private Timer timer;
    private JFrame frame;
    private boolean[][] field;


    public static void main(String[] args) {

        LifeViewer viewer = new LifeViewer();
        viewer.start();

    }

    public LifeViewer() {

        frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.BLACK);
        this.addMouseListener(this);

        JButton start = new JButton("START");
        start.addActionListener(actionEvent -> run());

        frame.getContentPane().add(start, BorderLayout.NORTH);

        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);

        field = new boolean[600 / 10][600 / 10];
    }

    private void run() {

        if(timer != null){

            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LifeLogic.compute(field);
                SwingUtilities.invokeLater(() -> frame.repaint());
            }
        }, 100, 1000 / 15);

    }

    public void start() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[j][i]) {
                    g.setColor(Color.RED);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }
                g.setColor(Color.DARK_GRAY);
                g.drawRect(i * 10, j * 10, 10, 10);

            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getX() / 10 < field.length && me.getY() / 10 < field[0].length) {
            field[me.getY() / 10][me.getX() / 10] = !field[me.getY() / 10][me.getX() / 10];
            if (timer != null) {
                timer.cancel();
            }
            SwingUtilities.invokeLater(() -> this.repaint());

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
