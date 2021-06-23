import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import NotesService from "../../service/NotesService";

class CreateNotes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            patientId: '',
            firstName: '',
            lastName: '',
            note: '',
            dateNote: '',
        }
        this.changePatientIdHandler = this.changePatientIdHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeNoteHandler = this.changeNoteHandler.bind(this);
        this.changeDateNoteHandler = this.changeDateNoteHandler.bind(this);
    }

    componentDidMount() {
        if (this.state.id === '_add') {
            return
        } else {
            NotesService.getNotesById(this.state.id).then((response) => {
                let notes = response.data;
                this.setState({
                    patientId: notes.patientId,
                    firstName: notes.patientFirstName,
                    lastName: notes.patientLastName,
                    note: notes.note,
                    dateNote: notes.dateNote,

                });
            });
        }
    }

    saveOrUpdateNotes = (e) => {
        e.preventDefault();
        let note = {
            patientId: this.state.patientId,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            note: this.state.note,
            dateNote: this.state.dateNote
        };
        console.log("Note =>" + JSON.stringify(note));

        if (this.state.id === '_add') {
            NotesService.createNotes(note).then(response => {
                this.props.history.push('/notes');
            });
        } else {
            NotesService.updateNotes(note, this.state.id).then(response => {
                this.props.history.push('/notes');
            })
        }
    }

    changeFirstNameHandler = (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastName: event.target.value});
    }

    changePatientIdHandler = (event) => {
        this.setState({patientId: event.target.value})
    }

    changeNoteHandler = (event) => {
        this.setState({note: event.target.value})
    }

    changeDateNoteHandler = (event) => {
        this.setState({dateNote: event.target.value})
    }


    cancel() {
        this.props.history.push('/notes')
    }

    getTitle() {
        if(this.state.id === '_add') {
            return <h3 className="text-center">Add Notes</h3>
        } else {
            return <h3 className="text-center">Update Notes</h3>
        }
    }

    render() {
        return (
            <div>
                <br/><br/>
                <div className = "container">
                    <div className = "row">
                        <div className ="card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label>Patient ID : </label>
                                        <input placeholder="Patient ID" name="patientId" className="form-control"
                                               value={this.state.patientId}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>First Name:</label>
                                        <input placeholder="First Name" name="firstName" className="form-control"
                                               value={this.state.firstName} />
                                    </div>
                                    <div className = "form-group">
                                        <label>Last Name:</label>
                                        <input placeholder="Last Name" name="lastName" className="form-control"
                                               value={this.state.lastName}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Note : </label>
                                        <input placeholder="Note" name="note" className="form-control"
                                               value={this.state.note} onChange={this.changeNoteHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Date Note : </label>
                                        <input placeholder="yyyy-MM-dd" name="sex" className="form-control"
                                               value={this.state.dateNote} onChange={this.changeDateNoteHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveOrUpdateNotes}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }

}

export default CreateNotes;