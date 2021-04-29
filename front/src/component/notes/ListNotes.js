import React, {Component} from "react";
import NotesServices from "../../service/NotesService";

class ListNotes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            notes: []
        }
        this.addNotes = this.addNotes.bind(this);
    }

    componentDidMount() {
        NotesServices.getNotes().then(response => {
            this.setState({notes: response.data});
        })
    }

    updateNotes(id) {
        this.props.history.push(`/add-notes/${id}`);
    }

    viewNotes(id) {
        this.props.history.push(`/view-notes/${id}`);
    }

    addNotes() {
        this.props.history.push('/add-notes');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">List Notes</h2>
                <br/>
                <div className="container-fluid button-patient">
                    <button className="btn btn-primary" onClick={this.addNotes}>Add Notes</button>
                </div>
                <br/><br/>
                <div className="row">
                    <table className="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr className="tab-name">
                            <td>Notes ID</td>
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
                                <tr key={note.id}>
                                    <td>{note.id}</td>
                                    <td>{note.patientId}</td>
                                    <td>{note.lastName}</td>
                                    <td>{note.firstName}</td>
                                    <td>{note.note}</td>
                                    <td>{note.dateNote}</td>
                                    <td>
                                        <button style={{marginLeft: "20px"}} onClick={ () => this.updateNotes(note.id)} className="btn btn-primary">Update</button>
                                        <button style={{marginLeft: "20px"}} onClick={ () => this.viewNotes(note.id)} className="btn btn-primary">View</button>

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