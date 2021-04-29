import React, {Component} from "react";
import RdvService from "../../service/RdvService";

class RendezVous extends Component {

    constructor(props) {
        super(props);

        this.state = {
            rdvs: []
        }
    }

    componentDidMount() {
        RdvService.getRdv().then((response) => {
            this.setState({rdvs: response.data})
            console.log(this.state.data)
        })
    }


    render() {
        return (
            <div>
                <h2 className="text-center">List RDV</h2>
                <br/>

                <br/><br/>
                <div className ="row">
                    <table className ="table table-striped table-bordered tableau-list">
                        <thead>
                        <tr className="tab-name">
                            <td>ID RDV</td>
                            <td>Name Patient</td>
                            <td>Date of RDV</td>
                            <td>Notes RDV</td>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.rdvs.map(rdv =>
                                <tr key = {rdv.id}>
                                    <td>{rdv.idRdv}</td>
                                    <td>{rdv.namePatient}</td>
                                    <td>{rdv.datePriseRdv}</td>
                                    <td>{rdv.notesRdv}</td>
                                </tr>
                            )
                        }
                        </tbody>
                    </table>

                </div>

            </div>
        );
    }
}

export default RendezVous;