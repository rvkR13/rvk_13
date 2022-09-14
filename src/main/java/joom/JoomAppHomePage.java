package joom;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;
import static joom.Waiters.waitUntilVisible;

@Data
public class JoomAppHomePage {
    protected AndroidDriver driver;

    //Закрытие стартовой сторис
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Close\"]")
    private WebElement closedStartModal;

    //Открытие страницы профиля. ---мейн скрин
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Profile\"]")
    private WebElement profile;

    // поисковая строка  ---мейн скрин
    @FindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Search\"])[1]")
    private WebElement searchString;
    //элемент поисковой строки для ввода текста---мейн скрин
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
    private WebElement searchStringInputText;

    //этот элемент хотел использовать для свайпа влево.
    @FindBy(xpath = "//*[@id=\"selectedElementContainer\"]/div/div[2]/div/div[3]/div/div/div/div/div/div/table/tbody/tr[2]/td[2]/div/span")
    private WebElement mainScreen;

    //клик по верхней левой плитке в товарах
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.viewpager.widget.a/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/" +
            "android.view.ViewGroup[1]/android.widget.ImageView[1]")
    public WebElement openFirstGoods;

    //элемент Наименования у товара
    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Favorites\"]/android.view.View[2]")
    private WebElement buttonAddInListFavorites;

    //кнопка Наименования у товара
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/" +
            "android.view.ViewGroup[2]/android.view.View[1]")
    public WebElement product_info_title;

    //кнопка Promotions
    @FindBy(xpath = " //android.widget.LinearLayout[@content-desc=\"Promotions\"]/android.view.ViewGroup/android.widget.TextView")
    public WebElement buttonPromotions;

    //кнопка Избранное в профиле
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.Button[1]")
    private WebElement buttonFavorites;

    //рефреш в избранном. Но это тоже не то, такая же история как с горизонтальным свайпом
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.a/android.view.ViewGroup")
    private WebElement refreshOnFavorites;

    //элемент экрана для того чтобы понятнуть вниз во вкладке избранное
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.a/android.view.ViewGroup")
    private WebElement element;

    //кнопка Store
    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Stores\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement buttonStore;

    //элемент SALE  необходим для свайпа
    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Sale \uD83D\uDD25\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement buttonSale;

    //стартовая всплывашка
    @FindBy(xpath = "//android.view.View[@content-desc=\"Close\"]")
    private WebElement startPopUpAds;

    //кнопка назад в верхнем левом углу
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Back\"]")
    private WebElement backButton;

    //элемент для ожидания загрузки .используется в ТЕСТЕ
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.FrameLayout[3]/androidx.viewpager.widget.a/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]")
    public WebElement mainProd;

    public JoomAppHomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        // чтобы заработал findElementByAndroidUIAutomator(он ниже) нужно это раскомментить
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    }

    /**
     * Закрытие стартового окна
     * Был нужен, но сейчас почему при запуске приложения Joom этого экрана нет.
     *
     * @return
     */
    @Step("Закрытие стартовой вслывающей сторис")
    public JoomAppHomePage closeStartStory() {
        closedStartModal.click();
        return this;
    }

    /**
     * закрытие сторис при открытии приложения
     *
     * @return
     */
    @Step("Закрытие стартового поп Ап окна")// название отредачить
    public JoomAppHomePage closeStartPopUpAds() {
        startPopUpAds.click();
        return this;
    }

    /**
     * клик по поисковой странице
     *
     * @return
     */
    @Step("клик по поиско строке и ввод текста")
    public SearchPage enterTextOnSearchString() {
        searchString.click();
        searchStringInputText.click();
        return new SearchPage(driver);
    }

    /**
     * переход в профиль из главной
     *
     * @return
     */
    public ProfilePage openProfilePage() {
        profile.click(); // переход в профиль из главной
        return new ProfilePage(driver);
    }

    /**
     * Свайп влево
     * для справа вправло
     * если нужен горизонтальный  необходимо менять Х координату
     * если нужен вертикальный  необходимо менять Y координату
     *
     * @return
     */

    public JoomAppHomePage swipeElementsLeft() {
        waitUntilVisible(driver, buttonPromotions);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(ofMillis(0),
                PointerInput.Origin.viewport(), 1000, 800));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        sequence.addAction(new Pause(finger, ofMillis(600)));
        sequence.addAction(finger.createPointerMove(ofMillis(600),
                PointerInput.Origin.viewport(), 80, 800));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(singletonList(sequence));
        return this;
    }

    /**
     * Свайп вправо
     * для справа вправло
     * если нужен горизонтальный  необходимо менять Х координату
     * если нужен вертикальный  необходимо менять Y координату
     *
     * @return
     */
    public JoomAppHomePage swipeElementsR() {
        waitUntilVisible(driver, buttonPromotions);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(ofMillis(0),
                PointerInput.Origin.viewport(), 80, 800));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        sequence.addAction(new Pause(finger, ofMillis(600)));
        sequence.addAction(finger.createPointerMove(ofMillis(600),
                PointerInput.Origin.viewport(), 1000, 800));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(singletonList(sequence));
        return this;
    }

    /**
     * открытие товара на домашней странице
     *
     * @return
     */
    public ProductPage openGoods() {
        openFirstGoods.click();
        return new ProductPage(driver);
    }

    /**
     * переход на вкладку Store
     */
    public JoomAppHomePage openStore() {
        buttonStore.click();
        return this;
    }

    /**
     * метод чтобы обновить экран потянув его вниз
     * координаты X Y направления и области свайпа
     *
     * @param x
     * @param y
     */
    public void swipeVertical(int x, int y) {
        new Actions(driver).dragAndDropBy(element, x, y).perform();
    }
}