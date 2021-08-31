package comp1140.ass2.gui;

import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    private final Group root = new Group();

    //  FIXME Task 9 (CR): Implement a basic playable Cublino game in JavaFX that only allows valid moves to be played

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cublino");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        Boards boards = new Boards("PWa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7");

        for (int x = 0; x < 7; x++)
            for (int y = 0; y < 7; y++) {
                Rectangle tile = new Rectangle(x * 80 + 45, y * 80 + 45, 70, 70);
                tile.setFill(Color.ROSYBROWN);
                root.getChildren().add(tile);

                Die die = boards.getAt(x, y);
                if (die == null) continue;
                FXDie fxdie = new FXDie(die);
                fxdie.setLayoutX(die.getX() * 80 + 50);
                fxdie.setLayoutY(die.getY() * 80 + 50);
                root.getChildren().add(fxdie);
            }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class FXDie extends Group {
        public FXDie(Die makeFrom) {
            Rectangle r = new Rectangle(0, 0, 60, 60);
            if (makeFrom.getColor())
                r.setFill(Color.WHITE);
            else
                r.setFill(Color.BLACK);
            getChildren().add(r);

            Color c = makeFrom.getColor() ? Color.BLACK : Color.WHITE;
            switch(makeFrom.getTop()) {
                case 1: pip(30, 30, c); break;
                case 2: pip(10, 10, c);
                        pip(50, 50, c); break;
                case 3: pip(10, 50, c);
                        pip(30, 30, c);
                        pip(50, 10, c); break;
                case 5: pip(30, 30, c);
                case 4: pip(15, 15, c);
                        pip(15, 45, c);
                        pip(45, 15, c);
                        pip(45, 45, c); break;
                default:pip(20, 15, c);
                        pip(20, 30, c);
                        pip(20, 45, c);
                        pip(40, 15, c);
                        pip(40, 30, c);
                        pip(40, 45, c); break;
            }
        }

        void pip(double x, double y, Color c) {
            Circle p = new Circle(x, y, 5);
            p.setFill(c);
            getChildren().add(p);
        }
    }
}
