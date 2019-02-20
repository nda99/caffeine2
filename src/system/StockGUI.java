package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StockGUI extends JFrame implements ActionListener{

    //StockGUI Components:
    private JFrame frame = new JFrame();
    private JPanel centerPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JLabel label1 = new JLabel("Stock", JLabel.LEFT);

    public StockGUI(){
        buildGUI();
    }

    public void buildGUI(){
        frame.setTitle("Caffiene App");
        frame.setSize(500,700);
        frame.setLocation(300,500);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout(50, 50));
        JLabel title;
        title = new JLabel(" ** Stock ** ", JLabel.CENTER);
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        title.setFont(titleFont);
        frame.add(title, BorderLayout.NORTH);
        centerPanel.setLayout(new GridLayout(7, 1,10,10));
        Font itemsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        label1.setFont(itemsFont);
        centerPanel.add(label1);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(westPanel, BorderLayout.WEST);

        frame.add(centerPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent event) {}


}
