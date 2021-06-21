import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import NotesService from "../../service/NotesService";
import ReportsService from "../../service/ReportsService";
import moment from "moment";
import '../../CSS/ViewPatient.css';


class ViewPatient extends Component {


    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            patient: {},
            notes: [],
            noteId: '',
            patientId: '',
            patientLastName: '',
            patientFirstName: '',
            note: '',
            dateNote: moment().utc().format("YYYY-MM-DD hh:mm:ss"),

        }
        this.addNotes = this.addNotes.bind(this);
        this.changeNote = this.changeNote.bind(this);
    }


    componentDidMount() {
        PatientService.getPatientById(this.state.id).then(response => {
            this.setState({patient: response.data});
        })
        NotesService.getNotesById(this.state.notes).then(response => {
            this.setState({notes: response.data});
        })
    }




    addNotes = (e) => {
        e.preventDefault();
        let note = {
            id: this.state.noteId,
            patientId: this.state.id,
            patientLastName: this.state.patient.lastName,
            patientFirstName: this.state.patient.firstName,
            note: this.state.note,
            dateNote: this.state.dateNote
        }
        if (this.state.id) {
            NotesService.createNotes(note).then(response => {
                window.location.reload(true);
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

    deleteNote(id) {
        NotesService.deleteNotes(id).then(response => {
            this.setState({notes: this.state.notes.filter(note => note.id !== id)});
            this.handleRefresh();
        })
    }

    updateNote(note) {
        NotesService.updateNotes(note, this.state.id).then(response => {
            this.props.history.push('/');
        })
    }


    handleRefresh = () => {
        this.setState({});
    }

    handleRefreshAdd = () => {
        this.setState({});
    }


    getDate = () => {
        this.setState({dateNote: new Date()})
    }


    render() {
        return (
            <div>
                <br/><br/>

                <div className="card col-md-12 container-fluid">
                    <h3 className="text-center">View Patient Details</h3>
                    <div className="card-body">

                        <div className="row">
                            <label>Patient First Name : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.firstName}
                            </div>
                        </div>
                        <div className="row">
                            <label>Patient Last Name : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.lastName}
                            </div>
                        </div>
                        <div className="row">
                            <label> Date of Birth : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.dateOfBirth}
                            </div>
                        </div>
                        <div className="row">
                            <label>Sex : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.sex}
                            </div>
                        </div>
                        <div className="row">
                            <label>Address : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.address}
                            </div>
                        </div>
                        <div className="row">
                            <label>Phone Number : </label>
                            <div className="font-weight-bold">
                                {this.state.patient.phoneNumber}
                            </div>
                        </div>

                        <div className="container-fluid">
                            <button className="btn btn-sm btn-dark">Reports</button>
                        </div>

                        <div className="separation card"/>
                        <br/>
                        <div className="container card notes">
                            <div className="row">
                                <table className="table table-striped table-bordered tableau-list">
                                    <thead>
                                    <tr className="tab-name">
                                        <td className="container-fluid">Note</td>
                                        <td className="container-fluid">Date</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        this.state.notes.map(note =>
                                            <tr key={note.id}>
                                                <td className="note">{note.note}</td>
                                                <td>
                                                    {note.dateNote}
                                                </td>
                                                <td>

                                                    <button onClick={() => this.deleteNote(note.id)}
                                                            className="btn btn-sm btn-danger">X
                                                    </button>

                                                </td>
                                            </tr>
                                        )
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div className="card container form-note">
                            <div className="container">
                                <form>
                                    <div className="hide-form">
                                        <label>ID Patient</label>
                                        <input placeholder="" name="patientId" className="form-control"
                                               value={this.state.id}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>ID Notes : </label>
                                        <input placeholder="" name="idNote" className="form-control"
                                               value={this.state.note.id}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>Last Name</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.state.lastName}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>First Name</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.state.firstName}/>
                                    </div>
                                    <div className="hide-form">
                                        <label>Date Note</label>
                                        <input placeholder="" name="LastName" className="form-control"
                                               value={this.getDate}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Notes : </label>
                                        <textarea name="note" className="form-control"
                                                  value={this.state.note} onChange={this.changeNote}/>
                                    </div>
                                    <button className="btn btn-primary btn-sm" onClick={this.addNotes}>Add Notes
                                    </button>
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