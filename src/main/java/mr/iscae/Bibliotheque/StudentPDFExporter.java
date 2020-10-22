package mr.iscae.Bibliotheque;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import mr.iscae.Bibliotheque.entity.Student;

public class StudentPDFExporter {
	private List<Student> listStudents;

	public StudentPDFExporter(List<Student> listStudents) {
		super();
		this.listStudents = listStudents;
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		cell.setPhrase(new Phrase("ID"));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Name"));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Email"));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Phone"));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table) {
		
		for(Student student : listStudents) {
			table.addCell(String.valueOf(student.getId()));
			table.addCell(student.getName());
			table.addCell(student.getEmail());
			table.addCell(String.valueOf(student.getPhoneNo()));
		}
		
	}
	
	public void export(HttpServletResponse response ) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		document.add(new Paragraph("List of all students"));
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		document.close();
	}

}
