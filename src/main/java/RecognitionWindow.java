import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecognitionWindow extends JFrame {

    private final JLabel lblDigit;
    private double[][] data;

    public RecognitionWindow() {
        setTitle("Digit Recognition");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(125, 225, 100, 25);
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(new ResetButtonListener());
        this.lblDigit = new JLabel("");
        this.lblDigit.setFont(new Font("Verdana", Font.PLAIN, 100));
        this.lblDigit.setBounds(140, 100, 150, 100);
        getContentPane().add(btnReset);
        getContentPane().add(this.lblDigit);
    }

    public void loadImage() {
        this.data = Shared.getDrawPanel().getData();
    }

    public void recognize() {
        this.lblDigit.setText(Model.getInstance().recognize(this.data) + "");
    }

    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            Shared.setDrawWindow(new DrawWindow());
        }
    }

}
