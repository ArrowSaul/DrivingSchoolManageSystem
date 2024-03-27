package com.jframeevent.add;

import com.actionlistener.ExpenseManagement;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static ui.MainJFrame.jPanel2;

public class ActionExpenseAddJFrame extends JFrame implements ActionListener {

    //定义缴费按钮
    JButton add = new JButton();
    //定义单号，单据日期，学员编号，姓名，性别，身份证号，手机号，准假车型，费用类型，套餐金额，优惠金额，实际缴费金额，业务员，操作员的输入框
    JTextField odd = new JTextField();
    JTextField documentdate = new JTextField();
    JTextField studentid = new JTextField();
    JTextField  name= new JTextField();
    JTextField gender = new JTextField();
    JTextField idnumber = new JTextField();
    JTextField phonenumber = new JTextField();
    JTextField vehicletype = new JTextField();
    JTextField expensetype = new JTextField();
    JTextField packageamount = new JTextField();
    JTextField coupon = new JTextField();
    JTextField paymentamount = new JTextField();
    JTextField salesman = new JTextField();
    JTextField operator = new JTextField();

    //初始化界面
    public void initJFrame() {
        this.setSize(500, 800);//设置宽高
        this.setTitle("费用管理");//设置标题
        this.setDefaultCloseOperation(2);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }
    public void initView() {
        //1.添加费用管理文字
        JLabel drivingschoolManagementsystem = new JLabel("费 用 管 理");
        drivingschoolManagementsystem.setForeground(Color.black);
        drivingschoolManagementsystem.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 28));
        drivingschoolManagementsystem.setBounds(170, 40, 300, 80);
        this.getContentPane().add(drivingschoolManagementsystem);

        //2. 添加单号文字
        JLabel oddText = new JLabel("单号:");
        oddText.setForeground(Color.black);
        oddText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        oddText.setBounds(65, 103, 100, 80);
        this.getContentPane().add(oddText);

        //3.添加单号输入框
        odd.setBounds(200, 130, 150, 30);
        this.getContentPane().add(odd);
        //4. 添加单据日期文字
        JLabel documentdateText = new JLabel("单据日期:");
        documentdateText.setForeground(Color.black);
        documentdateText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        documentdateText.setBounds(65, 143, 100, 80);
        this.getContentPane().add(documentdateText);
        //5.添加单据日期输入框
        documentdate.setBounds(200, 170, 150, 30);
        this.getContentPane().add(documentdate);

        //6. 添加学员编号文字
        JLabel studentidText = new JLabel("学员编号:");
        studentidText.setForeground(Color.black);
        studentidText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        studentidText.setBounds(65, 183, 100, 80);
        this.getContentPane().add(studentidText);

        //7.添加学员编号输入框
        studentid.setBounds(200, 210, 150, 30);
        this.getContentPane().add(studentid);
        //8. 添加姓名文字
        JLabel nameText = new JLabel("姓名:");
        nameText.setForeground(Color.black);
        nameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        nameText.setBounds(65, 223, 100, 80);
        this.getContentPane().add(nameText);

        //9.添加姓名输入框
        name.setBounds(200, 250, 150, 30);
        this.getContentPane().add(name);
        //10. 添加性别文字
        JLabel genderText = new JLabel("性别:");
        genderText.setForeground(Color.black);
        genderText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        genderText.setBounds(65, 263, 100, 80);
        this.getContentPane().add(genderText);

        //11.添加性别输入框
        gender.setBounds(200, 290, 150, 30);
        this.getContentPane().add(gender);
        //12. 添加身份证号文字
        JLabel idnumberText = new JLabel("身份证号:");
        idnumberText.setForeground(Color.black);
        idnumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        idnumberText.setBounds(65, 303, 100, 80);
        this.getContentPane().add(idnumberText);

        //13.添加身份证号输入框
        idnumber.setBounds(200, 330, 150, 30);
        this.getContentPane().add(idnumber);
        //14. 添加手机号文字
        JLabel phonenumberText = new JLabel("手机号:");
        phonenumberText.setForeground(Color.black);
        phonenumberText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        phonenumberText.setBounds(65, 343, 100, 80);
        this.getContentPane().add(phonenumberText);
        //15.添加手机号入框
        phonenumber.setBounds(200, 370, 150, 30);
        this.getContentPane().add(phonenumber);
        //16. 添加准假车型文字
        JLabel vehicletypeText = new JLabel("准假车型:");
        vehicletypeText.setForeground(Color.black);
        vehicletypeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        vehicletypeText.setBounds(65, 383, 100, 80);
        this.getContentPane().add(vehicletypeText);
        //17.添加准假车型输入框
        vehicletype.setBounds(200, 410, 150, 30);
        this.getContentPane().add(vehicletype);
        //18. 添加费用类型文字
        JLabel expensetypeText = new JLabel("费用类型:");
        expensetypeText.setForeground(Color.black);
        expensetypeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        expensetypeText.setBounds(65, 423, 100, 80);
        this.getContentPane().add(expensetypeText);
        //19.添加费用类型输入框
        expensetype.setBounds(200, 450, 150, 30);
        this.getContentPane().add(expensetype);
        //20. 添加套餐金额文字
        JLabel packageamountText = new JLabel("套餐金额:");
        packageamountText.setForeground(Color.black);
        packageamountText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        packageamountText.setBounds(65, 463, 100, 80);
        this.getContentPane().add(packageamountText);
        //21.添加套餐金额输入框
        packageamount.setBounds(200, 490, 150, 30);
        this.getContentPane().add(packageamount);
        //22. 添加优惠金额文字
        JLabel couponText = new JLabel("优惠金额:");
        couponText.setForeground(Color.black);
        couponText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        couponText.setBounds(65, 503, 100, 80);
        this.getContentPane().add(couponText);
        //23.添加优惠金额输入框
        coupon.setBounds(200, 530, 150, 30);
        this.getContentPane().add(coupon);
        //24. 添加实际缴费金额文字
        JLabel paymentamountText = new JLabel("实际缴费金额:");
        paymentamountText.setForeground(Color.black);
        paymentamountText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        paymentamountText.setBounds(65, 543, 200, 80);
        this.getContentPane().add(paymentamountText);
        //25.添加实际缴费金额输入框
        paymentamount.setBounds(200, 570, 150, 30);
        this.getContentPane().add(paymentamount);
        //26. 添加业务员文字
        JLabel salesmanText = new JLabel("业务员:");
        salesmanText.setForeground(Color.black);
        salesmanText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        salesmanText.setBounds(65, 583, 100, 80);
        this.getContentPane().add(salesmanText);
        //27.添加业务员输入框
        salesman.setBounds(200, 610, 150, 30);
        this.getContentPane().add(salesman);
        //28. 添加操作员文字
        JLabel operatorText = new JLabel("操作员:");
        operatorText.setForeground(Color.black);
        operatorText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        operatorText.setBounds(65, 623, 100, 80);
        this.getContentPane().add(operatorText);
        //29.添加操作员输入框
        operator.setBounds(200, 650, 150, 30);
        this.getContentPane().add(operator);

        //30.添加缴费按钮
        add.setBounds(150, 710, 150, 47);
        add.setIcon(new ImageIcon(".\\image\\add.jpg"));
        //去除按钮的边框
        add.setBorderPainted(false);
        //去除按钮的背景
        add.setContentAreaFilled(true);
        //给登录按钮绑定鼠标事件
        add.addActionListener(this);
        this.getContentPane().add(add);

        //19.添加背景图片
        JLabel background = new JLabel(new ImageIcon(""));
        background.setBounds(0, 0, 1000, 600);
        this.getContentPane().add(background);
    }
    //得到单据编号的方法
    public static String getreceipt() {
        Connection con = Mysql.con;
        String max_Value;
        try {
            new Mysql("root", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(单据编号)  as max_value FROM expensemanage");
            try {
                if (rs.next()) {
                    //获取表中单据编号的值
                    max_Value = rs.getString("max_value");
                    //取出SJ后的字符串
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
                        String s2 = "SJ00000"+s1;
                        return s2;
                    }else if(wei==2){
                        String s2 = "SJ0000"+s1;
                        return s2;
                    }else if(wei==3){
                        String s2 = "SJ000"+s1;
                        return s2;
                    }else if(wei==4){
                        String s2 = "SJ00"+s1;
                        return s2;
                    }else if(wei==5){
                        String s2 = "SJ0"+s1;
                        return s2;
                    }else if(wei==6){
                        String s2 = "SJ"+s1;
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
        //如果为空返回SJ000000
        return "SJ000000";
    }
    //对SJ后的字符串转换得到的数字加一后的数字进行位数判断
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
            System.out.println("点击了缴费按钮");
            //获得十四个文本输入框中的内容
            String oddinfo = odd.getText();
            String documentdateinfo = documentdate.getText();
            String studentidinfo = studentid.getText();
            String nameinfo = name.getText();
            String genderinfo = gender.getText();
            String idnumberinfo = idnumber.getText();
            String phonenumberinfo = phonenumber.getText();
            String vehicletypeinfo = vehicletype.getText();
            String expensetypeinfo = expensetype.getText();
            String packageamountinfo = packageamount.getText();
            String couponinfo = coupon.getText();
            String paymentamountinfo = paymentamount.getText();
            String salesmaninfo = salesman.getText();
            String operatorinfo = operator.getText();
            String[] arr = new String[]{oddinfo,documentdateinfo,studentidinfo, nameinfo , genderinfo, idnumberinfo,phonenumberinfo,vehicletypeinfo,expensetypeinfo,packageamountinfo,couponinfo,paymentamountinfo,salesmaninfo,operatorinfo};
            if (arr[0] == null || arr[1] == null || arr[2] == null || arr[3] == null || arr[4] == null||arr[5]==null||arr[6]==null||arr[7]==null||arr[8]==null||arr[9]==null||arr[10]==null||arr[11]==null||arr[12]==null||arr[13]==null) {
                System.out.println("输入的内容不完整，请继续补充");
                showJDialog("输入的内容不完整，请继续补充");
            } else {
                Connection con = Mysql.con;
                PreparedStatement preSql;
                String Uri = "insert into expensemanage values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try {
                    preSql = con.prepareStatement(Uri);
                    preSql.setString(1, oddinfo);
                    preSql.setString(2, documentdateinfo);
                    preSql.setString(3, studentidinfo);
                    preSql.setString(4, nameinfo);
                    preSql.setString(5, genderinfo);
                    preSql.setString(6, idnumberinfo);
                    preSql.setString(7, phonenumberinfo);
                    preSql.setString(8, vehicletypeinfo);
                    preSql.setString(9, expensetypeinfo);
                    preSql.setString(10, packageamountinfo);
                    preSql.setString(11, couponinfo);
                    preSql.setString(12, paymentamountinfo);
                    preSql.setString(13, salesmaninfo);
                    preSql.setString(14, operatorinfo);
                    preSql.setString(15, getreceipt());
                    preSql.executeUpdate();
                    showJDialog("增添成功");
                    jPanel2.removeAll();
                    ExpenseManagement.table();
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