import React, {Component} from "react";

import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';

const localizer = momentLocalizer(moment);

class RendezVous extends Component {

    constructor() {
        super();
        const now = new Date();
        const events = [
            {
                id: 0,
                title: 'Test RendezVous',
                allDay: true,
                start: new Date(Date.now()),
                end: new Date(Date.now()),
            },
            {
                id: 1,
                title: 'Test Test',
                start: now,
                end: now,
            },
            {
                id: 2,
                title: 'Test Test',
                start: "2021-04-27",
                end: "2021-04-29",
            }
        ]
        this.state = {
            events
        };
    }

    render() {
        return (
            <div style={{ height: '500pt'}}>
                <Calendar
                    events={this.state.events}
                    startAccessor="start"
                    endAccessor="end"
                    defaultDate={moment().toDate()}
                    localizer={localizer}
                />
            </div>
        );
    }
}

export default RendezVous;