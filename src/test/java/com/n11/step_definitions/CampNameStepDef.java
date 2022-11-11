package com.n11.step_definitions;


import com.n11.pages.CampaignsPage;
import com.n11.utilities.ConfigurationReader;
import com.n11.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;


public class CampNameStepDef {

    WebDriver driver = Driver.get();
    WebDriverWait wait = new WebDriverWait(Driver.get(), 15);
    //GetCampaignUrls getCampaignUrls = new GetCampaignUrls();
    CampaignsPage campaignsPage = new CampaignsPage();

    @Given("Kullanici tarayiciyi acar kampanyalar sayfasina gider")
    public void kullanici_tarayiciyi_acar_kampanyalar_sayfasina_gider() {
        //Goes to the given url
        driver.get(ConfigurationReader.get("url"));
        //Confirmation that expected url equals current url
        Assert.assertEquals(ConfigurationReader.get("url"),driver.getCurrentUrl());
        //Wait until close cookies button is clickable
        //wait.until(ExpectedConditions.elementToBeClickable(campaignsPage.closeCookie));
        //Close cookies into the page
        campaignsPage.closeCookies();
    }

    @When("Kullanici her bir kategoriye tek tek tıkladiğinda kampanyalar sayfasına gider")
    public void kullanici_her_bir_kategoriye_tek_tek_tıkladiğinda_kampanyalar_sayfasına_gider() {
        //Click each category one by one and get campaign urls in the n11 campaigns page
        campaignsPage.getCampaignUrls();
    }

    @Then("Kullanıcı kategorilerdeki her kampanya URL ini excel dosyasına aktarır")
    public void Kullanici_kategorilerdeki_her_kampanya_URL_ini_excel_dosyasına_aktarır() throws IOException {
        //Import all urls which pulled from categories and write to the Excel
        campaignsPage.importToExcel();
    }
}
