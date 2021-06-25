import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import '../../CSS/ListPatient.css';

class ListPatientComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            patients: []
        }
        this.addPatient = this.addPatient.bind(this);
        this.updatePatient = this.updatePatient.bind(this);
        this.deletePatient = this.deletePatient.bind(this);
        this.takeRdv = this.takeRdv.bind(this);
    }

    componentDidMount() {
        PatientService.getPatient().then((response) => {
            this.setState({patients: response.data});
        });
    }

    deletePatient(id) {
        PatientService.deletePatient(id).then (response => {
            this.setState({patients: this.state.patients.filter(patient => patient.id !== id)});
            window.location.href="/";
        });
    }

    handleRefresh = () => {
        this.setState({});
    }

    addPatient() {
        this.props.history.push('/add-patient/_add');
    }

    viewPatient(id) {
        this.props.history.push(`/view-patient/${id}`);
    }

    updatePatient(id) {
        this.props.history.push(`/add-patient/${id}`);
    }

    takeRdv() {
        this.props.history.push('/rdv');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">List Patients</h2>
                <br/>
                <div className ="container-fluid button-patient">
                    <button className="btn btn-primary" onClick={this.addPatient}>Add Patient</button>
                </div>
                <br/><br/>
                <div className ="row">
                    <table className ="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr className="tab-name">
                            <td>Last Name</td>
                            <td>First Name</td>
                            <td>Birth Date</td>
                            <td>Sex</td>
                            <td>Address</td>
                            <td>Phone</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.patients.map(patient =>
                                    <tr key = {patient.id}>
                                        <td>{patient.lastName}</td>
                                        <td>{patient.firstName}</td>
                                        <td>{patient.dateOfBirth}</td>
                                        <td>{patient.sex}</td>
                                        <td>{patient.address}</td>
                                        <td>{patient.phoneNumber}</td>
                                        <td>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.updatePatient(patient.id_patient)} className="btn btn-primary">Update</button>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.viewPatient(patient.id_patient)} className="btn btn-primary">View</button>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.deletePatient(patient.id_patient)} className="btn btn-danger">Delete</button>

                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>

                </div>

            </div>
        )
    }

}

export default ListPatientComponent;