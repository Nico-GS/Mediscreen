import axios from "axios";

const URL_API_RDV = "http://localhost:8081/api/rdv";

class RdvService {

    getRdv() {
        return axios.get(URL_API_RDV);
    }

    getRdvById(idRdv) {
        return axios.get(URL_API_RDV + '/' + idRdv);
    }

    createRdv(rdv) {
        return axios.post(URL_API_RDV, rdv);
    }

    updateRdv(rdv, rdvId) {
        return axios.put(URL_API_RDV + '/' + rdv, rdvId);
    }

    deleteRdv(rdvId) {
        return axios.delete(URL_API_RDV + '/' + rdvId);
    }

}

export default new RdvService();