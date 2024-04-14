package Utilidades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import Modelo.Usuario;

public class GenerarExcel {
	
	public static void generarExcel(List<Usuario> usuarios) {
		HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Usuarios");

        // Crear encabezados de columnas
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nombre");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Presentación");

        // Llenar filas con datos de usuarios
        int rowNum = 1;
        for (Usuario usuario : usuarios) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(usuario.getNombre());
            row.createCell(1).setCellValue(usuario.getEmail());
            row.createCell(2).setCellValue(usuario.getPresentacionPerfil());
        }

        // Ajustar el ancho de las columnas automáticamente
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar el archivo de Excel
        try (FileOutputStream fileOut = new FileOutputStream("excelPhotoTDS.xls")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cerrar el libro de trabajo
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
