package serviceImpl;

import dao.HospitalDao;
import daoImpl.HospitalDaoImpl;
import models.Hospital;
import models.Patient;
import service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl  implements HospitalService {
   HospitalDao hospitalDao = new HospitalDaoImpl();
    @Override
    public String addHospital(Hospital hospital) {
        return hospitalDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return List.of();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return List.of();
    }

    @Override
    public String deleteHospitalById(Long id) {
        return "";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return Map.of();
    }
}
