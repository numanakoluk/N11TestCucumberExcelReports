package com.n11.pages;

import com.n11.utilities.Driver;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CampaignsPage {
    public CampaignsPage() {
        PageFactory.initElements(Driver.get(), this);}

    //Close cookie button
    @FindBy(xpath ="//*[@class=\"setCookieBtn clearAll\"]")
    public WebElement closeCookie;

    //Gives all category names in campaigns page for using size and put them in a list
    @FindBy(xpath ="//*[@class=\"campPromTab\"]/li")
    public  List<WebElement> categoryTitle; //= driver.findElements(By.xpath("//*[@class=\"campPromTab\"]/li"));

    static WebDriver driver = Driver.get();

    //Get each category names dynamically and click them in a for loop
    public WebElement getCategoryTitle(int categoryNumber) {
        return driver.findElement(By.xpath("//*[@class=\"campPromTab\"]/li[" + categoryNumber + "]"));
    }

    //Gives all campaigns numbers in each category for using size
    public List<WebElement> getCampaignsNumbers(int categoryNumber) {
        return driver.findElements(By.xpath("//*[@class=\"tabPanel\"]/div/section[" + categoryNumber + "]/ul/li/a"));
    }

    //Get each category names and campaign urls dynamically in a for loop
    public WebElement getCampaigns(int categoryNumber, int campaignNumber) {
        return driver.findElement(By.xpath("//*[@class=\"tabPanel\"]/div/section[" + categoryNumber + "]/ul/li[" + campaignNumber + "]/a"));
    }

    ArrayList<String> categoryName = new ArrayList<>();
    ArrayList<String> campaignsUrlText = new ArrayList<>();


    public void closeCookies() {
        //Click and close cookies in page
        closeCookie.click();
    }

    public void getCampaignUrls() {

        //Count all each category in a loop
        for (int i = 1; i <= categoryTitle.size(); i++) {
            //Click each category
            getCategoryTitle(i).click();
            //Count all campaigns each category in a loop
            for (int j = 1; j <= getCampaignsNumbers(i).size(); j++) {
                //Get next category
                categoryName.add(getCategoryTitle(i).getText());
                //Add campaign urls in each category to an array list
                campaignsUrlText.add(getCampaigns((i), j).getAttribute("href"));
            }
        }
    }

    public void importToExcel() throws IOException {

        //Give a name to an Excel file
        String excelFileName = "Campaigns.xls";
        //Give a name to an Excel sheet
        String sheetName = "CampaignUrls";
        //Create a workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //Create a sheet in an Exel file
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //Count category name list in a loop
        for (int i = 0; i<categoryName.size(); i++){
            //Create rows and cells then set a value which is category name
            sheet.createRow(i).createCell(0).setCellValue(categoryName.get(i));
            //Create rows and cells then set a value which is campaign urls
            sheet.getRow(i).createCell(1).setCellValue(campaignsUrlText.get(i));
        }
        FileOutputStream fileOut = new FileOutputStream(excelFileName);

        //Write this workbook to a stream
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}



