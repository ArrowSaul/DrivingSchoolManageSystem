package com.jframeevent.update;

import com.actionlistener.CoachManagement;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.actionlistener.CoachManagement.coachmodel;
import static com.actionlistener.CoachManagement.coachtable;
import static ui.MainJFrame.jPanel2;

public class ActionCoachUpdateJFrame extends JFrame implements ActionListener {
    //定义修改按钮
    JButton update = new JButton();
    //定义教练姓名，手机号码，身份证号，教练车牌，车辆型号的输入框
    JTextField coachname = new JTextField();
    JTextField phonenumber = new JTextField();
    JTextField idnumber = new JTextField();
    JTextField coachcar = new JTextField();
    JTextField cartype = new JTextField();

    //初始化界面
    public void initJFrame() {
        this.setSize(500, 600);//设置宽高
        this.setTitle("教练管理");//设置标题
        this.setDefaultCloseOperation(2);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }

    public void initView() {
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

        //12.添加修改按钮
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

        int rowIndex = coachtable.getSelectedRow();
        if (rowIndex == -1) {
            showJDialog("请选择你要修改的行！");
            return;
        }

            //让当前界面显示出来
            this.setVisible(true);
            if (e.getSource() == update) {
                System.out.println("点击了修改按钮");
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
                    new Mysql("root", "123456");
                    Connection con = Mysql.con;
                    try {
                        if (!coachnameinfo.equals(coachmodel.getValueAt(rowIndex, 0))) {
                            // 如果教练姓名发生了变化，需要使用修改后的教练姓名作为 WHERE 子句的条件
                            String sql = "UPDATE coachmanage SET 教练姓名=?,手机号码=?,身份证号=?,教练车辆=?,车辆型号=? WHERE 教练姓名=?";
                            PreparedStatement pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, coachnameinfo);
                            pstmt.setString(2, phonenumberinfo);
                            pstmt.setString(3, idnumberinfo);
                            pstmt.setString(4, coachcarinfo);
                            pstmt.setString(5, cartypeinfo);
                            pstmt.setString(6, (String) coachmodel.getValueAt(rowIndex, 0));
                            pstmt.executeUpdate();
                        } else {
                            // 如果教练姓名没有变化，使用原先的教练姓名作为 WHERE 子句的条件
                            String sql = "UPDATE coachmanage SET 手机号码=?,身份证号=?,教练车辆=?,车辆型号=? WHERE 教练姓名=?";
                            PreparedStatement pstmt = con.prepareStatement(sql);
                            pstmt.setString(1, phonenumberinfo);
                            pstmt.setString(2, idnumberinfo);
                            pstmt.setString(3, coachcarinfo);
                            pstmt.setString(4, cartypeinfo);
                            pstmt.setString(5, coachnameinfo);
                            pstmt.executeUpdate();
                        }
                        coachmodel.setValueAt(coachnameinfo, rowIndex, 0);
                        coachmodel.setValueAt(phonenumberinfo, rowIndex, 1);
                        coachmodel.setValueAt(idnumberinfo, rowIndex, 2);
                        coachmodel.setValueAt(coachcarinfo, rowIndex, 3);
                        coachmodel.setValueAt(cartypeinfo, rowIndex, 4);
                        showJDialog("修改成功");
                        jPanel2.removeAll();
                        CoachManagement.table();
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
