package edu.umb.CS681.hw09;

import java.util.List;
import java.util.ArrayList;

public record Position(double latitude, double longitude, double altitude){

        public List<Double> coordinate(double latitude, double longitude, double altitude){
            List<Double> coordinates = new ArrayList<>();
                coordinates.add(latitude);
                coordinates.add(longitude);
                coordinates.add(altitude);    
                return coordinates;     
        }

        public Position change(double newLat, double newLon, double newAlt){

            return new Position(newLat, newLon, newAlt);
        }

        public boolean higherAltThan(Position anotherPosition){

            return this.altitude > anotherPosition.altitude;
        }

        public boolean lowerAltThan(Position anotherPosition){

            return this.altitude < anotherPosition.altitude;

        } 

        public boolean northOf(Position anotherPosition) {

           return this.latitude > anotherPosition.latitude;
        }

        public boolean southOf(Position anotherPosition){

           return this.latitude < anotherPosition.latitude;

        }
    }

