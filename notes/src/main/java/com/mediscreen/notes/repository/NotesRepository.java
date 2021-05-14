package com.mediscreen.notes.repository;

import com.mediscreen.notes.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends MongoRepository<Notes, String> {

    List<Notes> findByPatientLastNameAndPatientFirstName (String patientLastName, String patientFirstName);

}
