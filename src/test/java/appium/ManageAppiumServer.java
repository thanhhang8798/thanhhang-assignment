package appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class ManageAppiumServer {
    public static AppiumServiceBuilder appiumServiceBuilder;
    public static AppiumDriverLocalService appiumDriverLocalService;

    public static void startAppiumServer() {
        killAppiumSession();

        appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1");
        appiumServiceBuilder.usingPort(4723);

        appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");


        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumDriverLocalService.start();
    }

    public static void stopAppiumServer() {
        if(appiumDriverLocalService.isRunning() && appiumDriverLocalService != null) {
            appiumDriverLocalService.stop();
        }
    }

    // kill session: khi có nhiều session thì chạy câu lenh này để đóng session cũ đi trước khi chạy session mới
    private static void killAppiumSession() {
        String[] command = {"/usr/bin/killall", "-KILL", "node"};
        try{
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
