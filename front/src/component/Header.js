import React, { Component } from 'react';
import {Link} from "react-router-dom";

class Header extends Component {

    constructor(props) {
        super(props);

        this.state = {

        }
    }

    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <button className="navbar-toggler-icon" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggle-icon"/>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Home</Link>
                        </li>
                        {/*<li className="nav-item">*/}
                        {/*    <Link className="nav-link" to="/rdv">RDV</Link>*/}
                        {/*</li>*/}
                        {/*<li className="nav-item">*/}
                        {/*    <Link className="nav-link" to="/notes">Notes</Link>*/}
                        {/*</li>*/}
                    </ul>
                </div>
            </nav>
        )
    }
}

export default Header;