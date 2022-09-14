import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.qameta.allure.Epic;
import joom.JoomAppHomePage;
import joom.ProductPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static joom.Waiters.waitUntilVisible;

public class JoomTest {
    private static final int PORT = 4724;
    private AppiumDriverLocalService service;
    protected AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
//        service = new AppiumServiceBuilder()//запуск аппиум сервера в ide
//                .withIPAddress("127.0.0.1")
//                .usingPort(PORT)
//                .build();
//        service.withBasePath("/wd/hub/");
//        service.start();
//        UiAutomator2Options options = new UiAutomator2Options()
//                .setDeviceName("emulator-5554")
//                .setApp("C:\\Users\\user\\Downloads\\joom(4.2.1).apk")
//                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
//                .eventTimings();
//        URL remoteUrl = new URL("http://127.0.0.1:4724/wd/hub");
//        driver = new AndroidDriver(remoteUrl, options);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:deviceName", "emulator-5556");
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\user\\Downloads\\joom(4.2.1).apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
        // service.stop();
    }

    /**
     * Кейс: Выбор валюты
     * Шаги:
     * Перейти в профиль
     * Выбрать настройки
     * Выбрать “Валюта”
     * Выбрать “Доллар США”
     * Перейти на Главную страницу
     * Ожидаемый результат: цены в долларах
     */
    @Epic("Joom")
    @Test
    public void choiseUSdollar() {
        JoomAppHomePage joomApp = new JoomAppHomePage(driver);
        // при запуске приложения может быть либо модалка или попАп
        // joomApp.closeModal();
        joomApp.closeStartPopUpAds();
        joomApp.openProfilePage()
                .openSettings()
                .choiseUS()
                .pressBackButton()
                .returnToHomePage();
        waitUntilVisible(driver, joomApp.getMainProd());
        String actualText = driver.findElement(By.xpath("//*[@class='android.widget.TextView'][contains(@content-desc, 'Current price: $')][@index= 1]")).getText();
        Assert.assertTrue("сообщение об ошибке", actualText.contains("$"));
    }

    /**
     * Кейс: Поиск товара через поисковую стороку и его сортировка
     * Шаги:
     * Нажать “Поиск в Joom”
     * Ввести “Платья”
     * Выбрать из предложенных вариантов “платья”
     * Выбрать “Сортировать”
     * Выбрать “По возрастающей цене”
     * Ожидаемый результат: цена товаров стала отсортирована по возрастанию.
     */
    @Test
    public void search() {
        JoomAppHomePage joomApp = new JoomAppHomePage(driver);
        // при запуске приложения может быть либо модалка или попАп
        // joomApp.closeModal();
        joomApp.closeStartPopUpAds();
        joomApp.enterTextOnSearchString()
                .inputTextToString("dresse")
                .choiseSearch()
                .sort();
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            String elements = driver.findElement((By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                    "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                    "/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout[2]" +
                    "/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[" + i + "]/android.widget.TextView"))).getText();
//            String value = elements.substring(4);
//            System.out.println(value);
//            String value2 = value.replace(",", ".");
//            Double x = Double.parseDouble(value2);
//            list.add(x);
            String value = elements.replaceAll("^\\D+", "");
            String value2 = value.replace(",", ".");
            Double x = Double.parseDouble(value2);
            list.add(x);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i + 1 < list.size()) {
                Assert.assertTrue(list.get(i) > list.get(i + 1));
            }
        }
    }

    /**
     * Вкладки
     * Шаги:
     * Открыть главный экран
     * Сделать свайп вправо
     * Ожидаемый результат: вкладка 'Магазины' открыта (активна), вкладка 'Лучшее' не активна.
     */

    @Test
    public void swipe() {
        JoomAppHomePage joomApp = new JoomAppHomePage(driver);
        // при запуске приложения может быть либо модалка или попАп
        // joomApp.closeModal();
        joomApp.closeStartPopUpAds();
        joomApp.swipeElementsLeft();
        String actuatStatus = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Promotions\"]/android.view.ViewGroup/android.widget.TextView"))
                .getAttribute("enabled");
        Assert.assertTrue("сообщение об ошибке", actuatStatus.contains("true"));
    }

    /**
     * Кейс: Добавление товара в избранное
     * Шаги:
     * Открыть товар
     * Добавить товар в избранное
     * Перейти на вкладку профиля
     * Открыть список с избранными товарами
     * Обновить экран (потянуть вниз)
     * Ожидаемый результат: в списке избранного есть один товар.
     */

    @Test
    public void addFavoriteGoodsAndOpenFavoryteList() {

        JoomAppHomePage joomApp = new JoomAppHomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        // при запуске приложения может быть либо модалка или попАп
        // joomApp.closeModal();
        joomApp.closeStartPopUpAds();
        joomApp.openGoods();
        String actualNameGoods = joomApp.product_info_title.getText();
        productPage.addToFavoritesGoods()
                .pressBackButton()
                .openProfilePage()
                .openFavorites()
                .swipeVertical(444, 888);
        String expText = driver.findElement((By.xpath("//*[@class='android.widget.TextView'][contains(@content-desc,'Product')]"))).getAttribute("content-desc");
        String expectedStr = expText.substring(8, expText.indexOf(","));
        Assert.assertEquals(actualNameGoods, expectedStr);
    }
}