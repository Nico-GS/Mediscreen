import React, {Component} from "react";
import ReportsService from "../../service/ReportsService";
import PatientService from "../../service/PatientService";


class CreateReports extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            lastName: '',
            firstName: ''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
    }

    componentDidMount() {
        ReportsService.getReportsById(this.state.id).then(response => {
            let reports = response.data;
            this.setState({
                lastName: reports.lastName,
                firstName: reports.firstName
            })
        })

    }

    createReports = (e) => {
        e.preventDefault();
        let reports = {
            lastName: this.state.lastName,
            firstName: this.state.firstName
        }
        ReportsService.getReportsByLastAndFirstName(reports, this.state.id).then(response => {
            this.props.history.push("/");
        })
    }

    changeFirstNameHandler = (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastName: event.target.value});
    }

    render() {
        return (
            <div>
                <br/><br/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <form>
                                <div className = "form-group">
                                    <label>First Name:</label>
                                    <input placeholder="First Name" name="firstName" className="form-control"
                                           value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label>Last Name:</label>
                                    <input placeholder="Last Name" name="lastName" className="form-control"
                                           value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                </div>

                                <button className="btn btn-success" onClick={this.createReports}>Generate</button>

                            </form>
                        </div>
                    </div>

                </div>
            </div>
        )
    }

}

export default CreateReports;