package com.example.demo;

public class Jobs {

    private int Id;
    private String jname;
    private int salary;

    public Jobs(int Id, String jname, int salary)
    {
        this.Id = Id;
        this.jname = jname;
        this.salary = salary;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job [Id =" + getId() + ", Name" + getJname()
                + ", Salary" + getSalary() + "]";
    }
}

