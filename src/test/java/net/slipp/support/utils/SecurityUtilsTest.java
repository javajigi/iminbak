package net.slipp.support.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtilsTest {
    private static Logger log = LoggerFactory.getLogger(SecurityUtilsTest.class);

    @Test
    public void createToken() {
        String token = SecurityUtils.createToken();
        log.debug("token : {}", token);
        assertTrue(token.contains(":"));
    }

    @Test
    public void validUuid() throws Exception {
        String sessionToken = "bf947efc-deaa-4272-be52-8d2334e365aa:1402967598743";
        String clientToken = "bf947efc-deaa-4272-be52-8d2334e365aa:1402967598743";
        boolean actual = SecurityUtils.validUuid(sessionToken, clientToken);
        assertTrue(actual);
    }
    
    @Test
    public void intervalSecond() throws Exception {
        String clientToken = "bf947efc-deaa-4272-be52-8d2334e365aa:1402967598743";
        long second = SecurityUtils.intervalSecond(clientToken);
        log.debug("second : {}", second);
    }
}
