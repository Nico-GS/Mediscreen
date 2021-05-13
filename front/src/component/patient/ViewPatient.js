import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import NotesService from "../../service/NotesService";

class ViewPatient extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            patient: {},
            note: {}
        }
        this.addNotes = this.addNotes.bind(this);
    }

    componentDidMount() {
        PatientService.getPatientById(this.state.id).then(response => {
            this.setState({patient: response.data});
        })
        NotesService.getNotesById(this.state.id).then(response => {
            this.setState({note: response.data});
        })
    }

    addNotes() {
        this.props.history.push('/add-notes');
    }

    render() {
        return (
            <div>
                <br/><br/>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center">View Patient Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label>Patient First Name : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.firstName}
                            </div>
                        </div>
                        <div className = "row">
                            <label>Patient Last Name : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.lastName}
                            </div>
                        </div>
                        <div className = "row">
                            <label> Date of Birth : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.dateOfBirth}
                            </div>
                        </div>
                        <div className = "row">
                            <label>Sex : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.sex}
                            </div>
                        </div>
                        <div className = "row">
                            <label>Address : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.address}
                            </div>
                        </div>
                        <div className = "row">
                            <label>Phone Number : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.phoneNumber}
                            </div>
                        </div>
                        <div className ="container-fluid button-patient">
                            <button className="btn btn-primary" onClick={this.addNotes}>Add Notes</button>
                        </div>
                    </div>

                </div>
            </div>
        )
    }

}

export default ViewPatient