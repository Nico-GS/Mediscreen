import React, {Component} from "react";
import NotesServices from "../../service/NotesService";
import PatientService from "../../service/PatientService";

class CreateNotes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            patientId: '',
            lastName: '',
            firstName: '',
            note: '',
            dateNote: '',
            idPatient: ''
        }
        this.changePatIdHandler = this.changePatIdHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeNoteHandler = this.changeNoteHandler.bind(this);
        this.changeDateNoteHandler = this.changeDateNoteHandler.bind(this);
    }

    componentDidMount() {
        if(this.state.id === '_add') {
            return
        } else {
            NotesServices.getNotes(this.state.id).then((response => {
                let note = response.data;
                this.setState({
                    patientId: note.patientId,
                    lastName: note.lastName,
                    firstName: note.firstName,
                    note: note.note,
                    dateNote: note.dateNote
                })
            }))
            PatientService.getPatientById(this.state.idPatient).then(response => {
                this.setState({idPatient: response.data})
            })
        }
    }

    saveOrUpdateNotes = (e) => {
        e.preventDefault();
        let note = {
            patientId: this.state.patientId,
            lastName: this.state.lastName,
            firstName: this.state.firstName,
            note: this.state.note,
            dateNote: this.state.dateNote
        };
        console.log("Notes =>" + JSON.stringify(note));
        console.log(NotesServices.createNotes(note));

        if (this.state.id === '_add') {
            NotesServices.createNotes(note).then(response => {
                this.props.history.push('/notes');
            });
        } else {
            NotesServices.updateNotes(note, this.state.id).then(response => {
                this.props.history.push('/notes');
            })
        }
    }

    changePatIdHandler = (event) => {
        this.setState({patientId: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastName: event.target.value});
    }

    changeFirstNameHandler = (event) => {
        this.setState({firstName: event.target.value});
    }

    changeNoteHandler = (event) => {
        this.setState({note: event.target.value});
    }

    changeDateNoteHandler = (event) => {
        this.setState({dateNote: event.target.value});
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
                                        <label>Patient ID</label>
                                        <input placeholder="Patient ID" name="patientId" className="form-control"
                                               value={this.state.id}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Last Name</label>
                                        <input placeholder="Last Name" name="lastName" className="form-control"
                                               value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>First Name</label>
                                        <input placeholder="First Name" name="firstName" className="form-control"
                                               value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label>Note</label>
                                        <input  name="note" className="form-control"
                                                value={this.state.note} onChange={this.changeNoteHandler}/>
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