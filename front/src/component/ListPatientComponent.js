import React, {Component} from "react";
import PatientService from "../service/PatientService";
import '../CSS/ListPatient.css';

class ListPatientComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            patients: []
        }
        this.addPatient = this.addPatient.bind(this);
        this.updatePatient = this.updatePatient.bind(this);
        this.deletePatient = this.deletePatient.bind(this);
    }

    componentDidMount() {
        PatientService.getPatient().then((response) => {
            this.setState({patients: response.data});
        });
    }

    deletePatient(id) {
        PatientService.deletePatient(id).then (response => {
            this.setState({patients: this.state.patients.filter(patient => patient.id !== id)});
            this.props.history.push('/');
        });
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

    render() {
        return (
            <div>
                <h2 className="text-center">List Patients</h2>
                <div className ="container-fluid">
                    <button className="btn btn-primary button-add" onClick={this.addPatient}>Add Patient</button>
                </div>
                <br/><br/>
                <div className ="row">
                    <table className ="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr>
                            <td>Patient ID</td>
                            <td>Patient LastName</td>
                            <td>Patient FirstName</td>
                            <td>Birth Date</td>
                            <td>Sex</td>
                            <td>Address</td>
                            <td>Phone Number</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.patients.map(patient =>
                                    <tr key = {patient.id}>
                                        <td>{patient.id_patient}</td>
                                        <td>{patient.lastName}</td>
                                        <td>{patient.firstName}</td>
                                        <td>{patient.dateOfBirth}</td>
                                        <td>{patient.sex}</td>
                                        <td>{patient.address}</td>
                                        <td>{patient.phoneNumber}</td>
                                        <td>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.updatePatient(patient.id_patient)} className="btn btn-primary">Update</button>
                                            <button style={{marginLeft: "20px"}} onClick={ () => this.viewPatient(patient.id_patient)} className="btn btn-primary">View</button>
                                            <button style={{marginTop: "5px", marginLeft: "35px"}} onClick={ () => this.deletePatient(patient.id_patient)} className="btn btn-sm btn-danger">Delete</button>

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