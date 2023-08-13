package settings;

import com.example.runner.settings.JettySettings;

public class TestJettySettings extends JettySettings {
  @Override
  public int getMinThreadPollSize() {
    return 10;
  }

  @Override
  public void setMinThreadPollSize(int minThreadPollSize) {
  }

  @Override
  public int getMaxThreadPollSize() {
    return 100;
  }

  @Override
  public void setMaxThreadPollSize(int maxThreadPollSize) {
  }

  @Override
  public int getIdleTimout() {
    return 100;
  }

  @Override
  public void setIdleTimout(int idleTimout) {
  }

  @Override
  public int getPort() {
    return 9082;
  }

  @Override
  public void setPort(int port) {
  }
}
