import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import NotesService from "../../service/NotesService";
import '../../CSS/ViewPatient.css';

class ViewPatient extends Component {


    constructor(props) {
        super(props);


        this.state = {
            id: this.props.match.params.id,
            patient: {},
            noteId: '',
            patientId: '',
            patientLastName: '',
            patientFirstName: '',
            note: '',
            dateNote: '',


        }
        this.addNotes = this.addNotes.bind(this);
        this.changeNote = this.changeNote.bind(this);
    }


    componentDidMount() {
        PatientService.getPatientById(this.state.id).then(response => {
            this.setState({patient: response.data});
        })
        // NotesService.getNotesById(this.state.id).then(response => {
        //     this.setState({note: response.data});
        // })
        if (this.state.id) {
            return
        } else {
            NotesService.getNotes(this.state.id).then((response) => {
                let note = response.data;
                this.setState({
                    id: note.id,
                    patientId: note.patientId,
                    patientFirstName: note.patientFirstName,
                    patientLastName: note.patientLastName,
                    note: note.note,
                    dateNote: note.dateNote
                });
            });
        }


    }

    addNotes = (e) => {
        e.preventDefault();
        let note = {
            id: this.state.id,
            patientId: this.state.patient.id,
            patientLastName: this.state.patient.lastName,
            patientFirstName: this.state.patient.firstName,
            note: this.state.note,
            dateNote: this.state.dateNote
        }
        if(this.state.id) {
            NotesService.createNotes(note).then(response => {
                this.props.history.push('/')
            });
        } else {
            NotesService.updateNotes(note, this.state.id).then(response => {
                this.props.history.push('/notes');
            })
        }
    }

    changeNote = (event) => {
        this.setState({note: event.target.value});
    }




    render() {
        return (
            <div>
                <br/><br/>
                <div className ="card col-md-6 offset-md-3">
                    <h3 className ="text-center">View Patient Details</h3>
                    <div className ="card-body">
                        <div className ="row">
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
                        <div className = "row">
                            <label>Notes : </label>
                            <div className="font-weight-bold">
                            </div>
                        </div>


                        <div className="container card form-note">
                            <div className ="container-fluid button-patient">
                                <div>ID Patient : {this.state.id}</div>
                                <form>
                                    <div className ="hide-form">
                                        <label>ID Patient</label>
                                        <input placeholder="" name="patientId" className="form-control"
                                               value={this.state.id} />
                                    </div>
                                    <div className ="hide-form">
                                        <label>ID Notes : </label>
                                        <input placeholder="" name="idNote" className="form-control"
                                               value={this.state.note.id} />
                                    </div>
                                    <div className ="hide-form">
                                        <label>Last Name</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.state.lastName} />
                                    </div>
                                    <div className ="hide-form">
                                        <label>First Name</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.state.firstName} />
                                    </div>
                                    <div className ="hide-form">
                                        <label>Date Note</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.state.note.dateNote} />
                                    </div>
                                    <div className = "form-group">
                                        <label>Notes : </label>
                                        <input placeholder="" name="note" className="form-control"
                                               value={this.state.note} onChange={this.changeNote}/>
                                    </div>
                                    <button className="btn btn-primary btn-sm" onClick={this.addNotes}>Add Notes</button>
                                </form>
                            </div>
                        </div>


                    </div>

                </div>
            </div>
        )
    }

}

export default ViewPatient