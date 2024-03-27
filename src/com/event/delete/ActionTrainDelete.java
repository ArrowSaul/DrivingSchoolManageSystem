package com.event.delete;

import com.mysql.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import static com.actionlistener.StudenttrainingManagement.trainmodel;
import static com.actionlistener.StudenttrainingManagement.traintable;
import static com.mysql.Mysql.con;


public class ActionTrainDelete extends Component implements ActionListener {
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
        new Mysql("root","123456");
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        int row = traintable.getSelectedRow();
        if (row == -1) {
            //
            showJDialog("请选择你要删除的行！");
            return;
        }

        // 获取选中行的学员姓名
        String studentname =String.valueOf(trainmodel.getValueAt(row, 0));

        // 从数据库中删除选中行
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(String.format("DELETE FROM studenttrainingmanage WHERE 学员姓名='%s'", studentname));

            // 从表格模型中删除选中行
            trainmodel.removeRow(row);
            showJDialog("删除成功");

        } catch (SQLException ex) {
            ex.printStackTrace();
            showJDialog("删除失败");
        }
    }
}
