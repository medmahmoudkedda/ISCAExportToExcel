package mr.iscae.Bibliotheque.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lowagie.text.DocumentException;

import mr.iscae.Bibliotheque.StudentExcelExporter;
import mr.iscae.Bibliotheque.StudentPDFExporter;
import mr.iscae.Bibliotheque.entity.Student;
import mr.iscae.Bibliotheque.repository.StudentRepository;


@Controller
@RequestMapping("/students/")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;	
	

	@GetMapping("exportToPdf")
	public void  exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		
		String headerkey = "Content-Disposition";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		String filename = "Students_" + currentDate + ".pdf";
		String headervalue = "attachment; filename=" + filename;
		
		response.setHeader(headerkey, headervalue);
		List<Student> listStudents = this.studentRepository.findAll();
		
		StudentPDFExporter exporter = new StudentPDFExporter(listStudents);
		exporter.export(response);
	}
	
	@GetMapping("exportToCsv")
	public void  exportToCsv(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("text/csv");
		String headerkey = "Content-Disposition";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		String filename = "Students_" + currentDate + ".csv";
		String headervalue = "attachment; filename=" + filename;
		response.setHeader(headerkey, headervalue);
		List<Student> listStudents = this.studentRepository.findAll();
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"ID","Name","Email","Phone"};
		String[] nameMapping = {"id","name","email","phoneNo"};
		csvWriter.writeHeader(csvHeader);
		
		for(Student student : listStudents) {
			 csvWriter.write(student, nameMapping);
		}
		csvWriter.close();
	}
	
	@GetMapping("exportToExcel")
	public void  exportToExcel(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		String filename = "Students_" + currentDate + ".xlsx";
		String headervalue = "attachment; filename=" + filename;
		
		response.setHeader(headerkey, headervalue);
		
		List<Student> listStudents = this.studentRepository.findAll();
		
		StudentExcelExporter exporter = new StudentExcelExporter(listStudents);
		
		exporter.export(response);
	}
	

	
	
	
}
