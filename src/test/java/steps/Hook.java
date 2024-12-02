package steps;

import Base.BaseUtil;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;

public class Hook extends BaseUtil {

    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        // Start a new scenario log
        base.scenarioDef = base.features.createNode(scenario.getName());

        // Set up GeckoDriver using WebDriverManager
        WebDriverManager.firefoxdriver().setup();

        // Configure Firefox options (headless mode can be used as per the environment requirement)
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        

        // Initialize FirefoxDriver with options
        base.Driver = new FirefoxDriver(firefoxOptions);
    }

    @After
    public void TearDownTest(Scenario scenario) {
        // Take screenshot or perform any custom logging on failure (optional)
        if (scenario.isFailed()) {
            System.out.println("Test failed: " + scenario.getName());
            // Add screenshot capture logic here (if needed)
        }

        // Log and close the browser
        System.out.println("Closing the browser.");
        if (base.Driver != null) {
            base.Driver.quit();
        }
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before executing step: " + scenario.getId());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        // Optional: Add post-step logging if required
        System.out.println("After executing step: " + scenario.getId());
    }
}
