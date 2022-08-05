package com.example.nf.newfine_backend.test.excel.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellReference;

public class ExcelCellRef {
    /**
     * Cell에 해당하는 Column Name을 가젼온다(A,B,C..)
     * 만약 Cell이 Null이라면 int cellIndex의 값으로
     * Column Name을 가져온다.
     *
     * @param cell
     * @param cellIndex
     * @return
     */
    public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if (cell != null) {
            cellNum = cell.getColumnIndex();
        } else {
            cellNum = cellIndex;
        }

        return CellReference.convertNumToColString(cellNum);
    }

    public static String getValue(Cell cell) {
        String value = "";

        if (cell == null) {
            value = "";
        } else {
            switch (cell.getCellType()) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
//                    value = (int) cell.getNumericCellValue() + "";   // (int) 형으로 변환하지 않으면 소수점 발생 가능
//                    try {
//                        value = cell.getNumericCellValue() + "";
//                    } catch (Exception e){
//                        value = (int) cell.getNumericCellValue() + "";
//                    }
                    cell.setCellType(CellType.STRING);
                    value = cell.getStringCellValue();
                    break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue() + "";
                        break;
                    case ERROR:
                        value = cell.getErrorCellValue() + "";
                        break;
                    case BLANK:
                        value = "";
                        break;
                    case FORMULA:
                        value = cell.getCellFormula();
                        break;
                    default:
                        value = cell.getStringCellValue();
                }
            }

            return value;
        }
    }
