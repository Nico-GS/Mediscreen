import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import NotesService from "../../service/NotesService";
import ReportsService from "../../service/ReportsService";
import moment from "moment";
import '../../CSS/ViewPatient.css';
import Popup from "reactjs-popup";


class ViewNotes extends Component {


    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            notes: [],
            noteId: '',
            patientId: '',
            patientLastName: '',
            patientFirstName: '',
            note: '',
            dateNote: moment().utc().format("YYYY-MM-DD hh:mm:ss")
        }
        this.addNotes = this.addNotes.bind(this);
        this.changeNote = this.changeNote.bind(this);
        // this.updateNote = this.updateNote.bind(this);
    }


    componentDidMount() {
        NotesService.getNotesById(this.state.id).then(response => {
            this.setState({note: response.data});
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
            dateNote: this.state.dateNote,
        }
        if (this.state.id) {
            NotesService.createNotes(note).then(response => {
                window.location.reload(true);
            });
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

    handleRefresh = () => {
        this.setState({});
    }

    render() {
        return (
            <div>
                <br/><br/>

                <div className="card col-md-12 container-fluid">
                    <h3 className="text-center">View Notes</h3>
                    <div className="card-body">

                        <div className="row">
                            <label>ID Notes</label>
                            <div className="font-weight-bold">
                                {this.state.note.id}
                            </div>
                        </div>
                        <div className="row">
                            <label>Patient ID : </label>
                            <div className="font-weight-bold">
                                {this.state.note.patientId}
                            </div>
                        </div>
                        <div className="row">
                            <label>LastName</label>
                            <div className="font-weight-bold">
                                {this.state.note.patientLastName}
                            </div>
                        </div>
                        <div className="row">
                            <label>FirstName </label>
                            <div className="font-weight-bold">
                                {this.state.note.patientFirstName}
                            </div>
                        </div>
                        <div className="row">
                            <label>Note</label>
                            <div className="font-weight-bold">
                                {this.state.note.note}
                            </div>
                        </div>
                        <div className="row">
                            <label>Date Note</label>
                            <div className="font-weight-bold">
                                {this.state.note.dateNote}
                            </div>
                        </div>


                        <div className="separation card"/>
                        <br/>


                    </div>

                </div>
            </div>
        )
    }

}

export default ViewNotes