import axios from "axios";

const URL_API_PATIENT = "http://localhost:8081/api/patient";

class PatientService {
    
    getPatient() {
        return axios.get(URL_API_PATIENT);
    }

    getPatientById(patientId) {
        return axios.get(URL_API_PATIENT + '/' + patientId);
    }

    createPatient(patient) {
        return axios.post(URL_API_PATIENT, patient);
    }

    updatePatient(patient, patientId) {
        return axios.put(URL_API_PATIENT + '/' + patientId, patient);
    }

    deletePatient(patientId) {
        return axios.delete(URL_API_PATIENT + '/' + patientId);
    }
}

export default new PatientService();