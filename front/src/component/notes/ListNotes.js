import React, {Component} from "react";
import NotesServices from "../../service/NotesService";
import NotesService from "../../service/NotesService";

class ListNotes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            notes: []
        }
    }

    componentDidMount() {
        NotesServices.getNotes().then(response => {
            this.setState({notes: response.data});
        })
    }

    updateNotes(id) {
        this.props.history.push(`/add-notes/${id}`);
    }


    deleteNote(id) {
        NotesService.deleteNotes(id).then(response => {
            this.setState({notes: this.state.notes.filter(note => note.id !== id)});
            this.props.history.push('/notes')
        })
    }

    render() {
        return (
            <div>
                <h2 className="text-center">List Notes</h2>
                <br/>
                <div className="container-fluid button-patient">
                </div>
                <br/><br/>
                <div className="row">
                    <table className="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr className="tab-name">
                            <td>Notes ID</td>
                            <td>Patient ID</td>
                            <td>First Name</td>
                            <td>Last Name</td>
                            <td>Note</td>
                            <td>Date Note</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.notes.map(note =>
                                <tr key={note.id}>
                                    <td>{note.id}</td>
                                    <td>{note.patientId}</td>
                                    <td>{note.patientFirstName}</td>
                                    <td>{note.patientLastName}</td>
                                    <td>{note.note}</td>
                                    <td>{note.dateNote}</td>
                                    <td>
                                        {/*<button style={{marginLeft: "20px"}} onClick={ () => this.updateNotes(note.id)} className="btn btn-primary">Update</button>*/}
                                        <button onClick={ () => this.deleteNote(note.id)} className="btn btn-sm btn-danger">Delete</button>

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