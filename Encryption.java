// Luke Donovan, 2017
// Micro-implementation of the Diffie–Hellman key exchange

import java.util.Random;
import java.math.BigInteger;

public class Encryption {
	public static final BigInteger modulus = new BigInteger("10").pow(600).add(new BigInteger("543"));
	private BigInteger privateKey, publicKey, sharedKey;

	public Encryption() {
		this.privateKey = new BigInteger(32, new Random());
		this.publicKey  = new BigInteger("3").modPow(this.privateKey, modulus);
	}

	public void connect(BigInteger publicKey) {
		this.sharedKey = publicKey.modPow(this.privateKey, modulus);
	}

	public BigInteger getPrivateKey() { return this.privateKey; } // Development only
	public BigInteger getPublicKey()  { return this.publicKey;  }
	public BigInteger getSharedKey()  { return this.sharedKey;  } // Development only

	public static void main(String args[]) {
		Encryption alice = new Encryption(),
		           bobby = new Encryption();

		alice.connect(bobby.getPublicKey());
		bobby.connect(alice.getPublicKey());

		System.out.println(alice.getSharedKey());
		System.out.println(bobby.getSharedKey());
	}
}
