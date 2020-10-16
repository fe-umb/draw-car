package input;

import cena.Cena;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

/**
 *
 * @author Fernanda P. Umberto - 20943426
 */

public class KeyBoard implements KeyListener {

    private Cena cena;

    public KeyBoard(Cena cena) {
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                cena.angulo += 45;
                cena.tx = 1;
                cena.tz = 1;
                cena.ty = 0;
                break;
            case KeyEvent.VK_DOWN:
                cena.angulo -= 45;
                cena.tx = 1;
                cena.tz = 1;
                cena.ty = 0;
                break;
            case KeyEvent.VK_RIGHT:
                cena.angulo += 90;
                cena.tx = 0;
                cena.tz = 0;
                cena.ty = 1;
                break;
            case KeyEvent.VK_LEFT:
                cena.angulo -= 90;
                cena.tx = 0;
                cena.tz = 0;
                cena.ty = 1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
