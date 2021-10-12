package comp1140.ass2.Controller;

import comp1140.ass2.gui.guiPieces.GuiDie;

/**
 * A difficult AI controller
 */
public class DifficultAI extends Controller{

    public DifficultAI(boolean isWhite) {
        super(false, "Hard AI " + (isWhite ? 1 : 2),
                isWhite ? GuiDie.Skin.PLAIN_WHITE : GuiDie.Skin.PLAIN_BLACK);
    }
}
