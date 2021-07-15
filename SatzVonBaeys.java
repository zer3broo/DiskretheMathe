import java.util.Scanner;

public class SatzVonBaeys {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Anzahl unterschielicher Ereignisse eingeben: ");
		int anzahl=scanner.nextInt();
		double [][] vierFelderTafel  = new double [anzahl+1][2];
		
		
		double [] ersteEreignisseP = new double [anzahl];
		double [][] zweiteEreignisseP = new double [anzahl][2];
		
		//Eingabe Wahrscheinlichkeiten erstes Ereigniss
		for (int i = 0; i < anzahl; i++) {
			System.out.println("Wahrscheinlichkeit Nr."+(i+1)+" eingeben");
			ersteEreignisseP[i]=scanner.nextDouble();
		}
		//Eingabe  Wahscheinlichkeiten zweites Ergeigniss
		for (int i = 0; i < anzahl; i++) {
			System.out.println("Wahrscheinlichkeit Nr."+(i+1)+" 'positiv' eingeben");
			zweiteEreignisseP[i][0]=scanner.nextDouble();
			zweiteEreignisseP[i][1]=1-zweiteEreignisseP[i][0];
		
		}
		
		//Aufstellen vier Felder-Tafel 
		for (int i = 0; i < anzahl; i++) {
			vierFelderTafel[i][0]=zweiteEreignisseP[i][0]*ersteEreignisseP[i];
			vierFelderTafel[i][1]=zweiteEreignisseP[i][1]*ersteEreignisseP[i];
			vierFelderTafel[anzahl][0]+=vierFelderTafel[i][0];
			vierFelderTafel[anzahl][1]+=vierFelderTafel[i][1];
		}
		System.out.println("     A                    nA     ");
		String ausgabe="";
		for (int i = 0; i < vierFelderTafel.length; i++) {
			ausgabe+=vierFelderTafel[i][0]+"           "+vierFelderTafel[i][1]+"\n";
		}
		System.out.println(ausgabe);
		
		//Endergebnisse 
		double [] ergebnisse = new double[4];
		for (int i = 0; i < anzahl; i++) {
			ergebnisse[i]=vierFelderTafel[i][0]/vierFelderTafel[anzahl][0];
		}
		//Print ergebnisse 
		for (int i = 0; i < anzahl; i++) {
			System.out.println("P(p2|pE"+(i+1)+")="+vierFelderTafel[i][0]+"/"+vierFelderTafel[anzahl][0]+
					"="+ergebnisse[i]);
		}		
	}
}
