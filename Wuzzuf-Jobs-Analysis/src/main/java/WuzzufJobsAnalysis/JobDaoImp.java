package WuzzufJobsAnalysis;

import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobDaoImp implements JobDao {
    private List<JobModel> jobs;

    private String path = null;
    private Dataset<Row> data = null;
    private SparkSession sparkSession = null;

    public JobDaoImp(){
        this.path = "src/main/resources/Wuzzuf_Jobs.csv";
        // open SaprkSession
        this.sparkSession = SparkSession.builder().appName ("Wuzzuf Jobs DataAnalysis").master("local[5]").getOrCreate();
        // Get DataFrameReader using SparkSession.
        DataFrameReader dataFrameReader = sparkSession.read();
        // Set header option to true to specify that first row in file contains column name.
        dataFrameReader.option("header", true);
        // read csvFile.
        this.data = dataFrameReader.csv(this.path);
        // clean Data.
        cleanData();
        // Construct data objects.
        this.jobs = constructData();
    }

    public List<JobModel> getAllData() {
        return this.jobs;
    }

    private void cleanData(){
        // Remove Duplicates:
        data = data.dropDuplicates();
        // Remove Nulls from YearsOfExp column
        String sql = "Select * FROM WuzzufJobs WHERE YearsExp != \"null Yrs of Exp\"";
        data.createOrReplaceTempView ("WuzzufJobs");
        data = sparkSession.sql(sql);
    }

    private List<JobModel> constructData(){
        // List contain all columns as lists
        List<List<Row>> columnLists = new ArrayList<>();
        // Array of Column names
        String[] columnNames = data.columns();
        // List of Data objects
        List<JobModel> dataObjects = new ArrayList<>();

        // construct list of Columns Lists
        for (String col : columnNames) {
            columnLists.add(data.select(col).collectAsList().stream().collect(Collectors.toList()));
        }

        // construct Data Objects List
        for (int i=0 ; i < columnLists.get(0).size() ; i++) {

            String Title = columnLists.get(0).get(i).get(0).toString();
            String Company = columnLists.get(1).get(i).get(0).toString();
            String Location = columnLists.get(2).get(i).get(0).toString();
            String Type = columnLists.get(3).get(i).get(0).toString();
            String Level = columnLists.get(4).get(i).get(0).toString();
            String YearsExp = columnLists.get(5).get(i).get(0).toString();
            String Country = columnLists.get(6).get(i).get(0).toString();
            String Skills = columnLists.get(7).get(i).get(0).toString();

            dataObjects.add(new JobModel(Title, Company, Location, Type, Level, YearsExp, Country, Skills));
        }
        return dataObjects;
    }
}

