import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import './CSS/Home.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import ListPatientComponent from "./component/ListPatientComponent";
import CreatePatient from "./component/CreatePatient";
import ViewPatient from "./component/ViewPatient";
import RendezVous from "./component/RendezVous";
import Header from "./component/Header";
import Footer from "./component/Footer";




function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Header />
                <div className="container">
                    <Switch>
                        <Route path="/" exact component = {ListPatientComponent}/>
                        <Route path="/patients" component = {ListPatientComponent}/>
                        <Route path="/add-patient/:id" component = {CreatePatient}/>
                        <Route path="/view-patient/:id" component = {ViewPatient}/>
                        <Route path="/rdv" exact component ={RendezVous}/>
                    </Switch>
                </div>
                <Footer />
            </BrowserRouter>
        </div>
    )
}

export default App;