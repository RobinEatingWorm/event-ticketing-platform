package LeaveTheEventScreen;

import javax.swing.*;

public class SuccessfulViewModule {

    private String message;

    public static void main(String[] args) {
        FaliureViewModel b = new FaliureViewModel("you have succeed in leaving this event");
        b.GeneratePage();
    }

    public SuccessfulViewModule(String message){
        this.message = message;
    }

    public void GeneratePage(){
        JFrame jf = new JFrame(" ");
        jf.setBounds(500, 250, 400, 300);

        JLabel jLabel = new JLabel(this.message, SwingConstants.CENTER);
        jf.add(jLabel);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

