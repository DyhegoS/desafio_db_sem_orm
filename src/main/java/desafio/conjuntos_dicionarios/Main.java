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
            File entrys = new File("C:\\temp\\jsons");

            File[] jsonData = entrys.listFiles(File::isFile);

            for(File json : jsonData){
                String[] record = objectMapper.readValue(json, new TypeReference<String[]>() {});
                convertRecords(record);
                System.out.println("====================================================================");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static List<Department> convertRecords(String[] records) {
        Map<Long, String> dict = new HashMap<>();
        Department dp = new Department();
        List<Department> result = new ArrayList<>();
        
        for(String rec : records){
            String[] parts = rec.split(",");
            String name = parts[3];
            Long id = Long.parseLong(parts[2]);
            Double salary = Double.parseDouble(parts[4]);
            Long departmentId = Long.parseLong(parts[0]);
            String departmentName = parts[1];

            if(!dict.containsKey(departmentId)){
                dict.put(departmentId, departmentName);
            }

            for(Long d : dict.keySet()){
                if(departmentName == dict.get(d)){
                    dp.addEmployee(new Employee(id, name, salary, new Department(departmentId, departmentName)));
                    System.out.println(dp);
                }
            }
              
        }     
    
        return null;
    }
}