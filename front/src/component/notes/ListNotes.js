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

    addNotes() {
        this.props.history.push('/add-notes');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">List Notes</h2>
                <br/>
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
                                </tr>
                            )
                        }
                        </tbody>
                    </table>
                    <div className="container-fluid button-patient">
                        <button className="btn btn-primary" onClick={this.addNotes}>Add Notes</button>
                    </div>
                </div>

            </div>
        )
    }

}

export default ListNotes;