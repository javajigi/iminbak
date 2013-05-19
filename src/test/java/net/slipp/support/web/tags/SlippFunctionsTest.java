package net.slipp.support.web.tags;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlippFunctionsTest {
	private static final Logger logger = LoggerFactory.getLogger(SlippFunctionsTest.class);

	@Test
	public void wiki() throws Exception {
		String source = "{code:title=java}\n WikiContents wikiContents = new WikiContents();{code}\n" 
				+ "!1234!\n !2345!";
		String actual = SlippFunctions.wiki(source);
		logger.debug("convert wiki contents : {}", actual);
	}
	
	@Test
	public void stripHttp() throws Exception {
		String url = "http://localhost:8080";
		String actual = SlippFunctions.stripHttp(url);
		assertThat(actual, is("//localhost:8080"));
	}
}
