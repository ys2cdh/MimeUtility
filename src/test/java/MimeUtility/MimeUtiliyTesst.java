package MimeUtility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MimeUtiliyTesst {
	 @Test 
	 public void testSum(){
		 String result = MimeUtility.decode("=?ks_c_5601-1987?B?W7S6vbq3ucXNXTIwMjKz4iC9xbPixq/B/V+068fRuc6xuSDAx7fhLCDDyrDtt8m758g=?=\r\n\t=?ks_c_5601-1987?B?uCDB2LrxIL3Dsd4=?=");                                 
		 assertEquals("[뉴스레터]2022년 신년특집_대한민국 의료, 초고령사회 준비 시급",result);                                     
	    }

	
}
