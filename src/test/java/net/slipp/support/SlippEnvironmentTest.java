package net.slipp.support;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SlippEnvironmentTest {

	@Test
	public void loadProperties() {
		SlippEnvironment environment = new SlippEnvironment();
		assertThat(environment.getProperty("environment"), is("DEVELOPMENT"));
	}

}
