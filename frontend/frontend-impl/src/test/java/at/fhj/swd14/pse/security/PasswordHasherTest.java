package at.fhj.swd14.pse.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author thomas.spitzer
 * 
 */
public class PasswordHasherTest {

	private static final int NUMBER_OF_GENERATED_RANDOM_PASSWORDS = 1000;
	private static final String REGEX = "[A-Za-z0-9!#$%&()?*+,-.\\/:;<=>_]*";

	private static final Logger LOG = LogManager.getLogger(PasswordHasherTest.class);

	private PasswordHasher passwordHasher = PasswordHasher.getInstance();

	@Test
	public void testHashing() {
		String rawPassword = "x";
		String salt = "CRzTaAbyUxxEUs56";
		String expectedHashedPassword = "edfd3e005a2da55ee99bb4b49afa33c6fa1bc945d93db54f5cba483355462acefdfd77ce1602449c9365d4eafb342e210ed0d33df4ceed40b92fa515bab5f89d";

		Assert.assertNotNull("Could not instantiate PasswordHasher", passwordHasher);
		String hashedPassword = passwordHasher.hash(rawPassword, salt);
		
		Assert.assertEquals("Generated hash is wrong", expectedHashedPassword, hashedPassword);
	}

	@Test
	public void testSalt() {
		String generatedSalt = passwordHasher.generateSalt();
		Assert.assertTrue("Salt is not alphanumeric", StringUtils.isAlphanumeric(generatedSalt));
		Assert.assertEquals("Length is not as expected", generatedSalt.length(), 16);
	}

	@Test
	public void testInitalPasswordGenerator() {
		// generate a lot of random passwords an check all of them
		for (int i = 0; i < NUMBER_OF_GENERATED_RANDOM_PASSWORDS; i++) {
			String generatedPassword = passwordHasher.generateRandomInitalPassword();
			Assert.assertEquals("Length is not as expected", generatedPassword.length(), 16);
			boolean matches = generatedPassword.matches(REGEX);
			Assert.assertTrue(matches);
			LOG.debug(
					"Password [" + (i + 1) + "] is [" + generatedPassword + "] and fulfills all password requirements");
		}
	}

}
