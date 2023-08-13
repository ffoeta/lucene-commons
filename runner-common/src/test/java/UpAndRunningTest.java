import com.example.runner.jetty.JettyServerBuilder;
import org.junit.jupiter.api.Test;
import settings.TestJettySettings;

public class UpAndRunningTest {

  @Test
  public void doTest() throws Exception {
    TestJettySettings testJettySettings = new TestJettySettings();
    var jettyServer = JettyServerBuilder
        .jettyServerBuilder(testJettySettings)
        .build();

    jettyServer.start();
    jettyServer.stop();
  }
}
