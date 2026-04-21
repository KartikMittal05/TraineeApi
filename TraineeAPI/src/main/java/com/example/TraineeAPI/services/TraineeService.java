// package com.example.TraineeAPI.services;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.TraineeAPI.entities.Trainee;
// import com.example.TraineeAPI.repositories.ITraineeRepository;

// @Service
// public class TraineeService implements ITraineeService {

//     @Autowired
//     private ITraineeRepository repo;

//     @Override
//     public List<Trainee> getAllTrainees() {
//         return repo.findAll();   
//     }

//     @Override
//     public Optional<Trainee> getTraineeById(int id) {
//         return repo.findById(id);  
//     }
    
//     @Override
//     public Optional<Trainee> getTraineeByName(String name) {
//         return repo.findByTraineeName(name);
//     }
    
//     @Override
//     public Trainee addTrainee(Trainee trainee) {
//         return repo.save(trainee);  
//     }

//     @Override
//     public Trainee updateTrainee(Trainee trainee) {
//         return repo.save(trainee);  
//     }

//     @Override
//     public boolean deleteTrainee(int id) {
//         if (repo.existsById(id)) {  
//             repo.deleteById(id);
//             return true;
//         }
//         return false;
//     }
// }



package com.example.TraineeAPI.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TraineeAPI.entities.Trainee;
import com.example.TraineeAPI.repositories.ITraineeRepository;

@Service
public class TraineeService implements ITraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    @Autowired
    private ITraineeRepository repo;

    @Override
    public List<Trainee> getAllTrainees() {

        logger.info("Fetching all trainees from database");

        List<Trainee> list = repo.findAll();

        logger.debug("Total trainees fetched: {}", list.size());
        return list;
    }

    @Override
    public Optional<Trainee> getTraineeById(int id) {

        logger.info("Fetching trainee with id {}", id);

        Optional<Trainee> trainee = repo.findById(id);

        if (trainee.isPresent()) {
            logger.debug("Trainee found with id {}", id);
        } else {
            logger.warn("Trainee not found with id {}", id);
        }

        return trainee;
    }

    @Override
    public Optional<Trainee> getTraineeByName(String name) {

        logger.info("Fetching trainee with name {}", name);

        Optional<Trainee> trainee = repo.findByTraineeName(name);

        if (trainee.isPresent()) {
            logger.debug("Trainee found with name {}", name);
        } else {
            logger.warn("Trainee not found with name {}", name);
        }

        return trainee;
    }

    @Override
    public Trainee addTrainee(Trainee trainee) {

        logger.info("Saving trainee with name {}", trainee.getTraineeName());

        Trainee saved = repo.save(trainee);

        logger.info("Trainee saved with id {}", saved.getTraineeId());

        return saved;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {

        logger.info("Updating trainee with id {}", trainee.getTraineeId());

        Trainee updated = repo.save(trainee);

        logger.info("Trainee updated with id {}", updated.getTraineeId());

        return updated;
    }

    @Override
    public boolean deleteTrainee(int id) {

        logger.info("Attempting to delete trainee with id {}", id);

        if (repo.existsById(id)) {
            repo.deleteById(id);
            logger.info("Trainee deleted with id {}", id);
            return true;
        }

        logger.error("Delete failed: trainee not found with id {}", id);
        return false;
    }
}
