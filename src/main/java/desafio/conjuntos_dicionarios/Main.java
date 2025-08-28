package desafio.conjuntos_dicionarios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import desafio.conjuntos_dicionarios.entities.Department;
import desafio.conjuntos_dicionarios.entities.Employee;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File entrys = new File("C:\\temp\\csv");

            File[] csvData = entrys.listFiles(File::isFile);

            for(File csv : csvData){
                String[] record = objectMapper.readValue(csv, new TypeReference<String[]>() {});
                convertRecords(record);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static List<Department> convertRecords(String[] records) {
        Map<Long, Department> dict = new HashMap<>();
        List<Department> list = new ArrayList<>();
        
        for(String rec : records){
            String[] parts = rec.split(",");
            String name = parts[3];
            Long id = Long.parseLong(parts[2]);
            Double salary = Double.parseDouble(parts[4]);
            Long departmentId = Long.parseLong(parts[0]);
            String departmentName = parts[1];

            if(dict.containsKey(departmentId)){
                dict.get(departmentId).addEmployee(new Employee(id, name, salary, new Department(departmentId, departmentName)));
            }
            else{
                dict.put(departmentId, new Department(departmentId, departmentName));

            }
                 
            
        }
        
        return list;
    }
}