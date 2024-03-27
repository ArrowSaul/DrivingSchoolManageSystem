package com.jframeevent.update;

import com.actionlistener.GraduationInformation;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.actionlistener.GraduationInformation.graduatemodel;
import static com.actionlistener.GraduationInformation.graduatetable;
import static ui.MainJFrame.jPanel2;

public class ActionGraduateUpdateJFrame extends JFrame implements ActionListener {
    //定义修改按钮
    JButton update = new JButton();
    //定义姓名，驾照类型，时间的输入框
    JTextField  name= new JTextField();
    JTextField  drivetype= new JTextField();
    JTextField time = new JTextField();


    //初始化界面
    public void initJFrame() {
        this.setSize(500, 600);//设置宽高
        this.setTitle("毕业信息");//设置标题
        this.setDefaultCloseOperation(2);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initView() {
        //1.添加毕业信息设置文字
        JLabel drivingschoolManagementsystem = new JLabel("毕 业 信 息");
        drivingschoolManagementsystem.setForeground(Color.black);
        drivingschoolManagementsystem.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 28));
        drivingschoolManagementsystem.setBounds(170, 40, 300, 80);
        this.getContentPane().add(drivingschoolManagementsystem);

        //2. 添加姓名文字
        JLabel nameText = new JLabel("姓名:");
        nameText.setForeground(Color.black);
        nameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        nameText.setBounds(115, 103, 100, 80);
        this.getContentPane().add(nameText);

        //3.添加姓名输入框
        name.setBounds(200, 130, 150, 30);
        this.getContentPane().add(name);

        //4. 添加驾照类型文字
        JLabel drivetypeText = new JLabel("驾照类型:");
        drivetypeText.setForeground(Color.black);
        drivetypeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        drivetypeText.setBounds(115, 143, 100, 80);
        this.getContentPane().add(drivetypeText);

        //5.添加驾照类型输入框
        drivetype.setBounds(200, 170, 150, 30);
        this.getContentPane().add(drivetype);

        //6. 添加毕业时间文字
        JLabel timeText = new JLabel("时间:");
        timeText.setForeground(Color.black);
        timeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        timeText.setBounds(115, 183, 100, 80);
        this.getContentPane().add(timeText);

        //7.添加毕业时间输入框
        time.setBounds(200, 210, 150, 30);
        this.getContentPane().add(time);

        //8.添加修改按钮
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

        int rowIndex = graduatetable.getSelectedRow();
        if (rowIndex == -1) {
            //
            showJDialog("请选择你要修改的行！");
            return;
        }

        //让当前界面显示出来
        this.setVisible(true);
        if (e.getSource() == update) {
            System.out.println("点击了修改按钮");
            //获取五个文本输入框中的内容
            String nameinfo = name.getText();
            String drivetypeinfo = drivetype.getText();
            String timeinfo = time.getText();
            String[] arr = new String[]{nameinfo, drivetypeinfo, timeinfo};
            if (arr[0].equals("") || arr[1].equals("") || arr[2].equals("")) {
                System.out.println("输入的内容不完整，请继续补充");
                showJDialog("输入的内容不完整，请继续补充");

            } else {
                new Mysql("root", "123456");
                Connection con = Mysql.con;
                try {
                    if (!nameinfo.equals(graduatemodel.getValueAt(rowIndex, 0))) {
                        // 如果姓名发生了变化，需要使用修改后的姓名作为 WHERE 子句的条件
                        String sql = "UPDATE graduationinform SET 姓名=?,驾照类型=?,毕业时间=? WHERE 姓名=?";
                        PreparedStatement pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, nameinfo);
                        pstmt.setString(2, drivetypeinfo);
                        pstmt.setString(3, timeinfo);
                        pstmt.setString(4, (String) graduatemodel.getValueAt(rowIndex, 0));
                        pstmt.executeUpdate();
                    } else {
                        // 如果姓名没有变化，使用原先的姓名作为 WHERE 子句的条件
                        String sql = "UPDATE graduationinform SET 驾照类型=?,毕业时间=? WHERE 姓名=?";
                        PreparedStatement pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, drivetypeinfo);
                        pstmt.setString(2, timeinfo);
                        pstmt.setString(3, nameinfo);
                        pstmt.executeUpdate();
                    }
                    graduatemodel.setValueAt(nameinfo, rowIndex, 0);
                    graduatemodel.setValueAt(drivetypeinfo, rowIndex, 1);
                    graduatemodel.setValueAt(timeinfo, rowIndex, 2);
                    showJDialog("修改成功");
                    jPanel2.removeAll();
                    GraduationInformation.table();
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
