package CollegeAdmissionSystem.Presentation;

import CollegeAdmissionSystem.Entity.Course;
import CollegeAdmissionSystem.Entity.Department;
import CollegeAdmissionSystem.Entity.Direction;
import CollegeAdmissionSystem.Entity.Student;
import CollegeAdmissionSystem.Repository.IDepartmentReadonlyRepository;
import CollegeAdmissionSystem.Repository.IStudentRepository;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.function.IntFunction;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

public class GridBagLayout {
    private final List<Department> departments;
    private IDepartmentReadonlyRepository _departmentRepository;
    private IStudentRepository _studentRepository;
    private JLabel lastNameLbl;
    private JTextField lastNameTxt  , surnameText, nameText, phoneText, mailText,BirthDayText,IdText;
    JComboBox facultyBox =new JComboBox(), departBox=new JComboBox(), courseBox=new JComboBox();
    DefaultTableModel tableModel = new DefaultTableModel( new String[0][0],new String[] {"Course Name", "Department","Direction","Hours per week","Course Id"});
    JTable StudentCourses =new JTable(tableModel);
    JScrollPane pane = new JScrollPane(StudentCourses);
    public void setSavedStudent(Student savedStudent) {
        if(savedStudent !=null)
        {
            surnameText.setText(savedStudent.SurName);
            lastNameTxt.setText(savedStudent.LastName);
            nameText.setText(savedStudent.Name);
            phoneText.setText(savedStudent.Phone);
            IdText.setText(String.valueOf(savedStudent.Id));
            BirthDayText.setText(savedStudent.Birthday);
            this.savedStudent = savedStudent;
            tableModel.setRowCount(0);

            if (savedStudent.Courses==null)return;//todo сделать чтобы при мапинге данных из бд в поле спиского типа оно создавалось  пустым

            for (Course course : savedStudent.Courses) {
                Vector<String> row= new Vector<String>();
                row.add(course.Name);
                row.add(course.Direction.Department.Name);
                row.add(course.Direction.Name);
                row.add(String.valueOf(course.HoursPerWeek));
                row.add(String.valueOf(course.Id));
                tableModel.addRow(row);
            }

        }


    }

    private Student savedStudent;

    public GridBagLayout(IDepartmentReadonlyRepository departmentRepository, IStudentRepository studentRepository) {
        _departmentRepository = departmentRepository;
        _studentRepository = studentRepository;
       departments = _departmentRepository.getDepartments();
        for (Department department : departments) {
            facultyBox.addItem(department.Name);
        }
        facultyBox.addItemListener(e -> {
         //   System.out.println(" facultyBox.addItemListener");
            var directions=departments.get(facultyBox.getSelectedIndex()).Directions;
            departBox.removeAllItems();
            courseBox.removeAllItems();
            addCourseBtn.setEnabled(false);
            if(directions==null)return;
            for (Direction direction : directions) {
                departBox.addItem(direction.Name);
            }
        });
        departBox.addItemListener(e -> {
         //   System.out.println(" departBox.addItemListener");
            courseBox.removeAllItems();
            addCourseBtn.setEnabled(false);
            var directions=departments.get(facultyBox.getSelectedIndex()).Directions;
            if(directions==null||departBox.getSelectedIndex()<0)return;
            var courses=directions.get(departBox.getSelectedIndex()).Courses;
            if(courses==null) return;
            for (Course cours : courses) {
                courseBox.addItem(cours.Name);
            }
        });
        courseBox.addItemListener(e ->{
            addCourseBtn.setEnabled(true);

        });
    }

    private  JButton addCourseBtn;
    private JButton removeCourseBtn;
    private final String[] studentInputLablText=new String[]
            {//"Enter Student's data"	,
            "Sur Name",
            "Last Name",
            "Name",
            "Phone number",
            "E-mail",
             "BirthDay",
             "Number"
            };
    public  void createPanelUI(Container container) {
     //   JButton button;
        JLabel label1, surnameLabel, nameLabel, phoneLabel, mailLabel, choiceLabel,
                facultyLabel, departLabel, courseLabel, basketLabel;


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

        addCourseBtn = new JButton ( "Add course" );
      addCourseBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              if(facultyBox.getSelectedIndex()<0) return;
              var directions=departments.get(facultyBox.getSelectedIndex()).Directions;
              if(directions==null||departBox.getSelectedIndex()<0)return;
              var courses=directions.get(departBox.getSelectedIndex()).Courses;
              if(courses==null||courseBox.getSelectedIndex()<0) return;
              if(savedStudent==null) return;
              setSavedStudent(_studentRepository.AddCourseToStudent(savedStudent.Id,courses.get(courseBox.getSelectedIndex()).Id));
          }
      });
        addCourseBtn.setEnabled(false);
        constraints.ipady = 25;
        constraints.weightx = 0.0;
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 5;
        container.add ( addCourseBtn, constraints );

         removeCourseBtn = new JButton ( "Remove course" );
           removeCourseBtn.addActionListener(new
                                                  ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent e) {

                                                          setSavedStudent(
                                                                  _studentRepository.RemoveCourseFromStudent(savedStudent.Id,savedStudent.Courses.get( StudentCourses.getSelectedRow()).Id)
                                                          );

                                                      }
                                                  });
        constraints.ipady = 25;
        constraints.weightx = 0.0;
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 6;
        container.add ( removeCourseBtn, constraints );
        constraints.gridy = 0;
        constraints.gridx = 0;
        label1 = new JLabel ("Enter Student's data");
        JLabel []studentLbls=new JLabel[studentInputLablText.length];
        JTextField[] studentTxtFields = new JTextField[studentInputLablText.length];
        for (String s : studentInputLablText) {
            studentLbls[constraints.gridy]=new JLabel(s);
            studentTxtFields[constraints.gridy] = new JTextField(30);
            studentTxtFields[constraints.gridy].setToolTipText(s);
            constraints.gridy++;
            container.add ( studentLbls[constraints.gridy-1], constraints );
            constraints.gridx++;
            container.add ( studentTxtFields[constraints.gridy-1], constraints );
            constraints.gridx--;
        };
            /*
"Sur Name","Last Name","Name","Phone number","E-mail","BirthDay","Number"
       Сохраним ссылки на элементы массива  чтобы ими было легче оперировать
        */
        surnameText= studentTxtFields[0] ;
        lastNameTxt= studentTxtFields[1] ;
        nameText= studentTxtFields[2] ;
        phoneText= studentTxtFields[3] ;
        mailText= studentTxtFields[4] ;
        BirthDayText=studentTxtFields[5] ;
        IdText= studentTxtFields[6] ;

        IdText.setEditable(false);
        constraints.gridy++  ;

        JButton save2dbBtn = new JButton("Save 2 db");
        save2dbBtn.addActionListener(e -> {
            String txt =BirthDayText.getText();
            //Wed Jun 14 18:43:25 MSK 2023
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM d hh:mm:ss G yyyy");
            Date dd=null;
            try {
             dd= format.parse(txt);

            } catch (ParseException ex) {

            }
            setSavedStudent(_studentRepository.CreateStudent(lastNameTxt.getText(),nameText.getText(),surnameText.getText(),mailText.getText(),phoneText.getText(),dd));

        });
        container.add (save2dbBtn, constraints );
        mailText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println(((JTextField)e.getSource()).getText());
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField src=((JTextField)e.getSource());
                Student studentByEmail = _studentRepository.getStudentByEmail(src.getText());
                setSavedStudent(studentByEmail);

            }
        });

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






        constraints.gridx = 3;
        constraints.gridy = 2;
        container.add ( facultyBox, constraints );
        facultyBox.setSelectedIndex(0);
        //facultyBox.setSelectedIndex(0);

        //String [] department1s ={"department1","department2","department3"};

        constraints.gridx = 3;
        constraints.gridy = 3;
        container.add ( departBox, constraints );



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
        listPanel.add(StudentCourses);
        container.add ( listPanel, constraints );

    }
}

