package spring.adobe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Base64;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEncrypt {

/*	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
*/
	@Test
	public void test() {
		SlapCrypt crypto = new SlapCrypt();
		String plaintest = "Test string to be AES encrypted";
		byte[] encbyte;
		String rawData = "";
		try {
			encbyte = crypto.ecrypt(plaintest, "Vta49KVHOi5x^RNf", "Vta49KVHOi5x^RNf");
			byte[] encoded = Base64.getEncoder().encode(encbyte);
			String enc64 = new String(encoded);
		    byte[] cipher = Base64.getDecoder().decode(new String(enc64).getBytes("UTF-8"));
			rawData = crypto.decrypt(cipher, "Vta49KVHOi5x^RNf", "Vta49KVHOi5x^RNf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(plaintest,rawData);
//		fail("Not yet implemented");
	}

}
