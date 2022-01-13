package tests;
import org.testng.annotations.Test;

import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class ComparingTables {
	private WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		String url = "https://the-internet.herokuapp.com/tables";
		int timeout = 5;
		String chromeDriverPath = "C:\\Users\\wzwjq0\\eclipse-workspace\\Automation\\drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@Test
	public void webTableComparison() {

		// Initialize List of List of strings to represent table1
		List<List<String>> table1DataList = new ArrayList<List<String>>();
		// Initialize List of List of strings to represent table2
		List<List<String>> table2DataList = new ArrayList<List<String>>();

		// read table 1
		WebElement table1 = driver.findElement(By.xpath("//*[@id='table1']"));
		List<WebElement> table1Rows = table1.findElements(By.tagName("tr"));
		int table1RowCount = table1Rows.size();
		// read table 1 rows
		for (int i = 0; i < table1RowCount; i++) {
			// Initialize of a list to represent a row
			List<String> table1RowDataList = new ArrayList<String>(table1RowCount);
			// read each header names
			List<WebElement> table1Header = table1Rows.get(i).findElements(By.tagName("th"));
			for (WebElement th : table1Header) {
				String headerValue = th.findElement(By.tagName("span")).getText();
				table1RowDataList.add(headerValue);
			}

			// read each cell of given row
			List<WebElement> table1Columns = table1Rows.get(i).findElements(By.tagName("td"));
			for (WebElement columns : table1Columns) {
				String colValue = columns.getText();
				table1RowDataList.add(colValue);
			}
			// remove actions cell value
			table1RowDataList.remove(table1RowDataList.size() - 1);
			// add row to the list of rows
			table1DataList.add(table1RowDataList);
		}

		// read table 2
		WebElement table2 = driver.findElement(By.xpath("//*[@id='table2']"));
		List<WebElement> table2Rows = table2.findElements(By.tagName("tr"));
		int table2RowCount = table2Rows.size();
		// read table 2 rows
		for (int i = 0; i < table2RowCount; i++) {
			// Initialize of a list to represent a row
			List<String> table2RowDataList = new ArrayList<String>(table2RowCount);
			// read each header names
			List<WebElement> table2Header = table1Rows.get(i).findElements(By.tagName("th"));
			for (WebElement th : table2Header) {
				String headerValue = th.findElement(By.tagName("span")).getText();
				table2RowDataList.add(headerValue);
			}

			// read each cell of given row
			List<WebElement> table2Columns = table2Rows.get(i).findElements(By.tagName("td"));
			for (WebElement columns : table2Columns) {
				String colValue = columns.getText();
				table2RowDataList.add(colValue);
			}
			// remove Actions Header and Cell value
			table2RowDataList.remove(table2RowDataList.size() - 1);
			// add row to the list of rows
			table2DataList.add(table2RowDataList);
		}

		System.out.println(table1DataList);
		System.out.println(table2DataList);

		// verify whether both tables has equal number of rows
		assertTrue(table1DataList.size() == table2DataList.size(),
				"Rows Count of tables didn't match. \n Table 1 Row Count = " + table1DataList.size()
						+ "\nTable 2 Row Count = " + table2DataList.size());
		// Verify whether Headers of both tables matches
		assertTrue(table1DataList.get(0).equals(table2DataList.get(0)),
				"Headers of the table didn't match.\nTable 1 Headers: " + table1DataList.get(0) + "\nTable 2 Headers: "
						+ table2DataList.get(0));

		// compare the data in the tables by verifying data of row from one table is present in another table
		for (List<String> td : table1DataList) {
			System.out.println(td);
			assertTrue(table2DataList.contains(td),
					"This Row Data in Table 1 is not matching with Table 2. \nTable 1 Not Matching Data - " + td);
		}
		
	}
}
