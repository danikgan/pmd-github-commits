package second.process;

import common.CloseWorkbook;
import common.TemporaryFiles;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import second.process.data.BugzillaRestOutput;

import java.io.IOException;
import java.util.LinkedList;

public class SaveBugzillaHistory {
    private String[] columns = { "Project", "Bugzilla ID", "Change ID",
            "Changer", "Date",
            "Added", "Field Name", "Removed"};
    // constructor
    public SaveBugzillaHistory(LinkedList<BugzillaRestOutput> bugzillaRestOutputs, String inputPath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet( "Bugs History");
        // header font
        Font headerFont = workbook.createFont();
        headerFont.setBold(true); // font bold
        headerFont.setFontHeightInPoints((short) 14); // font size
        headerFont.setColor(IndexedColors.BLACK.getIndex()); // font color
        // header cell style
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setWrapText(true); // auto-wrapping for all rows, not just header
        // Create a header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        // Create Other rows and cells with contacts data
        int rowNum = 1;
        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setWrapText(true); // auto-wrapping for all rows, except for header
        for (BugzillaRestOutput bugzillaRestOutput : bugzillaRestOutputs) {
            for (int i = 0; i < bugzillaRestOutput.getChanges().size(); i++) { // equals to the # of commits
                Row row = sheet.createRow(rowNum++);
                // writing
                row.createCell(0).setCellValue(bugzillaRestOutput.getProject());
                row.createCell(1).setCellValue(bugzillaRestOutput.getId());
                row.createCell(2).setCellValue(bugzillaRestOutput.getChanges().get(i));
                row.createCell(3).setCellValue(bugzillaRestOutput.getWho().get(i));
                row.createCell(4).setCellValue(bugzillaRestOutput.getWhen().get(i));
                row.createCell(5).setCellValue(bugzillaRestOutput.getAdded().get(i));
                row.createCell(6).setCellValue(bugzillaRestOutput.getField_name().get(i));
                row.createCell(7).setCellValue(bugzillaRestOutput.getRemoved().get(i));
            }
        }
        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        System.out.println("Closing...");
        try {
            new CloseWorkbook(inputPath + "/" +
                    TemporaryFiles.analysing.OUTPUT_TWO.getString(), workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
