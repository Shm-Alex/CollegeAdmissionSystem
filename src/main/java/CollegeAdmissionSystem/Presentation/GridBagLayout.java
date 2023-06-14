package CollegeAdmissionSystem.Presentation;

import java.awt.*;
import javax.swing.*;

public class GridBagLayout {

    public static void createPanelUI(Container container) {
        JButton button;
        JLabel label1, surnameLabel, nameLabel, phoneLabel, mailLabel, choiceLabel,
                facultyLabel, departLabel, courseLabel, basketLabel;
        JTextField surnameText, nameText, phoneText, mailText;
        JComboBox facultyBox, departBox, courseBox;
        JMenuBar menuBar;
        JMenu instMenu, regstudMenu, aboutMenu;
        JMenuItem cleardbItem, populcoursItem;
        JPanel listPanel;


        container.setComponentOrientation ( ComponentOrientation.LEFT_TO_RIGHT );

        container.setLayout ( new java.awt.GridBagLayout () );
        GridBagConstraints constraints = new GridBagConstraints ();


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;

        /*
        button = new JButton ( "Install" );
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add ( button, constraints );

        button = new JButton ( "To populate courses dictionaries" );
        constraints.gridx = 1;
        constraints.gridy = 0;
        container.add ( button, constraints );

        button = new JButton ( "Registered Students" );
        constraints.gridx = 2;
        constraints.gridy = 0;
        container.add ( button, constraints );

        button = new JButton ( "About" );
        constraints.gridx = 3;
        constraints.gridy = 0;
        container.add ( button, constraints );


         */

        Font font = new Font("Verdana", Font.PLAIN, 16);

        //menuBar = new JMenuBar();
        //constraints.gridx = 0;
        //constraints.gridy = 0;
        //container.add ( menuBar, constraints );

        instMenu = new JMenu("Install");
        instMenu.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add ( instMenu, constraints );

        //cleardbItem = new JMenuItem("Clear database");
        //instMenu.add(cleardbItem, constraints);
        //constraints.gridx = 0;
        //constraints.gridy = 1;
        //container.add ( cleardbItem, constraints );

        //populcoursItem = new JMenuItem("Populate dictionaries");
        //instMenu.add(populcoursItem, constraints);
        //constraints.gridx = 0;
        //constraints.gridy = 2;
        //container.add ( populcoursItem, constraints );


        regstudMenu = new JMenu("Registered Students");
        regstudMenu.setFont(font);
        constraints.gridx = 1;
        constraints.gridy = 0;
        container.add ( regstudMenu, constraints );

        aboutMenu = new JMenu("About");
        aboutMenu.setFont(font);
        constraints.gridx = 3;
        constraints.gridy = 0;
        container.add ( aboutMenu, constraints );

        button = new JButton ( "Add course" );
        constraints.ipady = 25;
        constraints.weightx = 0.0;
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 5;
        container.add ( button, constraints );

        button = new JButton ( "Remove course" );
        constraints.ipady = 25;
        constraints.weightx = 0.0;
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 5;
        container.add ( button, constraints );

        label1 = new JLabel ("Enter Student's data");
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add ( label1, constraints );

        surnameLabel = new JLabel ("Surname");
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add ( surnameLabel, constraints );

        nameLabel = new JLabel ("Name");
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add ( nameLabel, constraints );

        phoneLabel = new JLabel ("Phone number");
        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add ( phoneLabel, constraints );

        mailLabel = new JLabel ("E-mail");
        constraints.gridx = 0;
        constraints.gridy = 5;
        container.add ( mailLabel, constraints );

        surnameText = new JTextField(30);
        constraints.gridx = 1;
        constraints.gridy = 2;
        container.add ( surnameText, constraints );

        nameText = new JTextField (30);
        constraints.gridx = 1;
        constraints.gridy = 3;
        container.add ( nameText, constraints );

        phoneText = new JTextField (30);
        constraints.gridx = 1;
        constraints.gridy = 4;
        container.add ( phoneText, constraints );

        mailText = new JTextField (30);
        constraints.gridx = 1;
        constraints.gridy = 5;
        container.add ( mailText, constraints );

        choiceLabel = new JLabel ("Make your choice");
        constraints.gridx = 2;
        constraints.gridy = 1;
        container.add ( choiceLabel, constraints );

        facultyLabel = new JLabel ("Select Faculty");
        constraints.gridx = 2;
        constraints.gridy = 2;
        container.add ( facultyLabel, constraints );

        departLabel = new JLabel ("Select Department");
        constraints.gridx = 2;
        constraints.gridy = 3;
        container.add ( departLabel, constraints );

        courseLabel = new JLabel ("Select Course");
        constraints.gridx = 2;
        constraints.gridy = 4;
        container.add ( courseLabel, constraints );

        String [] faculties ={"faculty1","faculty2","faculty3"};
        facultyBox = new JComboBox (faculties);
        constraints.gridx = 3;
        constraints.gridy = 2;
        container.add ( facultyBox, constraints );

        String [] departments ={"department1","department2","department3"};
        departBox = new JComboBox (departments);
        constraints.gridx = 3;
        constraints.gridy = 3;
        container.add ( departBox, constraints );

        String [] courses ={"course1","course2","course3"};
        courseBox = new JComboBox (courses);
        constraints.gridx = 3;
        constraints.gridy = 4;
        container.add ( courseBox, constraints );

        basketLabel = new JLabel ("Course Basket");
        constraints.gridx = 4;
        constraints.gridy = 1;
        container.add ( basketLabel, constraints );

        listPanel = new JPanel ();
        constraints.gridx = 4;
        constraints.gridy = 2;
        container.add ( listPanel, constraints );

    }
}

