package joom;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProfilePage {
    protected AndroidDriver driver;
    //элемент экрана для того чтобы понятнуть вниз во вкладке избранное
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.a/android.view.ViewGroup")
    private WebElement elementForSwipe;

    //Кнопка настроек
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Settings\"]")
    private WebElement settings;

    //кнопка назад в верхнем левом углу
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Back\"]")
    private WebElement backButton;

    //кнопка Домой(возврат на главную)
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/" +
            "android.view.ViewGroup[1]")
    private WebElement homePageButton;

    //кнопка Избранное в профиле
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.Button[1]")
    private WebElement buttonFavorites;

    //рефреш в избранном.
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.a/android.view.ViewGroup")
    private WebElement refreshOnFavorites;

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    }

    /**
     * Открытие на экране профиля Настроек
     *
     * @return
     */
    public SettingsPage openSettings() {
        settings.click();// в профиле открытие настроек
        return new SettingsPage(driver);
    }

    /**
     * Переход с экрана профиль на домашнюю страницу
     */
    public JoomAppHomePage returnToHomePage() {
        homePageButton.click();// из профиля переход на домашнюю
        return new JoomAppHomePage(driver);
    }

    /**
     * клик по элементу Favorites
     *
     * @return
     */
    public ProfilePage openFavorites() {
        buttonFavorites.click();
        //  refreshOnFavorites.click();
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
        new Actions(driver).dragAndDropBy(elementForSwipe, x, y).perform();
    }
}