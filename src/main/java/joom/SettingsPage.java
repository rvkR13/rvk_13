package joom;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SettingsPage {
    protected AndroidDriver driver;

    public SettingsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    }

    //Элемент  кнопки валюты для открытия списка
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Currency\")")
    private WebElement currencyButton; //

    //скролл в списке валюты до строки US Dollar
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"US Dollar\"))")
    private WebElement usDollar;

    //кнопка назад в верхнем левом углу
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Back\"]")
    private WebElement backButton;

    //кнопка Домой(возврат на главную)
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]")
    private WebElement homePageButton;

    /**
     * Находясь на экране настроек клик по элементк Валюты
     * Скролл в списке до строки US Dollar
     *
     * @return
     */
    public SettingsPage choiseUS() {
        currencyButton.click();// в настройках клик по выбору Валюты
        usDollar.click();// в валюте выбор нужного значения
        return this;
    }

    public ProfilePage pressBackButton() {
        backButton.click();// из настроек клик по кнопке назад в Профиль
        return new ProfilePage(driver);
    }


    }


