package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SwingVisualizer {
    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    public SwingVisualizer(){
        frame = new JFrame("World");
        label = new JLabel("Wizualizacja...",SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 28));
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50,300,300,300));

        panel.setLayout(new FlowLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void changeLabel(String newLabel){
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e){
            System.err.format("IOException: %s%n", e);
        }

        label.setText("<html>" + newLabel.replaceAll("\n", "<br/>") + "</html>");
    }
}
