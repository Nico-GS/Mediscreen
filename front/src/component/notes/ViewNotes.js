import React, {Component} from "react";
import NotesService from "../../service/NotesService";

class ViewNotes extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            note: {},
        }
    }

    componentDidMount() {
        NotesService.getNotesById(this.state.id).then(response => {
            this.setState({note: response.data});
        })
    }

    render() {
        return (
            <div>
                <br/><br/>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center">View Notes Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label>Patient ID</label>
                            <div className="font-weight-bold">
                                {this.state.note.patientId}
                            </div>
                        </div>
                        <div className = "row">
                            <label>Note</label>
                            <div className="font-weight-bold">
                                {this.state.note.note}
                            </div>
                        </div>
                        <div className = "row">
                            <label>Date Note</label>
                            <div className="font-weight-bold">
                                {this.state.note.dateNote}
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }

}
export default ViewNotes;