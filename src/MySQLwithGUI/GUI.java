package MySQLwithGUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class GUI {

    JFrame frame;
    JButton Query1;
    JButton Query2;
    JButton Query3;
    JButton Query4;

     JTextField Instructions;
    JTextField Query5;
    JTextField Query6;

    JTextField Result;
    JLabel LabelOne;
    JLabel LabelTwo;
    JPanel panel;

    public GUI() {
        frame = new JFrame();
        panel = new JPanel();
        Query1 = new JButton("Query#1");
        Query2 = new JButton("Query#2");
        Query3 = new JButton("Query#3");
        Query4 = new JButton("Query#4");

        Instructions = new JTextField(90);


        Query5 = new JTextField(16);
        Query5.setText("This is for 1 degree of freedom");
        Query6 = new JTextField(16);
        Query6.setText("This is for 2 degrees of freedom");
        Result = new JTextField(25);
        Result.setText("This is for the Resulting Query.");



        JButton Query3 = new JButton("Query#3");
        JButton Query4 = new JButton("Query#4");
        frame.setSize(500, 500);

        frame.setTitle("MySQL GUI");

        panel.add(Query1);
        panel.add(Query2);
        panel.add(Query3);
        panel.add(Query4);
        panel.add(Query5);
        panel.add(Query6);
        panel.add(Result);
        panel.add(Instructions);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Result.setText("Query#1");
                System.out.println("Query#1");
                Connector.runner1();


            }

        };

        ActionListener buttonListener2 = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Result.setText("Query#2");
                System.out.println("Query#2");
                Connector.runner2();

            }

        };

        ActionListener buttonListener3 = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Result.setText("Query#3");
                System.out.println("Query#3");
                Connector.runner3();

            }

        };

        ActionListener buttonListener4 = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Result.setText("Query#4");
                System.out.println("Query#4");
                Connector.runner4();

            }

        };

        ActionListener buttonListener5 = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //String s = ae.getActionCommand();

                System.out.println(Query5.getText());
                String string = Query5.getText();
                String [] spliter = string.split(",");

                String stringOne = spliter[0];
                String stringTwo = spliter[1];

                System.out.println(stringOne);
                System.out.println("------------");
                System.out.println(stringTwo);
                System.out.println("------------");
                System.out.println(string);
                try {
                    Connector.runner5(stringOne,stringTwo);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                //Result.setText(Query5.getText());
                //Connector.output(Query6.getText());

            }

        };

        ActionListener buttonListener6 = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                System.out.println(Query6.getText());
                String string = Query5.getText();
                String [] spliter = string.split(",");

                String stringOne = spliter[0];
                String stringTwo = spliter[1];

                System.out.println(stringOne);
                System.out.println("------------");
                System.out.println(stringTwo);
                System.out.println("------------");
                System.out.println(string);


                Connector.runner6(stringOne,stringTwo);


                //Result.setText(Query6.getText());
                //Connector.output(Query6.getText());

            }

        };


        Query1.addActionListener(buttonListener);
        Query2.addActionListener(buttonListener2);
        Query3.addActionListener(buttonListener3);
        Query4.addActionListener(buttonListener4);
        Query5.addActionListener(buttonListener5);
        Query6.addActionListener(buttonListener6);


    }
}
