import javax.swing.*;
import java.awt.*;

class PanelLabiryntu extends JPanel {
    private Labirynt labirynt;

    public PanelLabiryntu(Labirynt labirynt) {
        this.labirynt = labirynt;
    }

    public void setLabirynt(Labirynt labirynt) {
        this.labirynt = labirynt;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int szer = getWidth() / labirynt.kolumny;
        int wys = getHeight() / labirynt.wiersze;

        for (int i = 0; i < labirynt.wiersze; i++) {
            for (int j = 0; j < labirynt.kolumny; j++) {
                Komorka k = labirynt.siatka[i][j];
                if (k.blokowana) g.setColor(Color.BLACK);
                else if (k == labirynt.start) g.setColor(Color.GREEN);
                else if (k == labirynt.cel) g.setColor(Color.RED);
                else if (k.wSciezce) g.setColor(Color.CYAN);
                else g.setColor(Color.WHITE);
                g.fillRect(j * szer, i * wys, szer, wys);
                g.setColor(Color.GRAY);
                g.drawRect(j * szer, i * wys, szer, wys);
            }
        }
    }
}