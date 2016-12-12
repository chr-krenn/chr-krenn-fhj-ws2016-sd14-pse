package at.fhj.swd14.pse.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * PasswordHasher uses SHA512 algorithm and makes 64007 iterations.
 *
 * @author thomas.spitzer
 *
 */
public final class PasswordHasher {

	private static final String SPECIAL_CHARACTERS_ALLOWED_IN_PASSWORD = "!#$%&()?*+,-./:;<=>_";

	private static final String CHARACTERS_ALLOWED_IN_PASSWORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
			+ SPECIAL_CHARACTERS_ALLOWED_IN_PASSWORD;

	private static final int PASSWORD_LENGTH = 16;
	private static final int MIN_RANDOM_NUMBER_LENGTH = 2;
	private static final int MAX_RANDOM_NUMBER_LENGTH = 9;
	private static final int MAX_LOOPS = 50;

	/** The logger for this class. */
	private static final Logger LOG = LogManager.getLogger(PasswordHasher.class);

	private static PasswordHasher instance;

	private PasswordHasher() {
		LOG.debug("PasswordHasher instantiated");
	}

	public static synchronized PasswordHasher getInstance() {
		if (instance == null) {
			instance = new PasswordHasher();
		}
		return instance;
	}

	/**
    * Encodes the rawPass using a MessageDigest. If a salt is specified it will be merged with the password before
    * encoding.
    *
    * @param rawPass plain text format
    * @param salt the salt
    * @return base64 encoded string
    */
   
   public String hash(String rawPass, String salt){
	   String generatedPassword = null;
	       try {
	            MessageDigest md = MessageDigest.getInstance(getStrength());
	            md.update(salt.getBytes("UTF-8"));
	            byte[] bytes = md.digest(rawPass.getBytes("UTF-8"));
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++){
	               sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	           } 
	          catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
	           LOG.error("Could not enocde password", e);
	          }
	       return generatedPassword;
	   }

	private String getStrength() {
		return "SHA-512";
	}

	/**
	 * Generates a alphanumeric String with a length of PASSWORD_LENGTH chars.
	 *
	 * @return the random salt string
	 */
	public String generateSalt() {
		return RandomStringUtils.random(PASSWORD_LENGTH, 0, 0, true, true, null, new SecureRandom());
	}

	private String generateRandomUpperCaseCharacter() {
		return RandomStringUtils.random(1, 0, 0, true, false, null, new SecureRandom()).toUpperCase();
	}

	private String generateRandomLowerCaseCharacter() {
		return RandomStringUtils.random(1, 0, 0, true, false, null, new SecureRandom()).toLowerCase();
	}

	private String generateRandomNumber() {
		return RandomStringUtils.random(1, 0, 0, false, true, null, new SecureRandom());
	}

	private String generateRandomSpecialCharacter() {
		return RandomStringUtils.random(1, 0, SPECIAL_CHARACTERS_ALLOWED_IN_PASSWORD.length(), false, false,
				SPECIAL_CHARACTERS_ALLOWED_IN_PASSWORD.toCharArray(), new SecureRandom());
	}

	private String generateInternalPassword() {
		return RandomStringUtils.random(PASSWORD_LENGTH, 0, CHARACTERS_ALLOWED_IN_PASSWORD.length(), false, false,
				CHARACTERS_ALLOWED_IN_PASSWORD.toCharArray(), new SecureRandom());
	}

	private int getPosition(int randomCount, HashMap<Integer, Integer> excludePositions) {
		int cnt = randomCount;
		int position = -1;
		int loopCounter = 0;
		boolean found = false;
		while (!found) {
			// try to find a new random position
			String randomPosition = RandomStringUtils.randomNumeric(cnt);
			String posString = randomPosition.replaceFirst("^0+(?!$)", "");
			// MODULO operation
			position = Integer.parseInt(posString) % PASSWORD_LENGTH;
			// check if this position is already used
			if (/** NOT */
			!excludePositions.containsKey(position)) {
				found = true;
			} else {
				cnt++;
				if (cnt > MAX_RANDOM_NUMBER_LENGTH) {
					cnt = MIN_RANDOM_NUMBER_LENGTH;
				}
				loopCounter++;
				if (loopCounter > MAX_LOOPS) {
					LOG.warn("Could not find a unique position within [" + MAX_LOOPS + "] loops.");
					position = 0;
					found = true;
				}
			}
		}
		excludePositions.put(position, 0);
		return position;
	}

	/**
	 * Creates a new random password fulfilling all requirements of UNYCOM
	 * passwords.
	 *
	 * The new password must meet the following requirements: * Contains at
	 * least 8 characters * Contains at least one upper-case letter * Contains
	 * at least one lower-case letter * Contains at least one number * Contains
	 * at least one special character * Permitted upper-case letters:
	 * ABCDEFGHIJKLMNOPQRSTUVWXYZ * Permitted lower-case letters:
	 * abcdefghijklmnopqrstuvwxyz * Permitted numbers: 0123456789 * Permitted
	 * special characters: !?#$%&()*+,-./:;<=>_
	 *
	 * @return a new random password.
	 */
	public String generateRandomInitalPassword() {
		HashMap<Integer, Integer> excludePositions = new HashMap<Integer, Integer>();
		String pwd = generateInternalPassword();
		int positionOfUpperCaseCharacter = getPosition(MIN_RANDOM_NUMBER_LENGTH, excludePositions);
		int positionOfLowerCaseCharacter = getPosition(MIN_RANDOM_NUMBER_LENGTH + 1, excludePositions);
		int positionOfRandomNumber = getPosition(MIN_RANDOM_NUMBER_LENGTH + 2, excludePositions);
		int positionOfSpecialCharacter = getPosition(MIN_RANDOM_NUMBER_LENGTH + 3, excludePositions);
		// now we've found 4 different positions to insert a random 1-digit
		// upper case character, a random 1-digit
		// lower case character, a random 1-digit number and random character
		// into a random alphanumeric string
		StringBuffer s = new StringBuffer(pwd);
		// LOG.debug("Before: " + s);
		s.replace(positionOfUpperCaseCharacter, positionOfUpperCaseCharacter + 1, generateRandomUpperCaseCharacter());
		s.replace(positionOfLowerCaseCharacter, positionOfLowerCaseCharacter + 1, generateRandomLowerCaseCharacter());
		s.replace(positionOfRandomNumber, positionOfRandomNumber + 1, generateRandomNumber());
		s.replace(positionOfSpecialCharacter, positionOfSpecialCharacter + 1, generateRandomSpecialCharacter());
		// LOG.debug("After: " + s);
		return s.toString();
	}

}
