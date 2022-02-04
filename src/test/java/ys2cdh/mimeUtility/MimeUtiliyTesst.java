package ys2cdh.mimeUtility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MimeUtiliyTesst {
	 @Test 
	 public void testSum(){
		 String result = MimeUtility.decode("=?ks_c_5601-1987?B?W7S6vbq3ucXNXTIwMjKz4iC9xbPixq/B/V+068fRuc6xuSDAx7fhLCDDyrDtt8m758g=?=\r\n\t=?ks_c_5601-1987?B?uCDB2LrxIL3Dsd4=?=");                                 
		 assertEquals("[뉴스레터]2022년 신년특집_대한민국 의료, 초고령사회 준비 시급",result);                                     
	    }

	 @Test 
	 public void test2(){
		 String result = MimeUtility.decode("=?ks_c_5601-1987?B?W09DTiBNb3ZpZXNdIDIys+IgMDK/+SAywdYgsKG+yCDG7by6x6Uu?=  =?ks_c_5601-1987?Q?xlsx?=");                                 
		 assertEquals("[OCN Movies] 22년 02월 2주 가안 편성표.xlsx",result);                                     
	    }
	 
	 @Test 
	 public void test3(){
		 String result = MimeUtility.decode("=?ks_c_5601-1987?Q?=BC=F6=C0=D4 =B9=B0=C7=B0 =B0=FC=BC=BC =B3=BB=BF=AA =B9=D7 \r\n"
					+ " =B0=FC=B7=C3 =BC=AD=B7=F9 (=C6=BC=BF=A3=C6=BC =C4=DA=B8=AE=BE=C6) - B/L No. \r\n"
					+ " 713079900?=");    
		 System.out.println(result);
		 assertEquals("수입 물품 관세 내역 및 관련 서류 (티엔티 코리아) - B/L No. 713079900",result);                                     
	    }

	 
	 
}
