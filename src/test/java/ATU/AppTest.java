package ATU;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Timeout(5)
    @Test
    void main() {
        App app = new App();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    assertTrue(Stage.getWindows().get(0).isShowing());
                    Platform.exit();
                }
                catch (IllegalArgumentException | InterruptedException e) {

                }
            }
        }).start();
        App.main(new String[]{""});
    }
}