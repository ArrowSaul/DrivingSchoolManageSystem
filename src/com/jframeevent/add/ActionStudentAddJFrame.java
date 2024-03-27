package com.jframeevent.add;

import com.actionlistener.StudentbasicinformationManagement;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static ui.MainJFrame.jPanel2;

public class ActionStudentAddJFrame extends JFrame implements ActionListener {

    //定义增添按钮
    JButton add = new JButton();
    //定义姓名，身份证号，手机号码，地址，申请类型，报名时间，所属教练的输入框
    JTextField name = new JTextField();
    JTextField idnumber = new JTextField();
    JTextField phonenumber = new JTextField();
    JTextField address = new JTextField();
    JTextField applytype = new JTextField();
    JTextField registrationtime = new JTextField();
    JTextField ofcoach = new JTextField();

    //初始化界面
    public void initJFrame() {
        this.setSize(500, 600);//设置宽高
        this.setTitle("学员基本信息管理");//设置标题
        this.setDefaultCloseOperation(2);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initView() {
        //1.添加学员基本信息管理文字
        JLabel drivingschoolManagementsystem = new JLabel("学员基本信息管理");
        drivingschoolManagementsystem.setForeground(Color.black);
        drivingschoolManagementsystem.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 28));
        drivingschoolManagementsystem.setBounds(170, 40, 300, 80);
        this.getContentPane().add(drivingschoolManagementsystem);

        //2. 添加姓名文字
        JLabel nameText = new JLabel("姓名:");
        nameText.setForeground(Color.black);
        nameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        nameText.setBounds(115, 143, 100, 80);
        this.getContentPane().add(nameText);
        //3.添加姓名输入框
        name.setBounds(200, 170, 150, 30);
        this.getContentPane().add(name);

        //4. 添加身份证号文字
        JLabel idnumberText = new JLabel("身份证号:");
        idnumberText.setForeground(Color.black);
        idnumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        idnumberText.setBounds(115, 183, 100, 80);
        this.getContentPane().add(idnumberText);

        //5.添加身份证号输入框
        idnumber.setBounds(200, 210, 150, 30);
        this.getContentPane().add(idnumber);
        //6. 添加手机号码文字
        JLabel phonenumberText = new JLabel("手机号码:");
        phonenumberText.setForeground(Color.black);
        phonenumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        phonenumberText.setBounds(115, 223, 100, 80);
        this.getContentPane().add(phonenumberText);

        //7.添加手机号码输入框
        phonenumber.setBounds(200, 250, 150, 30);
        this.getContentPane().add(phonenumber);
        //8. 添加地址文字
        JLabel addressText = new JLabel("地址:");
        addressText.setForeground(Color.black);
        addressText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        addressText.setBounds(115, 263, 100, 80);
        this.getContentPane().add(addressText);

        //9.添加地址输入框
        address.setBounds(200, 290, 150, 30);
        this.getContentPane().add(address);
        //10. 添加申请类型文字
        JLabel applytypeText = new JLabel("申请类型:");
        applytypeText.setForeground(Color.black);
        applytypeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        applytypeText.setBounds(115, 303, 100, 80);
        this.getContentPane().add(applytypeText);

        //11.添加申请类型输入框
        applytype.setBounds(200, 330, 150, 30);
        this.getContentPane().add(applytype);
        //12. 添加报名时间文字
        JLabel registrationtimeText = new JLabel("报名时间:");
        registrationtimeText.setForeground(Color.black);
        registrationtimeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        registrationtimeText.setBounds(115, 343, 100, 80);
        this.getContentPane().add(registrationtimeText);
        //13.添加报名时间输入框
        registrationtime.setBounds(200, 370, 150, 30);
        this.getContentPane().add(registrationtime);
        //14. 添加所属教练文字
        JLabel ofcoachText = new JLabel("所属教练:");
        ofcoachText.setForeground(Color.black);
        ofcoachText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        ofcoachText.setBounds(115, 383, 100, 80);
        this.getContentPane().add(ofcoachText);
        //15.添加所属教练输入框
        ofcoach.setBounds(200, 410, 150, 30);
        this.getContentPane().add(ofcoach);

        //16.添加增添按钮
        add.setBounds(150, 470, 150, 47);
        add.setIcon(new ImageIcon(".\\image\\add.jpg"));
        //去除按钮的边框
        add.setBorderPainted(false);
        //去除按钮的背景
        add.setContentAreaFilled(true);
        //给登录按钮绑定鼠标事件
        add.addActionListener(this);
        this.getContentPane().add(add);

        //17.添加背景图片
        JLabel background = new JLabel(new ImageIcon(""));
        background.setBounds(0, 0, 1000, 600);
        this.getContentPane().add(background);
    }
    public static String getstudentid() {
        Connection con = Mysql.con;
        String max_Value;
        try {
            new Mysql("root", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(学员编号)  as max_value FROM studentinformmanage");
            try {
                if (rs.next()) {
                    //获取表中学员编号的值
                    max_Value = rs.getString("max_value");
                    //取出XY后的字符串
                    String end = max_Value.substring(2);
                    //将字符串转化为数字
                    int num = Integer.parseInt(end);
                    //数字加一
                    num = num + 1;
                    //将加一后的数字转换为字符串
                    String s1 = Integer.toString(num);
                    //位数判断
                    int wei=pan(num);
                    //根据不同的位数补充不同的字符串
                    if (wei==1){
                        String s2 = "XY00000"+s1;
                        return s2;
                    }else if(wei==2){
                        String s2 = "XY0000"+s1;
                        return s2;
                    }else if(wei==3){
                        String s2 = "XY000"+s1;
                        return s2;
                    }else if(wei==4){
                        String s2 = "XY00"+s1;
                        return s2;
                    }else if(wei==5){
                        String s2 = "XY0"+s1;
                        return s2;
                    }else if(wei==6){
                        String s2 = "XY"+s1;
                        return s2;
                    }
                }
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
                e1.printStackTrace();
            }
            rs.close();
            stmt.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //如果为空返回XY000000
        return "XY000000";
    }

    //对XY后的字符串转换得到的数字加一后的数字进行位数判断
    private static int pan(int num) {
        int count = 0;
        while (num >= 10) {
            num /= 10;
            count++;
        }
        return count + 1;
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
            //获取八个文本输入框中的内容
            String nameinfo = name.getText();
            String idnumberinfo = idnumber.getText();
            String phonenumberinfo = phonenumber.getText();
            String addressinfo = address.getText();
            String applytypeinfo = applytype.getText();
            String registrationtimeinfo = registrationtime.getText();
            String ofcoachinfo = ofcoach.getText();
            String[] arr = new String[]{nameinfo,idnumberinfo, phonenumberinfo , addressinfo, applytypeinfo,registrationtimeinfo,ofcoachinfo};
            if (arr[0].equals("") || arr[1].equals("") || arr[2].equals("") || arr[3].equals("") || arr[4].equals("")||arr[5].equals("")||arr[6].equals("")/*||arr[7]==null*/) {
                System.out.println("输入的内容不完整，请继续补充");
                showJDialog("输入的内容不完整，请继续补充");
            } else {
                Connection con = Mysql.con;
                PreparedStatement preSql;
                String Uri = "insert into studentinformmanage values (?,?,?,?,?,?,?,?)";
                try {
                    preSql = con.prepareStatement(Uri);
                    preSql.setString(1, getstudentid());
                    preSql.setString(2, nameinfo);
                    preSql.setString(3, idnumberinfo);
                    preSql.setString(4, phonenumberinfo);
                    preSql.setString(5, addressinfo);
                    preSql.setString(6, applytypeinfo);
                    preSql.setString(7, registrationtimeinfo);
                    preSql.setString(8, ofcoachinfo);
                    preSql.executeUpdate();
                    showJDialog("增添成功");
                    jPanel2.removeAll();
                    StudentbasicinformationManagement.table();
                    jPanel2.revalidate();
                    jPanel2.repaint();
                    this.setVisible(false);
                } catch (SQLException e1) {
                    //调用showJDialog方法并展示弹框
                    showJDialog("增添失败");
                    //关闭当前界面
                    this.setVisible(false);
                }
            }
        }
    }
}