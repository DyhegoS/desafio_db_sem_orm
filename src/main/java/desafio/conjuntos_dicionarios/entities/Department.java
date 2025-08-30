package desafio.conjuntos_dicionarios.entities;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private Long id;
    private String name;
    private Set<Employee> emp = new HashSet<>();

    public Department(){
    }
    
    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmp() {
        return emp;
    }

    public void addEmployee(Employee emp){
        this.emp.add(emp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + ":\n");
        for(Employee e : emp){
            sb.append("   " + e);
        }
        return sb.toString();
    }
    
}
