import KotlinClass.Task;
import KotlinConstructor.TaskConstructor;
import KotlinHelpers.Helpers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuccesorForm {
    private JPanel main_panel;
    private JComboBox task_successor_info;
    private JButton create_task_btn;
    private JPanel add_succesor;
    JFrame frame = new JFrame();

    public Integer openDialogCount  = 0;

    public SuccesorForm(String data) {
        create_task_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDialogCount = 0;
                getinput(data);
            }
        });
    }

    public void show(){

        if(this.openDialogCount <= 0){
            getSuccesorData();
            this.frame.setBounds(200, 50, 200, 500);
            this.frame.setContentPane(add_succesor);
            this.frame.pack();
            frame.setLocationRelativeTo(null);
            this.frame.setVisible(true);
        }

        this.openDialogCount++;
    }

    public void getSuccesorData(){
        Helpers helpers = new Helpers();
        Task task = new Task();
        int arrayCount;
        arrayCount = helpers.readAll(1, task.filename()).length;
        for(int i = 0; i < arrayCount; i++){
            task_successor_info.addItem(helpers.readAll(2, task.filename())[i]);
        }
    }

    public void getinput(String id){
        TaskConstructor taskConstructor = new TaskConstructor();
        Task task = new Task();
        Helpers helpers = new Helpers();
        int index = task_successor_info.getSelectedIndex();

        taskConstructor.setSuccesor_task(helpers.readAll(0, task.filename())[index]);

        Boolean response = task.insertSuccessor(id, taskConstructor.getSuccessortask());

        if(response){
            JOptionPane.showMessageDialog(main_panel,"Task Succesfully Created");
        }else{
            JOptionPane.showMessageDialog(main_panel,"Unable to create");
        }
    }
}
