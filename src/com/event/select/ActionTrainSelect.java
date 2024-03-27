package com.event.select;

import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.actionlistener.StudenttrainingManagement.*;
import static com.mysql.Mysql.con;
import static ui.MainJFrame.jPanel2;

public class ActionTrainSelect extends Component implements ActionListener {
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

    public void actionPerformed(ActionEvent e) {
        new Mysql("root", "123456");
        System.out.println("点击了查询按钮");
        String traincontent = traintextField.getText();
        if (traincontent.equals("")) {
            System.out.println("请输入要查询的内容");
            showJDialog("请输入要查询的内容");
        } else {
            String sql = "SELECT * FROM studenttrainingmanage WHERE " +
                    "学员姓名 LIKE '%" + traincontent + "%' OR " +
                    "科目类型 LIKE '%" + traincontent + "%' OR " +
                    "学时 LIKE '%" + traincontent + "%' OR " +
                    "练习日期 LIKE '%" + traincontent + "%'";
            ArrayList<String[]> resultList = new ArrayList<>();
            try (
                    Statement stmt = con.createStatement();
                    ResultSet resultSet = stmt.executeQuery(sql)) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (resultSet.next()) {
                    String[] row = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = String.valueOf(resultSet.getObject(i));
                    }
                    resultList.add(row);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            trainmodel.setRowCount(0);
            // 添加数据到列表中
            for (String[] row : resultList) {
                trainmodel.addRow(row);
            }

            showJDialog("查询成功");
            traintable.repaint();
            jPanel2.revalidate();
            jPanel2.repaint();
        }
    }
}

