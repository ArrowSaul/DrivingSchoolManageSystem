package com.jframeevent.add;

import com.actionlistener.StudentCoursesetting;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static ui.MainJFrame.jPanel2;

public class ActionCourseAddJFrame extends JFrame implements ActionListener {

    //定义增添按钮
    JButton add = new JButton();
    //定义学员姓名，科目等级的输入框
    JTextField studentname = new JTextField();
    JTextField courselevel = new JTextField();

    //初始化界面
    public void initJFrame() {
        this.setSize(500, 600);//设置宽高
        this.setTitle("学员科目设置");//设置标题
        this.setDefaultCloseOperation(2);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initView() {
        //1.添加学员科目设置文字
        JLabel drivingschoolManagementsystem = new JLabel("学员科目设置");
        drivingschoolManagementsystem.setForeground(Color.black);
        drivingschoolManagementsystem.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 28));
        drivingschoolManagementsystem.setBounds(170, 40, 300, 80);
        this.getContentPane().add(drivingschoolManagementsystem);

        //2. 添加学员姓名文字
        JLabel studentnameText = new JLabel("学员姓名:");
        studentnameText.setForeground(Color.black);
        studentnameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        studentnameText.setBounds(115, 143, 100, 80);
        this.getContentPane().add(studentnameText);

        //3.添加学员姓名输入框
        studentname.setBounds(200, 170, 150, 30);
        this.getContentPane().add(studentname);

        //4. 添加科目等级文字
        JLabel courselevelText = new JLabel("科目等级:");
        courselevelText.setForeground(Color.black);
        courselevelText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        courselevelText.setBounds(115, 183, 100, 80);
        this.getContentPane().add(courselevelText);

        //5.添加科目等级输入框
        courselevel.setBounds(200, 210, 150, 30);
        this.getContentPane().add(courselevel);

        //6.添加增添按钮
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
    //得到学员编号的方法

    public static String getstudentid() {
        Connection con = Mysql.con;
        String max_Value;
        try {
            new Mysql("root", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(学员编号)  as max_value FROM studentcoursesetting");
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
            //获取两个文本输入框中的内容
            String studentnameinfo = studentname.getText();
            String courselevelinfo = courselevel.getText();
            String[] arr = new String[]{ studentnameinfo, courselevelinfo};
            if (arr[0].equals("") || arr[1].equals("")) {
                System.out.println("输入的内容不完整，请继续补充");
                showJDialog("输入的内容不完整，请继续补充");

            } else {
                Connection con = Mysql.con;
                PreparedStatement preSql;
                String Uri = "insert into studentcoursesetting values (?,?,?)";
                try {
                    preSql = con.prepareStatement(Uri);
                    preSql.setString(1, getstudentid());
                    preSql.setString(2, studentnameinfo);
                    preSql.setString(3, courselevelinfo);
                    preSql.executeUpdate();
                    showJDialog("增添成功");
                    jPanel2.removeAll();
                    StudentCoursesetting.table();
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
