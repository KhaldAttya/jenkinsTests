import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AppTest extends BaseTest
{

@Test
void test() throws InterruptedException, IOException {
Thread.sleep(4000);
Aeye.takeAppScreenshot(driver,ObjectRepo.statusBar,actualRepo+"content.png");

if (!Aeye.compareImages(actualRepo+"content.png",expectedRepo+"content.png",resultRepo+"content.png")){
   reportResult(resultRepo+"content.png");
    Assert.fail("UI issue is found");

}

}
}
