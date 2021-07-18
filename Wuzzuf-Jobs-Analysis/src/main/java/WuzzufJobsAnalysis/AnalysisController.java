package WuzzufJobsAnalysis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.*;

@RestController
public class AnalysisController {

    private JobAnalysis analysis;

    @Autowired
    public AnalysisController(JobAnalysis analysis){
        this.analysis = analysis;
    }

    @RequestMapping("/popularCompanies")
    public String popularCompanies() throws FileNotFoundException {
        return analysis.filterJobsByComp();
    }

    @RequestMapping("/popularTitles")
    public String popularTitles() throws FileNotFoundException {
        return analysis.filterJobsByTitle();
    }

    @RequestMapping("/popularArea")
    public String popularArea() throws FileNotFoundException {
        return analysis.filterJobsByArea();
    }

    @RequestMapping("/popularSkills")
    public String popularSkills() throws FileNotFoundException {
        return analysis.filterJobsBySkills();
    }

    // Display Charts
    @RequestMapping("/dashBoard")
    public String sayBye() throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/images.html"));
        String line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }

    }

}
