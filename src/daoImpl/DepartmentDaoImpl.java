package daoImpl;

import dao.DepartmentDao;
import dao.GenericDao;
import dao.HospitalDao;
import dataBase.DataBase;
import models.Department;
import models.Hospital;

import java.util.List;

import static dataBase.DataBase.hospitals;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
       if (hospital!=null){
           return hospital.getDepartments();
       }return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        for (Hospital hospital: hospitals){
            List<Department> departments = hospital.getDepartments();
            for (Department department:departments){
                if (department.getDepartmentName().equalsIgnoreCase(name)){
                    return department;
                }
            }
        }return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
    Hospital hospital = new HospitalDaoImpl().findHospitalById(hospitalId);
    if (hospital!=null){
        hospital.getDepartments().add(department);
        return "Successfully added";
    }
    return "Try again";
    }

    @Override
    public void removeById(Long id) {
     for (Hospital hospital: hospitals){
         for (Department department:hospital.getDepartments()){
             if (department.getId().equals(id)){
                 hospital.getDepartments().remove(department);
                 System.out.println("Successfully deleted");
                 return;
             }
         }
     } System.out.println("Department not found. Try again.");
    }

    @Override
    public String updateById(Long id, Department department) {
   for (Hospital hospital: hospitals){
       for (Department department1:hospital.getDepartments()){
           if (department1.getId().equals(id)){
               department1.setDepartmentName(department.getDepartmentName());
               return "Successfully updated";
           }
       }
   }return "Try again";
    }
}
