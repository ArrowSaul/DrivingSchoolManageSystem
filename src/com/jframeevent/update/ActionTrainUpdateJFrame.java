package com.jframeevent.update;

import com.actionlistener.StudenttrainingManagement;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.actionlistener.StudenttrainingManagement.trainmodel;
import static com.actionlistener.StudenttrainingManagement.traintable;
import static ui.MainJFrame.jPanel2;

public class ActionTrainUpdateJFrame extends JFrame implements ActionListener {
    //定义修改按钮
    JButton update = new JButton();
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

        //10.添加修改按钮
        update.setBounds(150, 400, 150, 47);
        update.setIcon(new ImageIcon(".\\image\\update.jpg"));
        //去除按钮的边框
        update.setBorderPainted(false);
        //去除按钮的背景
        update.setContentAreaFilled(true);
        //给登录按钮绑定鼠标事件
        update.addActionListener(this);
        this.getContentPane().add(update);

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

        int rowIndex = traintable.getSelectedRow();
        if (rowIndex == -1) {
            //
            showJDialog("请选择你要修改的行！");
            return;
        }

            //让当前界面显示出来
            this.setVisible(true);
            if (e.getSource() == update) {
                System.out.println("点击了修改按钮");
                //获取四个文本输入框中的内容
                String studentnameinfo = studentname.getText();
                String subjecttypeinfo = subjecttype.getText();
                String studytimeinfo = studytime.getText();
                String practicedateinfo = practicedate.getText();
                String[] arr = new String[]{studentnameinfo, subjecttypeinfo, studytimeinfo, practicedateinfo};
                if (arr[0].equals("") || arr[1].equals("") || arr[2].equals("") || arr[3].equals("")) {
                    System.out.println("输入的内容不完整，请继续补充");
                    showJDialog("输入的内容不完整，请继续补充");

                } else {
                    new Mysql("root", "123456");
                    Connection con = Mysql.con;
                    try {
                        if (!studentnameinfo.equals(trainmodel.getValueAt(rowIndex, 0))) {
                            // 如果教练姓名发生了变化，需要使用修改后的教练姓名作为 WHERE 子句的条件
                            String sql = "UPDATE studenttrainingmanage SET 学员姓名=?,科目类型=?,学时=?,练习日期=? WHERE 学员姓名=?";
                            PreparedStatement pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, studentnameinfo);
                            pstmt.setString(2, subjecttypeinfo);
                            pstmt.setString(3, studytimeinfo);
                            pstmt.setString(4, practicedateinfo);
                            pstmt.setString(5, (String) trainmodel.getValueAt(rowIndex, 0));
                            pstmt.executeUpdate();
                        } else {
                            // 如果教练姓名没有变化，使用原先的教练姓名作为 WHERE 子句的条件
                            String sql = "UPDATE studenttrainingmanage SET 科目类型=?,学时=?,练习日期=? WHERE 学员姓名=?";
                            PreparedStatement pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, studentnameinfo);
                            pstmt.setString(2, subjecttypeinfo);
                            pstmt.setString(3, studytimeinfo);
                            pstmt.setString(4, practicedateinfo);
                            pstmt.executeUpdate();
                        }
                        trainmodel.setValueAt(studentnameinfo, rowIndex, 0);
                        trainmodel.setValueAt(subjecttypeinfo, rowIndex, 1);
                        trainmodel.setValueAt(studytimeinfo, rowIndex, 2);
                        trainmodel.setValueAt(practicedateinfo, rowIndex, 3);
                        showJDialog("修改成功");
                        jPanel2.removeAll();
                        StudenttrainingManagement.table();
                        jPanel2.revalidate();
                        jPanel2.repaint();
                        this.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        showJDialog("数据库操作失败");
                    }
                }
            }
        }
    }
