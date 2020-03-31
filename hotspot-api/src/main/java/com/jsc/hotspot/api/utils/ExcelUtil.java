//package com.jsc.hotspot.api.utils;
//
//
///**
// * @author huixing
// * @description Excel千万级别数据导出
// * @date 2020/3/24
// */
//public class ExcelUtil {
//
//    /**
//     * 导出Excel
//     * @param sheetName sheet名称
//     * @param title 标题
//     * @param values 内容
//     * @param wb SXSSFWorkbook对象
//     * @return
//     */
//    public static SXSSFWorkbook getSXSSFWorkbook(String sheetName, String []title, String [][]values, SXSSFWorkbook  wb){
//
//        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
//        if(wb == null){
//            wb = new SXSSFWorkbook (-1);
//        }
//
//        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
//        Sheet sheet = wb.createSheet(sheetName);
//        //冻结首行
//        sheet.createFreezePane( 0, 1, 0, 1 );
//        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
//        Row row = sheet.createRow(0);
//
//
//        // 第四步，创建单元格，并设置值表头 设置表头居中
//        CellStyle style = wb.createCellStyle();
////        style.setAlignment(HSSFCe); // 创建一个居中格式
////        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
////        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
//        //声明列对象
//        Cell cell = null;
//
//        //创建标题
//        for(int i=0;i<title.length;i++){
//            cell = row.createCell(i);
//            cell.setCellValue(title[i]);
//            cell.setCellStyle(style);
//            sheet.autoSizeColumn(i);
//        }
//        //创建内容
//        for(int i=0;i<values.length;i++){
//            row = sheet.createRow(i + 1);
//            for(int j=0;j<values[i].length;j++){
//                //将内容按顺序赋给对应的列对象
//                row.createCell(j).setCellValue(values[i][j]);
//            }
//
//        }
//        // 必须在单元格设值以后进行
//        // 设置为根据内容自动调整列宽
//        for (int k = 0; k <title.length; k++) {
//            sheet.autoSizeColumn(k);
//            sheet.setColumnWidth(k,sheet.getColumnWidth(k)*17/10);
//        }
////        // 处理中文不能自动调整列宽的问题
//        setSizeColumn(sheet,title.length);
//
//        return wb;
//    }
//    // 自适应宽度(中文支持)
//    public static void setSizeColumn(Sheet sheet, int size) {
//        for (int columnNum = 1; columnNum <=size; columnNum++) {
//            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
//            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
//                Row currentRow;
//                //当前行未被使用过
//                if (sheet.getRow(rowNum) == null) {
//                    currentRow = sheet.createRow(rowNum);
//                } else {
//                    currentRow = sheet.getRow(rowNum);
//                }
//
//                if (currentRow.getCell(columnNum) != null) {
//                    Cell currentCell = currentRow.getCell(columnNum);
//                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                        int length = currentCell.getStringCellValue().getBytes().length;
//                        if (columnWidth < length) {
//                            columnWidth = length;
//                        }
//                    }
//                }
//            }
//            sheet.setColumnWidth(columnNum, columnWidth * 256);
//        }
//    }
//}
