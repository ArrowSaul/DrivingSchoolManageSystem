package com.jframeevent.add;

import com.actionlistener.StudenttrainingManagement;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ui.MainJFrame.jPanel2;

public class ActionTrainAddJFrame extends JFrame implements ActionListener {

    //定义增添按钮
    JButton add = new JButton();
    //定义学员姓名，科目类型，学时，练习日期的输入框
    JTextField studentname = new JTextField();
    JTextField subjecttype = new JTextField();
    JTextField studytime = new JTextField();
    JTextField practicedate = new JTextField();


    //初始化界面
    public void initJFrame() {
        this.setSize(500, 600);//设置宽高
        this.setTitle("学员训练管理");//设置标题
        this.setDefaultCloseOperation(2);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initView() {
        //1.添加学员训练管理文字
        JLabel drivingschoolManagementsystem = new JLabel("学员训练管理");
        drivingschoolManagementsystem.setForeground(Color.black);
        drivingschoolManagementsystem.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 28));
        drivingschoolManagementsystem.setBounds(170, 40, 300, 80);
        this.getContentPane().add(drivingschoolManagementsystem);

        //2. 添加学员姓名文字
        JLabel studentnameText = new JLabel("学员姓名:");
        studentnameText.setForeground(Color.black);
        studentnameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        studentnameText.setBounds(115, 103, 100, 80);
        this.getContentPane().add(studentnameText);

        //3.添加学员姓名输入框
        studentname.setBounds(200, 130, 150, 30);
        this.getContentPane().add(studentname);

        //4. 添加科目类型文字
        JLabel subjecttypeText = new JLabel("科目类型:");
        subjecttypeText.setForeground(Color.black);
        subjecttypeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        subjecttypeText.setBounds(115, 143, 100, 80);
        this.getContentPane().add(subjecttypeText);

        //5.添加科目类型输入框
        subjecttype.setBounds(200, 170, 150, 30);
        this.getContentPane().add(subjecttype);

        //6. 添加学时文字
        JLabel studytimeText = new JLabel("学时:");
        studytimeText.setForeground(Color.black);
        studytimeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        studytimeText.setBounds(115, 183, 100, 80);
        this.getContentPane().add(studytimeText);

        //7.添加学时输入框
        studytime.setBounds(200, 210, 150, 30);
        this.getContentPane().add(studytime);
        //8. 添加练习日期文字
        JLabel practicedateText = new JLabel("练习日期:");
        practicedateText.setForeground(Color.black);
        practicedateText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        practicedateText.setBounds(115, 223, 100, 80);
        this.getContentPane().add(practicedateText);

        //9.添加练习日期输入框
        practicedate.setBounds(200, 250, 150, 30);
        this.getContentPane().add(practicedate);

        //10.添加增添按钮
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
            //获取四个文本输入框中的内容
            String studentnameinfo = studentname.getText();
            String subjecttypeinfo = subjecttype.getText();
            String studytimeinfo = studytime.getText();
            String practicedateinfo = practicedate.getText();
            String[] arr = new String[]{studentnameinfo, subjecttypeinfo, studytimeinfo, practicedateinfo};
            if (arr[0] == null || arr[1] == null || arr[2] == null || arr[3] == null) {
                System.out.println("输入的内容不完整，请继续补充");
                showJDialog("输入的内容不完整，请继续补充");
            } else {
                Connection con = Mysql.con;
                PreparedStatement preSql;
                String Uri = "insert into studenttrainingmanage values (?,?,?,?)";
                try {
                    preSql = con.prepareStatement(Uri);
                    preSql.setString(1, studentnameinfo);
                    preSql.setString(2, subjecttypeinfo);
                    preSql.setString(3, studytimeinfo);
                    preSql.setString(4, practicedateinfo);
                    preSql.executeUpdate();
                    showJDialog("增添成功");
                    jPanel2.removeAll();
                    StudenttrainingManagement.table();
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
