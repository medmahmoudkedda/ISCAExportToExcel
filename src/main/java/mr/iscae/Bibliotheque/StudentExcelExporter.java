package mr.iscae.Bibliotheque;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mr.iscae.Bibliotheque.entity.Student;


public class StudentExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Student> listStudents;
	
	
	public StudentExcelExporter(List<Student> listStudents) {
		super();
		this.listStudents = listStudents;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("students");
	}
	
	private void writeHeaderRow() {
		Row row  = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("ID");
		
		cell = row.createCell(1);
		cell.setCellValue("Name");
		
		cell = row.createCell(2);
		cell.setCellValue("Email");
		
		cell = row.createCell(3);
		cell.setCellValue("Phone");
		
	}
	private void writeDataRows() {
		
		int rowCount = 1;
		
		for(Student student : listStudents) {
			Row row = sheet.createRow(rowCount);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(student.getId());
			
			cell = row.createCell(1);
			cell.setCellValue(student.getName());
			
			cell = row.createCell(2);
			cell.setCellValue(student.getEmail());
			
			cell = row.createCell(3);
			cell.setCellValue(student.getPhoneNo());
			
			rowCount++;
		}
		
	}
	
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		writeDataRows();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	

}
