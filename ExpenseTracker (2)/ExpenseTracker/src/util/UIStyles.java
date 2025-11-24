package util;

import java.awt.*;

import javax.swing.JButton;

public class UIStyles {

    public static final Color PRIMARY = new Color(52, 152, 219);
    public static final Color BG = new Color(245, 245, 245);

    public static Font titleFont() {
        return new Font("Segoe UI", Font.BOLD, 22);
    }

    public static Font normalFont() {
        return new Font("Segoe UI", Font.PLAIN, 16);
    }

    public static void styleButton(JButton btn) {
        btn.setBackground(PRIMARY);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(normalFont());
        btn.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(8, 15, 8, 15)
        );
    }
}
