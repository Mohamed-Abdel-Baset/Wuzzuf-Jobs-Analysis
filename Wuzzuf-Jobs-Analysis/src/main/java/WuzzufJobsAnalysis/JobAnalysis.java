package WuzzufJobsAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class JobAnalysis {
    private JobDaoImp jobData;
    private List<JobModel> jobs;

    @Autowired
    public JobAnalysis(JobDaoImp jobData){
        this.jobData = jobData;
        this.jobs = jobData.getAllData();
    }

    public String filterJobsByComp(){
        // create hashmap to contain each company frequency
        HashMap<String, Integer> compFreq = new HashMap<>();
        for(JobModel job : jobs){
            if (compFreq.containsKey(job.getCompany())){
                compFreq.put(job.getCompany(), compFreq.get(job.getCompany()) + 1);
            }
            else{
                compFreq.put(job.getCompany(), 1);
            }
        }
        // Sorting to get high frequency company at the beginning
        LinkedHashMap<String, Integer> sortedComp = Utities.sortByValue(compFreq);

        int limit = 15;
        List<String> companies = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        short n = 0;
        for (String i : sortedComp.keySet()) {
            companies.add(i);
            counts.add(sortedComp.get(i));
            n++;
            if (n >= limit){
                break;
            }
        }

        // Visualizing in bar chart
        Visualization.pieChart(companies, counts, "Most Popular Companies", 10);

        // Html output
        return Utities.getHtml(companies, counts, "Most Popular Companies", "Company");
    }

    public String filterJobsByTitle(){
        // create hashmap to contain each title frequency
        HashMap<String, Integer> titleFreq = new HashMap<>();
        for(JobModel job : jobs){
            if (titleFreq.containsKey(job.getTitle())){
                titleFreq.put(job.getTitle(), titleFreq.get(job.getTitle()) + 1);
            }
            else{
                titleFreq.put(job.getTitle(), 1);
            }
        }
        // Sorting to get high frequency titles at the beginning
        LinkedHashMap<String, Integer> sortedTitles = Utities.sortByValue(titleFreq);

        // Visualizing in bar chart
        int limit = 15 ;
        List<String> titles = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        short n = 0;
        for (String i : sortedTitles.keySet()) {
            titles.add(i);
            counts.add(sortedTitles.get(i));
            n++;
            if (n >= limit){
                break;
            }
        }
        Visualization.barChart(titles, counts, "Most Popular Titles", "Titles");

        // Html output
      return Utities.getHtml(titles, counts, "Most Popular Titles", "Title");
    }

    public String filterJobsByArea(){
        // create hashmap to contain each Area frequency
        HashMap<String, Integer> areaFreq = new HashMap<>();
        for(JobModel job : jobs){
            if (areaFreq.containsKey(job.getLocation())){
                areaFreq.put(job.getLocation(), areaFreq.get(job.getLocation()) + 1);
            }
            else{
                areaFreq.put(job.getLocation(), 1);
            }
        }

        // Sorting to get high frequency Areas at the beginning
        LinkedHashMap<String, Integer> sortedArea = Utities.sortByValue(areaFreq);

        // Visualizing in bar chart
        int limit = 15 ;
        List<String> areas = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        short n = 0;
        for (String i : sortedArea.keySet()) {
            areas.add(i);
            counts.add(sortedArea.get(i));
            n++;
            if (n >= limit){
                break;
            }
        }
        Visualization.barChart(areas, counts, "Most Popular Area", "Area");

        // Html output
        return Utities.getHtml(areas, counts, "Most Popular Area", "Area");
    }

    public String filterJobsBySkills() {
        List<String[]> jobSkills = new ArrayList<>();
        for (JobModel job : jobs) {
            jobSkills.add(job.getSkills());
        }

        // create hashmap to contain how many each skill has repeated:
        HashMap<String, Integer> skillsFreq = new HashMap<>();
        for (String[] skill : jobSkills) {
            for (String x : skill) {
                x = x.trim().replace("[", "").replace("]", "");
                if (skillsFreq.containsKey(x)) {
                    skillsFreq.put(x, skillsFreq.get(x) + 1);
                } else {
                    skillsFreq.put(x, 1);
                }
            }
        }

        // Sorting to get most frequent Skills at the beginning
        LinkedHashMap<String, Integer> sortedSkills = Utities.sortByValue(skillsFreq);

        // Visualizing in bar chart
        int limit = 15;
        List<String> skills = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        short n = 0;
        for (String i : sortedSkills.keySet()) {
            skills.add(i);
            counts.add(sortedSkills.get(i));
            n++;
            if (n >= limit) {
                break;
            }
        }
        Visualization.barChart(skills, counts, "Most Popular Skills", "Skills");

        // Html output
       return Utities.getHtml(skills, counts, "Most Popular Skills", "Skill");
    }
}
