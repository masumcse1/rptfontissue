package com.rtp.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportFontTest {

	public static void main(String[] args) {

		Connection con = null;
		try {
			// connection to the database
			String url = "jdbc:mysql://localhost:3306/rptdb";
			String username = "root";
			String password = "root";
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();
			ResultSet resultset = null;
			resultset = stmt.executeQuery("select * from survey");

			JRResultSetDataSource jasperReports = new JRResultSetDataSource(resultset);
			JasperReport sourceFile = JasperCompileManager.compileReport("D:/reportissue/works.jrxml");

			JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFile, null, jasperReports);

		
			// Export to PDF.
			JasperExportManager.exportReportToPdfFile(jasperPrint, "D:/reportissue/TextReport.pdf");
			System.out.println("---------------------- pdf complete-------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
