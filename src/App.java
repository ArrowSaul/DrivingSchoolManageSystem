import com.mysql.Mysql;
import ui.LoginJFrame;
import ui.MainJFrame;

public class App {
    public static void main(String[] args) {
       new Mysql("root","123456");
        MainJFrame mainJFrame =new MainJFrame();
        //LoginJFrame loginJFrame = new LoginJFrame();
        //RegisterJFrame registerJFrame =new RegisterJFrame();
        //ActionCoachAddJFrame actionCoachAddJFrame=new ActionCoachAddJFrame();
    }
}