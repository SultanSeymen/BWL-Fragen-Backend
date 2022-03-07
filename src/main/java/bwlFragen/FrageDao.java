package bwlFragen;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FrageDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdcbTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> getAntwort(String[] stichpunkte) {
		String query = "SELECT bwlFragen.frage, bwlFragen.antwort FROM bwlFragen WHERE ";
		String gesamteAbfrage = "frage like ";
		for (int i = 0; i < stichpunkte.length; i++) {
			if (stichpunkte[i] != null && stichpunkte[i + 1] != null)
				gesamteAbfrage += stichpunkte[i] + " and frage like ";
			else if (stichpunkte[i] != null)
				gesamteAbfrage +=stichpunkte[i] + ";";
		}
		gesamteAbfrage = query + gesamteAbfrage;
		System.out.println(gesamteAbfrage);
		List<Map<String, Object>> ergebnis = jdbcTemplate.queryForList(gesamteAbfrage);
		return ergebnis;
	}
}
