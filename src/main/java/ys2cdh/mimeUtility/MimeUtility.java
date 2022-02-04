package ys2cdh.mimeUtility;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.QuotedPrintableCodec;

public class MimeUtility {

	static Pattern pattern1 = Pattern.compile("\\=\\?([\\p{Graph}]*)\\?([A-Za-z])\\?([0-9A-Za-z/\\+\\=\\-\\.()\\s]*)\\?\\=");
	static QuotedPrintableCodec qp1 = new QuotedPrintableCodec();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String decode(String strEncode) {

		// \r\n\t으로 구분
//		String[] aryString = strEncode.split("\r\n\t");
//		String[] aryString = strEncode.split("\\?=");

		// charset 값과 decode 한 byte 반환

		// 바로 전 charSet
		String strRecvCharSet = "";
		// 바로 전 encode Type
		String strRecvEncodeType = "";
		StringBuffer strTotalValue = new StringBuffer();
		byte[] decodedBytes = null;
		byte[] decodedTotalBytes = null;
//		for (String strTemp : aryString) {
//			strTemp = strTemp.trim() +"?=";
		Matcher matcher = pattern1.matcher(strEncode);
		while (matcher.find()) {
			// charset
			String strCharSet = matcher.group(1);
			// encode Type
			// b , B : base64
			String strEncodeType = matcher.group(2);
			// value
			String strValue = matcher.group(3);

			strValue=strValue.replaceAll("\r\n\s", "");
			strValue=strValue.replaceAll("\r\n", "");
			decodedBytes = decodeStringToByte(strEncodeType, strValue);

			if (0 < strRecvCharSet.length() && 0 < strRecvEncodeType.length()) {
				// charset 값 또는 encode 방식이 이전꺼랑 다르면 한 번은 값을 반환 하여야 한다.
				if (!strRecvCharSet.equals(strCharSet) || !strRecvEncodeType.equals(strEncodeType)) {
					try {
						strTotalValue.append(new String(decodedTotalBytes, strRecvCharSet));
					} catch (UnsupportedEncodingException e) {
						System.out.println("String charset Error : " + strRecvCharSet);
						e.printStackTrace();
					}

					decodedTotalBytes = null;
					strRecvCharSet = "";
					strRecvEncodeType = "";
				}
			}

			if (null != decodedTotalBytes) {
				decodedTotalBytes = addByteArray(decodedBytes, decodedTotalBytes);
			} else {
				decodedTotalBytes = decodedBytes;
			}

			strRecvCharSet = strCharSet;
			strRecvEncodeType = strEncodeType;

		}

		try {
			strTotalValue.append(new String(decodedTotalBytes, strRecvCharSet));
		} catch (UnsupportedEncodingException e) {
			System.out.println("String charset Error : " + strRecvCharSet);
			e.printStackTrace();
		}

		return strTotalValue.toString();
	}

	private static byte[] addByteArray(byte[] decodedBytes, byte[] decodedTotalBytes) {
		byte[] decodedBytes1 = decodedTotalBytes.clone();
		decodedTotalBytes = new byte[decodedBytes1.length + decodedBytes.length];
		System.arraycopy(decodedBytes1, 0, decodedTotalBytes, 0, decodedBytes1.length);
		System.arraycopy(decodedBytes, 0, decodedTotalBytes, decodedBytes1.length, decodedBytes.length);
		return decodedTotalBytes;
	}

	private static byte[] decodeStringToByte(String strEncodeType, String strValue) {
		byte[] decodedBytes = null;
		switch (strEncodeType.toUpperCase()) {
		// base64
		case "B":
			decodedBytes = Base64.getDecoder().decode(strValue);
			break;
		case "Q":
			try {
				decodedBytes = QuotedPrintableCodec.decodeQuotedPrintable(strValue.getBytes());
			} catch (DecoderException e) {
				System.out.println("QuotedPrintableCodec decode error : " + strValue);
				e.printStackTrace();
			}
			break;
		default:
		}
		return decodedBytes;
	}
}
