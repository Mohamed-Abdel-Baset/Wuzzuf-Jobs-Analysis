package WuzzufJobsAnalysis;

public class JobModel {
    //setter
    private String title;
    private String company;
    private String location;
    private String type;
    private String level;
    private String yearsOfExp;
    private String country;
    private String[] skills;

    //Constructor
    public JobModel(String title, String company, String location, String type, String level, String yearsOfExp, String country, String skills){
        setTitle(title);
        setCompany(company);
        setLocation(location);
        setType(type);
        setLevel(level);
        setYearsOfExp(yearsOfExp);
        setCountry(country);
        setSkills(skills.replaceAll(" ", "").split(","));
    }

    //getters:
    public String getTitle(){
        return this.title;
    }
    public String getCompany(){
        return this.company;
    }
    public String getLocation(){
        return this.location;
    }
    public String getType(){
        return this.type;
    }
    public String getCountry(){ return this.country; }
    public String getYearsOfExp(){
        return this.yearsOfExp;
    }
    public String getLevel(){
        return this.level;
    }
    public String[] getSkills(){
        return this.skills;
    }

    //setters:
    public void setCompany(String company) {
        this.company = company;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearsOfExp(String yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public void setType(String type) { this.type = type; }
}
