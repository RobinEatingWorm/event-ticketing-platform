package screens;

import javax.swing.*;

public class SuccessViewModel {

    private String orgName;

    public SuccessViewModel(String orgName){
        this.orgName=orgName;
    }

    public void generatePage() {
        JFrame frame= new JFrame();
        JPanel panelMain= new JPanel();
        panelMain.add(new JLabel("You Are Following Organizer:"+ this.orgName ));
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("System Message");
        frame.setBounds(600,300,250,200);
        frame.setVisible(true);

    }

}

