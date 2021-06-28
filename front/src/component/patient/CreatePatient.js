import React, {Component} from "react";
import PatientService from "../../service/PatientService";


class CreatePatient extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            lastName: '',
            firstName: '',
            dateOfBirth: '',
            sex: '',
            address: '',
            phoneNumber: '',
            error: '',
            validationMessage: "error"
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeDateOfBirthHandler = this.changeDateOfBirthHandler.bind(this);
        this.changeSexHandler = this.changeSexHandler.bind(this);
        this.changeAddressHandler = this.changeAddressHandler.bind(this);
        this.changePhoneNumberHandler = this.changePhoneNumberHandler.bind(this);
        this.saveOrUpdatePatient = this.saveOrUpdatePatient.bind(this);
    }


    componentDidMount() {

        if (this.state.id === '_add') {
            return
        } else {
            PatientService.getPatientById(this.state.id).then((response) => {
                let patient = response.data;
                this.setState({
                    lastName: patient.lastName,
                    firstName: patient.firstName,
                    dateOfBirth: patient.dateOfBirth,
                    sex: patient.sex,
                    address: patient.address,
                    phoneNumber: patient.phoneNumber
                });
            });

        }
    }

    saveOrUpdatePatient = (e) => {
        e.preventDefault();
        let patient = {
            lastName: this.state.lastName,
            firstName: this.state.firstName,
            dateOfBirth: this.state.dateOfBirth,
            sex: this.state.sex,
            address: this.state.address,
            phoneNumber: this.state.phoneNumber
        };

        if (this.state.id === '_add') {
            PatientService.createPatient(patient).then(response => {
                this.props.history.push('/patients');
            });
        } else {
            PatientService.updatePatient(patient, this.state.id).then(response => {
                this.props.history.push('/patients');
            })
        }
    }


    changeFirstNameHandler = (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastName: event.target.value});
    }

    changeDateOfBirthHandler = (event) => {
        const target = event.target;
        this.setState({
            dateOfBirth: event.target.value,
            error: target.validationMessage
        });
    }

    changeSexHandler = (event) => {
        this.setState({sex: event.target.value});
    }

    changeAddressHandler = (event) => {
        this.setState({address: event.target.value});
    }

    changePhoneNumberHandler = (event) => {
        this.setState({phoneNumber: event.target.value});
    }


    cancel() {
        this.props.history.push('/patients')
    }

    getTitle() {
        if (this.state.id === '_add') {
            return <h3 className="text-center">Add Patient</h3>
        } else {
            return <h3 className="text-center">Update Patient</h3>
        }
    }


    render() {
        return (

            <div>

                <br/><br/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>First Name:</label>
                                        <input name="firstName" className="form-control"
                                               value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Last Name:</label>
                                        <input name="lastName" className="form-control"
                                               value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="username">Birth</label>
                                        <input
                                            value={this.state.dateOfBirth}
                                            onChange={this.changeDateOfBirthHandler}
                                            placeholder="YYYY-MM-DD"
                                            pattern="(?:19|20)(?:(?:[13579][26]|[02468][048])-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))|(?:[0-9]{2}-(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:29|30))|(?:(?:0[13578]|1[02])-31)))"
                                            className="form-control"
                                        />
                                        <div className="invalid-feedback d-block">
                                            {this.state.error}
                                        </div>
                                    </div>


                                    <div className="form-group">
                                        <label>Sex:</label>
                                        <input placeholder="M or F" name="sex" className="form-control"
                                               value={this.state.sex} onChange={this.changeSexHandler}
                                               maxLength="1"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label>Address:</label>
                                        <input name="address" className="form-control"
                                               value={this.state.address} onChange={this.changeAddressHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Phone Number:</label>
                                        <input placeholder="XXXXXXXXXX" name="emailId" className="form-control"
                                               value={this.state.phoneNumber}
                                               onChange={this.changePhoneNumberHandler}
                                               maxLength="10"
                                        />
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveOrUpdatePatient}>Save
                                    </button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }

}

export default CreatePatient;