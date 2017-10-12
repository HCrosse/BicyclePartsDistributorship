package Users;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class LoginAccount extends Person {

  private String username;
  private byte[] salt;
  private byte[] password;

  private final int ITERATIONS = 16384;
  private final int KEYLENGTH = 512;

  public LoginAccount() {
    super();
    username = null;
    password = null;
  }

  public LoginAccount(String[] strings, String un, String pw) {
    super(strings);
    username = un.toLowerCase();
    salt = getSalt();
    password = hashPassword(pw.toCharArray(), salt, ITERATIONS, KEYLENGTH);
  }

  private byte[] getSalt() {
    SecureRandom r = new SecureRandom();
    byte[] salt = new byte[64];
    r.nextBytes(salt);
    return salt;
  }

  private byte[] hashPassword(final char[] password, final byte[] salt, final int iterations,
      final int keyLength) {
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
      PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
      SecretKey key = skf.generateSecret( spec );
      return key.getEncoded( );
    } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
      throw new RuntimeException( e );
    }
  }

  boolean successfulLogin(String un, String pw) {
    if (!un.toLowerCase().equals(username)) {
      return false;
    }
    byte[] hashedPW = hashPassword(pw.toCharArray(), salt, ITERATIONS, KEYLENGTH);
    return Arrays.equals(hashedPW, password);
  }

  String getUsername() {
    return username;
  }

}
