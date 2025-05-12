import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OknoLabiryntu extends JFrame {
    private Labirynt labirynt;
    private PanelLabiryntu panel;
    private JButton labiryntBtn;
    private JButton sciezkaBtn;

    public OknoLabiryntu() {
        setTitle("Labirynt z przyciskiem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 850);
        setLocationRelativeTo(null);

        labirynt = new Labirynt(25, 25);
        labirynt.generuj();

        panel = new PanelLabiryntu(labirynt);

        labiryntBtn = new JButton("Nowy labirynt");
        sciezkaBtn = new JButton("Pokaż ścieżkę");

        labiryntBtn.addActionListener(e -> {
            labirynt = new Labirynt(25, 25);
            labirynt.generuj();
            panel.setLabirynt(labirynt);
            panel.repaint();
        });

        sciezkaBtn.addActionListener(e -> {

        });

        JPanel przyciski = new JPanel();
        przyciski.add(labiryntBtn);
        przyciski.add(sciezkaBtn);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(przyciski, BorderLayout.SOUTH);

        setVisible(true);
    }
}
