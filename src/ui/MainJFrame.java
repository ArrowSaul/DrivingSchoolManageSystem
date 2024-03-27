package ui;

import com.actionlistener.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainJFrame extends JFrame implements ActionListener {
    public static JPanel jPanel1;
    public static JPanel jPanel2;
    //创建对应监听的对象
    CoachManagement coach=new CoachManagement();
    StudentbasicinformationManagement studentbasicinformation=new StudentbasicinformationManagement();
    ExpenseManagement expense =new ExpenseManagement();
    StudenttrainingManagement studenttraining = new StudenttrainingManagement();
    StudentCoursesetting studentCourse=new StudentCoursesetting();
    GraduationInformation graduation=new GraduationInformation();
    // 选项下面的条目对象
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭");
    JMenuItem accountItem = new JMenuItem("公众号");
    // 选项-教练管理
    JMenuItem coachManagement = new JMenuItem("教练管理");
    JMenuItem studentbasicinformationManagement = new JMenuItem("学员基本信息管理");
    // 选项-费用管理
    JMenuItem expenseManagement = new JMenuItem("费用管理");
    // 选项-学员训练管理
    JMenuItem studenttrainingManagement = new JMenuItem("学员训练管理");
    // 选项-学员科目设置
    JMenuItem studentCoursesetting = new JMenuItem("学员科目设置");
    // 选项-毕业信息
    JMenuItem graduationInformation = new JMenuItem("毕业信息");
    // 主界面
    public MainJFrame() {
        // 初始化界面
        initJFrame();
        // 初始化菜单
        initJMenuBar();
        // 初始化图片
        initImage();
        // 显示
        this.setVisible(true);
    }

    // 图片
    private void initImage() {
        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon(""));
        background.setBounds(0, 0, 1500, 800);
        this.getContentPane().add(background);
        // 刷新一下界面
        this.getContentPane().repaint();
    }

    // 菜单
    private void initJMenuBar() {

        // 菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        // 选项-功能
        JMenu functionJMenu = new JMenu("功能");
        // 选项-关于我们
        JMenu aboutJMenu = new JMenu("关于我们");
        // 选项下面的条目添加到选项中
        functionJMenu.add(coachManagement);
        functionJMenu.add(studentbasicinformationManagement);
        functionJMenu.add(expenseManagement);
        functionJMenu.add(studenttrainingManagement);
        functionJMenu.add(studentCoursesetting);
        functionJMenu.add(graduationInformation);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        // 给条目绑定事件
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        coachManagement.addActionListener(coach);
        studentbasicinformationManagement.addActionListener(studentbasicinformation);
        expenseManagement.addActionListener(expense);
        studenttrainingManagement.addActionListener(studenttraining);
        studentCoursesetting.addActionListener(studentCourse);
        graduationInformation.addActionListener(graduation);


        // 将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    // 界面
    private void initJFrame() {
        // 设置界面的宽高
        this.setSize(1000, 600);
        // 设置界面的标题
        this.setTitle("驾校管理系统");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //实例化第一个面板
        jPanel1=new JPanel();
        jPanel1.setBorder(BorderFactory.createTitledBorder("基本信息处理"));
        jPanel1.setBounds(0,0,986,100);
        jPanel1.setLayout(new FlowLayout());
        //实例化第二个面板
        jPanel2=new JPanel();
        jPanel2.setBorder(BorderFactory.createTitledBorder("基本信息展示"));
        jPanel2.setBounds(0,100,986,500);
        jPanel2.setLayout(new FlowLayout());
        //将盘子添加到主界面
        this.getContentPane().add(jPanel1);
        this.getContentPane().add(jPanel2);
        // 取消默认的居中放置
        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object obj = e.getSource();
        // 判断
        if (obj == closeItem) {
            System.out.println("系统关闭");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon(".\\image\\weixin.png"));
            jLabel.setBounds(0, 0, 725, 706);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(725, 706);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (obj == reLoginItem) {
            System.out.println("点击了重新登录");
            new LoginJFrame();
        }
    }
}


