package com.jframeevent.update;

import com.actionlistener.StudentbasicinformationManagement;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.actionlistener.StudentbasicinformationManagement.studentmodel;
import static com.actionlistener.StudentbasicinformationManagement.studenttable;
import static ui.MainJFrame.jPanel2;

public class ActionStudentUpdateJFrame extends JFrame implements ActionListener {
    //定义增添按钮
    JButton update = new JButton();
    //定义学员编号，姓名，身份证号，手机号码，地址，申请类型，报名时间，所属教练的输入框
    JTextField studentid = new JTextField();
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
        //2. 添加学员编号文字
        JLabel studentidText = new JLabel("学员编号:");
        studentidText.setForeground(Color.black);
        studentidText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        studentidText.setBounds(115, 103, 100, 80);
        this.getContentPane().add(studentidText);
        //3.添加学员编号输入框
        studentid.setBounds(200, 130, 150, 30);
        this.getContentPane().add(studentid);
        //4. 添加姓名文字
        JLabel nameText = new JLabel("姓名:");
        nameText.setForeground(Color.black);
        nameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        nameText.setBounds(115, 143, 100, 80);
        this.getContentPane().add(nameText);
        //5.添加姓名输入框
        name.setBounds(200, 170, 150, 30);
        this.getContentPane().add(name);

        //6. 添加身份证号文字
        JLabel idnumberText = new JLabel("身份证号:");
        idnumberText.setForeground(Color.black);
        idnumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        idnumberText.setBounds(115, 183, 100, 80);
        this.getContentPane().add(idnumberText);

        //7.添加身份证号输入框
        idnumber.setBounds(200, 210, 150, 30);
        this.getContentPane().add(idnumber);
        //8. 添加手机号码文字
        JLabel phonenumberText = new JLabel("手机号码:");
        phonenumberText.setForeground(Color.black);
        phonenumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        phonenumberText.setBounds(115, 223, 100, 80);
        this.getContentPane().add(phonenumberText);

        //9.添加手机号码输入框
        phonenumber.setBounds(200, 250, 150, 30);
        this.getContentPane().add(phonenumber);
        //10. 添加地址文字
        JLabel addressText = new JLabel("地址:");
        addressText.setForeground(Color.black);
        addressText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        addressText.setBounds(115, 263, 100, 80);
        this.getContentPane().add(addressText);

        //11.添加地址输入框
        address.setBounds(200, 290, 150, 30);
        this.getContentPane().add(address);
        //12. 添加申请类型文字
        JLabel applytypeText = new JLabel("申请类型:");
        applytypeText.setForeground(Color.black);
        applytypeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        applytypeText.setBounds(115, 303, 100, 80);
        this.getContentPane().add(applytypeText);

        //13.添加申请类型输入框
        applytype.setBounds(200, 330, 150, 30);
        this.getContentPane().add(applytype);
        //14. 添加报名时间文字
        JLabel registrationtimeText = new JLabel("报名时间:");
        registrationtimeText.setForeground(Color.black);
        registrationtimeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        registrationtimeText.setBounds(115, 343, 100, 80);
        this.getContentPane().add(registrationtimeText);
        //15.添加报名时间输入框
        registrationtime.setBounds(200, 370, 150, 30);
        this.getContentPane().add(registrationtime);
        //16. 添加所属教练文字
        JLabel ofcoachText = new JLabel("所属教练:");
        ofcoachText.setForeground(Color.black);
        ofcoachText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        ofcoachText.setBounds(115, 383, 100, 80);
        this.getContentPane().add(ofcoachText);
        //17.添加所属教练输入框
        ofcoach.setBounds(200, 410, 150, 30);
        this.getContentPane().add(ofcoach);

        //18.添加修改按钮
        update.setBounds(150, 470, 150, 47);
        update.setIcon(new ImageIcon(".\\image\\update.jpg"));
        //去除按钮的边框
        update.setBorderPainted(false);
        //去除按钮的背景
        update.setContentAreaFilled(true);
        //给修改按钮绑定鼠标事件
        update.addActionListener(this);
        this.getContentPane().add(update);

        //19.添加背景图片
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

        int rowIndex = studenttable.getSelectedRow();
        if (rowIndex == -1) {
            //
            showJDialog("请选择你要修改的行！");
            return;
        }

            //让当前界面显示出来
            this.setVisible(true);
            if (e.getSource() == update) {
                System.out.println("点击了修改按钮");
                //获取八个文本输入框中的内容
                String studentidinfo = studentid.getText();
                String nameinfo = name.getText();
                String idnumberinfo = idnumber.getText();
                String phonenumberinfo = phonenumber.getText();
                String addressinfo = address.getText();
                String applytypeinfo = applytype.getText();
                String registrationtimeinfo = registrationtime.getText();
                String ofcoachinfo = ofcoach.getText();
                String[] arr = new String[]{studentidinfo, nameinfo, idnumberinfo, phonenumberinfo, addressinfo,applytypeinfo,registrationtimeinfo,ofcoachinfo};
                if (arr[0].equals("") || arr[1].equals("") || arr[2].equals("") || arr[3].equals("") || arr[4].equals("")) {
                    System.out.println("输入的内容不完整，请继续补充");
                    showJDialog("输入的内容不完整，请继续补充");

                } else {
                    new Mysql("root", "123456");
                    Connection con = Mysql.con;
                    try {
                        if (!studentidinfo.equals(studentmodel.getValueAt(rowIndex, 0))) {
                            // 如果学员编号发生了变化，需要使用修改后的学员编号作为 WHERE 子句的条件
                            String sql = "UPDATE studentinformmanage SET 学员编号=?,姓名=?,身份证号=?,手机号码=?,地址=?,申请类型=?,报名时间=?,所属教练=? WHERE 学员编号=?";
                            PreparedStatement pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, studentidinfo);
                            pstmt.setString(2, nameinfo);
                            pstmt.setString(3, idnumberinfo);
                            pstmt.setString(4, phonenumberinfo);
                            pstmt.setString(5, addressinfo);
                            pstmt.setString(6, applytypeinfo);
                            pstmt.setString(7, registrationtimeinfo);
                            pstmt.setString(8, ofcoachinfo);
                            pstmt.setString(9, (String) studentmodel.getValueAt(rowIndex, 0));
                            pstmt.executeUpdate();
                        } else {
                            // 如果学员编号没有变化，使用原先的学员编号作为 WHERE 子句的条件
                            String sql = "UPDATE studentinformmanage SET 姓名=?,身份证号=?,手机号码=?,地址=?,申请类型=?,报名时间=?,所属教练=? WHERE 学员编号=?";
                            PreparedStatement pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, nameinfo);
                            pstmt.setString(2, idnumberinfo);
                            pstmt.setString(3, phonenumberinfo);
                            pstmt.setString(4, addressinfo);
                            pstmt.setString(5, applytypeinfo);
                            pstmt.setString(6, registrationtimeinfo);
                            pstmt.setString(7, ofcoachinfo);
                            pstmt.setString(8, studentidinfo);
                            pstmt.executeUpdate();
                        }
                        studentmodel.setValueAt(nameinfo, rowIndex, 0);
                        studentmodel.setValueAt(idnumberinfo, rowIndex, 1);
                        studentmodel.setValueAt(phonenumberinfo, rowIndex, 2);
                        studentmodel.setValueAt(addressinfo, rowIndex, 3);
                        studentmodel.setValueAt(applytypeinfo, rowIndex, 4);
                        studentmodel.setValueAt(registrationtimeinfo, rowIndex, 5);
                        studentmodel.setValueAt(ofcoachinfo, rowIndex, 6);
                        studentmodel.setValueAt(studentidinfo, rowIndex, 7);
                        showJDialog("修改成功");
                        jPanel2.removeAll();
                        StudentbasicinformationManagement.table();
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
