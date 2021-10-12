package comp1140.ass2.Controller;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.gui.guiPieces.GuiDie;

public class Human extends Controller {

    public Human(boolean isWhite) {
        super(true, "Player " + (isWhite ? 1 : 2),
                isWhite ? GuiDie.Skin.PLAIN_WHITE : GuiDie.Skin.PLAIN_BLACK);
    }
}
