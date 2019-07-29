

import org.openqa.selenium.By;

public class ObjectRepo {

    // Elements
    public static By statusBar;



    public static void objectRepo(String platform) {

        switch (platform) {
            case "native-android": {
                nativeAndroid();
            }
            break;
            case "native-ios": {
                nativeIOS();
            }
            break;
            case "react-android": {
                reactAndroid();
            }
            break;
            case "react-ios": {
                reactIOS();
            }
            break;
            default:
                System.out.println("Choose platform as per naming convention. ie native-android");

                break;
        }

    }

    private static void reactIOS() {



    }

    private static void reactAndroid() {


    }

    private static void nativeIOS() {



    }

    private static void nativeAndroid() {

        statusBar = By.id("android:id/statusBarBackground");

    }
}
