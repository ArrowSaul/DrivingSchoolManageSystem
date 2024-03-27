package com.jframeevent.add;

import com.actionlistener.CoachManagement;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ui.MainJFrame.jPanel2;

public class ActionCoachAddJFrame extends JFrame implements ActionListener {
    //定义增添按钮
    JButton add = new JButton();
    //定义教练姓名，手机号码，身份证号，教练车牌，车辆型号的输入框
    JTextField coachname = new JTextField();
    JTextField phonenumber = new JTextField();
    JTextField idnumber = new JTextField();
    JTextField coachcar = new JTextField();
    JTextField cartype = new JTextField();

    //初始化界面
    public void initJFrame() {
        /*this.repaint();*/
        this.setSize(500, 600);//设置宽高
        this.setTitle("教练管理");//设置标题
        this.setDefaultCloseOperation(2);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initView() {
        SwingUtilities.updateComponentTreeUI(this);
        //1.添加教练管理文字
        JLabel drivingschoolManagementsystem = new JLabel("教 练 管 理");
        drivingschoolManagementsystem.setForeground(Color.black);
        drivingschoolManagementsystem.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 28));
        drivingschoolManagementsystem.setBounds(170, 40, 300, 80);
        this.getContentPane().add(drivingschoolManagementsystem);

        //2. 添加教练姓名文字
        JLabel coachnameText = new JLabel("教练姓名:");
        coachnameText.setForeground(Color.black);
        coachnameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        coachnameText.setBounds(115, 103, 100, 80);
        this.getContentPane().add(coachnameText);

        //3.添加教练姓名输入框
        coachname.setBounds(200, 130, 150, 30);
        this.getContentPane().add(coachname);

        //4. 添加手机号码文字
        JLabel phonenumberText = new JLabel("手机号码:");
        phonenumberText.setForeground(Color.black);
        phonenumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        phonenumberText.setBounds(115, 143, 100, 80);
        this.getContentPane().add(phonenumberText);

        //5.添加手机号码输入框
        phonenumber.setBounds(200, 170, 150, 30);
        this.getContentPane().add(phonenumber);

        //6. 添加身份证号文字
        JLabel idnumberText = new JLabel("身份证号:");
        idnumberText.setForeground(Color.black);
        idnumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        idnumberText.setBounds(115, 183, 100, 80);
        this.getContentPane().add(idnumberText);

        //7.添加身份证号输入框
        idnumber.setBounds(200, 210, 150, 30);
        this.getContentPane().add(idnumber);
        //8. 添加教练车牌文字
        JLabel coachcarText = new JLabel("教练车牌:");
        coachcarText.setForeground(Color.black);
        coachcarText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        coachcarText.setBounds(115, 223, 100, 80);
        this.getContentPane().add(coachcarText);

        //9.添加教练车牌输入框
        coachcar.setBounds(200, 250, 150, 30);
        this.getContentPane().add(coachcar);
        //10. 添加车辆型号文字
        JLabel cartypeText = new JLabel("车辆型号:");
        cartypeText.setForeground(Color.black);
        cartypeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        cartypeText.setBounds(115, 263, 100, 80);
        this.getContentPane().add(cartypeText);

        //11.添加车辆型号输入框
        cartype.setBounds(200, 290, 150, 30);
        this.getContentPane().add(cartype);

        //12.添加增添按钮
        add.setBounds(150, 400, 150, 47);
        add.setIcon(new ImageIcon(".\\image\\add.jpg"));
        //去除按钮的边框
        add.setBorderPainted(false);
        //去除按钮的背景
        add.setContentAreaFilled(true);
        //给登录按钮绑定鼠标事件
        add.addActionListener(this);
        this.getContentPane().add(add);

        //8.添加背景图片
        JLabel background = new JLabel(new ImageIcon(""));
        background.setBounds(0, 0, 1000, 600);
        this.getContentPane().add(background);
    }
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);
        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        //让弹框展示出来
        jDialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //初始化界面
        initJFrame();
        //在这个界面中添加内容
        initView();
        //让当前界面显示出来
        this.setVisible(true);

        if (e.getSource() == add) {
            System.out.println("点击了增添按钮");
            //获取五个文本输入框中的内容
            String coachnameinfo = coachname.getText();
            String phonenumberinfo = phonenumber.getText();
            String idnumberinfo = idnumber.getText();
            String coachcarinfo = coachcar.getText();
            String cartypeinfo = cartype.getText();
            String[] arr = new String[]{coachnameinfo, phonenumberinfo, idnumberinfo, coachcarinfo, cartypeinfo};
            if (arr[0].equals("") || arr[1].equals("") || arr[2].equals("") || arr[3].equals("") || arr[4].equals("")) {
                System.out.println("输入的内容不完整，请继续补充");
                showJDialog("输入的内容不完整，请继续补充");
            } else {
                System.out.println(arr[0]);
                Connection con = Mysql.con;
                PreparedStatement preSql;
                String Uri = "insert into coachmanage values (?,?,?,?,?)";
                try {
                    preSql = con.prepareStatement(Uri);
                    preSql.setString(1, coachnameinfo);
                    preSql.setString(2, phonenumberinfo);
                    preSql.setString(3, idnumberinfo);
                    preSql.setString(4, coachcarinfo);
                    preSql.setString(5, cartypeinfo);
                    preSql.executeUpdate();
                    showJDialog("添加成功");
                    jPanel2.removeAll();
                    CoachManagement.table();
                    jPanel2.revalidate();
                    jPanel2.repaint();
                    this.setVisible(false);
                }   catch (SQLException e1) {
                    //调用showJDialog方法并展示弹框
                    showJDialog("添加失败");
                    //关闭当前界面
                    this.setVisible(false);
                }
            }
        }
    }
}
