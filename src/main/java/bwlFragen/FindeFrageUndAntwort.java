package bwlFragen;

import org.springframework.stereotype.Component;

@Component
public class FindeFrageUndAntwort {

	public String[] filtereAlleStichpunkte(String stichpunkte) {
		String einzelnerStichpunkt = "";
		String[] stichpunkteArray = new String[5];
		int j = 0;
		for (int charPosition = 0; charPosition < stichpunkte.length(); charPosition++) {
			if (stichpunkte.charAt(charPosition) != ',' && stichpunkte.charAt(charPosition) != ' ') {
				einzelnerStichpunkt = einzelnerStichpunkt + stichpunkte.charAt(charPosition);
			}

			if (stichpunkte.charAt(charPosition) == ',' || charPosition == stichpunkte.length() - 1) {
				stichpunkteArray[j] = einzelnerStichpunkt;
				einzelnerStichpunkt = "";
				j++;
			}

			if (j == 5) {
				break;
			}
		}
		return stichpunkteArray;
	}

	public String[] verschatelStichpunkteInSQLAbfrage(String[] stichpunkte) {
		for (int i = 0; i < stichpunkte.length; i++) {
			if (stichpunkte[i] != null)
				stichpunkte[i] = "'%" + stichpunkte[i] + "%'";
		}
		return stichpunkte;
	}
	
	public String[] getAlleStichpunkte(String stichpunkte) {
		return verschatelStichpunkteInSQLAbfrage(filtereAlleStichpunkte(stichpunkte));
	}
}