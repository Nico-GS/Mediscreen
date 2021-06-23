import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import './CSS/Home.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import ListPatientComponent from "./component/patient/ListPatientComponent";
import CreatePatient from "./component/patient/CreatePatient";
import ViewPatient from "./component/patient/ViewPatient";
import RendezVous from "./component/patient/RendezVous";
import Header from "./component/Header";
import Footer from "./component/Footer";
import ListNotes from "./component/notes/ListNotes";
import CreateNotes from "./component/notes/CreateNotes";
import CreateReports from "./component/reports/CreateReports";
import ViewNotes from "./component/notes/ViewNotes";




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
                        <Route path="/view-notes/:id" component = {ViewNotes} />
                        <Route path="/add-notes/:id" component = {CreateNotes} />
                        <Route path="/rdv" exact component ={RendezVous}/>
                        <Route path="/notes" exact component ={ListNotes}/>
                        <Route path="/add-notes/" component = {CreateNotes}/>
                    </Switch>
                </div>
                <Footer />
            </BrowserRouter>
        </div>
    )
}

export default App;