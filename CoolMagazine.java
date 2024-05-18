package HomeWorkIO;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.logging.log4j.util.Chars.TAB;

/*
1) Нужно считать первичные данные с Excel файла в мои листы Student, Teacher, Subject
2) Сделать работу базовую со StreamApi к примеру сортировка
3) Считать обратно в json файл



 */
public class CoolMagazine {
    public static void main(String[] args) {
       List<Student> students = new ArrayList<>();
        getStudents(students);
        students.stream().filter(st -> st.getAverage() > 80).forEach(System.out::println);
        System.out.println("-----------------------------------------");
        students.stream().filter(st -> st.getTeacher().getSubject().getName().equals("АИСД")).forEach(System.out::println);
    }

    public static List<Student> getStudents(List<Student> studentList) {
        try {
            FileInputStream file = new FileInputStream("Book2.xlsx");
            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int id = 0;
                String name = "";
                String surName = "sur";
                int average = 0;
                String fullName = "full";
                String nameSubject = "";

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            if (name.equals("")) {
                                name = cell.getStringCellValue();
                            } else if (surName.equals("sur")) {
                                surName = cell.getStringCellValue();
                            } else if (fullName.equals("full")) {
                                fullName = cell.getStringCellValue();
                            } else {
                                nameSubject = cell.getStringCellValue();
                            }
                            break;
                        case NUMERIC:
                            if (id == 0) {
                                id = (int) cell.getNumericCellValue();
                            } else {
                                average = (int) cell.getNumericCellValue();
                            }
                            break;
                    }
                }
                Subject subject = new Subject(nameSubject);
                Teacher teacher = new Teacher(fullName, subject);
                Student student = new Student(id, name, surName, average, teacher);
                studentList.add(student);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }
    public static void saveToJson(List<Student> tasks) {

        JSONArray jarray = new JSONArray(tasks.toArray());
        try {
            FileWriter writer = new FileWriter(new File("Test"));
            writer.write(jarray.toString(TAB));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Student> readFromJsonFile(String fileName){
        ArrayList<Student> result = new ArrayList<>();

        try{
            String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

            JSONObject obj = new JSONObject(text);
            JSONArray arr = obj.getJSONArray("students");

            for(int i = 0; i < arr.length(); i++){
                int id = arr.getJSONObject(i).getInt("id");
                String name = arr.getJSONObject(i).getString("name");
                String surName = arr.getJSONObject(i).getString("surNname");
                int average = arr.getJSONObject(i).getInt("average");
                JSONObject teacher = arr.getJSONObject(i).getJSONObject("teacher");
                String fullName = arr.getJSONObject(i).getJSONObject("teacher").getString("fullName");
                JSONObject subject = arr.getJSONObject(i).getJSONObject("teacher").getJSONObject("subject");
                String nameSubject = arr.getJSONObject(i).getJSONObject("teacher").getJSONObject("subject").getString("name");
                result.add(new Student(id, name, surName, average, new Teacher(fullName, new Subject(nameSubject))));

            }
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        return result;
    }
}

