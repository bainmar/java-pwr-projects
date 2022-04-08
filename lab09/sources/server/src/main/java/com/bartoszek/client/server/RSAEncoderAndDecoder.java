package com.bartoszek.client.server;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSAEncoderAndDecoder {
	private BigInteger p;
	private BigInteger q;
	private BigInteger N;
	private BigInteger phi;
	private BigInteger e;
	private BigInteger d;
	private int numberOfBits = 128;
	private Random r;

	public RSAEncoderAndDecoder() {
		r = new Random();
		p = BigInteger.probablePrime(numberOfBits, r);
		q = BigInteger.probablePrime(numberOfBits, r);
		N = p.multiply(q);
		e = BigInteger.probablePrime(numberOfBits / 2, r);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
			e.add(BigInteger.ONE);
		}
		d = e.modInverse(phi);
	}

	public BigInteger getN() {
		return N;
	}

	public BigInteger getE() {
		return e;
	}

	public BigInteger getD() {
		return d;
	}

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		RSAEncoderAndDecoder rsa = new RSAEncoderAndDecoder();

		String eText;
		System.out.println("Wprowadz tekst:");
		eText = input.nextLine();
		byte[] encrypted = rsa.encrypt(eText.getBytes());
		byte[] decrypted = rsa.decrypt(encrypted);
		System.out.println("Rozkodawne bajty " + bytesToString(decrypted));
		System.out.println("Rozszyfrowany tekst: " + new String(decrypted));
	}

	public byte[] encrypt(byte[] messageBytes) {
		return (new BigInteger(messageBytes)).modPow(e, N).toByteArray();
	}

	public byte[] decrypt(byte[] messageBytes) {
		return (new BigInteger(messageBytes)).modPow(d, N).toByteArray();
	}

	private static String bytesToString(byte[] encrypted) {
		String result = "";
		for (byte b : encrypted) {
			result += Byte.toString(b)+" ";
		}
		return result;
	}
}