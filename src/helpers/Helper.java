package helpers;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Helper {

    public static void addRandomColorToPane(Pane pane) {
        String enteredByUser = Helper.generateRandomHexColor();
        pane.setStyle("-fx-background-color: " + enteredByUser);
    }

    public static Color generateRandomColor() {
        Random r = new Random();
        return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public static String generateRandomHexColor() {
        Random ra = new Random();
        int r, g, b;
        r = ra.nextInt(255);
        g = ra.nextInt(255);
        b = ra.nextInt(255);
        Color color = new Color(r, g, b);
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if (hex.length() < 6) {
            hex = "0" + hex;
        }
        return "#" + hex;
    }
}
