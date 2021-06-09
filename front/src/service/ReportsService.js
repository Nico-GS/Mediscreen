import axios from "axios";

const URL_API_REPORTS = "http://localhost:8083/api/reports";
const URL_GET_PATIENT = "http://localhost:8083/api/reports/lastAndFirstName";

class ReportsService {

    getReportsById(reportsId) {
        return axios.get(URL_API_REPORTS + "/" + reportsId);
    }

    getReportsByLastAndFirstName (firstName, lastName) {
        return axios.request({
            url: URL_GET_PATIENT,
            method: "GET",
            params: {
                firstName: firstName,
                lastName: lastName
            }
        })
    }

}

export default new ReportsService();