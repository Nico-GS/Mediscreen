import React, {Component} from "react";
import PatientService from "../../service/PatientService";
import '../../CSS/ListPatient.css';
import NotesService from "../../service/NotesService";

class ListNotes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            notes: []
        }
        this.addNotes = this.addNotes.bind(this);
        this.updateNotes = this.updateNotes.bind(this);
        this.deleteNotes = this.deleteNotes.bind(this);
    }

    componentDidMount() {
        NotesService.getNotes().then((response) => {
            this.setState({notes: response.data});
        });
    }

    deleteNotes(id) {
        NotesService.deleteNotes(id).then (response => {
            this.setState({notes: this.state.notes.filter(note => note.id !== id)});
            this.props.history.push('/');
        });
    }

    addNotes() {
        this.props.history.push('/add-notes/_add');
    }

    viewNotes(id) {
        this.props.history.push(`/view-notes/${id}`);
    }

    updateNotes(id) {
        this.props.history.push(`/add-notes/${id}`);
    }



    render() {
        return (
            <div>
                <h2 className="text-center">List Notes</h2>
                <br/>
                <div className ="container-fluid button-patient">
                    <button className="btn btn-primary" onClick={this.addNotes}>Add Notes</button>
                </div>
                <br/><br/>
                <div className ="row">
                    <table className ="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr className="tab-name">
                            <td>Patient ID</td>
                            <td>Last Name</td>
                            <td>First Name</td>
                            <td>Note</td>
                            <td>Date Note</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.notes.map(note =>
                                <tr key = {note.id}>
                                    <td>{note.patientId}</td>
                                    <td>{note.patientLastName}</td>
                                    <td>{note.patientFirstName}</td>
                                    <td>{note.note}</td>
                                    <td>{note.dateNote}</td>
                                    <td>
                                        <button style={{marginLeft: "20px"}} onClick={ () => this.updateNotes(note.id)} className="btn btn-primary">Update</button>
                                        <button style={{marginLeft: "20px"}} onClick={ () => this.viewNotes(note.id)} className="btn btn-primary">View</button>
                                        <button style={{marginLeft: "20px"}} onClick={ () => this.deleteNotes(note.id)} className="btn btn-danger">Delete</button>

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

export default ListNotes;