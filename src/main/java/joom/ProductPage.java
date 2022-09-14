package joom;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProductPage {
    protected AndroidDriver driver;

    public ProductPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    }

    //элемент Наименования у товара
    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Favorites\"]/android.view.View[2]")
    private WebElement buttonAddInListFavorites;
    //кнопка назад в верхнем левом углу
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Back\"]")
    private WebElement backButton;

    /**
     * добавления товара в избранное по клику на сердечку
     * @return
     */
    public ProductPage addToFavoritesGoods() {
        //product_info_title.getText();
        buttonAddInListFavorites.click();
        return this;
    }

    /**
     * Клик по кнопке назад
     * @return
     */
    public JoomAppHomePage pressBackButton() {
        backButton.click();
        return new JoomAppHomePage(driver);
    }
}