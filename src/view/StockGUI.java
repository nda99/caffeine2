package view;

import model.*;
import model.Menu;
import model.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Map;

import javax.swing.*;

public class StockGUI extends JFrame implements ActionListener{

    private JFrame frame = new JFrame();
    private JPanel northPanel = new JPanel();
    private JPanel centralPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel eastPanel = new JPanel();
    private Map<String, MenuItem> allItems = Menu.getMenuMap();


    public StockGUI(){
        buildGUI();
    }

    public void viewStock()
    {
        centralPanel.removeAll();
        centralPanel.repaint();
        centralPanel.revalidate();
        String[] columnNames = {"ItemID",
                "item Name",
                "Category",
                "Quantity",
                "unit Price"};

        Object[][] data = new Object[allItems.keySet().size()][5] ;
        //for (Object obj : data)
        int counter = 0;
        for (String item : allItems.keySet())
        {

            String[] itemDetails = new String[5];
            MenuItem itemTemp = allItems.get(item);
            itemDetails[1] = item;
            itemDetails[0] = Integer.toString(itemTemp.getNumber());
            itemDetails[2] = Menu.translateCatToString(itemTemp.getCategory());
            itemDetails[3] = Integer.toString(itemTemp.getQuantity());
            itemDetails[4] = Double.toString(itemTemp.getPrice());
            data[counter] = itemDetails;

            counter ++;
        }
        JTable tableA = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tableA);
//        scrollPane.setPreferredSize(new Dimension(480, 300));
        //tableA.setFillsViewportHeight(true);
        centralPanel.add(scrollPane);
        frame.add(centralPanel);

    }

    private void setupCentralPanel()
    {
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setLayout(new GridLayout(1,1));
        viewStock();
    }

    private void setupNorthPanel()
    {
        //North Panel has two sub panels, a: is for the title, b: is for the filtering by date and print
        JPanel a = new JPanel();
        JPanel b = new JPanel();
        //Setting up A panel
        a.setLayout(new BorderLayout(10,10));
        JLabel title;
        title = new JLabel(" ** Stock ** ", JLabel.CENTER);
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
        title.setFont(titleFont);
        a.add(title,BorderLayout.CENTER);
        //Setting up B panel
        b.setLayout(new GridLayout(1,6,5,5));
        //Adding both panels to the north panel
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.add(a);
        northPanel.add(b);
        //Adding north panel to the main frame
        frame.add(northPanel, BorderLayout.NORTH);

    }

    public void buildGUI(){
        //Setting up the main frame
        frame.setLayout(new BorderLayout(10, 10));
        frame.setTitle("Caffeine App");
        frame.setSize(600,700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(300,500);
        frame.setVisible(true);

        //Adding central panel to the main frame through the setupCentralPanel method
        setupCentralPanel();
        //Adding north panel to the main frame through the setupNorthPanel method
        setupNorthPanel();
        //Adding east and west panels to the main frame
        frame.add(eastPanel,BorderLayout.EAST);
        frame.add(westPanel,BorderLayout.WEST);

    }

    @Override
    public void actionPerformed(ActionEvent event) {}


}
