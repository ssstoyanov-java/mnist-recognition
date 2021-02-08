import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DrawWindow extends JFrame {

    public DrawWindow() {
        setTitle("Digit Recognition");
        setSize(new Dimension(300, 430));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        Shared.setDrawPanel(new DrawPanel());
        JButton btnRecognize = new JButton("Predict");
        btnRecognize.setBounds(100, 340, 100, 50);
        btnRecognize.setFocusPainted(false);
        btnRecognize.addActionListener(new RecognizeListener());
        getContentPane().add(Shared.getDrawPanel());
        getContentPane().add(btnRecognize);
        setVisible(true);
    }

    private class RecognizeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            Shared.getRecognitionWindow().loadImage();
            Shared.getRecognitionWindow().setVisible(true);
            Shared.getRecognitionWindow().recognize();
        }
    }

}
