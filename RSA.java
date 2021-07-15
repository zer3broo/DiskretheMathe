package RSA;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

public class RSA {
	
	static ArrayList<String[]>  inputNumbers = new ArrayList<>();
	static ArrayList<Long> encryptetet = new ArrayList<>();
	
	public static void main(String[] args) {
		
	
	//	verschlüsseln();
	
		
		
// Auskommentierter Teil beinhaltetet code zum eingeben, von Chiffrat als Zahlen in die Liste 
//		"Unkown Chiffrat". Benötigten key auskommentieren und mit passenden Werten füllen.  
//		
//		ArrayList<Long> unknownChiffrat = new ArrayList<Long>();
//		ArrayList<Long> message = new ArrayList<>();
//		unknownChiffrat.add((long) 31);
//		unknownChiffrat.add((long) 33);
//		unknownChiffrat.add((long) 01);
//		unknownChiffrat.add((long) 72);
//		unknownChiffrat.add((long) 76);
//		unknownChiffrat.add((long) 1737);
//		unknownChiffrat.add((long) 2158);
		
// 		Beide Methoden brauchen unkownChiffrat 	
		
//		key priavateKey = new key(10,3);
//		key publicKey = new key(10,3);
//		
//		message=decrypteWithPublicKey(publicKey, unknownChiffrat);
//		
//		
//		message=decrypteWithPrivateKey(priavateKey, unknownChiffrat);
//		
//		System.out.println("massage decrypteet:" + message);
		

		
		
	}
	

	private static ArrayList<Long> decrypteWithPublicKey(key publicKey, ArrayList<Long> unknownChiffrat) {
		// TODO Auto-generated method stub
		return decrypte(getPrivateKey(publicKey), unknownChiffrat);
	}


	private static ArrayList<Long> decrypteWithPrivateKey(key priavateKey, ArrayList<Long> unknownChiffrat) {
		// TODO Auto-generated method stub
		return decrypte(priavateKey, unknownChiffrat);
	}


	private static void verschlüsseln() {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Blockgröße der Buchstaben angeben");
		int blockGroese= scanner.nextInt();
		String abbruch="";
		do {
			inputNumbers.add(new String [blockGroese]);
			for (int i = 0; i < blockGroese; i++) {
				System.out.println("Bitte Buchstabe als Nummer eingeben");
				inputNumbers.get(inputNumbers.size()-1)[i]= scanner.next();
			}
			System.out.println("weitere Eingabe? wenn nein 'ende' eingeben");
			abbruch=scanner.next();
		} while(!abbruch.contains("ende"));
		key publicKey;
		System.out.println("Parameter für den Public key eingeben:");
		System.out.println("N und e gegeben ? ja eingeben");
		if(scanner.next().contains("ja") ) {
			System.out.println("e eingeben");
			int e = scanner.nextInt();
			System.out.println("N eingeben");
			int N = scanner.nextInt();
		    publicKey = new key(e,N);
			encryptetet = encrypte(publicKey);
		} else {
			System.out.println("q: ");
			int q = scanner.nextInt();
			System.out.println("p: ");
			int p = scanner.nextInt();
			System.out.println("e: ");
			int e = scanner.nextInt();
		    publicKey = new key(e,(p*q));
			encryptetet = encrypte(publicKey);
		}
		
		System.out.println("encryptet:"+encryptetet);
		System.out.println(getPrivateKey(publicKey));
		System.out.println("decryptet:"+decrypte(getPrivateKey(publicKey), encryptetet));
	}


	private static key getPrivateKey(key publicKey) {

	       // System.out.printf("\nPublic Key = (%d, %d)\n", e, rsaModul);

	        int q = 0;
	        int p = 0;
	        int d = 0;

	        for (int i = 0; i < 1000; i++) {
	            for (int j = 0; j < 1000; j++) {
	                if(i*j == publicKey.N && i!=1 && j!=0){
	                    q = i;
	                    p = j;
	                    break;
	                }
	            }
	        }

	        //int phi = (q - 1) * (p - 1);
	        for (int i = 0; i < 100000; i++) {
	            if(((i*publicKey.e)%phi(publicKey.N)) == 1){
	                d = i;
	                break;
	            }
	        }
	        System.out.println(d);
	    return new key(d, publicKey.N);
		
	}

	private static int phi(int n) 
	{ 
		int result = n; // Initialize result as n 

		// Consider all prime factors of n and subtract their 
		// multiples from result 
		for (int p = 2; p * p <= n; ++p) { 
			
			// Check if p is a prime factor. 
			if (n % p == 0) { 
				
				// If yes, then update n and result 
				while (n % p == 0) 
					n /= p; 
				result -= result / p; 
			} 
		} 

		// If n has a prime factor greater than sqrt(n) 
		// (There can be at-most one such prime factor) 
		if (n > 1) 
			result -= result / n; 
		return result; 
	} 

	private static  ArrayList<Long> encrypte(key encrypteKey) {
		ArrayList<Long> value = new ArrayList<>();
		ArrayList<Long> chiffrat = new ArrayList<>();
		
		for(String [] x : inputNumbers) {
			String temp="";
			for (int i = 0; i < x.length; i++) {
				temp+= x[i];
			}
			value.add(Long.valueOf(temp));
		}
		for(Long x: value) {
			chiffrat.add(powMod(x,  encrypteKey.e,(long) encrypteKey.N));
		}
		
		return chiffrat;
		
	}

	private static ArrayList<Long> decrypte(key decrypteKey, ArrayList<Long> chiffrat) {
		ArrayList<Long> klarText = new ArrayList<>();
		for(Long x : chiffrat) {
			klarText.add(powMod(x, decrypteKey.e, decrypteKey.N));
		}
		return klarText;
	}
	
	public static long powMod(long base, int exp , long modulus) {
		base %= modulus;
		  long result = 1;
		  while (exp > 0) {
		    if ((exp & 1) == 1 ) {
		    	result = (result * base) % modulus;
		    }
		    base = (base * base) % modulus;
		    exp /= 2;
		  }
		  return result;
	}
}

class key { 
	int e;
	int N;
	
	public key(int e, int N) {
		// TODO Auto-generated constructor stub
		this.e=e;
		this.N=N;
	}
	public int getE() {
		return e;
	}
	public int getN() {
		return N;
	}
	public void setE(int e) {
		this.e = e;
	}
	public void setN(int n) {
		N = n;
	}

	@Override
	public String toString() {
		
		return "(" +String.valueOf(this.e) + "," +String.valueOf(this.N)+")";
	}
}
