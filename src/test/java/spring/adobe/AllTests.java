package spring.adobe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

@RunWith(Suite.class)
@SuiteClasses({TestEncrypt.class})
public class AllTests {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestEncrypt.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
      System.out.println(result.toString());
      if(result.wasSuccessful() ) {
    	  System.out.println("Test OK");
      }
   }
}