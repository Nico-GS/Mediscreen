import React, {Component} from "react";
import NotesServices from "../../service/NotesService";

class CreateNotes extends Component {

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

    render() {
        return (
            <div>
                <h2 className="text-center">List Notes</h2>
                <br/>
                <br/><br/>
                <div className ="row">
                    <table className ="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr className="tab-name">
                            <td>Notes ID</td>
                            <td>Patient ID</td>
                            <td>Note</td>
                            <td>Date Note</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.notes.map(note =>
                                <tr key = {note.id}>
                                    <td>{note.id}</td>
                                    <td>{note.patientId}</td>
                                    <td>{note.note}</td>
                                    <td>{note.dateNote}</td>

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
export default CreateNotes;