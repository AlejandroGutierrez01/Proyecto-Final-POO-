import com.mysql.cj.log.Log;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Iniciar Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Login().loginPane);
        frame.pack();
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}