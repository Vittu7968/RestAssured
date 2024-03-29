package com.rest.Auth;

import java.util.Base64;

public class Base64Encoding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String username_password="MyUsername:MyPassword";
		
		String base64Encoded=Base64.getEncoder().encodeToString(username_password.getBytes());
		System.out.println("Base64Encode string is :"+base64Encoded);
		byte[] decodedBytes=Base64.getDecoder().decode(base64Encoded);
		System.out.println("Decoded string is :"+ new String(decodedBytes));
		
		/*
		 * 
		 * In the provided code, there's no explicit use of .toString() method. However, I understand your concern. Let's clarify:

The line new String(decodedBytes) does effectively create a new String object from the decoded byte array decodedBytes. This constructor of String is used to construct a new String by decoding the specified array of bytes using the platform's default charset.

The decodedBytes variable holds the byte array resulting from decoding the Base64-encoded string. When you create a new String from this byte array, Java uses the platform's default charset to interpret the bytes as characters and construct the string.

The reason why we don't use .toString() here is because decodedBytes is already a byte array, not an object that needs to be converted to a string representation. Instead, we use the String constructor that accepts a byte array to create a new string directly from the decoded bytes.

If you were to use .toString() here, it would actually convert the byte[] array object itself to a string representation (which is not what you want), rather than creating a new String from the bytes contained within the array. Therefore, using the String constructor with the byte[] array directly achieves the desired result of constructing a string from the decoded bytes.






		 */

	}

}
