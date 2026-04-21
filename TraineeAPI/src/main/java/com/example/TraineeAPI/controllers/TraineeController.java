// package com.example.TraineeAPI.controllers;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.example.TraineeAPI.entities.Trainee;
// import com.example.TraineeAPI.services.ITraineeService;

// @RestController
// public class TraineeController {

//     @Autowired
//     private ITraineeService service;

// //    @GetMapping("/trainees")
// //    public List<Trainee> getAllTrainees() {
// //        return service.getAllTrainees();
// //    }
    
//     @GetMapping("/trainees")
//     public ResponseEntity<List<Trainee>> getAllTraineesNew() {
//         List<Trainee> list = service.getAllTrainees();

//         if (list.isEmpty()) {
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         }
//         return new ResponseEntity<>(list, HttpStatus.OK);
//     }

// //    @GetMapping("/trainees/{id}")
// //    public Trainee getTraineeById(@PathVariable int id) {
// //        Optional<Trainee> trainee = service.getTraineeById(id);
// //
// //        if (trainee.isPresent()) {
// //            return trainee.get();
// //        }
// //        return null;
// //    }
    
//     @GetMapping("/trainees/{id}")
//     public ResponseEntity<Trainee> getTraineeById(@PathVariable int id) {
//         Optional<Trainee> trainee = service.getTraineeById(id);
//         Trainee t=null;
//         if (trainee.isPresent()) {
//         		t=trainee.get();
//             return new ResponseEntity<>(t,HttpStatus.OK);
//         }
//         else {
//         	return new ResponseEntity<>(t,HttpStatus.NOT_FOUND);
//         }
        
//     }

// //    @GetMapping("/trainees/name/{name}")
// //    public Trainee getTraineeByName(@PathVariable String name) {
// //        Optional<Trainee> trainee = service.getTraineeByName(name);
// //
// //        if (trainee.isPresent()) {
// //            return trainee.get();
// //        }
// //        return null;
// //    }

    
// //    @GetMapping("/trainees/byName")
// //    public Trainee getByName(@RequestParam("name") String name) {
// //        return service.getTraineeByName(name).orElse(null);
// //    }
    
//     @GetMapping("/trainees/byName")
//     public ResponseEntity<Trainee> getByNameNew(@RequestParam("name") String name) {
//         Optional<Trainee> trainee = service.getTraineeByName(name);

//         if (trainee.isPresent()) {
//             return new ResponseEntity<>(trainee.get(), HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
    
// //    @PostMapping("/trainees")
// //    public Trainee addTrainee(@RequestBody Trainee trainee) {
// //        return service.addTrainee(trainee);
// //    }
    
//     @PostMapping("/trainees")
//     public ResponseEntity<Trainee> addTraineeNew(@RequestBody Trainee trainee) {
//         Trainee saved = service.addTrainee(trainee);
//         return new ResponseEntity<>(saved, HttpStatus.CREATED);
//     }

// //    @PutMapping("/trainees")
// //    public Trainee updateTrainee(@RequestBody Trainee trainee) {
// //        return service.updateTrainee(trainee);
// //    }
    
//     @PutMapping("/trainees")
//     public ResponseEntity<Trainee> updateTraineeNew(@RequestBody Trainee trainee) {
//         Trainee updated = service.updateTrainee(trainee);
//         return new ResponseEntity<>(updated, HttpStatus.OK);
//     }

// //    @DeleteMapping("/trainees/{id}")
// //    public String deleteTrainee(@PathVariable int id) {
// //
// //        boolean status = service.deleteTrainee(id);
// //
// //        if (status) {
// //            return "Trainee deleted successfully";
// //        } else {
// //            return "Trainee not found";
// //        }
// //    }
    
//     @DeleteMapping("/trainees/{id}")
//     public ResponseEntity<String> deleteTraineeNew(@PathVariable int id) {

//         boolean status = service.deleteTrainee(id);

//         if (status) {
//             return new ResponseEntity<>("Trainee deleted successfully", HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>("Trainee not found", HttpStatus.NOT_FOUND);
//         }
//     }
// }

package com.example.TraineeAPI.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TraineeAPI.entities.Trainee;
import com.example.TraineeAPI.services.ITraineeService;

@RestController
public class TraineeController {

    private static final Logger logger = LoggerFactory.getLogger(TraineeController.class);

    @Autowired
    private ITraineeService service;

    @GetMapping("/trainees")
    public ResponseEntity<List<Trainee>> getAllTrainees() {

        logger.info("GET /trainees called");

        List<Trainee> list = service.getAllTrainees();

        if (list.isEmpty()) {
            logger.warn("No trainees found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Fetched {} trainees", list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/trainees/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable int id) {

        logger.info("GET /trainees/{} called", id);

        Optional<Trainee> trainee = service.getTraineeById(id);

        if (trainee.isPresent()) {
            logger.info("Trainee found with id {}", id);
            return new ResponseEntity<>(trainee.get(), HttpStatus.OK);
        } else {
            logger.error("Trainee NOT found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/trainees/byName")
    public ResponseEntity<Trainee> getByName(@RequestParam("name") String name) {

        logger.info("GET /trainees/byName called with name={}", name);

        Optional<Trainee> trainee = service.getTraineeByName(name);

        if (trainee.isPresent()) {
            logger.info("Trainee found with name {}", name);
            return new ResponseEntity<>(trainee.get(), HttpStatus.OK);
        } else {
            logger.warn("Trainee NOT found with name {}", name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/trainees")
    public ResponseEntity<Trainee> addTrainee(@RequestBody Trainee trainee) {

        logger.info("POST /trainees called with name {}", trainee.getTraineeName());

        Trainee saved = service.addTrainee(trainee);

        logger.info("Trainee created with id {}", saved.getTraineeId());

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/trainees")
    public ResponseEntity<Trainee> updateTrainee(@RequestBody Trainee trainee) {

        logger.info("PUT /trainees called for id {}", trainee.getTraineeId());

        Trainee updated = service.updateTrainee(trainee);

        logger.info("Trainee updated with id {}", updated.getTraineeId());

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/trainees/{id}")
    public ResponseEntity<String> deleteTrainee(@PathVariable int id) {

        logger.info("DELETE /trainees/{} called", id);

        boolean status = service.deleteTrainee(id);

        if (status) {
            logger.info("Trainee deleted with id {}", id);
            return new ResponseEntity<>("Trainee deleted successfully", HttpStatus.OK);
        } else {
            logger.error("Delete failed. Trainee not found with id {}", id);
            return new ResponseEntity<>("Trainee not found", HttpStatus.NOT_FOUND);
        }
    }
}
