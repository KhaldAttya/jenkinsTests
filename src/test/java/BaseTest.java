
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

abstract class BaseTest {

    public static AppiumDriver<?> driver;
    public static String platform;
    public static String deviceName;
    public static String actualRepo;
    public static String expectedRepo;
    public static String resultRepo;

    private static void setRepoPaths() {
        actualRepo = System.getProperty("user.dir") + File.separator + "test-data" + File.separator + platform
                + File.separator + deviceName + File.separator + "actual" + File.separator;

        expectedRepo = System.getProperty("user.dir") + File.separator + "test-data" + File.separator + platform
                + File.separator + deviceName + File.separator + "expected" + File.separator;

        resultRepo = System.getProperty("user.dir") + File.separator + "test-data" + File.separator + platform
                + File.separator + deviceName + File.separator + "result" + File.separator;
    }

    public static void init(String platform, String deviceName) {
        BaseTest.platform = platform;
        BaseTest.deviceName = deviceName;
        ObjectRepo.objectRepo(platform);
        setRepoPaths();
    }

    @BeforeSuite
    @Parameters({"platform", "DEVICE_NAME"})
    static void setUpBeforeClass(String platform, String DEVICE_NAME) throws Exception {
        init(platform, DEVICE_NAME);
    }

    @AfterClass
    static void tearDownAfterClass() throws Exception {
    }


    public void reportResult(String result) throws IOException {
        Path content = Paths.get(result);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(result, is);
        }
    }

    public WebElement element(By element) {
        return driver.findElement(element);

    }

    public RemoteWebElement remoteElement(By element) {
        return (RemoteWebElement) driver.findElement(element);
    }

    @BeforeTest
    @Parameters({"APPIUM_VERSION", "PLATFORM_VERSION", "PLATFORM_NAME", "AUTOMATION_NAME", "DEVICE_NAME", "udid", "appActivity", "appPackage", "bundleId", "host"})
    void setUp(String APPIUM_VERSION,
               String PLATFORM_VERSION,
               String PLATFORM_NAME,
               String AUTOMATION_NAME,
               String DEVICE_NAME,
               String udid,
               String appActivity,
               String appPackage,
               String bundleId,
               String host) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, APPIUM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("bundleId", bundleId);
        capabilities.setCapability("autoDismissAlerts", "");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        driver = new AppiumDriver<>(new URL(host), capabilities);


    }

    @AfterTest
    void tearDown() throws Exception {
        driver.quit();

    }

}
