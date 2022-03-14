import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public ArrayList<String> DDL(){
    File f = new File("C:\\Users\\mikke\\IdeaProjects\\DataBaseTest\\src\\imdb-data.csv");
    ArrayList<String> ddl = new ArrayList<>();
    try{
        Scanner sc = new Scanner(f);
        String line = sc.nextLine();
        String[] split = line.split(";");
        ddl.add(split[0]);
        ddl.add(split[1]);
        ddl.add(split[2]);
        ddl.add(split[3]);
        ddl.add(split[4]);
        ddl.add(split[5]);

        String createTable = "CREATE TABLE movies ("
                +split[0]+" varchar(255), "+split[1]+" varchar(255), "+split[2]+" varchar(255), "+split[3]+" varchar(255), "+split[4]+" varchar(255), "+split[5]+" varchar(255));";

        ddlFile(createTable);
    }
    catch (Exception e){
        System.out.println("Fejl");
    }
    return ddl;
    }

    public void DML(){
        ArrayList<String> ddlList = DDL();
        File f = new File("C:\\Users\\mikke\\IdeaProjects\\DataBaseTest\\src\\imdb-data.csv");
        ArrayList<String> listOfMovies = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(f);
            scanner.nextLine();
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] split = line.split(";");
                String year = split[0];
                String length = split[1];
                String title = split[2];
                String subject = split[3];
                String popularity = split[4];
                String awards = split[5];

                String insertSQL ="INSERT INTO movies" +
                        "(`"+ddlList.get(0)+"`,`"+ddlList.get(1)+"`,`"+ddlList.get(2)+"`,`"+ddlList.get(3)+"`,`"+ddlList.get(4)+"`,`"+ddlList.get(5)+"`)"+
                        " VALUES ('"+year+"','"+length+"','"+title+"','"+subject+"','"+popularity+"','"+awards+"');";

                listOfMovies.add(insertSQL);
                dmlFile(listOfMovies);
            }
        }
        catch (Exception e){
            System.out.println("Fejl 2");
        }
    }

    public static void ddlFile(String test) throws IOException {
        File w = new File("src/DML");
        FileWriter writer = new FileWriter(w);
        writer.write(test);
        writer.close();
    }

    public static void dmlFile(ArrayList<String> test) throws IOException {
        File w = new File("C:\\Users\\mikke\\IdeaProjects\\DataBaseTest\\src\\DDL");
        FileWriter writer = new FileWriter(w);
        for (String str: test){
            writer.write(str);
            String line = "\n";
            writer.write(line);
        }
        writer.close();
    }


}
