import KotlinClass.Project;
import KotlinClass.Task;
import KotlinConstructor.TaskConstructor;
import KotlinHelpers.Helpers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDurationForm {
    private JPanel panel1;
    private JPanel main_panel;
    private JButton editDuration;
    private JTextField duration;
    private JPanel durationPanel;
    JFrame frame = new JFrame();

    public Integer openDialogCount  = 0;

    public EditDurationForm(String data) {

        editDuration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDialogCount = 0;
                getinput(data);
            }
        });
    }

    public void show(){

        if(this.openDialogCount <= 0){
            this.frame.setBounds(200, 50, 200, 500);
            this.frame.setContentPane(durationPanel);
            this.frame.pack();
            frame.setLocationRelativeTo(null);
            this.frame.setVisible(true);
        }

        this.openDialogCount++;
    }


    public Boolean getinput(String id){
        TaskConstructor taskConstructor = new TaskConstructor();
        Task task = new Task();
        var returndata =  false;
        taskConstructor.settaskDuration(duration.getText());
        Boolean response = task.EditDuration(id, taskConstructor.gettaskDuration());

        if(response){
            JOptionPane.showMessageDialog(main_panel,"Task Succesfully edited");
            returndata = true;
        }else{
            JOptionPane.showMessageDialog(main_panel,"Unable to edit");
            returndata = false;
        }
        return returndata;
    }
}
