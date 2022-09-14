package joom;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SearchPage {
    protected AndroidDriver driver;

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    }

    //элемент ввода текста в поискову строку
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.EditText")
    private WebElement inputText;

    //пробежать по списку и найти искомое слово dresses
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"dresses\"))")
    private WebElement choiseDresses;

    // открытие окна сортировок
    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Filters, None\"]")
    private WebElement openFilters;

    //открытие списка фильтров
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.LinearLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[2]")
    private WebElement openSort;

    //выбор сортировка сначала дорогие. СДелано через поиск текста в кнопке
    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"Descending price\"))")
    private WebElement choiseDescendingPrice;

    //кнопка SHOW в окне фильтрации
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.Button")
    private WebElement buttonShow;

    /**
     * Ввод в поисковую строку keySearch
     * @param keySearch
     * @return
     */
    public SearchPage inputTextToString(String keySearch) {
        inputText.sendKeys(keySearch);
        return this;
    }

    /**
     * Открытие страницы с фильтрами сортировки
     * Клик по строке "сортировка по..."
     * Выбор сортировки по убыванию цены
     * Клик по элементу показать
     * @return
     */
    @Step("манипуляции с сортировой")
    public SearchPage sort() {
        openFilters.click();
        openSort.click();
        choiseDescendingPrice.click();
        buttonShow.click();
        return this;
    }

    /**
     * Выбор в поиске строки с текстом
     * @return
     */
    public SearchPage choiseSearch() {
        choiseDresses.click();
        return this;
    }
}