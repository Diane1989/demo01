package com.dyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceResponse;
import com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsResponse;
import com.aliyun.tea.TeaException;
import com.dyx.Result.Result;
import com.dyx.domain.bo.ApprovalInstanceBo;
import com.dyx.service.OaApprovalService;
import com.dyx.util.AccessToken;
import com.dyx.util.AddTableRow;

import io.micrometer.core.instrument.MultiGauge;
import javafx.scene.control.Cell;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class OaApprovalServiceImpl implements OaApprovalService {

    @Autowired
    private AccessToken accessToken;

    @Override
    public Result getApprovalInstanceDetails() throws Exception {
        com.aliyun.dingtalkworkflow_1_0.Client client = OaApprovalServiceImpl.createClient();
        com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceHeaders getProcessInstanceHeaders = new com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceHeaders();
        getProcessInstanceHeaders.xAcsDingtalkAccessToken = accessToken.getAccessToken();
        com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceRequest getProcessInstanceRequest = new com.aliyun.dingtalkworkflow_1_0.models.GetProcessInstanceRequest()
                .setProcessInstanceId("weZFZ97-ToS8_8d7dJjezg06971713419298");
        try {
            GetProcessInstanceResponse response =  client.getProcessInstanceWithOptions(getProcessInstanceRequest, getProcessInstanceHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            System.out.printf(JSON.toJSONString(response.getBody()));
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }
        }
        return Result.success();
    }

    @Override
    public Result getApprovalInstanceIdList() throws Exception {
        com.aliyun.dingtalkworkflow_1_0.Client client = OaApprovalServiceImpl.createClient();
        com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsHeaders listProcessInstanceIdsHeaders = new com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsHeaders();
        listProcessInstanceIdsHeaders.xAcsDingtalkAccessToken = accessToken.getAccessToken();
        System.out.printf(accessToken.getAccessToken());
        com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsRequest listProcessInstanceIdsRequest = new com.aliyun.dingtalkworkflow_1_0.models.ListProcessInstanceIdsRequest()
                .setProcessCode("PROC-7C269E6C-DCF3-4E3F-8B78-2423315AB6B6")
                .setNextToken(0L)
                .setMaxResults(10L)
                .setStartTime(1496678400000L)
                .setEndTime(1713582995000L)
                .setUserIds(java.util.Arrays.asList(
                        "020217234422848283"
                ))
                .setStatuses(java.util.Arrays.asList(
                        "RUNNING"
                ));
        try {
            ListProcessInstanceIdsResponse response = client.listProcessInstanceIdsWithOptions(listProcessInstanceIdsRequest, listProcessInstanceIdsHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            System.out.printf(JSON.toJSONString(response.getBody()));
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }
        }
        return Result.success();
    }

    @Override
    public void printApprovalPdf(HttpServletResponse response) throws Exception {

    }


//    @Override
//    public void printApprovalPdf(HttpServletResponse response) throws Exception {
//        try {
//            // 创建 PDF 文档
//            PDDocument pdfDocument = new PDDocument();
//            PDPage page = new PDPage();
//            pdfDocument.addPage(page);
//            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
//
//            // 设置字体和字号
//            PDFont font = PDType1Font.HELVETICA_BOLD;
//            float fontSize = 12;
//
//            // 添加标题
//            String title = "审批详情";
//            contentStream.beginText();
//            contentStream.setFont(font, fontSize);
//            contentStream.newLineAtOffset(100, 700); // 设置标题位置
//            contentStream.showText(title);
//            contentStream.endText();
//
//            // 添加表格
//            float margin = 50;
//            float yStart = 650;
//            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
//            float yPosition = yStart;
//            float rowHeight = 20;
//            boolean drawContent = true;
//            boolean drawLines = true;
//
//            BaseTable baseTable = new BaseTable(yPosition, yStart, margin, tableWidth, rowHeight, pdfDocument, page, true, drawContent, drawLines);
//            MultiGauge.Row<PDPage> headerRow = baseTable.createRow(15f);
//            Cell<PDPage> cell = headerRow.createCell(100, "表头");
//            cell.setFont(PDType1Font.HELVETICA_BOLD);
//            baseTable.addHeaderRow(headerRow);
//
//            // 添加审批内容
//            List<Map<String, String>> formComponentValues = getFormComponentValues(); // 获取审批内容列表
//            for (Map<String, String> componentValue : formComponentValues) {
//                String name = componentValue.get("name");
//                String value = componentValue.get("value");
//                if (name != null && value != null) {
//                    MultiGauge.Row<PDPage> row = baseTable.createRow(rowHeight);
//                    row.createCell(33, name);
//                    row.createCell(33, value);
//                    baseTable.add(row);
//                }
//            }
//
//            // 添加审批详情链接
//            MultiGauge.Row<PDPage> linkRow = baseTable.createRow(rowHeight);
//            linkRow.createCell(100, "请查阅审批详情链接");
//            baseTable.add(linkRow);
//
//            // 将表格渲染到页面
//            baseTable.draw();
//
//            // 将 PDF 写入响应流
//            response.setContentType("application/pdf");
//            response.setHeader("Content-Disposition", "attachment; filename=审批详情.pdf");
//            pdfDocument.save(response.getOutputStream());
//
//            // 关闭流
//            contentStream.close();
//            pdfDocument.close();
//
//            System.out.println("PDF 文档已创建成功！");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void printApprovalDetails(HttpServletResponse response) throws Exception {
        try {
            XWPFDocument document = new XWPFDocument();

            // 添加标题
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText("审批详情");
            titleRun.setBold(true);
            titleRun.setFontSize(30);

            // 创建表格
            XWPFTable table = null;
            List<XWPFTable> tables = document.getTables();
            if (tables != null && tables.size() > 0) {
                table = tables.get(0); // 获取第一个表格
            }
            if (table == null) {
                table = document.createTable(); // 创建新表格
            }

            AddTableRow addTableRow = new AddTableRow();

            addTableRow.addTableRow(table, "审批单号", "202404181427000319446", "");
            addTableRow.addTableRow(table, "创建人", "李漩", "");
            addTableRow.addTableRow(table, "创建时间", "2024-04-18T14:27Z", "");
            addTableRow.addTableRow(table, "创建部门", "AAA", "");



            // 添加审批内容
            List<Map<String, String>> formComponentValues = getFormComponentValues(); // 获取审批内容列表
            for (Map<String, String> componentValue : formComponentValues) {
                String name = componentValue.get("name");
                String value = componentValue.get("value");
                if (name != null && value != null) {
                    addTableRow.addTableRow(table, name, value,"");
                }
            }

            // 添加审批详情链接
            addTableRow.addTableRow(table, "请查阅审批详情链接", "aflow.dingtalk.com?procInsId=an61lbvSR5WxoBfqAvLl2w06971713421651&taskId=86141787608&businessId=202404181427000319446","");

            // 将文档写入 ByteArrayOutputStream
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            document.write(outStream);
            document.close();


            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            response.setHeader("Content-Disposition", "attachment; filename=审批详情.docx");

            // 将 ByteArrayOutputStream 中的内容写入响应体
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(outStream.toByteArray());
            outputStream.close();

            System.out.println("Word 文档已创建成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void printApprovalDetailsToPdf(HttpServletResponse response) throws Exception {
        try {
            XWPFDocument document = new XWPFDocument();

            // 添加标题
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText("审批详情");
            titleRun.setBold(true);
            titleRun.setFontSize(30);

            // 创建表格
            XWPFTable table = null;
            List<XWPFTable> tables = document.getTables();
            if (tables != null && tables.size() > 0) {
                table = tables.get(0); // 获取第一个表格
            }
            if (table == null) {
                table = document.createTable(); // 创建新表格
            }

            AddTableRow addTableRow = new AddTableRow();

            addTableRow.addTableRow(table, "审批单号", "202404181427000319446", "");
            addTableRow.addTableRow(table, "创建人", "李漩", "");
            addTableRow.addTableRow(table, "创建时间", "2024-04-18T14:27Z", "");
            addTableRow.addTableRow(table, "创建部门", "AAA", "");



            // 添加审批内容
            List<Map<String, String>> formComponentValues = getFormComponentValues(); // 获取审批内容列表
            for (Map<String, String> componentValue : formComponentValues) {
                String name = componentValue.get("name");
                String value = componentValue.get("value");
                if (name != null && value != null) {
                    addTableRow.addTableRow(table, name, value,"");
                }
            }

            // 添加审批详情链接
            addTableRow.addTableRow(table, "请查阅审批详情链接", "aflow.dingtalk.com?procInsId=an61lbvSR5WxoBfqAvLl2w06971713421651&taskId=86141787608&businessId=202404181427000319446","");

            // 将文档写入 ByteArrayOutputStream
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            document.write(outStream);
            document.close();

            // 转换为 PDF
            ByteArrayInputStream docxInputStream = new ByteArrayInputStream(outStream.toByteArray());
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            convertToPdf(docxInputStream, pdfStream);

            // 设置响应头
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=审批详情.pdf");

            // 将 PDF 写入响应体
            ServletOutputStream outputStream = response.getOutputStream();
            pdfStream.writeTo(outputStream);
            outputStream.close();

            System.out.println("PDF 文档已创建成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void convertToPdf(InputStream docxInputStream, OutputStream pdfOutputStream) throws IOException {
        // Load the Word document
        XWPFDocument document = new XWPFDocument(docxInputStream);

        // Create a PDF document
        PDDocument pdfDocument = new PDDocument();
        PDPage page = new PDPage();
        pdfDocument.addPage(page);

        // Create a PDF content stream
        PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);

        // Create a font
        PDFont font = PDType1Font.HELVETICA;
        float fontSize = 12;

        // Iterate through paragraphs in the Word document and add them to the PDF
        for (XWPFParagraph p : document.getParagraphs()) {
            // Create a new line
            contentStream.newLineAtOffset(100, 700);

            // Set alignment
//            if (p.getAlignment() == ParagraphAlignment.CENTER) {
//                contentStream.setTextAlignment(TextAlignment.CENTER);
//            } else if (p.getAlignment() == ParagraphAlignment.RIGHT) {
//                contentStream.setTextAlignment(TextAlignment.RIGHT);
//            } else {
//                contentStream.setTextAlignment(TextAlignment.LEFT);
//            }

            // Iterate through runs in the Word paragraph and add their text to the PDF
            for (XWPFRun r : p.getRuns()) {
                // Set font size and style
                if (r.getFontSize() != -1) {
                    fontSize = r.getFontSize();
                }
                if (r.isBold()) {
                    font = PDType1Font.HELVETICA_BOLD;
                }
                if (r.isItalic()) {
                    font = PDType1Font.HELVETICA_OBLIQUE;
                }

                // Add text to the PDF content stream
                contentStream.setFont(font, fontSize);
                contentStream.showText(r.getText(0));
            }
            contentStream.newLine();
        }

        // Close the content stream and save the PDF document
        contentStream.close();
        pdfDocument.save(pdfOutputStream);

        // Close the PDF document
        pdfDocument.close();
    }



//    public static void convertToPdf(InputStream docxInputStream, OutputStream pdfOutputStream) throws IOException {
//        // Load the Word document
//        XWPFDocument document = new XWPFDocument(docxInputStream);
//
//        // Create a PdfWriter
//        PdfWriter writer = new PdfWriter(pdfOutputStream);
//
//        // Create a PdfDocument
//        PdfDocument pdf = new PdfDocument(writer);
//
//        // Create a Document
//        com.itextpdf.layout.Document pdfDocument = new com.itextpdf.layout.Document(pdf);
//
//        // Iterate through paragraphs in the Word document and add them to the PDF
//        for (XWPFParagraph p : document.getParagraphs()) {
//
//            System.out.println("Paragraph text: " + p.getText());
//
//            System.out.println("Alignment: " + p.getAlignment());
//            // Create a new paragraph in the PDF document
//            com.itextpdf.layout.element.Paragraph pdfParagraph = new com.itextpdf.layout.element.Paragraph();
//
//            // Iterate through runs in the Word paragraph and add their text to the PDF paragraph
//            for (XWPFRun r : p.getRuns()) {
//                System.out.println("Run text: " + r.getText(0));
//                // Create a new Text object with the run's text
//                Text text = new Text(r.getText(0));
//
//
//                // Apply the run's font size and style to the Text object
//                text.setFontSize(r.getFontSize());
//                if (r.isBold()) {
//                    text.setBold();
//                }
//                if (r.isItalic()) {
//                    text.setItalic();
//                }
//                if (r.getUnderline() != UnderlinePatterns.NONE) {
//                    text.setUnderline();
//                }
////                if (r.getColor() != null) {
////                    text.setFontColor(Color.convertRgbToCmyk(new DeviceRgb(r.getColor().getRGB())));
////                }
//
//                // Add the Text object to the PDF paragraph
//                pdfParagraph.add(text);
//            }
//
//            // Add the PDF paragraph to the PDF document
//            pdfDocument.add(pdfParagraph);
//        }
//
//        // Close the document
//        pdfDocument.close();
//        pdf.close();
//        writer.close();
//    }
//


    private static List<Map<String, String>> getFormComponentValues() {
        // 模拟获取审批内容列表的方法，实际应根据您的业务逻辑来获取
        // 这里简单地返回一个示例列表
        // 请根据实际情况修改返回的内容
        List<Map<String, String>> formComponentValues = new ArrayList<>();
        formComponentValues.add(createEntry("开始时间", "2024-04-23 10:12"));
        formComponentValues.add(createEntry("结束时间", "2024-04-23 11:12"));
        formComponentValues.add(createEntry("请假类型", "事假"));
        formComponentValues.add(createEntry("请假理由", "但是比v输入法牛逼高分低能吧sRDGVwrehbgSGV"));
        return formComponentValues;
    }

    private static Map<String, String> createEntry(String key, String value) {
        Map<String, String> entry = new HashMap<>();
        entry.put("name", key);
        entry.put("value", value);
        return entry;
    }



    public static com.aliyun.dingtalkworkflow_1_0.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkworkflow_1_0.Client(config);
    }


}
