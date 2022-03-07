package bwlFragen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class bwlFragenController implements CommandLineRunner{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	FrageDao frageDao;
	
	@Autowired
	private FindeFrageUndAntwort findeFrageUndAntwort;
	
	@GetMapping(path = "/getBWLFragen")
	public ResponseEntity<String> getAntwort(@RequestParam String stichpunkte){
		String[] alleStichpunkteGefiltert = findeFrageUndAntwort.getAlleStichpunkte(stichpunkte);
		return ResponseEntity.ok(frageDao.getAntwort(alleStichpunkteGefiltert).toString());
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Creating Table:");
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS bwlFragen( id int primary key auto_increment, frage varchar(255), antwort varchar(3), anmerkung varchar (355) );");
		System.out.println("Table created.");
	}
}
