import KotlinClass.ProgressStatus;
import KotlinClass.Project;
import KotlinClass.Task;
import KotlinClass.Team;
import KotlinConstructor.ProjectConstructor;
import KotlinConstructor.TaskConstructor;
import KotlinConstructor.TeamConstructor;
import KotlinHelpers.Helpers;
import ScalaState.ProcessScala;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.Duration;

public class ProjectMain {
    JFrame frame = new JFrame();
    private JPanel mainpanel;
    private JTabbedPane tabbedPane_main;
    private JTextField project_name;
    private JButton createProject;
    private JComboBox projectStatus;
    private JTable team_table;
    private JButton create_team_btn;
    private JTextField team_name;
    private JComboBox team_status;
    private JTable project_table;
    private JTextField task_name;
    private JComboBox task_team_assigned;
    private JTextField task_duration;
    private JButton create_task_btn;
    private JComboBox task_status;
    private JPanel create_task_panel;
    private JTable task_table;
    private JComboBox task_project_name;
    private JTable project_related_task;
    private JComboBox project_name_select;
    private JButton btn_kotlin;
    private JButton btn_scala;
    private JLabel projectname;
    private JLabel projectduration;
    private JLabel projectdatefinish;
    private JPanel project_create_newproject;
    private JPanel create_team_panel;


    public String projectidname;
    public ProjectMain() {
        createProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInput();
                showtable();
                selectProgress();
            }
        });
        create_team_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showtable();
                getTeamInput();
                showTeamTableData();
            }
        });
        tabbedPane_main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showTeamTableData();
            }
        });
        tabbedPane_main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showtable();
            }
        });
        create_task_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTaskInput();
                showtasktable();
            }
        });
        tabbedPane_main.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                teamList();
                showtasktable();
            }
        });
        task_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                taskConfuration(task_table.getModel().getValueAt(task_table.getSelectedRow(), 0).toString());
            }
        });
        tabbedPane_main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                selectProgress();
            }
        });
        btn_kotlin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProgressStatus progressStatus = new ProgressStatus();
                Helpers helpers = new Helpers();
                Project project = new Project();
                int index = project_name_select.getSelectedIndex();
                var project_id = helpers.readAll(0, project.filename())[index];
                projectidname = (String) project_name_select.getSelectedItem();
                progressStatus.ProcessKotlin(project_id);
                showtaskRelatedToProject(project_id);
                showProjectDuration(project_id);
            }
        });
        btn_scala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcessScala processScala = new ProcessScala();
                Helpers helpers = new Helpers();
                Project project = new Project();

                int index = project_name_select.getSelectedIndex();
                var project_id = helpers.readAll(0, project.filename())[index];
                projectidname = (String) project_name_select.getSelectedItem();
                processScala.processScala(project_id);
                showtaskRelatedToProject(project_id);
                showProjectDuration(project_id);
            }
        });
        project_related_task.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                EditDuration(project_related_task.getModel().getValueAt(project_related_task.getSelectedRow(), 0).toString());
            }
        });
    }

    public void selectProgress(){
        Helpers helpers = new Helpers();
        Project project = new Project();
        int arrayCount = 0;

        arrayCount = helpers.readAll(1, project.filename()).length;
        project_name_select.removeAllItems();
        for(int i = 0; i < arrayCount; i++){
            project_name_select.addItem(helpers.readAll(1, project.filename())[i]);
        }
    }

    public void EditDuration(String index){
        EditDurationForm editDurationForm = new EditDurationForm(index);
        editDurationForm.show();
    }

    public void taskConfuration(String data) {
        SuccesorForm succesorForm = new SuccesorForm(data);
        succesorForm.show();
    }
    public void show(){
        showtable();

        projectStatus.addItem("active");
        projectStatus.addItem("pending");

        team_status.addItem("active");
        team_status.addItem("pending");

        this.frame.setBounds(200, 50, 1000, 700);
        this.frame.setContentPane(mainpanel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void teamList(){
        task_status.addItem("active");
        task_status.addItem("pending");

        Helpers helpers = new Helpers();
        Team team = new Team();
        Project project = new Project();
        int arrayCount = 0;

        arrayCount = helpers.readAll(1, team.filename()).length;
        for(int i = 0; i < arrayCount; i++){
            task_team_assigned.addItem(helpers.readAll(1, team.filename())[i]);
        }
        arrayCount = helpers.readAll(1, project.filename()).length;
        for(int i = 0; i < arrayCount; i++){
            task_project_name.addItem(helpers.readAll(1, project.filename())[i]);
        }
    }

    public void showTeamTableData(){
        Team team = new Team();
        Helpers helpers = new Helpers();

        DefaultTableModel model = new DefaultTableModel();
        int arrayCount = helpers.readAll(0, team.filename()).length;

        String[] name = new String[arrayCount];
        String[] datecreated = new String[arrayCount];
        String[] status = new String[arrayCount];

        int division = 0;

        for(int i = 0; i < arrayCount; i++){
            name[i] = helpers.readAll(1, team.filename())[i];
            status[i] = helpers.readAll(2, team.filename())[i];
            datecreated[i] = helpers.readAll(3, team.filename())[i];
        }

        model.addColumn("TEAM NAME", name);
        model.addColumn("STATUS", status);
        model.addColumn("DATE CREATED", datecreated);
        team_table.setModel(model);
    }

    public void showtable(){

        Project project = new Project();
        Helpers helpers = new Helpers();

        DefaultTableModel model = new DefaultTableModel();
        int arrayCount = helpers.readAll(0, project.filename()).length;

        String[] id = new String[arrayCount];
        String[] name = new String[arrayCount];
        String[] datecreated = new String[arrayCount];
        String[] status = new String[arrayCount];

        for(int i = 0; i < arrayCount; i++){
            id[i] = helpers.readAll(0, project.filename())[i];
            this.projectidname = id[i];
            name[i] = helpers.readAll(1, project.filename())[i];
            status[i] = helpers.readAll(2, project.filename())[i];
            datecreated[i] = helpers.readAll(3, project.filename())[i];
        }

        model.addColumn("PROJECT ID", id);
        model.addColumn("PROJECT NAME", name);
        model.addColumn("STATUS", status);
        model.addColumn("DATE CREATED", datecreated);

        project_table.setModel(model);
    }

    public void showProjectDuration(String index){
        ProgressStatus progressStatus = new ProgressStatus();

        projectname.setText(progressStatus.ProcessKotlin(index)[0]);
        projectduration.setText(progressStatus.ProcessKotlin(index)[1]);
        projectdatefinish.setText(progressStatus.ProcessKotlin(index)[2]);

    }

    public void showtaskRelatedToProject(String projectId){
        Task task = new Task();
        Helpers helpers = new Helpers();

        DefaultTableModel model = new DefaultTableModel();
        int arrayCount = helpers.countRelatedFile(task.filename(), projectId, 1);

        String[] id = new String[arrayCount];
        String[] taskname = new String[arrayCount];
        String[] team = new String[arrayCount];
        String[] duration = new String[arrayCount];
        String[] predecesor = new String[arrayCount];

        for(int i = 0; i < arrayCount; i++){
            id[i] = task.showTaskRelatedTask(projectId, 0)[i]; // id
            taskname[i] = task.showTaskRelatedTask(projectId, 2)[i]; // task name
            team[i] = task.showTaskRelatedTask(projectId, 3)[i]; // team
            duration[i] = task.showTaskRelatedTask(projectId, 6)[i]; // duration
            predecesor[i] = task.showTaskRelatedTask(projectId, 7)[i]; // predecessor
        }

        model.addColumn("ID", id);
        model.addColumn("TASK NAME", taskname);
        model.addColumn("TEAM", team);
        model.addColumn("DURATION", duration);
        model.addColumn("PREDECESSOR", predecesor);

        project_related_task.setModel(model);
    }

    public void showtasktable(){
        Task task = new Task();
        Helpers helpers = new Helpers();

        DefaultTableModel model = new DefaultTableModel();
        int arrayCount = helpers.readAll(0, task.filename()).length;

        String[] taskname = new String[arrayCount];
        String[] datecreated = new String[arrayCount];
        String[] status = new String[arrayCount];
        String[] team = new String[arrayCount];
        String[] project = new String[arrayCount];
        String[] duration = new String[arrayCount];
        String[] id = new String[arrayCount];

        for(int i = 0; i < arrayCount; i++){
            id[i] = helpers.readAll(0, task.filename())[i];
            project[i] = helpers.readAll(1, task.filename())[i];
            taskname[i] = helpers.readAll(2, task.filename())[i];
            team[i] = helpers.readAll(3, task.filename())[i];
            status[i] = helpers.readAll(4, task.filename())[i];
            datecreated[i] = helpers.readAll(5, task.filename())[i];
            duration[i] = helpers.readAll(6, task.filename())[i];
        }

        model.addColumn("ID", id);
        model.addColumn("PROJECT", project);
        model.addColumn("TASK", taskname);
        model.addColumn("TEAM", team);
        model.addColumn("STATUS", status);
        model.addColumn("DATE CREATED", datecreated);
        model.addColumn("DURATION", duration);

        task_table.setModel(model);
    }

    public void getTeamInput(){
        Helpers helper = new Helpers();
        Team team = new Team();
        TeamConstructor teamConstructor = new TeamConstructor();

        int recordCount = helper.countRecord(team.filename());
        // create id incremented to the file count
        recordCount = recordCount + 1;

        teamConstructor.setId(Integer.toString(recordCount));
        teamConstructor.setteamname(team_name.getText());
        teamConstructor.setTeamstatus((String)team_status.getSelectedItem());
        teamConstructor.setCreatedAt(helper.generateDate());

        Boolean response = team.create(
                teamConstructor.getId(),
                teamConstructor.getteamname(),
                teamConstructor.getteamstatus(),
                teamConstructor.getcreatedAt()
        );

        if(response){
            JOptionPane.showMessageDialog(frame,"Project Succesfully Created");
        }else{
            JOptionPane.showMessageDialog(frame,"Unable to create");
        }
    }

    public void getInput(){
        // count data in file
        
        Helpers helper = new Helpers();
        Project project = new Project();
        ProjectConstructor projectConstructor = new ProjectConstructor();

        int recordCount = helper.countRecord(project.filename());
        // create id incremented to the file count
        recordCount = recordCount + 1;

        projectConstructor.setId(Integer.toString(recordCount));
        projectConstructor.setProjectname(project_name.getText());
        projectConstructor.setStatus((String)projectStatus.getSelectedItem());
        projectConstructor.setCreatedAt(helper.generateDate());

        Boolean response = project.create(
                projectConstructor.getId(),
                projectConstructor.getprojectname(),
                projectConstructor.getStatus(),
                projectConstructor.getcreatedAt()
        );

        if(response){
            JOptionPane.showMessageDialog(frame,"Project Succesfully Created");
        }else{
            JOptionPane.showMessageDialog(frame,"Unable to create");
        }
    }

    public void getTaskInput(){
        // count data in file

        Helpers helper = new Helpers();
        Task task = new Task();
        Project project =  new Project();

        TaskConstructor taskConstructor = new TaskConstructor();

        int recordCount = helper.countRecord(task.filename());
        // create id incremented to the file count
        recordCount = recordCount + 1;

        int index = task_project_name.getSelectedIndex();
        taskConstructor.setProjectname(helper.readAll(0, project.filename())[index]);

        taskConstructor.setId(Integer.toString(recordCount));
        taskConstructor.setTaskname(task_name.getText());
        taskConstructor.setTaskTeam((String)task_team_assigned.getSelectedItem());
        taskConstructor.setTaskStatus((String)task_status.getSelectedItem());
        taskConstructor.setTaskDuration(task_duration.getText());
        taskConstructor.setCreatedAt(helper.generateDate());

        Boolean response = task.create(
                taskConstructor.getId(),
                taskConstructor.getproject_name(),
                taskConstructor.gettaskname(),
                taskConstructor.getTaskTeam(),
                taskConstructor.gettaskStatus(),
                taskConstructor.getcreatedAt(),
                taskConstructor.getTaskDuration()
        );

        if(response){
            JOptionPane.showMessageDialog(frame,"Task Succesfully Created");
        }else{
            JOptionPane.showMessageDialog(frame,"Unable to create");
        }
    }
}
