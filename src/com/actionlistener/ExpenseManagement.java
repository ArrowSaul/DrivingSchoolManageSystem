package com.actionlistener;

import com.event.delete.ActionExpenseDelete;
import com.event.select.ActionExpenseSelect;
import com.jframeevent.add.ActionExpenseAddJFrame;
import com.jframeevent.update.ActionExpenseUpdateJFrame;
import com.mysql.Mysql;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import static ui.MainJFrame.jPanel1;
import static ui.MainJFrame.jPanel2;

public class ExpenseManagement implements ActionListener {
    public static JTable expensetable;
    public static DefaultTableModel expensemodel;

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    public static JTextField expensetextField;


    //添加监听
    ActionExpenseAddJFrame actionExpenseAddJFrame = new ActionExpenseAddJFrame();
    ActionExpenseDelete actionExpenseDelete = new ActionExpenseDelete();
    ActionExpenseUpdateJFrame actionExpenseUpdateJFrame = new ActionExpenseUpdateJFrame();
    ActionExpenseSelect actionExpenseSelect = new ActionExpenseSelect();


    @Override
    public void actionPerformed(ActionEvent e) {

        //添加按钮
        allEvent();

        //创建表格
        table();

    }

    void allEvent() {
        jPanel1.removeAll();
        jPanel2.removeAll();
        button1 = new JButton("增添费用信息");
        button2 = new JButton("删除费用信息");
        button3 = new JButton("修改费用信息");
        button4 = new JButton("查询费用信息");
        expensetextField = new JTextField("请输入查询内容", 20);
        expensetextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (expensetextField.getText().equals("请输入查询内容")) {
                    expensetextField.setText("");     //将提示文字清空
                    expensetextField.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (expensetextField.getText().equals("")) {
                    expensetextField.setForeground(Color.gray); //将提示文字设置为灰色
                    expensetextField.setText("请输入查询内容");     //显示提示文字
                }

            }
        });

        button1.addActionListener(actionExpenseAddJFrame);
        button2.addActionListener((ActionListener) actionExpenseDelete);
        button3.addActionListener(actionExpenseUpdateJFrame);
        button4.addActionListener((ActionListener) actionExpenseSelect);

        button1.setBounds(0, 0, 100, 30);
        button2.setBounds(100, 0, 100, 30);
        button3.setBounds(200, 0, 100, 30);
        button4.setBounds(300, 0, 100, 30);

        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        jPanel1.add(button4);
        jPanel1.add(expensetextField);
        // 更新面板
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public static void table() {

        // 创建表格模型
        expensemodel = new DefaultTableModel();
        expensetable = new JTable(expensemodel);
        JScrollPane scrollPane = new JScrollPane(expensetable);
        expensetable.setPreferredScrollableViewportSize(jPanel2.getSize());
        jPanel2.add(scrollPane);
        Connection con = Mysql.con;
        // 查询数据库
        try {
            new Mysql("root", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM expensemanage ");
            ResultSetMetaData metaData = rs.getMetaData();


            // 添加表格列
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                expensemodel.addColumn(metaData.getColumnName(i));
            }

            // 添加表格行
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                expensemodel.addRow(rowData);
            }

            rs.close();
            stmt.close();
            /*con.close();*/
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}


