import dataBase.Counter;
import dataBase.DataBase;
import enums.Gender;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import serviceImpl.DepartmentServiceImpl;
import serviceImpl.DoctorServiceImpl;
import serviceImpl.HospitalServiceImpl;
import serviceImpl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static <Scanner1> void main(String[] args) {

      /*  1)  Оорукана тууралу бир программа жазыныз. Dao жана Service менен иштешкиле
      (ар бир класстын dao жана service interface'тери жана ошол interface'ти implements
       класстары болуш керек). Database деген класс ачып, ичинде бардык маалыматтарды сактаган
       маалымат структурасы болсун(List<Hospital> hospitals).

        2) Класстар:
       * Hospital (Long id, String hospitalName, String address, List<Department> departments,
        List<Doctor> doctors, List<Patient> patients),
       * Department (Long id, String departmentName, List<Doctor> doctors),
       * Doctor (Long id, String firstName, String lastName, Gender gender, int experienceYear),
       * Patient (Long id, String firstName, String lastName, int age, Gender gender);3) Gender enum.

        3)
        HospitalService :

        String addHospital(Hospital hospital);
        Hospital findHospitalById(Long id);
        List<Hospital> getAllHospital();
        List<Patient> getAllPatientFromHospital(Long id);
        String deleteHospitalById(Long id);
        Map<String, Hospital> getAllHospitalByAddress(String address);

        GenericService : (Department, Doctor,Patient)

        String add(Long  hospitalId, T t);

        void removeById(Long id);

        String updateById(Long id, T t);

        DepartmentService:

        List<Department> getAllDepartmentByHospital(Long id);
        Department findDepartmentByName(String name);

        DoctorService:

        Doctor findDoctorById(Long id);

        // Department‘ти id менен табып, анан Doctor‘лорду листтеги айдилери менен табып анан
        ошол табылган Department'ге табылган Doctor'лорду кошуп коюнунуз
        String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId);
        List<Doctor> getAllDoctorsByHospitalId(Long id);
        List<Doctor> getAllDoctorsByDepartmentId(Long id);

        PatientService:

        String addPatientsToHospital(Long id,List<Patient> patients);
        Patient getPatientById(Long id);
        Map<Integer, Patient> getPatientByAge();
        List<Patient> sortPatientsByAge(String ascOrDesc);*/
        PatientServiceImpl patientService = new PatientServiceImpl();
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        HospitalServiceImpl hospitalService = new HospitalServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1= new Scanner(System.in);

        while (true){
            try {

                System.out.println("1.Department service");
                System.out.println("2.Doctor service");
                System.out.println("3.Patient service");
                System.out.println("4.Hospital service");
                System.out.print("Choose an option: ");
                int choice = scanner1.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: {
                        try {
                            boolean check = true;
                            while (check) {
                                System.out.println("1. Create department");
                                System.out.println("2. Get all department by hospital id");
                                System.out.println("3. Get group by department name");
                                System.out.println("4.  Delete department by id");
                                System.out.println("5. Update department name");
                                System.out.println("6. Return menu");

                                int choice1 = scanner.nextInt();
                                scanner.nextLine();
                                switch (choice1) {
                                    case 1: {
                                        try {
                                            System.out.println("Department name: ");
                                            String name = scanner.nextLine();
                                            for (int i = 0; i < name.length(); i++) {
                                                if (!Character.isLetter(name.charAt(i))) {
                                                    throw new IllegalArgumentException("Only letters");
                                                }
                                            } Long id= Counter.counterDepartmentId();
                                            List<Doctor> doctors1 = new ArrayList<>();
                                            Department department1 = new Department(id,name,doctors1);
                                            System.out.println(department1);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                    case 2: {
                                        System.out.println("All departments");
                                        Long id= scanner1.nextLong();
                                        scanner1.nextLine();
                                        System.out.println(departmentService.getAllDepartmentByHospital(id));
                                        for (Hospital hospital1:DataBase.hospitals){
                                            for (Department department1:hospital1.getDepartments()){
                                                if (department1==null){
                                                    System.out.println("Is empty");
                                                }
                                            }
                                        }
                                    }
                                    break;
                                    case 3: {try {
                                        System.out.println("Enter department name");
                                        String name = scanner.nextLine();
                                        for (int i = 0; i < name.length(); i++) {
                                            if (!Character.isLetter(name.charAt(i))) {
                                                throw new IllegalArgumentException("Only letters");
                                            }
                                        } System.out.println(departmentService.findDepartmentByName(name));
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    }
                                    break;
                                    case 4: {
                                        System.out.println("Enter id department for delete");
                                        Long id = scanner1.nextLong();
                                        scanner.nextLine();
                                       doctorService.removeById(id);
                                    }
                                    break;
                                    case 5:{
                                        try {
                                            System.out.println("Write id department to update: ");
                                            Long id = scanner1.nextLong();
                                            scanner.nextLine();
                                            System.out.println("Write name for update:");
                                            String name= scanner.nextLine();
                                            for (int i = 0; i < name.length(); i++) {
                                                if (!Character.isLetter(name.charAt(i))){
                                                    throw new IllegalArgumentException("Write only letters");
                                                }
                                            }
                                           Department department1 = new Department();
                                            department1.setDepartmentName(name);
                                            List<Doctor> doctors1 = new ArrayList<>();
                                           Department department2  = new Department(id,name,doctors1);
                                            System.out.println("Successfully updated"+ department2);
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e.getMessage());
                                        }catch (Exception e){
                                            System.out.println("Error "+e.getMessage());
                                        }
                                    }break;
                                    case 6: {
                                        check = false;

                                        scanner1.nextLine();
                                        break;

                                    }
                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
                            scanner.nextLine();
                            scanner1.nextLine();
                        }
                    }

                    scanner.nextLine();
                    break;
                    case 2: {
                        try {
                            boolean check = true;
                            while (check) {
                                System.out.println("1. Create doctor ");
                                System.out.println("2. Find doctor by id");
                                System.out.println("3. Get all doctors by hospital id");
                                System.out.println("4. Delete doctor by id");
                                System.out.println("5. Get all doctors by department id");
                                System.out.println("6. Update doctors");
                                System.out.println("5. Return to menu");

                                int choice1 = scanner1.nextInt();
                                scanner.nextLine();
                                switch (choice1) {
                                    case 1: {
                                        Long id= scanner1.nextLong();
                                        scanner.nextLine();
                                        Doctor doctor = new Doctor(1L,"ghk","TYuib",Gender.FEMALE,12);
                                        System.out.println(doctorService.add(id, doctor));
                                    }
                                    break;
                                    case 2: {
                                        System.out.println("Enter doctor id: ");
                                        Long id= scanner1.nextLong();
                                        scanner1.nextLine();
                                        System.out.println(doctorService.findDoctorById(id));
                                    }
                                    break;

                                    case 3:
                                        {
                                            System.out.println("Enter hospital id");
                                            Long id= scanner1.nextLong();
                                            scanner1.nextLine();
                                            System.out.println(doctorService.getAllDoctorsByHospitalId(id));
                                        }
                                    break;
                                    case 4: {
                                        System.out.println("Enter doctor id: ");
                                        Long id=scanner1.nextLong();
                                        scanner1.nextLine();
                                        doctorService.removeById(id);
                                    }break;
                                    case 5:{
                                        try {
                                            System.out.println("Write id doctor to update: ");
                                            Long id = scanner1.nextLong();
                                            scanner.nextLine();

                                            System.out.println("Write name for update:");
                                            String name= scanner.nextLine();
                                            for (int i = 0; i < name.length(); i++) {
                                                if (!Character.isLetter(name.charAt(i))){
                                                    throw new IllegalArgumentException("Write only letters");
                                                }
                                            }

                                            Doctor doctor = new Doctor();
                                            doctor.setFirstName(name);
                                            String doctor1 = doctorService.updateById(id,doctor);
                                            System.out.println("Successfully updated"+ doctor1);
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e.getMessage());
                                        }catch (Exception e){
                                            System.out.println("Error "+e.getMessage());
                                        }
                                    }
                                    case 6:{
                                        check = false;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }

                    scanner.nextLine();
                    break;
                    case 3: {
                        try {
                            boolean check = true;
                            while (check) {
                                System.out.println("1. Create patient ");
                                System.out.println("2. Get patient by id ");
                                System.out.println("3. Get patient by age");
                                System.out.println("4. Sort patient by age");
                                System.out.println("5. Delete patient by id");
                                System.out.println("6. Return to menu");

                                int choice1 = scanner1.nextInt();
                                scanner.nextLine();
                                switch (choice1) {
                                    case 1: {
                                        System.out.println("Witch hospital do you want to add: ");
                                        Long id= scanner1.nextLong();
                                        scanner.nextLine();
                                       Patient patient = new Patient(1L,"gbbj","tgyu",12,Gender.FEMALE);
                                        Patient patient1 = new Patient(2L,"fdsbj","dvcu",12,Gender.FEMALE);
                                        Patient patient2 = new Patient(3L,"vddsj","vdvu",12,Gender.FEMALE);
                                        System.out.println(patientService.add(id, patient));
                                        System.out.println(patientService.add(id, patient1));
                                        System.out.println(patientService.add(id, patient2));
                                    }
                                    break;
                                    case 2: {
                                        System.out.println("Enter patient id: ");
                                        Long id= scanner1.nextLong();
                                        scanner1.nextLine();
                                        patientService.getPatientById(id);
                                    }
                                    break;

                                    case 3:
                                    {
                                        System.out.println(patientService.getPatientByAge());
                                    }
                                    break;
                                    case 4: {
                                        String asc= "asc";
                                        System.out.println(patientService.sortPatientsByAge(asc));
                                    }break;
                                    case 5:{
                                        System.out.println("Enter patient id: ");
                                        Long id= scanner1.nextLong();
                                        scanner1.nextLine();
                                        patientService.removeById(id);
                                    }
                                    case 6:{
                                        check = false;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }

                    scanner.nextLine();
                    break;
                    case 4:{
                        try {
                            boolean check= true;
                            while (check){

                                System.out.println("1. Create hospital");
                                System.out.println("2. Find hospital by id");
                                System.out.println("3. Get all hospital");
                                System.out.println("4. Get all patient from hospital id");
                                System.out.println("5. Delete hospital by id");
                                System.out.println("6. Get all hospital by address");
                                System.out.println("7. Return to menu");

                                int choice1= scanner1.nextInt();
                                scanner.nextLine();
                                switch (choice1){
                                    case 1:{
                                        Hospital hospital1 = new Hospital(1L,"gihui","Tdfbi",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
                                        Hospital hospital2 = new Hospital(2L,"fdbui","Tfd",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
                                        Hospital hospital3 = new Hospital(3L,"gdsvi","sbfdUi",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
                                        Hospital hospital4 = new Hospital(4L,"gsvi","TYUi",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
                                        System.out.println(hospitalService.addHospital(hospital1));
                                        System.out.println(hospitalService.addHospital(hospital2));
                                        System.out.println(hospitalService.addHospital(hospital3));
                                        System.out.println(hospitalService.addHospital(hospital4));
                                    }break;
                                    case 2:{
                                        System.out.println("Enter hospital id: ");
                                        Long id= scanner1.nextLong();
                                        scanner.nextLine();
                                        System.out.println(hospitalService.findHospitalById(id));
                                    }break;
                                    case 3:{
                                        System.out.println(hospitalService.getAllHospital());
                                    }break;
                                    case 4:{
                                        System.out.println("Enter hospital id: ");
                                        Long id= scanner1.nextLong();
                                        scanner.nextLine();
                                        System.out.println(hospitalService.getAllPatientFromHospital(id));
                                    }break;
                                    case 5: {
                                        System.out.println("Enter hospital id: ");
                                        Long id= scanner1.nextLong();
                                        scanner.nextLine();
                                        System.out.println(hospitalService.deleteHospitalById(id));
                                    }
                                    case 6:{
                                        System.out.println("Enter hospital address: ");
                                        String address= scanner.nextLine();
                                        System.out.println(hospitalService.getAllHospitalByAddress(address));
                                    }
                                    case 7: {
                                        check = false;
                                        break;
                                    }

                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }
                    scanner.nextLine();
                    break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}