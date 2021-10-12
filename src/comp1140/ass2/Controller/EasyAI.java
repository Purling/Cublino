package comp1140.ass2.Controller;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.gui.guiPieces.GuiDie;

public class EasyAI extends Controller {

    public EasyAI(boolean isWhite) {
        super(false, "Easy AI " + (isWhite ? 1 : 2),
                isWhite ? GuiDie.Skin.PLAIN_WHITE : GuiDie.Skin.PLAIN_BLACK);
    }
}
