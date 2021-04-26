import React, {Component} from "react";
import Calendar from "react-calendar";

class RendezVous extends Component {

    constructor(props) {
        super(props);
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <Calendar />
            </div>
        );
    }
}

export default RendezVous;