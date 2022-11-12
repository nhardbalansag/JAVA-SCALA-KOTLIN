import KotlinClass.User;
import KotlinConstructor.UserConstructor;
import KotlinHelpers.Helpers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class register {
    JFrame frame = new JFrame();
    private JPanel panel_login_main;
    private JTextField firstname;
    private JPasswordField password;
    private JButton registerButton1;
    private JButton btn_alreadyRegistered;
    private JTextField lastname;
    private JTextField username;

    public register() {
        registerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Helpers helper = new Helpers();
                UserConstructor user_getset = new UserConstructor();
                User user = new User();

                user_getset.setId(1);
                user_getset.setfirstname(firstname.getText());
                user_getset.setlastname(lastname.getText());
                user_getset.setUsername(username.getText());
                user_getset.setCreatedAt(helper.generateDate());

                String passwordData = String.valueOf(password.getPassword());
                user_getset.setPassword(passwordData);

                Boolean response = user.create(
                        user_getset.getId(),
                        user_getset.getFirstname(),
                        user_getset.getlastname(),
                        user_getset.getUsername(),
                        user_getset.getPassword(),
                        user_getset.getcreatedAt()
                );

                if(response){
                    JOptionPane.showMessageDialog(frame,"Registered Succesfully");
                    frame.dispose();
                    showLogin();
                }
            }
        });
        btn_alreadyRegistered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showLogin();
            }
        });
    }

    public void show(){

        this.frame.setBounds(200, 50, 1000, 700);
        this.frame.setContentPane(panel_login_main);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void showLogin(){
        login loginScreen = new login();
        loginScreen.show();
    }
}
