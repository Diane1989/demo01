package com.dyx.util;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;
import java.util.List;

public class AddTableRow {


    public void addTableRow(XWPFTable table, String header, String content, String alignment) {
        XWPFTableRow row = table.createRow(); // 创建一行

        // 设置第一列
        XWPFTableCell cell0 = row.getCell(0);
        cell0.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        cell0.setText(header);

        // 设置第二列
        XWPFTableCell cell1 = row.createCell();
        cell1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        cell1.setText(content);

        // 设置第二列的对齐方式
        if (alignment.equals("center")) {
            cell1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        } else if (alignment.equals("top")) {
            cell1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.TOP);
        } else if (alignment.equals("bottom")) {
            cell1.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.BOTTOM);
        }

        // 如果表格需要更多列，继续在这里添加单元格
        // ...

        // 设置每一行的列数相同
        int colSpan = table.getRow(0).getTableCells().size() - row.getTableCells().size();
        for (int i = 0; i < colSpan; i++) {
            row.createCell();
        }
    }


    private void setCellWidth(XWPFTableCell cell, int width) {
        CTTcPr tcpr = cell.getCTTc().addNewTcPr();
        CTTblWidth cellWidth = tcpr.isSetTcW() ? tcpr.getTcW() : tcpr.addNewTcW();
        cellWidth.setW(BigInteger.valueOf(width));
        cellWidth.setType(STTblWidth.DXA);
    }
}

