import axios from "axios";

const URL_API_NOTES = "http://localhost:8082/api/notes";

class NotesService {

    getNotes() {
        return axios.get(URL_API_NOTES);
    }

    getNotesById(notesId) {
        return axios.get(URL_API_NOTES + '/' + notesId);
    }

    createNotes(note) {
        return axios.post(URL_API_NOTES, note);
    }

    updateNotes(note, notesId) {
        return axios.put(URL_API_NOTES + '/' + notesId, note);
    }

    // updateNotes(notesId, note) {
    //     return axios.put(`http://localhost:8082/api/notes/${notesId}`, note);
    // }

    // updateNotes (notesId, note) {
    //     return axios.request({
    //         url: `http://localhost:8082/api/notes/${notesId}`,
    //         method: "PUT",
    //         headers: {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'Access-Control-Allow-Origin': '*/*',
    //             'Access-Control-Allow-Headers': 'X-Requested-With, Content-Type',
    //         },
    //         data: {
    //             note: note
    //         }
    //     })
    // }

    deleteNotes(notesId) {
        return axios.delete(URL_API_NOTES + '/' + notesId);
    }
}

export default new NotesService();