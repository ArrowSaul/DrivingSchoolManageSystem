package ui;

import com.code.CodeUtil;
import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginJFrame extends JFrame implements MouseListener {
    //定义登录按钮
    JButton login = new JButton();
    //定义注册按钮
    JButton register = new JButton();
    //定义用户名，密码，验证码的输入框
    public static JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();
    //正确的验证码
    JLabel rightCode = new JLabel();

    public LoginJFrame() {
        //初始化界面
        initJFrame();
        //在这个界面中添加内容
        initView();
        //让当前界面显示出来
        this.setVisible(true);
    }

    //初始化界面
    public void initJFrame() {
        this.setSize(1000, 600);//设置宽高
        this.setTitle("管理员登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }

    public void initView() {
        //1.添加驾校管理系统文字
        JLabel drivingschoolManagementsystem = new JLabel("驾 校 管 理 系 统");
        drivingschoolManagementsystem.setForeground(Color.white);
        drivingschoolManagementsystem.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 28));
        drivingschoolManagementsystem.setBounds(395, 40, 300, 80);
        this.getContentPane().add(drivingschoolManagementsystem);

        //2. 添加用户名文字
        JLabel usernameText = new JLabel("用户名:");
        usernameText.setForeground(Color.white);
        usernameText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        usernameText.setBounds(230, 215, 100, 80);
        this.getContentPane().add(usernameText);

        //3.添加用户名输入框
        username.setBounds(295, 240, 410, 30);
        this.getContentPane().add(username);

        //4.添加密码文字
        JLabel passwordText = new JLabel("密码:");
        passwordText.setForeground(Color.white);
        passwordText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        passwordText.setBounds(250, 277, 100, 80);
        this.getContentPane().add(passwordText);

        //5.密码输入框
        password.setBounds(295, 301, 410, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel("验证码:");
        codeText.setForeground(Color.white);
        codeText.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        codeText.setBounds(730, 358, 120, 70);
        codeText.setBackground(Color.red);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(800, 380, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //设置颜色
        rightCode.setForeground(Color.white);
        //设置字体
        rightCode.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 18));
        //位置和宽高
        rightCode.setBounds(915, 360, 120, 70);
        //添加到界面
        this.getContentPane().add(rightCode);

        //6.添加登录按钮
        login.setBounds(295, 366, 410, 47);
        login.setIcon(new ImageIcon(".\\image\\login.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(true);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //7.添加注册按钮
        register.setBounds(295, 420, 70, 40);
        register.setIcon(new ImageIcon(".\\image\\register2.jpg"));
        //去除按钮的边框
        register.setBorderPainted(true);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //8.添加背景图片
        JLabel background = new JLabel(new ImageIcon(".\\image\\loginbackground.jpg"));
        background.setBounds(0, 0, 1000, 600);
        this.getContentPane().add(background);
    }

    //点击
    @Override
    public void mouseClicked(MouseEvent e) {
        ResultSet rs;
        if (e.getSource() == login) {
            System.out.println("点击了登录按钮");
            //获取两个文本输入框中的内容
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            //获取用户输入的验证码
            String codeInput = code.getText();

            System.out.println("用户输入的用户名为" + usernameInput);
            System.out.println("用户输入的密码为" + passwordInput);

            if (codeInput.length() == 0) {
                //调用showJDialog方法并展示弹框
                showJDialog("验证码为空");

            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                //校验用户名和密码是否为空
                System.out.println("用户名或者密码为空");
                //调用showJDialog方法并展示弹框
                showJDialog("用户名或者密码为空");

            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("验证码错误");

            } else {
                java.sql.Connection con = Mysql.con;
                PreparedStatement preSql;
                String Uri = "select *from manager where 用户名 = ? and 密码 = ?";
                try {
                    preSql = con.prepareStatement(Uri);
                    preSql.setString(1, usernameInput);
                    preSql.setString(2, passwordInput);
                    rs = preSql.executeQuery();
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
                try {
                    if (rs.next()) {
                        System.out.println("用户名和密码正确进入主页面");
                        //调用showJDialog方法并展示弹框
                        showJDialog("登录成功");

                        //关闭当前登录界面
                        this.setVisible(false);
                        //打开系统的主界面
                        //需要把当前登录的用户名传递给主界面
                        new MainJFrame();
                    } else {
                        System.out.println("用户名或密码错误，请重新输入");
                        showJDialog("用户名或密码错误，请重新输入");
                    }
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
        } else if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
            //关闭当前登录界面
            this.setVisible(false);
            //打开注册界面
            new RegisterJFrame();
        } else if (e.getSource() == rightCode) {
            System.out.println("更换验证码");
            //获取一个新的验证码
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
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

    //按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon(".\\image\\login.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon(".\\image\\register.jpg"));
        }
    }


    //松开按钮
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon(".\\image\\login.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon(".\\image\\register.jpg"));
        }
    }

    //鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {

    }

}


