package com.actionlistener;

import com.event.delete.ActionStudentDelete;
import com.event.select.ActionStudentSelect;
import com.jframeevent.add.ActionStudentAddJFrame;
import com.jframeevent.update.ActionStudentUpdateJFrame;
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

public class StudentbasicinformationManagement implements ActionListener {
    public static JTable studenttable;
    public static DefaultTableModel studentmodel;

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    public static JTextField studenttextField;


    //添加监听

    ActionStudentAddJFrame actionStudentAddJFrame=new ActionStudentAddJFrame();
    ActionStudentDelete actionStudentDelete =new ActionStudentDelete();
    ActionStudentUpdateJFrame actionStudentUpdateJFrame=new ActionStudentUpdateJFrame();
    ActionStudentSelect actionStudentSelect=new ActionStudentSelect();

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
        button1 = new JButton("增添学员信息");
        button2 = new JButton("删除学员信息");
        button3 = new JButton("修改学员信息");
        button4 = new JButton("查询学员信息");
        studenttextField = new JTextField("请输入查询内容",20);
        studenttextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (studenttextField.getText().equals("请输入查询内容")){
                    studenttextField.setText("");     //将提示文字清空
                    studenttextField.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (studenttextField.getText().equals("")){
                    studenttextField.setForeground(Color.gray); //将提示文字设置为灰色
                    studenttextField.setText("请输入查询内容");     //显示提示文字
                }

            }
        });

        button1.addActionListener(actionStudentAddJFrame);
        button2.addActionListener((ActionListener) actionStudentDelete);
        button3.addActionListener(actionStudentUpdateJFrame);
        button4.addActionListener((ActionListener) actionStudentSelect);

        button1.setBounds(0, 0, 100, 30);
        button2.setBounds(100, 0, 100, 30);
        button3.setBounds(200, 0, 100, 30);
        button4.setBounds(300, 0, 100, 30);

        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        jPanel1.add(button4);

        jPanel1.add(studenttextField);
        // 更新面板
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public static void table() {

        // 创建表格模型
        studentmodel = new DefaultTableModel();
        studenttable = new JTable(studentmodel);
        JScrollPane scrollPane = new JScrollPane(studenttable);
        studenttable.setPreferredScrollableViewportSize(jPanel2.getSize());
        jPanel2.add(scrollPane);

        Connection con = Mysql.con;
        // 查询数据库
        try {
            new Mysql("root","123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM studentinformmanage ");
            ResultSetMetaData metaData = rs.getMetaData();


            // 添加表格列
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                studentmodel.addColumn(metaData.getColumnName(i));
            }

            // 添加表格行
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                studentmodel.addRow(rowData);
            }

            rs.close();
            stmt.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
