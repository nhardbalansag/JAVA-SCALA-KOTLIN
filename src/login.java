import KotlinClass.ProgressStatus;
import KotlinClass.User;
import KotlinConstructor.UserConstructor;
import ScalaState.ProcessScala;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {
    JFrame frame = new JFrame();
    private JPanel panel_login_main;
    private JButton btn_login;
    private JTextField textField_username;
    private JPasswordField textField_password;
    private JPanel panel_login_secondmain;
    private JLabel lbl_login;
    private JLabel lbl_password;
    private JButton btn_register;

    public login() {

        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showRegister();
            }
        });
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getinput();
            }
        });
    }

    public void show(){
        ProcessScala processScala = new ProcessScala();
        processScala.processScala("p-id-1");
        ProgressStatus progressStatus = new ProgressStatus();
        progressStatus.ProcessKotlin("p-id-1");

        this.frame.setBounds(200, 50, 1000, 700);
        this.frame.setContentPane(panel_login_main);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void showRegister(){
        register registerScreen = new register();
        registerScreen.show();
    }

    public void getinput(){
        String passwordData = String.valueOf(textField_password.getPassword());
        UserConstructor user_contruct = new UserConstructor();
        User user_class = new User();

        user_contruct.setUsername(textField_username.getText());
        user_contruct.setPassword(passwordData);

        Boolean result =  user_class.readOne(user_contruct.getUsername(), user_contruct.getpassword());
        if(result){
            JOptionPane.showMessageDialog(frame,"Login Succesfully");
            showProjects();
        }else{
            JOptionPane.showMessageDialog(frame,"Login Failed");
        }
    }

    public void showProjects(){
        ProjectMain projectMain = new ProjectMain();
        projectMain.show();
        frame.dispose();
    }
}
